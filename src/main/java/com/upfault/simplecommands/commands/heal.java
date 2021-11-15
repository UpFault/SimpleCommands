package com.upfault.simplecommands.commands;

import com.upfault.simplecommands.SimpleCommands;
import com.upfault.simplecommands.utils.Utilities;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class heal implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //check if enabled
        if (SimpleCommands.getInstance().getConfig().get("heal-command").equals(false)) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("command-disabled"));
            return true;
        }

        // verify that the user has proper permissions
        if (!sender.hasPermission("simplecommands.user")) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-permissions-message"));
            return true;
        }

        String [] arr = {"§aYou bandaged your arm!", "§aYou stuck yourself with morphine!", "§aYou rested and feel better!", "§aYou used a MedKit and feel better!"};
        Random random = new Random();

        // randomly selects an index from the arr
        int select = random.nextInt(arr.length);

        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("heal")) {
                player.setHealth(20f);
            player.sendMessage(arr[select]);
            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1f, 1f);
        }
        return true;
    }
}
