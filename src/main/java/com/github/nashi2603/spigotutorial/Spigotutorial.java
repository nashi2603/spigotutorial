package com.github.nashi2603.spigotutorial;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Spigotutorial extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("plugin enabled!");
        this.getCommand("pltest").setExecutor(new Testcmd_pltest(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("plugin disabled!");
    }

}
