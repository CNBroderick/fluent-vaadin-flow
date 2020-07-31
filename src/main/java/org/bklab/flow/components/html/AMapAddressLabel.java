/*
 * Class: org.bklab.flow.component.AMapAddressLabel
 * Modify date: 2020/3/20 下午1:13
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.html;

import com.vaadin.flow.component.Html;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class AMapAddressLabel extends Html {

    public AMapAddressLabel(String address) {
        super(String.format("<a href='https://ditu.amap.com/search?query=%s' target='_blank'>%s</a>",
                URLEncoder.encode(address, StandardCharsets.UTF_8), address));
    }

}
