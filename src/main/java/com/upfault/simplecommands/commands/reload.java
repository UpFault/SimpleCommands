package com.upfault.simplecommands.commands;

import com.upfault.simplecommands.SimpleCommands;
import com.upfault.simplecommands.utils.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class reload implements CommandExecutor {

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

        if (args[0].equalsIgnoreCase("reload")) {
            SimpleCommands.getInstance().reloadConfig();
            SimpleCommands.getInstance().loadConfiguration();
            SimpleCommands.getInstance().loadLangFile();

            Utilities.informPlayer(sender, "configuration, values, and language settings reloaded");
        }
        return true;
    }
}
