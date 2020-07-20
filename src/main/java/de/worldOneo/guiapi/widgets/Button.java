package de.worldOneo.guiapi.widgets;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Consumer;

@Accessors(chain = true)
@Getter
@Setter
public class Button extends AbstractButton {
    private Material material = Material.STONE;
    private String title = "Button by GUIAPI";
    private int amount = 1;

    public Button(Consumer<InventoryClickEvent> inventoryClickEventConsumer) {
        super(inventoryClickEventConsumer);
    }

    @Override
    public ItemStack render() {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(title);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
