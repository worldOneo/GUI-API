package de.worldOneo.guiapi.widget;

import de.worldOneo.guiapi.utils.Pair;
import de.worldOneo.guiapi.widgets.AbstractMultipartWidget;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

@Accessors(chain = true)
@Getter
@Setter
public class MultipartPlaceHolder extends AbstractMultipartWidget {
    private List<Integer> slots;
    private Material material;

    @Override
    public List<Pair<ItemStack, Integer>> render() {
        ItemStack itemStack = new ItemStack(material);
        return slots.stream()
                .map(integer -> new Pair<>(itemStack, integer))
                .collect(Collectors.toList());
    }

    @Override
    public void clickEvent(InventoryClickEvent e) {
        e.setCancelled(true);
    }
}
