/*
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2020-07-06 16:22:14
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.login.BklabSignupForm
 * Copyright (c) 2008 - 2020. - Broderick Labs.
 */

package org.bklab.login;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * A Designer generated component for the bklab-signup-form template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("bklab-signup-form")
@JsModule("./src/org/bklab/login/bklab-signup-form.js")
public class BklabSignupForm extends PolymerTemplate<BklabSignupForm.BklabSignupFormModel> {

    @Id("mail")
    private EmailField mail;
    @Id("account")
    private TextField account;
    @Id("password")
    private PasswordField password;
    @Id("code")
    private TextField code;
    @Id("sendCode")
    private Button sendCode;
    @Id("signup")
    private Button signup;
    @Id("goLogin")
    private Button goLogin;

    /**
     * Creates a new BklabSignupForm.
     */
    public BklabSignupForm() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between BklabSignupForm and bklab-signup-form
     */
    public interface BklabSignupFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }

    public EmailField getMail() {
        return mail;
    }

    public BklabSignupForm setMail(EmailField mail) {
        this.mail = mail;
        return this;
    }

    public TextField getAccount() {
        return account;
    }

    public BklabSignupForm setAccount(TextField account) {
        this.account = account;
        return this;
    }

    public PasswordField getPassword() {
        return password;
    }

    public BklabSignupForm setPassword(PasswordField password) {
        this.password = password;
        return this;
    }

    public TextField getCode() {
        return code;
    }

    public BklabSignupForm setCode(TextField code) {
        this.code = code;
        return this;
    }

    public Button getSendCode() {
        return sendCode;
    }

    public BklabSignupForm setSendCode(Button sendCode) {
        this.sendCode = sendCode;
        return this;
    }

    public Button getSignup() {
        return signup;
    }

    public BklabSignupForm setSignup(Button signup) {
        this.signup = signup;
        return this;
    }

    public Button getGoLogin() {
        return goLogin;
    }

    public BklabSignupForm setGoLogin(Button goLogin) {
        this.goLogin = goLogin;
        return this;
    }
}
