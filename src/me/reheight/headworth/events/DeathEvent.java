package me.reheight.headworth.events;

import me.reheight.headworth.Main;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeathEvent implements Listener {
    Main plugin;
    public DeathEvent(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event) {
        Entity deadEntity = event.getEntity();
        UUID deadUUID = deadEntity.getUniqueId();
        Player deadPlayer = Bukkit.getPlayer(deadUUID);

        if ((deadPlayer == null) || (!deadPlayer.isOnline())) return;

        ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1);
        SkullMeta playerSkullMeta = (SkullMeta)playerSkull.getItemMeta();
        playerSkullMeta.setOwningPlayer(deadPlayer.getPlayer());
        List<String> loreStringList = new ArrayList<>();
        double worth = (plugin.econ.getBalance(deadPlayer) * (5.0F/100.0F));
        String displayWorth = plugin.econ.format(worth);
        loreStringList.add(ChatColor.translateAlternateColorCodes('&', "&8[&aWorth: &f" + displayWorth + "&8]"));
        playerSkullMeta.setLore(loreStringList);
        playerSkull.setItemMeta(playerSkullMeta);

        World playerWorld = deadPlayer.getWorld();
        Location playerLocation = deadPlayer.getLocation();

        playerWorld.dropItemNaturally(playerLocation, playerSkull);
        return;
    }
}
