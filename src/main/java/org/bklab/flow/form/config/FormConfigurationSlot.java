/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 11:01:07
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.config.FormConfigurationSlot
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FormConfigurationSlot {

    private String prepend;
    private String append;
    private List<FormComponentSlotOption> options;


    @Data
    @Accessors(chain = true)
    public static class FormComponentSlotOption {
        private String label;
        private String value;
    }


}
