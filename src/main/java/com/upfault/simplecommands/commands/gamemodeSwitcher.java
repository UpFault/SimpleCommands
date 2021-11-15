package com.upfault.simplecommands.commands;

import com.upfault.simplecommands.SimpleCommands;
import com.upfault.simplecommands.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class gamemodeSwitcher implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //check if enabled
        if (SimpleCommands.getInstance().getConfig().get("gamemode-command").equals(false)) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("command-disabled"));
            return true;
        }

        // verify that the user has proper permissions
        if (!sender.hasPermission("simplecommands.user")) {
            Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-permissions-message"));
            return true;
        }
        //SURVIVAL GAMEMODE
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("gms")) {
            if(args.length == 1) {
                String playerString = args[0];
                Player player1 = Bukkit.getPlayer(playerString);
                if (!(player1 != null && player1.isOnline())) {
                    Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-player"));
                    return true;
                } else {
                    player1.setGameMode(GameMode.SURVIVAL);
                    player1.sendMessage(SimpleCommands.prefix + "Set gamemode to survival!");
                    return true;
                }
            }

            if(args.length > 1) { Utilities.warnPlayer(sender, SimpleCommands.getPhrase("formatting-error-message")); return true; }

            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(SimpleCommands.prefix + "Set gamemode to survival!");
        }
        //SPECTATOR GAMEMODE
        if (command.getName().equalsIgnoreCase("gmsp")) {
            if(args.length == 1) {
                String playerString = args[0];
                Player player1 = Bukkit.getPlayer(playerString);
                if (!(player1 != null && player1.isOnline())) {
                    Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-player"));
                    return true;
                } else {
                    player1.setGameMode(GameMode.SPECTATOR);
                    player1.sendMessage(SimpleCommands.prefix + "Set gamemode to spectator!");
                    return true;
                }
            }

            if(args.length > 1) { Utilities.warnPlayer(sender, SimpleCommands.getPhrase("formatting-error-message")); return true; }

            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(SimpleCommands.prefix + "Set gamemode to spectator!");
        }
        //ADVENTURE GAMEMODE
        if (command.getName().equalsIgnoreCase("gma")) {
            if(args.length == 1) {
                String playerString = args[0];
                Player player1 = Bukkit.getPlayer(playerString);
                if (!(player1 != null && player1.isOnline())) {
                    Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-player"));
                    return true;
                } else {
                    player1.setGameMode(GameMode.ADVENTURE);
                    player1.sendMessage(SimpleCommands.prefix + "Set gamemode to adventure!");
                    return true;
                }
            }

            if(args.length > 1) { Utilities.warnPlayer(sender, SimpleCommands.getPhrase("formatting-error-message")); return true; }

            player.setGameMode(GameMode.ADVENTURE);
            player.sendMessage(SimpleCommands.prefix + "Set gamemode to adventure!");
        }
        //CREATIVE GAMEMODE
        if (command.getName().equalsIgnoreCase("gmc")) {
            if(args.length == 1) {
                String playerString = args[0];
                Player player1 = Bukkit.getPlayer(playerString);
                if (!(player1 != null && player1.isOnline())) {
                    Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-player"));
                    return true;
                } else {
                    player1.setGameMode(GameMode.CREATIVE);
                    player1.sendMessage(SimpleCommands.prefix + "Set gamemode to creative!");
                    return true;
                }
            }

            if(args.length > 1) { Utilities.warnPlayer(sender, SimpleCommands.getPhrase("formatting-error-message")); return true; }

            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(SimpleCommands.prefix + "Set gamemode to creative!");
        }

        GameMode[] arr = {GameMode.SURVIVAL, GameMode.SPECTATOR, GameMode.CREATIVE, GameMode.ADVENTURE};
        Random random = new Random();

        int select = random.nextInt(arr.length);
        //RANDOM GAMEMODE
        if (command.getName().equalsIgnoreCase("gmr")) {
            if(args.length == 1) {
                String playerString = args[0];
                Player player1 = Bukkit.getPlayer(playerString);
                if (!(player1 != null && player1.isOnline())) {
                    Utilities.warnPlayer(sender, SimpleCommands.getPhrase("no-player"));
                    return true;
                } else {
                    player1.setGameMode(arr[select]);
                    player1.sendMessage(SimpleCommands.prefix + "Selecting random gamemode!");
                    return true;
                }
            }

            if(args.length > 1) { Utilities.warnPlayer(sender, SimpleCommands.getPhrase("formatting-error-message")); return true; }

            player.setGameMode(arr[select]);
            player.sendMessage(SimpleCommands.prefix + "Selecting random gamemode!");
        }
        return true;
    }
}