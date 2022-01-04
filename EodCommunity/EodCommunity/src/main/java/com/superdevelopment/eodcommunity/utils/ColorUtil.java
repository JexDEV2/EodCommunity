package com.superdevelopment.eodcommunity.utils;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

public class ColorUtil {
    public static ChatColor fromRGB(int r, int g, int b) {
        return ChatColor.of(new Color(r, g, b));
    }
}