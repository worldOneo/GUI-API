package de.worldOneo.guiapi.widgets;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface Widget {
    ItemStack render();
    void beforeRender();
    void clickEvent(InventoryClickEvent e);

    int getSlot();
}
