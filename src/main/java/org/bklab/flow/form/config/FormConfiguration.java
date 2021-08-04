/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 11:14:27
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.config.FormConfiguration
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.config;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FormConfiguration {

    private List<FormConfigurationField> fields;

    private String formRef;
    private String formModel;
    private String size;
    private String labelPosition;
    private int labelWidth;
    private String formRules;
    private int gutter;
    private boolean disabled;
    private int span;
    private boolean formBtns;
    private String unFocusedComponentBorder;

    public static FormConfiguration create(String json) {
        return JSON.toJavaObject(JSON.parseObject(json), FormConfiguration.class);
    }

    public String json() {
        return json(true);
    }

    public String json(boolean pretty) {
        return JSON.toJSONString(this, pretty);
    }

}
