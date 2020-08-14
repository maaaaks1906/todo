package com.maks.todo.frontend;

import com.maks.todo.backend.entity.Todo;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditTodoDialog extends Dialog {

    private TextField content = new TextField("Content");
    private DateTimePicker deadline = new DateTimePicker("Deadline");

    private Button saveButton = new Button("Zapisz zmiany", this::onSaveButtonClick);
    private Button cancelButton = new Button("Anuluj", this::onCancelButtonClick);
    private OnSaveTodoClickListener onSaveTodoClickListener;

    private Todo todo;
    public EditTodoDialog(OnSaveTodoClickListener onSaveTodoClickListener) {
        this.onSaveTodoClickListener = onSaveTodoClickListener;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;

        updateLayout();
    }

    public Todo getTodo() {
        return todo;
    }

    private void updateLayout() {
        removeAll();

        HorizontalLayout header = new HorizontalLayout(new Label("Todo #" + todo.getId()));

        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2));
        formLayout.add(content, deadline);

        HorizontalLayout footer = new HorizontalLayout(saveButton, cancelButton);
        saveButton.getStyle().set("marginRight", "10px");

        content.setValue(todo.getContent());
        content.addValueChangeListener(e -> {
            todo.setContent(e.getValue());
        });

        if (todo.getDeadline() != null) {
            deadline.setValue(todo.getDeadline());
        }
        deadline.addValueChangeListener(e -> {
            todo.setDeadline(e.getValue());
        });

        add(header, formLayout, footer);
    }

    private void onSaveButtonClick(ClickEvent<Button> buttonClickEvent) {
        onSaveTodoClickListener.onSaveTodoClick(todo);
    }

    private void onCancelButtonClick(ClickEvent<Button> buttonClickEvent) {
        close();
    }
}
