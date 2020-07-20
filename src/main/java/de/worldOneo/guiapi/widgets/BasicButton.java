package de.worldOneo.guiapi.widgets;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class BasicButton extends AbstractButton {
    @Getter
    private ItemStack displayItem;

    BasicButton(Consumer<InventoryClickEvent> inventoryClickEventConsumer) {
        super(inventoryClickEventConsumer);
    }

    @Override
    public ItemStack render() {
        return getDisplayItem();
    }

    public BasicButton setDisplayItem(ItemStack displayItem) {
        this.displayItem = displayItem;
        return this;
    }
}
