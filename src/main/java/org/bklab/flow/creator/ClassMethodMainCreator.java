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

import com.vaadin.flow.component.orderedlayout.FlexLayout;
import org.bklab.flow.factory.FlexLayoutFactory;

public class ClassMethodMainCreator {

    public static void main(String[] args) {

        Class<?> factoryClass = FlexLayoutFactory.class;
        Class<?> targetClass = FlexLayout.class;
//        Class<? extends Enum<?>> variantClass = ButtonVariant.class;
        new PrintClassMethod(factoryClass, targetClass).print();
//        new PrintLumoTheme(factoryClass, targetClass).print(variantClass);
    }
}
