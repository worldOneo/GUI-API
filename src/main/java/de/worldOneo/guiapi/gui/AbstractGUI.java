package de.worldOneo.guiapi.gui;

import de.worldOneo.guiapi.GUIManager;
import de.worldOneo.guiapi.utils.Pair;
import de.worldOneo.guiapi.widgets.IMultipartWidget;
import de.worldOneo.guiapi.widgets.IWidget;
import lombok.Data;
import lombok.experimental.Accessors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Accessors(chain = true)
@Data
public abstract class AbstractGUI implements IGUI {
    private String GUITitle = "Made by GUIAPI";
    private List<IWidget> widgets = new ArrayList<>();
    private List<IMultipartWidget> multipartWidgets = new ArrayList<>();
    private int size = 9;
    private HashMap<Pair<ItemStack, Integer>, IWidget> pairWidgetHashMap = new HashMap<>();
    private HashMap<Pair<ItemStack, Integer>, IMultipartWidget> pairMultipartWidgetHashMap = new HashMap<>();

    public void open(Player player) {
        GUIManager.getInstance().open(this, player);
    }

    @Override
    public Inventory render() {
        Inventory inventory = Bukkit.createInventory(null, getSize(), getGUITitle());
        getWidgets().forEach(widget -> {
            ItemStack itemStack = widget.render();
            int slot = widget.getSlot();
            pairWidgetHashMap.put(new Pair<>(itemStack, slot), widget);
            inventory.setItem(slot, itemStack);
        });

        getMultipartWidgets().forEach(multipartWidget ->
                multipartWidget.render().forEach(itemStackIntegerPair -> {
                    pairMultipartWidgetHashMap.put(itemStackIntegerPair, multipartWidget);
                    inventory.setItem(itemStackIntegerPair.getValue(), itemStackIntegerPair.getKey());
                })
        );
        return inventory;
    }

    @Override
    public void addWidget(IWidget widget) {
        widgets.add(widget);
    }

    @Override
    public void addWidget(IMultipartWidget multipartWidget) {
        multipartWidgets.add(multipartWidget);
    }

    @Override
    public void clickEvent(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        Pair<ItemStack, Integer> pair = new Pair<>(e.getCurrentItem(), e.getSlot());
        IWidget widget = pairWidgetHashMap.get(pair);
        IMultipartWidget multipartWidget = pairMultipartWidgetHashMap.get(pair);
        if(multipartWidget != null){
            multipartWidget.clickEvent(e);
        }
        if(widget != null){
            widget.clickEvent(e);
        }
    }
}
