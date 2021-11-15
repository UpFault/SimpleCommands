package com.upfault.simplecommands.commands;

import com.upfault.simplecommands.SimpleCommands;
import com.upfault.simplecommands.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class invview implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //check if enabled
        if (SimpleCommands.getInstance().getConfig().get("invview-command").equals(false)) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("command-disabled"));
            return true;
        }

        // verify that the user has proper permissions
        if (!sender.hasPermission("simplecommands.user")) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-permissions-message"));
            return true;
        }


        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("inv")) {

            if(args.length > 1) { Utilities.warnPlayer(sender, SimpleCommands.getPhrase("formatting-error-message")); return true; }

            if(args.length == 1) {
                String playerString = args[0];
                Player player1 = Bukkit.getPlayer(playerString);
                if (!(player1 != null && player1.isOnline())) {
                    Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-player"));
                    return true;
                } else {
                    player.openInventory(player1.getInventory());
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1f, 1f);
                    player.sendMessage(SimpleCommands.prefix + "Opened " + player1.getDisplayName() + "'s inventory!" );
                    return true;
                }
            } else {
                player.openInventory(player.getInventory());
                player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1f, 1f);
                player.sendMessage(SimpleCommands.prefix + "Opened your inventory!" );
            }

        }
        return true;
    }
}
