/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-15 10:24:51
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.text.ClipboardHelper
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.text;

import com.google.gson.Gson;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.shared.Registration;
import org.bklab.flow.factory.NotificationFactory;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class ClipboardHelper {
    public static final SerializableConsumer<OnClipboardResult> defaultResultConsumer = getDefaultResultConsumer();
    private final static String JS_EXPRESSION = getJsExpression();
    private static ClipboardHelper instance;
    private final Map<Component, Registration> registrationMap = new LinkedHashMap<>();

    private ClipboardHelper() {
    }

    public static ClipboardHelper getInstance() {
        if (instance == null) instance = new ClipboardHelper();
        return instance;
    }

    private static SerializableConsumer<OnClipboardResult> getDefaultResultConsumer() {
        return onClipboardResult -> {
            NotificationFactory notificationFactory = new NotificationFactory(onClipboardResult.message).positionTopEnd()
                    .themeVariants(onClipboardResult.success ? NotificationVariant.LUMO_SUCCESS : NotificationVariant.LUMO_ERROR)
                    .duration(1000)
                    .open();
        };
    }

    private static String getJsExpression() {
        //language=JavaScript
        return "try{console.log('start copy '+$0+' which title is '+$1);const el=document" +
               ".createElement('textarea');el.value=$0;el" +
               ".setAttribute('readonly','');el.style.position='absolute';el.style.left='-9999px';document.body.appendChild(el);" +
               "const selected=document.getSelection().rangeCount>0?document.getSelection().getRangeAt(0):false;el.select();" +
               "document.execCommand('copy');document.body.removeChild(el);if(selected){document.getSelection().removeAllRanges();" +
               "document.getSelection().addRange(selected);return{'success':true,'message':'复制'+$1+'['+$0+']成功'}}" +
               "return{'success':false,'message':'复制'+$1+'['+$0+']失败，原因：选择创建节点错误。\\n'+e}}catch(e){console.log('复制失败：');" +
               "console.log(e);return{'success':false,'message':'复制'+$1+'['+$0+']失败，原因：\\n'+e}}";
    }

    public <C extends Component & ClickNotifier<C>> ClipboardHelper extend(Map<C, String> map) {
        map.forEach(this::extend);
        return this;
    }

    public <C extends Component & ClickNotifier<C>> ClipboardHelper extend(Map<C, String> map, SerializableConsumer<OnClipboardResult> resultConsumer) {
        map.forEach((component, content) -> extend(component, content, resultConsumer));
        return this;
    }

    public <C extends Component & ClickNotifier<C>> ClipboardHelper extend(C component, String content) {
        return extend(component, content, defaultResultConsumer);
    }

    public <C extends Component & ClickNotifier<C>> ClipboardHelper extend(C component, String content, SerializableConsumer<OnClipboardResult> resultConsumer) {
        return extend(component, content, "", defaultResultConsumer);
    }

    public <C extends Component & ClickNotifier<C>> ClipboardHelper extend(C component, String content, String title) {
        return extend(component, content, title, defaultResultConsumer);
    }

    public <C extends Component & ClickNotifier<C>> ClipboardHelper extend(C component, String content, String title, SerializableConsumer<OnClipboardResult> resultConsumer) {
        registrationMap.remove(component);
        Registration registration = component.addClickListener(componentClickEvent -> {
            UI ui = UI.getCurrent();
            ui.getPage().executeJs(JS_EXPRESSION, content, title)
                    .then(jsonValue -> {
                        if (jsonValue == null) {
                            Optional.ofNullable(resultConsumer).orElse(defaultResultConsumer).accept(new OnClipboardResult(false, "返回结果为空。"));
                            return;
                        }
                        Optional.ofNullable(resultConsumer).orElse(defaultResultConsumer)
                                .accept(OnClipboardResult.create(jsonValue.toJson()));
                    }, error -> {
                        Optional.ofNullable(resultConsumer).orElse(defaultResultConsumer).accept(new OnClipboardResult(false, error));
                        LoggerFactory.getLogger(getClass()).trace("复制[" + content + "]到浏览器失败，目标类：" + component.getClass().getName());
                    });
        });

        component.addDetachListener(detachEvent -> registrationMap.put(component, registration));
        registrationMap.remove(component);

        return this;
    }

    public boolean isExtend(Component component) {
        return registrationMap.containsKey(component);
    }

    public ClipboardHelper remove(Component component) {
        if (!isExtend(component)) return this;
        getRegistration(component).ifPresent(Registration::remove);
        return this;
    }

    public Map<Component, Registration> getRegistrationMap() {
        return registrationMap;
    }

    public Optional<Registration> getRegistration(Component component) {
        return Optional.ofNullable(registrationMap.get(component));
    }

    public static class OnClipboardResult {
        public boolean success;
        public String message;

        public OnClipboardResult() {
        }

        public OnClipboardResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public static OnClipboardResult create(String json) {
            LoggerFactory.getLogger(OnClipboardResult.class).info("create by \n" + json);
            System.out.println("create by \n" + json);
            try {
                return new Gson().fromJson(json, OnClipboardResult.class);
            } catch (Exception e) {
                return new OnClipboardResult(false, "null".equals(json) ? "返回空结果。" : "未知返回结果：" + json + "。");
            }
        }

        public boolean isSuccess() {
            return success;
        }

        public OnClipboardResult setSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public OnClipboardResult setMessage(String message) {
            this.message = message;
            return this;
        }

        @Override
        public String toString() {
            return success + "|" + message;
        }
    }
}
