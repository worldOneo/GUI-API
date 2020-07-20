package de.worldOneo.guiapi.utils;

import org.bukkit.ChatColor;

public class Utils {
    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
