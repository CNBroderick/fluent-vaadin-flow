/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-07-30 17:25:42
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.form.config.FormConfigurationTest
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.form.config;

import junit.framework.TestCase;

public class FormConfigurationTest extends TestCase {

    private static final String config = """
            {
                "fields": [{
                  "__config__": {
                    "label": "单行文本1",
                    "labelWidth": null,
                    "showLabel": true,
                    "changeTag": true,
                    "tag": "el-input",
                    "tagIcon": "input",
                    "required": true,
                    "layout": "colFormItem",
                    "span": 24,
                    "document": "https://element.eleme.cn/#/zh-CN/component/input",
                    "regList": [{
                      "pattern": "/^1(3|4|5|7|8|9)\\\\d{9}$/",
                      "message": "手机号格式错误"
                    }]
                  },
                  "__slot__": {
                    "prepend": "asd",
                    "append": "asd"
                  },
                  "__vModel__": "mobile213123",
                  "placeholder": "请输入手机号单行文本1",
                  "style": {
                    "width": "100%"
                  },
                  "clearable": true,
                  "prefix-icon": "el-icon-mobile",
                  "suffix-icon": "el-icon-success",
                  "maxlength": 11,
                  "show-word-limit": true,
                  "readonly": false,
                  "disabled": false
                }, {
                  "__config__": {
                    "label": "下拉选择",
                    "showLabel": true,
                    "labelWidth": null,
                    "tag": "el-select",
                    "tagIcon": "select",
                    "layout": "colFormItem",
                    "span": 24,
                    "required": true,
                    "regList": [],
                    "changeTag": true,
                    "document": "https://element.eleme.cn/#/zh-CN/component/select",
                    "formId": 102,
                    "renderKey": 1626920696978,
                    "defaultValue": []
                  },
                  "__slot__": {
                    "options": [{
                      "label": "选项一",
                      "value": 1
                    }, {
                      "label": "选项二",
                      "value": 2
                    }, {
                      "label": "选项一",
                      "value": 3
                    }]
                  },
                  "placeholder": "请选择下拉选择",
                  "style": {
                    "width": "100%"
                  },
                  "clearable": true,
                  "disabled": false,
                  "filterable": false,
                  "multiple": true,
                  "__vModel__": "mobile"
                }, {
                  "__config__": {
                    "label": "多行文本",
                    "labelWidth": null,
                    "showLabel": true,
                    "tag": "el-input",
                    "tagIcon": "textarea",
                    "required": true,
                    "layout": "colFormItem",
                    "span": 24,
                    "regList": [],
                    "changeTag": true,
                    "document": "https://element.eleme.cn/#/zh-CN/component/input",
                    "formId": 101,
                    "renderKey": 1626920694166,
                    "defaultValue": "撒大苏打撒旦\\n2321312\\n43534\\n213435\\n12435"
                  },
                  "type": "textarea",
                  "placeholder": "请输入多行文本",
                  "autosize": {
                    "minRows": 7,
                    "maxRows": 6
                  },
                  "style": {
                    "width": "100%"
                  },
                  "maxlength": "213",
                  "show-word-limit": true,
                  "readonly": false,
                  "disabled": false,
                  "__vModel__": "field101"
                }, {
                  "__config__": {
                    "label": "多选框组",
                    "tag": "el-checkbox-group",
                    "tagIcon": "checkbox",
                    "defaultValue": ["选项一"],
                    "span": 24,
                    "showLabel": true,
                    "labelWidth": 0,
                    "layout": "colFormItem",
                    "optionType": "default",
                    "required": true,
                    "regList": [],
                    "changeTag": true,
                    "border": false,
                    "document": "https://element.eleme.cn/#/zh-CN/component/checkbox",
                    "formId": 121,
                    "renderKey": 1627363737871
                  },
                  "__slot__": {
                    "options": [{
                      "label": "选项一",
                      "value": 1
                    }, {
                      "label": "选项二",
                      "value": 2
                    }]
                  },
                  "style": {},
                  "size": "medium",
                  "disabled": false,
                  "__vModel__": "field121",
                  "min": 2,
                  "max": 3
                }, {
                  "__config__": {
                    "label": "下拉选择",
                    "showLabel": true,
                    "labelWidth": null,
                    "tag": "el-select",
                    "tagIcon": "select",
                    "layout": "colFormItem",
                    "span": 24,
                    "required": true,
                    "regList": [],
                    "changeTag": true,
                    "document": "https://element.eleme.cn/#/zh-CN/component/select",
                    "formId": 122,
                    "renderKey": 1627363740705,
                    "defaultValue": 2
                  },
                  "__slot__": {
                    "options": [{
                      "label": "选项一",
                      "value": 1
                    }, {
                      "label": "选项二",
                      "value": 2
                    }, {
                      "label": "选项一@SDAD",
                      "value": 1
                    }]
                  },
                  "placeholder": "请选择下拉选择",
                  "style": {
                    "width": "100%"
                  },
                  "clearable": true,
                  "disabled": false,
                  "filterable": false,
                  "multiple": false,
                  "__vModel__": "field122"
                }, {
                  "__config__": {
                    "label": "日期选择",
                    "tag": "el-date-picker",
                    "tagIcon": "date",
                    "defaultValue": "2021-06-07",
                    "showLabel": true,
                    "labelWidth": null,
                    "span": 24,
                    "layout": "colFormItem",
                    "required": true,
                    "regList": [],
                    "changeTag": true,
                    "document": "https://element.eleme.cn/#/zh-CN/component/date-picker",
                    "formId": 110,
                    "renderKey": 1626920972887
                  },
                  "placeholder": "请选择日期选择",
                  "type": "week",
                  "style": {
                    "width": "100%"
                  },
                  "disabled": false,
                  "clearable": true,
                  "format": "yyyy 第 WW 周",
                  "value-format": "yyyy-MM-dd",
                  "readonly": false,
                  "__vModel__": "field110"
                }, {
                  "__config__": {
                    "label": "评分",
                    "tag": "el-rate",
                    "tagIcon": "rate",
                    "defaultValue": 0,
                    "span": 24,
                    "showLabel": true,
                    "labelWidth": null,
                    "layout": "colFormItem",
                    "required": true,
                    "regList": [],
                    "changeTag": true,
                    "document": "https://element.eleme.cn/#/zh-CN/component/rate",
                    "formId": 131,
                    "renderKey": 1627614182977
                  },
                  "style": {},
                  "max": 5,
                  "allow-half": true,
                  "show-text": true,
                  "show-score": false,
                  "disabled": false,
                  "__vModel__": "field131"
                }, {
                  "__config__": {
                    "label": "日期范围",
                    "tag": "el-date-picker",
                    "tagIcon": "date-range",
                    "defaultValue": null,
                    "span": 24,
                    "showLabel": true,
                    "labelWidth": null,
                    "required": true,
                    "layout": "colFormItem",
                    "regList": [],
                    "changeTag": true,
                    "document": "https://element.eleme.cn/#/zh-CN/component/date-picker",
                    "formId": 111,
                    "renderKey": 1626920993818
                  },
                  "style": {
                    "width": "100%"
                  },
                  "type": "datetimerange",
                  "range-separator": "至",
                  "start-placeholder": "开始日期",
                  "end-placeholder": "结束日期",
                  "disabled": false,
                  "clearable": true,
                  "format": "yyyy-MM-dd HH:mm:ss",
                  "value-format": "yyyy-MM-dd HH:mm:ss",
                  "readonly": false,
                  "__vModel__": "field111"
                }, {
                  "__config__": {
                    "label": "上传",
                    "tag": "el-upload",
                    "tagIcon": "upload",
                    "layout": "colFormItem",
                    "defaultValue": null,
                    "showLabel": true,
                    "labelWidth": null,
                    "required": true,
                    "span": 24,
                    "showTip": false,
                    "buttonText": "点击上传",
                    "regList": [{
                      "pattern": "",
                      "message": ""
                    }],
                    "changeTag": true,
                    "fileSize": 2,
                    "sizeUnit": "MB",
                    "document": "https://element.eleme.cn/#/zh-CN/component/upload",
                    "formId": 130,
                    "renderKey": 1627614176563
                  },
                  "__slot__": {
                    "list-type": true
                  },
                  "action": "https://jsonplaceholder.typicode.com/posts/",
                  "disabled": false,
                  "accept": "image/*",
                  "name": "file",
                  "auto-upload": true,
                  "list-type": "text",
                  "multiple": false,
                  "__vModel__": "field130"
                }, {
                  "__config__": {
                    "label": "编辑器",
                    "showLabel": true,
                    "changeTag": true,
                    "labelWidth": null,
                    "tag": "tinymce",
                    "tagIcon": "rich-text",
                    "defaultValue": "",
                    "span": 24,
                    "layout": "colFormItem",
                    "required": true,
                    "regList": [],
                    "document": "http://tinymce.ax-z.cn",
                    "formId": 112,
                    "renderKey": 1627614718378
                  },
                  "placeholder": "请输入编辑器",
                  "height": 300,
                  "branding": false,
                  "__vModel__": "field112"
                }, {
                  "__config__": {
                    "label": "多选框组",
                    "tag": "el-checkbox-group",
                    "tagIcon": "checkbox",
                    "defaultValue": [],
                    "span": 24,
                    "showLabel": true,
                    "labelWidth": null,
                    "layout": "colFormItem",
                    "optionType": "default",
                    "required": true,
                    "regList": [],
                    "changeTag": true,
                    "border": false,
                    "document": "https://element.eleme.cn/#/zh-CN/component/checkbox",
                    "formId": 132,
                    "renderKey": 1627614509120
                  },
                  "__slot__": {
                    "options": [{
                      "label": "选项一",
                      "value": 1
                    }, {
                      "label": "选项二",
                      "value": 2
                    }]
                  },
                  "style": {},
                  "size": "medium",
                  "disabled": false,
                  "__vModel__": "field132"
                }, {
                  "__config__": {
                    "layout": "rowFormItem",
                    "tagIcon": "row",
                    "layoutTree": true,
                    "document": "https://element.eleme.cn/#/zh-CN/component/layout#row-attributes",
                    "span": 24,
                    "formId": 129,
                    "renderKey": 1627613504166,
                    "componentName": "row129",
                    "children": [{
                      "__config__": {
                        "label": "",
                        "showLabel": false,
                        "changeTag": true,
                        "labelWidth": null,
                        "tag": "el-button",
                        "tagIcon": "button",
                        "span": 3,
                        "layout": "colFormItem",
                        "document": "https://element.eleme.cn/#/zh-CN/component/button",
                        "formId": 125,
                        "renderKey": 1627363753263
                      },
                      "__slot__": {
                        "default": "主要按钮"
                      },
                      "type": "primary",
                      "icon": "el-icon-search",
                      "round": false,
                      "size": "mini",
                      "plain": false,
                      "circle": false,
                      "disabled": false,
                      "__vModel__": "field125"
                    }, {
                      "__config__": {
                        "label": "",
                        "showLabel": false,
                        "changeTag": true,
                        "labelWidth": null,
                        "tag": "el-button",
                        "tagIcon": "button",
                        "span": 3,
                        "layout": "colFormItem",
                        "document": "https://element.eleme.cn/#/zh-CN/component/button",
                        "formId": 128,
                        "renderKey": 1627613484156
                      },
                      "__slot__": {
                        "default": "主要按钮"
                      },
                      "type": "primary",
                      "icon": "el-icon-search",
                      "round": false,
                      "size": "mini",
                      "plain": false,
                      "circle": false,
                      "disabled": false,
                      "__vModel__": "field128"
                    }]
                  },
                  "type": "flex",
                  "justify": "end",
                  "align": "middle"
                }],
                "formRef": "elForm",
                "formModel": "formData",
                "size": "mini",
                "labelPosition": "right",
                "labelWidth": 100,
                "formRules": "rules",
                "gutter": 15,
                "disabled": false,
                "span": 24,
                "formBtns": true,
                "unFocusedComponentBorder": false
              }
              
            """;

    public void testCreate() {
        FormConfiguration configuration = FormConfiguration.create(config);
        for (FormConfigurationField field : configuration.getFields()) {
            if (field.getConfig().getTagIcon().equals("checkbox")) {
                System.out.println(field.getConfig().getDefaultValue().getClass().getName());
                System.out.println(field.getConfig().getDefaultValue().getClass().getTypeName());
                return;
            }

        }


        System.out.println(configuration.json(true));
    }

    public void testJson() {
    }

}
