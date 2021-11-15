package com.upfault.simplecommands.commands;

import com.upfault.simplecommands.SimpleCommands;
import com.upfault.simplecommands.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Random;

public class feed implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //check if enabled
        if (SimpleCommands.getInstance().getConfig().get("feed-command").equals(false)) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("command-disabled"));
            return true;
        }

        // verify that the user has proper permissions
        if (!sender.hasPermission("simplecommands.user")) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-permissions-message"));
            return true;
        }

        String[] arr = {"§aYour stomach stops rumbling..", "§aYour hunger was satiated..", "§aYou're no longer hungry.."};
        Random random = new Random();

        int select = random.nextInt(arr.length);

        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("feed")) {
            if(args.length == 1) {
                String playerString = args[0];
                Player player1 = Bukkit.getPlayer(playerString);
                if (!(player1 != null && player1.isOnline())) {
                    Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-player"));
                    return true;
                } else {
                    player1.setFoodLevel(20);
                    player1.setSaturation(20);
                    player1.sendMessage(arr[select]);
                    player1.playSound(player1.getLocation(), Sound.ENTITY_PLAYER_BURP, 1f, 1f);
                    return true;
                }
            }

            if(args.length > 1) { Utilities.warnPlayer(sender, SimpleCommands.getPhrase("formatting-error-message")); return true; }

                player.setFoodLevel(20);
                player.setSaturation(20);
                player.sendMessage(arr[select]);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1f, 1f);
        }
        return true;
    }
}
