package com.maks.todo.frontend;

import com.maks.todo.backend.entity.ShoppingObject;
import com.maks.todo.backend.entity.Todo;
import com.maks.todo.backend.repository.ShoppingListRepository;
import com.maks.todo.backend.service.ShoppingListService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route ("shopping")
public class ShoppingListLayout extends VerticalLayout {

    private VerticalLayout shoppingObjects = new VerticalLayout();

    private TextField content = new TextField("", "Do kupienia");
    private TextField quantity = new TextField("", "Ilość");
    private Button add = new Button(VaadinIcon.PLUS_CIRCLE.create(), this::onAddButtonClick);

    private List<ShoppingObjectLayout> shoppingObjectLayouts = new ArrayList<>();

    private ShoppingListService shoppingListService;

    @Autowired // TODO: 12/08/2020 kiedy mam to pisac?
    public ShoppingListLayout() {
        createLayout();
        reloadShoppingObjects();
    }

    public void createLayout() {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        HorizontalLayout topLayout = new HorizontalLayout(content, quantity, add);

        add.addClickShortcut(Key.ENTER);

        add(topLayout);
    }

    private void reloadShoppingObjects() {
        List<ShoppingObject> dbTodos = shoppingListService.findAll();
        shoppingObjectLayouts.clear();
        shoppingObjects.removeAll();

        for (ShoppingObject s : dbTodos) {
            shoppingObjectLayouts.add(new ShoppingObjectLayout(s));
            shoppingObjects.add(shoppingObjectLayouts.get(shoppingObjectLayouts.size() - 1));
        }
    }

    private void onAddButtonClick(ClickEvent<Button> buttonClickEvent) {
        Notification.show("Dodano", 4000, Notification.Position.BOTTOM_CENTER); // TODO: 12/08/2020 Nie bardzo rozumiem co tu sie dzieje
        ShoppingObject shoppingObject = shoppingListService.save(                            // TODO: 14/08/2020 jakie sa zaleznosci miedzy klasami, tzn co za co jest odpowiedzialne bo mi sie myli service z repository
                new ShoppingObject(content.getValue(), quantity.getValue(), false
                ));
        shoppingObjectLayouts.add(new ShoppingObjectLayout(shoppingObject));
        shoppingObjects.add(shoppingObjectLayouts.get(shoppingObjectLayouts.size() - 1));
        content.clear();
    }

}
