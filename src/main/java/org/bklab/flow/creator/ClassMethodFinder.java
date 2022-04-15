package org.bklab.flow.creator;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassMethodFinder {

    private final Class<?> cls;

    public ClassMethodFinder(Class<?> cls) {
        this.cls = cls;
    }

    public Method[] getClassMethods() {
        Map<String, Method> uniqueMethods = new LinkedHashMap<>();
        Class<?> currentClass = cls;
        while (currentClass != null && currentClass != Object.class) {
            if (currentClass.getName().equals("com.vaadin.server.AbstractClientConnector")) {
                currentClass = currentClass.getSuperclass();
                continue;
            }
            addUniqueMethods(uniqueMethods, currentClass.getDeclaredMethods());
//            //获取接口中的所有方法
//            Class<?>[] interfaces = currentClass.getInterfaces();
//            for (Class<?> anInterface : interfaces) {
//                addUniqueMethods(uniqueMethods, anInterface.getMethods());
//            }
//            //获取父类，继续while循环
//            currentClass = currentClass.getSuperclass();
            currentClass = null;
        }

        Collection<Method> methods = uniqueMethods.values();

        return methods.toArray(new Method[0]);
    }

    private void addUniqueMethods(Map<String, Method> uniqueMethods, Method[] methods) {
        for (Method currentMethod : methods) {
            if (!currentMethod.isBridge()) {
                //获取方法的签名，格式是：返回值类型#方法名称:参数类型列表
                String signature = getSignature(currentMethod);
                //检查是否在子类中已经添加过该方法，如果在子类中已经添加过，则表示子类覆盖了该方法，无须再向uniqueMethods集合中添加该方法了
                if (!uniqueMethods.containsKey(signature)) {
                    try {
                        currentMethod.setAccessible(true);
                    } catch (Exception e) {
                        // Ignored. This is only a final precaution, nothing we can do.
                    }
                    uniqueMethods.put(signature, currentMethod);
                }
            }
        }
    }

    private String getSignature(Method method) {
        StringBuilder sb = new StringBuilder();
        Class<?> returnType = method.getReturnType();
        sb.append(returnType.getName()).append('#');
        sb.append(method.getName());
        Class<?>[] parameters = method.getParameterTypes();
        for (int i = 0; i < parameters.length; i++) {
            if (i == 0) {
                sb.append(':');
            } else {
                sb.append(',');
            }
            sb.append(parameters[i].getName());
        }
        return sb.toString();
    }

}
