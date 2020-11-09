package org.bklab.flow.creator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PrintLumoTheme {

    private final Class<?> factory;
    private final Class<?> target;

    public PrintLumoTheme(Class<?> factory, Class<?> aClass) {
        this.factory = factory;
        target = aClass;
    }

    public void print(Class<? extends Enum<?>> enumClass) {


        for (String name : getStyles(enumClass)) {
            String string = (factory.isInterface() ? "default ": "public ") + factory.getSimpleName() + " ";
            StringBuilder s = new StringBuilder();

            char[] charArray = (name.replaceFirst(target.getSimpleName().toUpperCase(), "").toLowerCase()).toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (c == '_' && i + 1 < charArray.length) {
                    s.append(String.valueOf(charArray[++i]).toUpperCase());
                } else {
                    s.append(c);
                }
            }

            string += s.toString() + "() {\n    ";
            string += "get().addThemeVariants(" + enumClass.getSimpleName() + "." + name + ");\n    ";
            string += "return this;\n}";

            System.out.println(string);
        }
    }

    private String[] getStyles(Class<? extends Enum<?>> enumClass) {
        List<String> list = new ArrayList<>();
        for (Field declaredField : enumClass.getDeclaredFields()) {
            String name = declaredField.getName();
            if(!name.equals("variant") && !name.contains("values")) list.add(name);
        }
        return list.toArray(new String[]{});
    }
}
