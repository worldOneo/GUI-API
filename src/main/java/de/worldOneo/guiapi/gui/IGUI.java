package de.worldOneo.guiapi.gui;

import de.worldOneo.guiapi.widgets.Widget;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public interface IGUI {
    void clickEvent(InventoryClickEvent e);

    String getGUITitle();

    Inventory render();

    void addWidget(Widget widget);
}
