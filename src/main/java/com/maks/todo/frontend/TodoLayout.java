package com.maks.todo.frontend;

import com.maks.todo.backend.entity.Todo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class TodoLayout extends HorizontalLayout {

    private Todo todo;

    private Checkbox isDone = new Checkbox();
    private TextField content = new TextField();
    private Button editButton = new Button(VaadinIcon.EDIT.create());

    private OnTodoDoubleClickListener onTodoDoubleClickListener;

    public TodoLayout(Todo todo, OnTodoDoubleClickListener onTodoDoubleClickListener) {
        this.todo = todo;
        this.onTodoDoubleClickListener = onTodoDoubleClickListener;

        createLayout();
    }

    private void createLayout() {
        content.getStyle().set("background-color", "white");
        setDefaultVerticalComponentAlignment(Alignment.CENTER);
        content.setValueChangeMode(ValueChangeMode.ON_BLUR);
        content.setReadOnly(true);
        content.setValue(todo.getContent());
        content.addValueChangeListener(e -> todo.setContent(e.getValue()));

        isDone.setValue(todo.getIsDone());
        isDone.addValueChangeListener(e -> todo.setIsDone(e.getValue()));

        addClickListener(event -> {
           if (event.getClickCount() == 2) {
               onTodoDoubleClickListener.onTodoDoubleClick(todo);
           }
        });

        editButton.addClickListener(e -> {
            onTodoDoubleClickListener.onTodoDoubleClick(todo);
        });

        add(isDone, content, editButton);
    }

    public boolean isDone() {
        return isDone.getValue();
    }

    public Todo getTodo() {
        return todo;
    }
}
