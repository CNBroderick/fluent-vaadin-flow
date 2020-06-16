package org.bklab.flow.creator;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.lang.reflect.Field;

public class PrintAlignment {

    private final Class<?> baseClass;

    public PrintAlignment(Class<?> baseClass) {
        this.baseClass = baseClass;
    }

    public static void main(String[] args) {
        new PrintAlignment(VerticalLayout.class).print();
    }

    private void print() {
        for (Field declaredField : FlexComponent.Alignment.class.getDeclaredFields()) {
            if (declaredField.getName().equals("bitMask")) continue;
            char[] chars = declaredField.getName().toLowerCase().toCharArray();

            String m = "default E ";

            StringBuilder a = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                a.append(chars[i] == '_' && i + 1 < chars.length ? (char) ((int) chars[++i] - 32) : chars[i]);
            }
            m += a.toString() + " (Component component) {\n    ";
            m += "get().setComponentAlignment(component, Alignment." + declaredField.getName() + ");\n    ";
            m += "return (E) this;\n}";
            System.out.println(m);
        }

        for (Field declaredField : FlexComponent.Alignment.class.getDeclaredFields()) {
            if (declaredField.getName().equals("bitMask")) continue;
            char[] chars = declaredField.getName().toLowerCase().toCharArray();

            String m = "default E ";

            StringBuilder a = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                a.append(chars[i] == '_' && i + 1 < chars.length ? (char) ((int) chars[++i] - 32) : chars[i]);
            }
            m += a.toString() + " () {\n    ";
            m += "get().setDefaultComponentAlignment(Alignment." + declaredField.getName() + ");\n    ";
            m += "return (E) this;\n}";
            System.out.println(m);
        }

    }
}
