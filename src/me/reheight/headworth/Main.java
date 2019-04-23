package me.reheight.headworth;

import me.reheight.headworth.events.DeathEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public Economy econ;

    private DeathEvent deathEvent = new DeathEvent(this);
    @Override
    public void onEnable() {
        getLogger().info("Plugin started!");

        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(deathEvent, this);
    }

    @Override
    public void onDisable() {

    }
}
