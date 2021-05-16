/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-16 14:44:23
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.login.BklabLoginView
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.login;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationResult;

import java.util.function.Consumer;

/**
 * A Designer generated component for the bklab-login-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("bklab-login-view")
@JsModule("./src/org/bklab/flow/login/bklab-login-view.ts")
public class BklabLoginView extends LitTemplate {

    @Id("content")
    private VerticalLayout vaadinVerticalLayout;
    @Id("logo")
    private Image logo;
    @Id("account")
    private TextField account;
    @Id("password")
    private PasswordField password;
    @Id("login")
    private Button login;
    @Id("forgot")
    private Button forgot;
    @Id("signup")
    private Button signup;
    @Id("copyright")
    private HorizontalLayout copyright;

    /**
     * Creates a new BklabLoginView.
     */
    public BklabLoginView() {
        // You can initialise any data required for the connected UI components here.
    }

    public BklabLoginView loginListener(DoLoginListener doLoginListener) {
        login.addClickListener(event -> doLoginListener.doLogin(account.getValue(), password.getValue()));
        return this;
    }

    public BklabLoginView forgotPassword(Consumer<String> forgotPassword) {
        forgot.addClickListener(event -> forgotPassword.accept(account.getValue()));
        return this;
    }

    public BklabLoginView signup(ComponentEventListener<ClickEvent<Button>> listener) {
        signup.addClickListener(listener);
        return this;
    }

    public Image getLogo() {
        return logo;
    }

    public TextField getAccount() {
        return account;
    }

    public PasswordField getPassword() {
        return password;
    }

    public Button getLogin() {
        return login;
    }

    public Button getForgot() {
        return forgot;
    }

    public Button getSignup() {
        return signup;
    }

    public HorizontalLayout getCopyright() {
        return copyright;
    }

    public interface DoLoginListener {
        ValidationResult doLogin(String userName, String password);
    }
}
