package de.worldOneo.guiapi;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnInventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e) {
        GUIManager.getInstance().handle(e);
    }
}
