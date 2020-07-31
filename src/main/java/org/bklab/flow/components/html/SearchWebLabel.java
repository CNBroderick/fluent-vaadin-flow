/*
 * Class: org.bklab.flow.component.SearchWebLabel
 * Modify date: 2020/3/20 下午1:13
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.components.html;

import com.vaadin.flow.component.Html;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SearchWebLabel extends Html {

    public static String BAIDU_ENGINE = "https://www.baidu.com/s?ie=UTF-8&wd=%s";
    public static String BING_ENGINE = "https://cn.bing.com/search?q=%s";
    public static String GOOGLE_ENGINE = "https://www.google.com/search?q=%s&ie=UTF-8";
    public static String SOGOU_ENGINE = "https://www.sogou.com/web?ie=utf-8&query=%s";

    public SearchWebLabel(String keyword) {
        this(keyword, BING_ENGINE);
    }

    public SearchWebLabel(String keyword, String engine) {
        super(String.format("<a href='%s' target='_blank'>%s</a>", String.format(engine, URLEncoder.encode(keyword, StandardCharsets.UTF_8)), keyword));
    }

}
