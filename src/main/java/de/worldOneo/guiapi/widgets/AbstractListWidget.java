package de.worldOneo.guiapi.widgets;

import de.worldOneo.guiapi.utils.Pair;
import de.worldOneo.guiapi.utils.Utils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@Accessors(chain = true)
@Getter
@Setter
public abstract class AbstractListWidget extends AbstractMultipartWidget {
    private List<Integer> slots;
    private List<ItemStack> itemStacks;
    private Integer backSlot;
    private Integer forwardSlot;
    private Consumer<ItemStack> callback;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private int index;

    @RequiredArgsConstructor
    private enum MenuItems {
        BACK(Utils.createNamedItemStack(Material.ARROW, "Back")),
        FORWARD(Utils.createNamedItemStack(Material.ARROW, "Next"));
        @Getter
        private final ItemStack itemStack;

    }

    @Override
    public List<Pair<ItemStack, Integer>> render() {
        List<Pair<ItemStack, Integer>> pairList = new ArrayList<>();
        int tempBackSlot = backSlot == null ? slots.get(0) : backSlot;
        int tempForwardSlot = forwardSlot == null ? slots.get(slots.size() - 1) : forwardSlot;
        pairList.add(new Pair<>(MenuItems.BACK.getItemStack(), tempBackSlot));
        pairList.add(new Pair<>(MenuItems.FORWARD.getItemStack(), tempForwardSlot));
        Iterator<Integer> iterator = slots.iterator();
        Iterator<ItemStack> itemStackIterator = itemStacks.stream().skip(index).limit(slots.size() - 2).iterator();
        while (iterator.hasNext() && itemStackIterator.hasNext()) {
            int slot = iterator.next();
            ItemStack itemStack = itemStackIterator.next();
            while ((slot == tempBackSlot || slot == tempForwardSlot) && iterator.hasNext()) {
                slot = iterator.next();
            }
            pairList.add(new Pair<>(itemStack, slot));
        }
        return pairList;
    }

    @Override
    public void clickEvent(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        e.setCancelled(true);
        ItemStack itemStack = e.getCurrentItem();
        if (itemStack.equals(MenuItems.BACK.getItemStack())) {
            index -= slots.size() - 2;
            index = index < 0 ? 0 : index;
            open((Player) e.getWhoClicked());
        } else if (itemStack.equals(MenuItems.FORWARD.getItemStack())) {
            int tmpIndex = index + slots.size() - 2;
            if (itemStacks.stream().skip(tmpIndex).count() != 0) {
                index = tmpIndex;
            }
            open((Player) e.getWhoClicked());
        } else if (callback != null) {
            callback.accept(itemStack);
        }
    }
}
