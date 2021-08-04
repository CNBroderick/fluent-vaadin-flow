/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 15:54:00
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.config.FormConfigurationField
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.config;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

@Data
@Accessors(chain = true)
public class FormConfigurationField implements Serializable {

    @JSONField(name = "__config__")
    private FormConfigurationConfig config;

    @JSONField(name = "__slot__")
    private FormConfigurationSlot slot;

    @JSONField(name = "__vModel__")
    private String model;

    private String placeholder;

    private Map<String, String> style;

    private boolean clearable;

    @JSONField(name = "prefix-icon")
    private String prefixIcon;

    @JSONField(name = "suffix-icon")
    private String suffixIcon;

    private int maxLength;

    private int minLength;


    @JSONField(name = "show-word-limit")
    private String showWordLimit;

    private boolean readonly;

    private boolean disabled;

    private boolean filterable;

    private boolean multiple;

    private boolean plain;

    private boolean circle;

    private String type;

    private FormConfigurationAutoSize autosize;

    private String size;

    private String format;

    @JSONField(name = "value-format")
    private String valueFormat;

    @JSONField(name = "range-separator")
    private String rangeSeparator;

    @JSONField(name = "start-placeholder")
    private String startPlaceholder;

    @JSONField(name = "end-placeholder")
    private String endPlaceholder;

    private String width;

    private String minWidth;

    private String maxWidth;

    private String height;

    private String minHeight;

    private String maxHeight;

    private boolean branding;

    private String justify;

    private String align;

    private String formRef;

    private String formModel;

    private String labelPosition;

    private String formRules;

    private int gutter;

    private int min;
    private int max;

    private boolean formBtns;

    private boolean unFocusedComponentBorder;

    private String action;
    private String accept;
    private String name;
    private String autoUpload;
    private String listType;
}
