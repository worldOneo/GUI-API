package de.worldOneo.guiapi.gui;

import de.worldOneo.guiapi.GUIManager;
import de.worldOneo.guiapi.utils.Pair;
import de.worldOneo.guiapi.widgets.Widget;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public abstract class AbstractGUI implements IGUI {
    private String GUITitle = "Made by GUIAPI";
    private List<Widget> widgets = new ArrayList<>();
    private int size = 9;
    private HashMap<Pair<ItemStack, Integer>, Widget> pairWidgetHashMap = new HashMap<>();

    public void open(Player player) {
        GUIManager.getInstance().open(this, player);
    }

    @Override
    public Inventory render() {
        Inventory inventory = Bukkit.createInventory(null, getSize(), getGUITitle());
        getWidgets().forEach(widget -> {
            widget.beforeRender();
            pairWidgetHashMap.put(new Pair<>(widget.render(), widget.getSlot()),widget);
        });
        pairWidgetHashMap.forEach((key, value) -> inventory.setItem(
                key.getValue(),
                key.getKey()
        ));
        return inventory;
    }

    public void addWidget(Widget widget) {
        widgets.add(widget);
    }

    @Override
    public void clickEvent(InventoryClickEvent e) {
        Widget widget = pairWidgetHashMap.get(new Pair<>(e.getCurrentItem(), e.getSlot()));
        if (e.getCurrentItem() == null || widget  == null) {
            return;
        }
        widget.clickEvent(e);
    }
}
