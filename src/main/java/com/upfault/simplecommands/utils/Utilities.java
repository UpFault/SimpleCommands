package com.upfault.simplecommands.utils;

import com.google.common.io.ByteStreams;
import com.upfault.simplecommands.SimpleCommands;
import multiversion.Sound;
import multiversion.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public final class Utilities {

    // list of transparent blocks to be ignored when a player looks at a block
    private static final Set<Material> TRANSPARENT = EnumSet.of(XMaterial.AIR.parseMaterial(), XMaterial.BLACK_CARPET.parseMaterial(), XMaterial.BLUE_CARPET.parseMaterial(),
            XMaterial.BROWN_CARPET.parseMaterial(), XMaterial.CYAN_CARPET.parseMaterial(), XMaterial.GRAY_CARPET.parseMaterial(), XMaterial.GREEN_CARPET.parseMaterial(), XMaterial.LIGHT_BLUE_CARPET.parseMaterial(),
            XMaterial.LIME_CARPET.parseMaterial(), XMaterial.MAGENTA_CARPET.parseMaterial(), XMaterial.ORANGE_CARPET.parseMaterial(), XMaterial.PINK_CARPET.parseMaterial(), XMaterial.PURPLE_CARPET.parseMaterial(),
            XMaterial.RED_CARPET.parseMaterial(), XMaterial.WHITE_CARPET.parseMaterial(), XMaterial.YELLOW_CARPET.parseMaterial());

    // list of all supported inventory blocks in the plugin
    public static final List<Material> INVENTORY_BLOCKS = Arrays.asList(XMaterial.CHEST.parseMaterial(), XMaterial.TRAPPED_CHEST.parseMaterial(), XMaterial.ENDER_CHEST.parseMaterial(), XMaterial.SHULKER_BOX.parseMaterial(), XMaterial.BLACK_SHULKER_BOX.parseMaterial(),
            XMaterial.BLUE_SHULKER_BOX.parseMaterial(), XMaterial.BROWN_SHULKER_BOX.parseMaterial(), XMaterial.CYAN_SHULKER_BOX.parseMaterial(), XMaterial.GRAY_SHULKER_BOX.parseMaterial(),
            XMaterial.GREEN_SHULKER_BOX.parseMaterial(), XMaterial.LIGHT_BLUE_SHULKER_BOX.parseMaterial(), XMaterial.LIGHT_GRAY_SHULKER_BOX.parseMaterial(), XMaterial.LIME_SHULKER_BOX.parseMaterial(),
            XMaterial.MAGENTA_SHULKER_BOX.parseMaterial(), XMaterial.ORANGE_SHULKER_BOX.parseMaterial(), XMaterial.PINK_SHULKER_BOX.parseMaterial(), XMaterial.PURPLE_SHULKER_BOX.parseMaterial(),
            XMaterial.RED_SHULKER_BOX.parseMaterial(), XMaterial.WHITE_SHULKER_BOX.parseMaterial(), XMaterial.YELLOW_SHULKER_BOX.parseMaterial());

    // load file from JAR with comments
    public static void loadResource(Plugin plugin, String resource) {
        File folder = plugin.getDataFolder();
        if (!folder.exists())
            folder.mkdir();
        File resourceFile = new File(folder, resource);
        try {
            if (!resourceFile.exists()) {
                resourceFile.createNewFile();
                try (InputStream in = plugin.getResource(resource);
                     OutputStream out = new FileOutputStream(resourceFile)) {
                    ByteStreams.copy(in, out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // warns player of something in plugin
    public static void warnPlayer(CommandSender sender, List<String> messages) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            playSound(ActionSound.ERROR, player);
        }
        for (String message : messages)
            sender.sendMessage(SimpleCommands.prefix + ChatColor.RESET + ChatColor.RED + message);
    }

    public static void warnPlayer(CommandSender sender, String message) {
        warnPlayer(sender, Collections.singletonList(message));
    }

    // informs player of something in plugin
    public static void informPlayer(CommandSender sender, List<String> messages) {
        for (String message : messages)
            sender.sendMessage(SimpleCommands.prefix + ChatColor.RESET + ChatColor.GRAY + message);
    }

    public static void informPlayer(CommandSender sender, String message) {
        informPlayer(sender, Collections.singletonList(message));
    }

    // play sound at player (version independent)
    public static void playSound(ActionSound sound, Player player) {

        switch (sound) {
            case OPEN:
                Sound.CHEST_OPEN.playSound(player);
                break;
            case MODIFY:
                Sound.ANVIL_USE.playSound(player);
                break;
            case SELECT:
                Sound.LEVEL_UP.playSound(player);
                break;
            case CLICK:
                Sound.CLICK.playSound(player);
                break;
            case POP:
                Sound.CHICKEN_EGG_POP.playSound(player);
                break;
            case BREAK:
                Sound.ANVIL_LAND.playSound(player);
                break;
            case ERROR:
                Sound.BAT_DEATH.playSound(player);
                break;
        }
    }
}
