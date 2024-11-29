package com.codrift.hiddenarmour;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class HiddenArmour extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register event listeners
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("HiddenArmour plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("HiddenArmour plugin disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Hide the player's armor periodically for themselves
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                    return;
                }
                hideArmorForPlayer(player);
            }
        }.runTaskTimer(this, 0L, 20L); // Run every second (20 ticks)
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // No cleanup needed since functionality is server-side
    }

    private void hideArmorForPlayer(Player player) {
        // Clear the player's own view of their armor
        player.sendEquipmentChange(player, org.bukkit.inventory.EquipmentSlot.HEAD, null);
        player.sendEquipmentChange(player, org.bukkit.inventory.EquipmentSlot.CHEST, null);
        player.sendEquipmentChange(player, org.bukkit.inventory.EquipmentSlot.LEGS, null);
        player.sendEquipmentChange(player, org.bukkit.inventory.EquipmentSlot.FEET, null);
    }
}