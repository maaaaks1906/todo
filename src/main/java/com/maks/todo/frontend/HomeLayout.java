package com.maks.todo.frontend;

import com.maks.todo.backend.entity.Todo;
import com.maks.todo.backend.service.TodoService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyPressEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("")
public class HomeLayout extends VerticalLayout implements OnTodoDoubleClickListener, OnSaveTodoClickListener {

    private TextField content = new TextField("Do zrobienia", "Zrobić zakupy dla mamy...");
    private VerticalLayout todos = new VerticalLayout();
    private Button deleteSelected = new Button("Usuń zaznaczone", this::onDeleteSelectedButtonClick);

    private List<TodoLayout> todoLayouts = new ArrayList<>();

    private EditTodoDialog editTodoDialog = new EditTodoDialog(this);

    private TodoService todoService;

    @Autowired
    public HomeLayout(TodoService todoService) {
        this.todoService = todoService;

        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        createLayout();
        reloadTodos();
    }

    private void createLayout() {
        getStyle().set("background-color", "#e8bfa0");

        todos.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        content.setWidth("30%");
        content.addKeyPressListener(Key.ENTER, this::onEnterKeyPress);

        add(content, todos, deleteSelected);
    }

    private void reloadTodos() {
        List<Todo> dbTodos = todoService.findAll();
        todoLayouts.clear();
        todos.removeAll();

        for (Todo t : dbTodos) {
            todoLayouts.add(new TodoLayout(t, this));
            todos.add(todoLayouts.get(todoLayouts.size() - 1));
        }
    }

    private void onEnterKeyPress(KeyPressEvent event) {
        Notification.show("Dodano zadanie do zrobienia", 2000, Notification.Position.BOTTOM_CENTER);

        Todo todo = todoService.save(new Todo(content.getValue(), false, null));
        todoLayouts.add(new TodoLayout(todo, this));
        todos.add(todoLayouts.get(todoLayouts.size() - 1));
        content.clear();
    }

    private void onDeleteSelectedButtonClick(ClickEvent<Button> clickEvent) {
        todoLayouts.stream().map(TodoLayout::getTodo).filter(Todo::getIsDone).forEach(todo -> todoService.delete(todo));
        todoLayouts.removeIf(TodoLayout::isDone);
        todos.removeAll();

        todoLayouts.forEach(todos::add);
    }

    @Override
    public void onTodoDoubleClick(Todo todo) {
        editTodoDialog.setTodo(todo);
        editTodoDialog.open();
    }


    @Override
    public void onSaveTodoClick(Todo todo) {
        editTodoDialog.close();

        Todo updatedTodo = editTodoDialog.getTodo();
        todoService.save(updatedTodo);
        reloadTodos();
    }
}
