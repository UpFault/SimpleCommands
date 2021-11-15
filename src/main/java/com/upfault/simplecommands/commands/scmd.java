package com.upfault.simplecommands.commands;

import com.upfault.simplecommands.SimpleCommands;
import com.upfault.simplecommands.utils.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class scmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // verify that the user has proper permissions
        if (!sender.hasPermission("simplecommands.op")) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-permissions-message"));
            return true;
        }

        if (!(args.length == 1)) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("formatting-error-message"));
            return true;
        }

        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("reload")) {
            SimpleCommands.reload(player);
        }

        if (SimpleCommands.getInstance().getConfig().get("help-command").equals(false)) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("command-disabled"));
            return true;
        }
        if (args[0].equalsIgnoreCase("help")) {
            player.sendMessage(SimpleCommands.prefix + ChatColor.GRAY + "Commands §c{}§7 = optional §c[]§7 = required");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/scmd reload §f-§7 Reload plugin");
            player.sendMessage(ChatColor.AQUA + "Aliases" + ChatColor.GRAY + " - simplecommands");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/help §f-§7 Prompts this message");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/heal {player} §f-§7 Heal yourself or others");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/feed {player} §f-§7 Feed yourself or others");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/ec {player} §f-§7 View your enderchest or others");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/inv {player} §f-§7 View your inventory or others");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/gms {player} §f-§7 Sets your or others gamemode to survival");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/gmsp {player} §f-§7 Sets your or others gamemode to  spectator");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/gmc {player} §f-§7 Sets your or others gamemode to creative");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/gma {player} §f-§7 Sets your or others gamemode to adventure");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/gmr {player} §f-§7 Randomly Sets your or others gamemode");
            player.sendMessage(ChatColor.AQUA + "- " + ChatColor.GRAY + "/v §f-§7 Hide yourself from others");
            player.sendMessage(ChatColor.AQUA + "Aliases" + ChatColor.GRAY + " - hide, vanish");
            player.sendMessage(ChatColor.AQUA + "------------------------------");
        }
        return true;
    }
}
