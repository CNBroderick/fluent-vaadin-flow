package org.bklab.flow.creator;

import elemental.json.JsonObject;
import org.bklab.flow.FlowFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class PrintClassMethod {

    private final Class<?> targetClass;
    private final Class<?> factoryClass;

    public PrintClassMethod(Class<?> factoryClass, Class<?> targetClass) {
        this.factoryClass = factoryClass;
        this.targetClass = targetClass;
    }


    public void print(Class<?>... excludeClasses) {
        Method[] componentFactoryDeclaredMethods = FlowFactory.class.getDeclaredMethods();

        for (Method method : new PrintClassMethod(factoryClass, targetClass).getMethods(targetClass, excludeClasses)) {
            String methodName = method.getName();
            try {
                if (!methodName.equals("add") && !methodName.equals("set")) {
                    if (methodName.startsWith("add") || methodName.startsWith("set")) {
                        String substring = methodName.substring(3);
                        methodName = substring.substring(0, 1).toLowerCase() + substring.substring(1);
                    }
                }
            } catch (Exception e) {
                continue;
            }

            String finalMethodName = methodName;
            if (Arrays.stream(componentFactoryDeclaredMethods).anyMatch(m -> finalMethodName.equals(m.getName()))) {
                continue;
            }

            String string = (factoryClass.isInterface() ? "default" : "public") + " " + (targetClass.isInterface() || Modifier.isAbstract(targetClass.getModifiers()) ? "E" : factoryClass.getSimpleName()) + " " + methodName;


            List<String> params = new ArrayList<>();
            for (Type genericParameterType : method.getGenericParameterTypes()) {
                String typeName = genericParameterType.getTypeName();
                int i = typeName.lastIndexOf('.');
                if (i > 0) {
                    typeName = typeName.substring(i + 1);
                }
                typeName = typeName.replaceAll("\\$", ".");

                params.add(typeName + " " + (method.getGenericParameterTypes().length == 1 ? methodName : typeName.substring(0, 1).toLowerCase() + typeName.substring(1).replaceAll("\\.", "").replaceAll("<", "").replaceAll(">", "")));
            }

            string += " (" + String.join(", ", params) + ") {\n";

            string += "get()." + method.getName() + "(";

            params = new ArrayList<>();
            for (Type genericParameterType : method.getGenericParameterTypes()) {
                String typeName = genericParameterType.getTypeName();
                int i = typeName.lastIndexOf('.');
                if (i > 0) {
                    typeName = typeName.substring(i + 1);
                }
                typeName = typeName.replaceAll("\\$", ".");

                params.add((method.getGenericParameterTypes().length == 1 ? methodName : typeName.substring(0, 1).toLowerCase() + typeName.substring(1).replaceAll("\\.", "").replaceAll("<", "").replaceAll(">", "")));
            }
            string += String.join(", ", params) + ");\n";
            string += "return " + (targetClass.isInterface() || Modifier.isAbstract(targetClass.getModifiers()) ? " (E) " : "") + "this;\n}";

            System.out.println(string);
        }
    }

    public Method[] getMethods(Class<?> findClass) {
        return getMethods(findClass, new Class[]{});
    }

    public Method[] getMethods(Class<?> findClass, Class<?>... excludeClasses) {
        List<Method> methods = new ArrayList<>();
        for (Method method : new ClassMethodFinder(findClass).getClassMethods()) {
            if (!Modifier.isPublic(method.getModifiers())) continue;

            if (method.getName().startsWith("get")) continue;
            if (method.getName().startsWith("is")) continue;

            if (method.getReturnType().isInstance(Collection.class)) continue;
            if (method.getReturnType().isInstance(JsonObject.class)) continue;
            if (method.getReturnType().getName().contains(".JsonObject")) continue;
            if (method.getReturnType().getName().contains(".List")) continue;

            if (Stream.of(excludeClasses).anyMatch(excludeClass -> method.getReturnType().isInstance(excludeClass))) {
                continue;
            }

            methods.add(method);
        }
        return methods.toArray(new Method[]{});
    }

}
