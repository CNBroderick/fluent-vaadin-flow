/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-02 11:07:56
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.flow.form.config.FormConfigurationSlot
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.config;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Optional;

@Data
@Accessors(chain = true)
public class FormConfigurationSlot {

    private String prepend;
    private String append;
    private List<FormComponentSlotOption> options;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class FormComponentSlotOption {
        private String label;
        private String value;

        @Override
        public String toString() {
            return Optional.ofNullable(label).orElse(value);
        }
    }


}
