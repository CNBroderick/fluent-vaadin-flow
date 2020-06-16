package org.bklab.flow.creator;

import com.vaadin.flow.component.textfield.GeneratedVaadinPasswordField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import org.bklab.flow.base.*;

public class ClassMethodMainCreator {

    public static void main(String[] args) {

        Class<?> factoryClass = GeneratedVaadinPasswordFieldFactory.class;
        Class<?> targetClass = GeneratedVaadinPasswordField.class;
        Class<? extends Enum<?>> variantClass = TextFieldVariant.class;
//        new PrintClassMethod(factoryClass, targetClass).print();
        new PrintLumoTheme(factoryClass, targetClass).print(variantClass);
    }
}
