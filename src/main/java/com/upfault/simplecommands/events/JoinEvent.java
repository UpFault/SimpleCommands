package com.upfault.simplecommands.events;

import com.upfault.simplecommands.utils.Utilities;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent event) throws InterruptedException {
        if(event.getPlayer().hasPermission("simplecommands.op")) {
            Utilities.informPlayer(event.getPlayer(), "§cNOTE: This plugin is not \"/reload\" safe");
            Utilities.informPlayer(event.getPlayer(), "§cUse /scmd reload instead.");

        }
    }
}
