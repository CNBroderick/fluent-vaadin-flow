package org.bklab;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.bklab.login.BklabLoginForm;

@Route(value = "login")
@PageTitle("登录到布克约森实验室")
public class LoginView extends BklabLoginForm {
    public LoginView() {

        loginListener((username, password) -> {
            UI.getCurrent().navigate(ComponentDemoView.class);
            return ValidationResult.ok();
        });
    }
}
