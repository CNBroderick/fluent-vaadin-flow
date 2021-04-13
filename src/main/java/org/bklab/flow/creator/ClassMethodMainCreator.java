/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-24 13:13:04
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.creator.ClassMethodMainCreator
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.flow.creator;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.selection.MultiSelect;
import dev.mett.vaadin.tooltip.config.TooltipConfiguration;
import org.bklab.flow.base.MultiSelectFactory;
import org.bklab.flow.factory.BigDecimalFieldFactory;
import org.bklab.flow.factory.CheckboxGroupFactory;
import org.bklab.flow.factory.IntegerFieldFactory;
import org.bklab.flow.factory.TooltipConfigurationFactory;

public class ClassMethodMainCreator {

    public static void main(String[] args) {

        Class<?> factoryClass = IntegerFieldFactory.class;
        Class<?> targetClass = IntegerField.class;
//        Class<? extends Enum<?>> variantClass = CheckboxGroupVariant.class;
        new PrintClassMethod(factoryClass, targetClass).print();
//        new PrintLumoTheme(factoryClass, targetClass).print(variantClass);
    }
}
