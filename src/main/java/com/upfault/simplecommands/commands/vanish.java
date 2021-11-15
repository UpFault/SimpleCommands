package com.upfault.simplecommands.commands;

import com.upfault.simplecommands.SimpleCommands;
import com.upfault.simplecommands.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class vanish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        //check if enabled
        if (SimpleCommands.getInstance().getConfig().get("vanish-command").equals(false)) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("command-disabled"));
            return true;
        }

        // verify that the user has proper permissions
        if (!sender.hasPermission("simplecommands.user")) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-permissions-message"));
            return true;
        }

            if(SimpleCommands.getInstance().vanish_list.containsKey(uuid)) {
                for (Player people : Bukkit.getOnlinePlayers()) {
                    people.showPlayer(SimpleCommands.getInstance(), player);
                }
                SimpleCommands.getInstance().vanish_list.remove(uuid, true);
                player.sendMessage(SimpleCommands.prefix + "You are now visible to other players on the server.");
            } else if(!SimpleCommands.getInstance().vanish_list.containsKey(uuid)) {
                for (Player people : Bukkit.getOnlinePlayers()) {
                    people.hidePlayer(SimpleCommands.getInstance(), player);
                }
                SimpleCommands.getInstance().vanish_list.put(uuid, true);
                player.sendMessage(SimpleCommands.prefix + "You are now invisible!");
        }
        return true;
    }
}
