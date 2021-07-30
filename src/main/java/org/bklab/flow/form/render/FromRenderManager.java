/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 14:13:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.render.FromRenderManager
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.render;

import java.util.LinkedHashMap;
import java.util.Map;

public class FromRenderManager {

    private static FromRenderManager instance;
    private final Map<String, IFormComponentRender<?, ?>> map = new LinkedHashMap<>();

    private FromRenderManager() {
    }

    public static FromRenderManager getInstance() {
        if (instance == null) {
            instance = new FromRenderManager().init();
        }
        return instance;
    }

    public FromRenderManager init() {
        return this
                .addRender("el-input", new FormTextFieldRender())
                .addRender("el-select", new FormComboBoxRender())
                ;
    }

    public FromRenderManager addRender(String tag, IFormComponentRender<?, ?> render) {
        map.put(tag, render);
        return this;
    }

    public IFormComponentRender<?, ?> getRender(String tag) {
        return map.get(tag);
    }

}
