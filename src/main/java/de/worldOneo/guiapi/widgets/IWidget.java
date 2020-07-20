package de.worldOneo.guiapi.widgets;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface IWidget {
    ItemStack render();

    void clickEvent(InventoryClickEvent e);

    int getSlot();
}
