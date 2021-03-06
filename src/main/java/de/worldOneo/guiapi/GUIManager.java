package de.worldOneo.guiapi;

import de.worldOneo.guiapi.gui.IGUI;
import de.worldOneo.guiapi.utils.Pair;
import de.worldOneo.guiapi.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Random;

public class GUIManager {
    private static final GUIManager instance = new GUIManager();
    private final HashMap<Player, Pair<String, IGUI>> playerIGUIMap = new HashMap<>();

    private GUIManager() {

    }

    public static GUIManager getInstance() {
        return instance;
    }

    public void open(IGUI igui, Player player) {
        Inventory inventory = igui.render();
        String title = igui.getGUITitle();
        title = generateID() + title;
        Inventory renamedInventory = Bukkit.createInventory(null, inventory.getSize(), title);
        renamedInventory.setContents(inventory.getContents());
        playerIGUIMap.put(player, new Pair<>(title, igui));
        player.openInventory(renamedInventory);
    }

    public void handle(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Pair<String, IGUI> pair = playerIGUIMap.get(player);
        if (pair == null) {
            return;
        }

        String title = pair.getKey();
        IGUI iGUI = pair.getValue();

        if (!title.equals(e.getView().getTitle())) return;

        iGUI.clickEvent(e);
    }

    private String generateID() {
        char[] chars = "1234567890abcdeflnokm".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append("&").append(chars[random.nextInt(chars.length)]);
        }
        stringBuilder.append("&r");
        return Utils.colorize(stringBuilder.toString());
    }
}
