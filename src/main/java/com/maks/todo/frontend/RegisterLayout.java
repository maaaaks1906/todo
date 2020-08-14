package com.maks.todo.frontend;

import com.maks.todo.backend.entity.User;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("register")
public class RegisterLayout extends VerticalLayout  {

    private static final String LOGO_URL = "https://images.squarespace-cdn.com/content/v1/5c5382928dfc8cdc537fe0e5/1549391629520-4FPOIWB2AEUN1BU4UPI2/ke17ZwdGBToddI8pDm48kMX8ocY37G2OJZo4ycyD-mJZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpyrMub11wXtV_YW6kbsbxdfLJrkQ8GGYUgLz0NSYzWbRs0FSTDkZwJGjyWCvqoRiPc/todoverde-logo-WEB-03.png";

    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField username = new TextField();
    private PasswordField password = new PasswordField();
    private EmailField email = new EmailField();

    private Button register = new Button("Register", this::onRegisterButtonClick);
    private Button clear = new Button("Clear", this::onClearButtonClick);

    private Binder<User> binder = new Binder<>(User.class);
    private User user = new User();

    public RegisterLayout() {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        binder.bindInstanceFields(this);
        binder.setBean(user);

        createLayout();
    }

    private void createLayout() {
        Image image = new Image(LOGO_URL, "Todo Logo");
        image.setHeight("200px");

        VerticalLayout imageLayout = new VerticalLayout(image);
        imageLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2));

        firstName.setPlaceholder("First Name");
        lastName.setPlaceholder("Last Name");
        username.setPlaceholder("Username");
        password.setPlaceholder("Password");
        email.setPlaceholder("E-mail");

        formLayout.add(firstName, lastName, username, password, email);
        formLayout.setColspan(email, 2);
        formLayout.getStyle().set("marginTop", "50px");

        HorizontalLayout buttonLayout = new HorizontalLayout(register, clear);
        register.addClickShortcut(Key.ENTER);
        register.addClassName("primary");
        register.getStyle().set("marginRight", "10px");

        VerticalLayout layout = new VerticalLayout(imageLayout, formLayout, buttonLayout);
        layout.setWidth("50%");

        add(layout);
    }

    private void onRegisterButtonClick(ClickEvent<Button> buttonClickEvent) {
        binder.validate();

        if (binder.isValid()) {
            Notification.show("Zarejestrowano...");
        }
    }

    private void onClearButtonClick(ClickEvent<Button> buttonClickEvent) {
        firstName.clear();
        lastName.clear();
        username.clear();
        password.clear();
        email.clear();
    }
}
