package com.maks.todo.frontend;

import com.maks.todo.backend.entity.ShoppingObject;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class ShoppingObjectLayout extends HorizontalLayout {

    private ShoppingObject shoppingObject;

    private Checkbox isDone = new Checkbox();
    private TextField content = new TextField();
    private TextField quantity = new TextField();

    public ShoppingObjectLayout(ShoppingObject shoppingObject) {
        this.shoppingObject = shoppingObject;

        createLayout();
    }

    private void createLayout() {
        setDefaultVerticalComponentAlignment(Alignment.CENTER);
        content.setValueChangeMode(ValueChangeMode.ON_BLUR);
        content.setReadOnly(true);                              // TODO: 12/08/2020 nie rozumiem skad program wie o ktora notatke chodzi
        content.setValue(shoppingObject.getContent());
        content.addValueChangeListener(e -> shoppingObject.setContent(e.getValue()));

        quantity.setValueChangeMode(ValueChangeMode.ON_BLUR);
        quantity.setReadOnly(true);
        quantity.setValue(shoppingObject.getQuantity());
        quantity.addValueChangeListener(e -> shoppingObject.setQuantity(e.getValue()));

        isDone.setValue(shoppingObject.getIsDone());
        isDone.addValueChangeListener(e -> shoppingObject.setIsDone(e.getValue()));

        add(isDone, content, quantity);
    }

    public boolean isDone() {
        return isDone.getValue(); // TODO: 12/08/2020 co to robi?
    }

    public ShoppingObject getShoppingObject() {
        return shoppingObject;
    }
}
