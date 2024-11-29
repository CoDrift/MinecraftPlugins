package com.codrift.killfeed;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class KillFeed extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register the event listener
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("KillFeed plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("KillFeed plugin disabled!");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location location = player.getLocation();

        // Format the death message with coordinates
        String deathMessage = ChatColor.RED + player.getName() + " died at coordinates: " +
                location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();

        // Broadcast the death message
        Bukkit.broadcastMessage(deathMessage);
    }
}
