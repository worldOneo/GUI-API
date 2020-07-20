package de.worldOneo.guiapi.widgets;

import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public abstract class AbstractButton extends AbstractWidget {
    private final Consumer<InventoryClickEvent> inventoryClickEventConsumer;

    public AbstractButton(Consumer<InventoryClickEvent> inventoryClickEventConsumer) {
        this.inventoryClickEventConsumer = inventoryClickEventConsumer;
    }

    @Override
    public void clickEvent(InventoryClickEvent e) {
        inventoryClickEventConsumer.accept(e);
    }
}
