/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 16:04:28
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.render.FluentFormRenderManager
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.render;

import org.bklab.flow.form.config.FormConfigurationConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class FluentFormRenderManager {

    private static FluentFormRenderManager instance;
    private final Map<String, IFormComponentRender<?, ?>> map = new LinkedHashMap<>();

    private FluentFormRenderManager() {
    }

    public static FluentFormRenderManager getInstance() {
        if (instance == null) {
            instance = new FluentFormRenderManager().init();
        }
        return instance;
    }

    public FluentFormRenderManager init() {
        return this
                .addRender("el-input input", new FormTextFieldRender())
                .addRender("el-select select", new FormComboBoxRender())
                .addRender("el-input textarea", new FormTextAreaRender())
                .addRender("el-checkbox-group checkbox", new FormCheckBoxGroupRender())
                ;
    }

    public FluentFormRenderManager addRender(String tag, IFormComponentRender<?, ?> render) {
        map.put(tag, render);
        return this;
    }

    public IFormComponentRender<?, ?> getRender(String tag) {
        return map.get(tag);
    }

    public IFormComponentRender<?, ?> getRender(String tag, String tagIcon) {
        return map.get(tag + " " + tagIcon);
    }

    public IFormComponentRender<?, ?> getRender(FormConfigurationConfig tag) {
        return getRender(tag.getTag(), tag.getTagIcon());
    }


}
