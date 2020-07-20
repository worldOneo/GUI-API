package de.worldOneo.guiapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GUIAPI extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new OnInventoryClickListener(), this);
    }
}
