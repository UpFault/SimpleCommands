package com.upfault.simplecommands.commands;

import com.upfault.simplecommands.SimpleCommands;
import com.upfault.simplecommands.utils.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //check if enabled
        if (SimpleCommands.getInstance().getConfig().get("fly-command").equals(false)) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("command-disabled"));
            return true;
        }

        // verify that the user has proper permissions
        if (!sender.hasPermission("simplecommands.user")) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-permissions-message"));
            return true;
        }

        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("fly")) {
            if (!player.getAllowFlight()) {
                player.setAllowFlight(true);
                player.setFlying(true);
                player.sendMessage(SimpleCommands.prefix + "Flight enabled...");
            } else {
                player.setAllowFlight(false);
                player.setFlying(false);
                player.sendMessage(SimpleCommands.prefix + "Flight disabled...");
            }
        }
        return true;
    }
}
