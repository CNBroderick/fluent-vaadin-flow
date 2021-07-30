/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-10 09:35:06
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.login.BklabSignupView
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.login;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

/**
 * A Designer generated component for the bklab-signup-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("bklab-signup-view")
@JsModule("./src/org/bklab/flow/login/bklab-signup-view.ts")
public class BklabSignupView extends LitTemplate {

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
     * Creates a new BklabSignupView.
     */
    public BklabSignupView() {
        // You can initialise any data required for the connected UI components here.
    }

    public EmailField getMail() {
        return mail;
    }

    public TextField getAccount() {
        return account;
    }

    public PasswordField getPassword() {
        return password;
    }

    public TextField getCode() {
        return code;
    }

    public Button getSendCode() {
        return sendCode;
    }

    public Button getSignup() {
        return signup;
    }

    public Button getGoLogin() {
        return goLogin;
    }
}
