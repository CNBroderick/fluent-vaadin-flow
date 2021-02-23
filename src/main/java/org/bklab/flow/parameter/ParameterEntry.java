package org.bklab.flow.parameter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.StringJoiner;

public class ParameterEntry {
    private int sequence;
    private String group;
    private String name;
    private String caption;
    private String value;
    private String defaultValue;
    private String description;
    private boolean readOnly;
    private String effectMode;
    private String editor;
    private JsonObject data;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public int getValue(int defaultValue) {
        String v = getValue();
        if (v == null || v.trim().isEmpty()) return defaultValue;
        return Integer.parseInt(v);
    }

    public boolean getValue(boolean defaultValue) {
        String v = getValue();
        if (v == null || v.trim().isEmpty()) return defaultValue;
        return Boolean.parseBoolean(v);
    }

    public long getValue(long defaultValue) {
        String v = getValue();
        if (v == null || v.trim().isEmpty()) return defaultValue;
        return Long.parseLong(v);
    }

    public double getValue(double defaultValue) {
        String v = getValue();
        if (v == null || v.trim().isEmpty()) return defaultValue;
        return Double.parseDouble(v);
    }

    public String getValue(String defaultValue) {
        return value == null || value.trim().isEmpty() ? defaultValue : value;
    }

    public Class<?> getValue(Class<?> defaultValue) {
        try {
            return Class.forName(getValue());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public <T, C extends T> T getValue(Class<T> defaultObject, C defaultNewInstance) {
        try {
            return Class.forName(getValue()).asSubclass(defaultObject).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return defaultNewInstance;
        }
    }

    public int getSequence() {
        return sequence;
    }

    public ParameterEntry setSequence(int sequence) {
        this.sequence = sequence;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public ParameterEntry setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getName() {
        return name;
    }

    public ParameterEntry setName(String name) {
        this.name = name;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public ParameterEntry setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getValue() {
        return value == null || value.trim().isEmpty() ? defaultValue : value;
    }

    public ParameterEntry setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public ParameterEntry setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ParameterEntry setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public ParameterEntry setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public String getEditor() {
        return editor;
    }

    public ParameterEntry setEditor(String editor) {
        this.editor = editor;
        return this;
    }

    public JsonObject getData() {
        return data;
    }

    public ParameterEntry setData(String data) {
        try {
            this.data = new Gson().fromJson(data, JsonObject.class);
        } catch (Exception e) {
            logger.error("解析Json错误", e);
        }
        return this;
    }

    public ParameterEntry setData(JsonObject data) {
        this.data = data;
        return this;
    }

    public Logger getLogger() {
        return logger;
    }

    public ParameterEntry setLogger(Logger logger) {
        this.logger = logger;
        return this;
    }

    public String getEffectMode() {
        return effectMode;
    }

    public ParameterEntry setEffectMode(String effectMode) {
        this.effectMode = effectMode;
        return this;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create().toJson(this);
    }
}
