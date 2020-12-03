package org.bklab.flow.util.internet;

import com.vaadin.flow.server.VaadinRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.Supplier;

public class RealIpUtil implements Supplier<String> {
    private String ipAddress;

    public RealIpUtil(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public RealIpUtil(HttpServletRequest request) {
        this(
                request.getHeader("x-forwarded-for"), request.getHeader("Proxy-Client-IP"),
                request.getHeader("WL-Proxy-Client-IP"), request.getRemoteAddr()
        );
    }

    public RealIpUtil(VaadinRequest request) {
        this(
                request.getHeader("x-forwarded-for"), request.getHeader("Proxy-Client-IP"),
                request.getHeader("WL-Proxy-Client-IP"), request.getRemoteAddr()
        );
    }

    private RealIpUtil(String x_forwarded_for, String Proxy_Client_IP, String WL_Proxy_Client_IP, String remoteAddr) {
        ipAddress = x_forwarded_for;
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = Proxy_Client_IP;
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = WL_Proxy_Client_IP;
        }
        try {
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = remoteAddr;
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    try {
                        ipAddress = InetAddress.getLocalHost().getHostAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        if (ipAddress == null) ipAddress = "";
        else if (ipAddress.indexOf('.') >= 0) this.ipAddress = ipAddress.split(":")[0];
    }

    public static String find(VaadinRequest request) {
        return new RealIpUtil(request).get();
    }

    public static String find(HttpServletRequest request) {
        return new RealIpUtil(request).get();
    }

    @Override
    public String get() {
        return ipAddress;
    }
}