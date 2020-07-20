package de.worldOneo.guiapi.widgets;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Accessors(chain = true)
@Getter
@Setter
public class PlaceHolder extends AbstractWidget {
    private Material material;


    @Override
    public ItemStack render() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public PlaceHolder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    @Override
    public void clickEvent(InventoryClickEvent e) {
    }
}
