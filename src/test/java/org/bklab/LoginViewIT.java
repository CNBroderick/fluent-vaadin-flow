/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-02 09:33:44
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.LoginViewIT
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.flow.data.binder.ValidationResult;
import org.bklab.flow.factory.NotificationFactory;
import org.bklab.login.BklabLoginForm;
import org.junit.Assert;
import org.junit.Test;

public class LoginViewIT extends AbstractViewTest {

    @Test
    public void testClickButtonShowsHelloUserNotificationWhenUserIsNotEmpty() {

        BklabLoginForm form = new BklabLoginForm();
        form.loginListener((userName, password) -> {
            new NotificationFactory()
                    .add(new Text("账户：" + userName))
                    .add(new Text("密码：" + password))
                    .add(new Text("登录成功"))
                    .duration(3)
                    .lumoSuccess()
                    .positionTopEnd()
                    .open();
            return ValidationResult.ok();
        })
                .forgotPassword(userName -> {
                    new NotificationFactory()
                            .add(new Text("账户：" + userName))
                            .add(new Text("忘记密码"))
                            .duration(3)
                            .lumoError()
                            .positionTopEnd()
                            .open();
                })
                .signup(event -> {
                    new NotificationFactory()
                            .add(new Text("正在跳转到注册页面"))
                            .duration(3)
                            .lumoError()
                            .positionTopEnd()
                            .open();
                })
        ;


        TextFieldElement textField = $(TextFieldElement.class).first();
        textField.setValue("Vaadiner");
        ButtonElement button = $(ButtonElement.class).first();
        button.click();
        Assert.assertTrue($(NotificationElement.class).exists());
        NotificationElement notification = $(NotificationElement.class).first();
        Assert.assertEquals("Hello Vaadiner", notification.getText());
    }


}
