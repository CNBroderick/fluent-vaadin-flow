/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 11:05:06
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.config.FormConfigurationConfig
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class FormConfigurationConfig implements Serializable {

    private String label;

    private String labelWidth;

    private boolean showLabel;

    private boolean changeTag;

    private String tag;

    private String tagIcon;

    private String defaultValue;

    private boolean required;

    private String layout;

    private String layoutTree;

    private int span;

    private String document;

    private List<FormConfigurationRegList> regList;

    private int formId;

    private String componentName;

    private int fileSize;

    private String sizeUnit;

    private String renderKey;

    private List<FormConfigurationField> children;
}
