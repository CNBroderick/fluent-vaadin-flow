/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 11:01:07
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.config.FormConfigurationAutoSize
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FormConfigurationAutoSize {

    private int minRows;
    private int maxRows;

}
