/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-10 09:26:20
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.LoginView
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bklab.flow.factory.NotificationFactory;
import org.bklab.flow.login.BklabLoginView;

@Route(value = "login")
@PageTitle("登录到布克约森实验室")
public class LoginView extends BklabLoginView {
    public LoginView() {

        loginListener((username, password) -> {
            new NotificationFactory().text("成功使用账号[%s]和密码[%s]登陆。".formatted(username, password))
                    .lumoSuccess().positionTopEnd().duration(1000).open();
            return ValidationResult.ok();
        });
        signup(buttonClickEvent -> new NotificationFactory().text("正在导航到注册页面。")
                .lumoSuccess().positionTopEnd().duration(1000).open());
        forgotPassword(account ->
                new NotificationFactory().text("[%s]的密码已被重置。").lumoSuccess().positionTopEnd().duration(1000).open()
        );
    }
}
