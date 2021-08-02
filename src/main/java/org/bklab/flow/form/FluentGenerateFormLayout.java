/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date: 2021-08-02 11:07:56
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name: org.bklab.flow.form.FluentGenerateFormLayout
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasEnabled;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.bklab.flow.dialog.ErrorDialog;
import org.bklab.flow.dialog.crud.Buildable;
import org.bklab.flow.factory.FormLayoutFactory;
import org.bklab.flow.form.config.FormConfiguration;
import org.bklab.flow.form.config.FormConfigurationConfig;
import org.bklab.flow.form.config.FormConfigurationField;
import org.bklab.flow.form.render.FluentFormRenderManager;
import org.bklab.flow.form.render.IFormComponentRender;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Setter
@Accessors(fluent = true)
public class FluentGenerateFormLayout extends FormLayout implements Buildable<FluentGenerateFormLayout> {

    private final FormConfiguration configuration;
    private final Map<String, FormField> formFieldMap = new LinkedHashMap<>();
    private final FormLayoutFactory factory = new FormLayoutFactory(this);
    private boolean strictMode = false;

    public FluentGenerateFormLayout(FormConfiguration configuration) {
        this.configuration = configuration;
    }

    public static FluentGenerateFormLayout create(String json) throws Exception {
        return new FluentGenerateFormLayout(JSON.toJavaObject(JSON.parseObject(json), FormConfiguration.class));
    }

    @Override
    public FluentGenerateFormLayout build() throws RuntimeException {
        configuration.getFields().forEach(this::addField);
        factory.initFormLayout().fitModalDialogWidth();
        if (configuration.isDisabled()) {
            formFieldMap.values().forEach(c -> {
                if (c instanceof HasEnabled) ((HasEnabled) c).setEnabled(false);
            });
        }
        return this;
    }

    private void addField(FormConfigurationField field) {
        FluentFormRenderManager renderManager = FluentFormRenderManager.getInstance();
        FormConfigurationConfig config = field.getConfig();
        if (config == null) return;

        if (formFieldMap.containsKey(field.getModel())) {
            throw new RuntimeException("Duplicate form field key[__vModel__] : \"%s\".".formatted(field.getModel()));
        }

        IFormComponentRender<?, ?> render = renderManager.getRender(config);
        if (render == null) {
            String message = "Not support field [%s] in current version.".formatted(config.getTag() + " " + config.getTagIcon());
            if (strictMode) {
                new ErrorDialog(message).build().open();
                throw new UnsupportedOperationException(message);
            }
            log.warn(message);
            return;
        }

        Component component = null;
        try {
            component = render.build(field);
        } catch (Exception e) {
            String message = "Build field [%s] thrown an error: %s.".formatted(config.getTag() + " " + config.getTagIcon(), e.getMessage());
            if (strictMode) {
                e.printStackTrace();
                new ErrorDialog(message, e).build().open();
                throw new UnsupportedOperationException(message, e);
            }
            log.warn(message, e);
        }

        if (component == null) {
            component = new Span("Not support field %s[%s] which type is '%s %s'."
                    .formatted(config.getLabel(), field.getModel(), config.getTag(), config.getTagIcon()));
        }

        if (config.isShowLabel()) {
            String label = config.getLabel();
            if (label == null || label.isBlank()) {
                label = field.getModel();
            }
            factory.formItem(component, label + "：");
        } else {
            factory.add(component);
        }

        formFieldMap.put(field.getModel(), new FormField(field, component, render));
    }

    public Set<String> validate() {
        return formFieldMap.values().stream()
                .filter(f -> !f.validate()).map(a -> a.field.getConfig().getLabel())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public String getValue() {
        return JSON.toJSONString(getJsonValue(), true);
    }

    public FluentGenerateFormLayout setValue(String jsonObject) {
        return setValue(JSONObject.parseObject(jsonObject));
    }

    public FluentGenerateFormLayout setValue(JSONObject jsonObject) {
        jsonObject.forEach((name, value) -> Optional.ofNullable(formFieldMap.get(name)).ifPresent(formField -> formField.setValue(value)));
        return this;
    }

    public JSONObject getJsonValue() {
        JSONObject json = new JSONObject();
        formFieldMap.forEach((name, formField) -> json.put(name, formField.getValue()));
        return json;
    }

    @Getter
    @Accessors(fluent = true)
    private static class FormField {
        private final FormConfigurationField field;
        private final Component component;
        private final IFormComponentRender<?, ?> render;

        public FormField(FormConfigurationField field, Component component, IFormComponentRender<?, ?> render) {
            this.field = field;
            this.component = component;
            this.render = render;
        }

        public boolean validate() {
            return render.validate(field, component);
        }

        public Object getValue() {
            return render.getObjectValue(component);
        }

        public void setValue(Object value) {
            render.setObjectValue(field, component, value);
        }
    }

}
