/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-06-25 20:27:59
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.login.BklabLoginForm
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.login;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.function.Consumer;

/**
 * A Designer generated component for the bklab-login-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("bklab-login-form")
@JsModule("./src/login/bklab-login-form.js")
public class BklabLoginForm extends PolymerTemplate<BklabLoginForm.BklabLoginFormModel> {

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
    private Element copyright;

    /**
     * Creates a new BklabLoginForm.
     */
    public BklabLoginForm() {
        // You can initialise any data required for the connected UI components here.
    }

    public BklabLoginForm loginListener(DoLoginListener doLoginListener) {
        login.addClickListener(event -> doLoginListener.doLogin(account.getValue(), password.getValue()));
        return this;
    }

    public BklabLoginForm forgotPassword(Consumer<String> forgotPassword) {
        forgot.addClickListener(event -> forgotPassword.accept(account.getValue()));
        return this;
    }

    public BklabLoginForm signup(ComponentEventListener<ClickEvent<Button>> listener) {
        signup.addClickListener(listener);
        return this;
    }

    public Image getLogo() {
        return logo;
    }

    public BklabLoginForm setLogo(Image logo) {
        this.logo = logo;
        return this;
    }

    public TextField getAccount() {
        return account;
    }

    public BklabLoginForm setAccount(TextField account) {
        this.account = account;
        return this;
    }

    public PasswordField getPassword() {
        return password;
    }

    public BklabLoginForm setPassword(PasswordField password) {
        this.password = password;
        return this;
    }

    public Button getLogin() {
        return login;
    }

    public BklabLoginForm setLogin(Button login) {
        this.login = login;
        return this;
    }

    public Button getForgot() {
        return forgot;
    }

    public BklabLoginForm setForgot(Button forgot) {
        this.forgot = forgot;
        return this;
    }

    public Button getSignup() {
        return signup;
    }

    public BklabLoginForm setSignup(Button signup) {
        this.signup = signup;
        return this;
    }

    public Element getCopyright() {
        return copyright;
    }

    /**
     * This model binds properties between BklabLoginForm and bklab-login-form
     */
    public interface BklabLoginFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }

    public interface DoLoginListener {
        ValidationResult doLogin(String userName, String password);
    }
}
