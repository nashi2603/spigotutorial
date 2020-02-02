package com.github.nashi2603.spigotutorial;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Spigotutorial extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("plugin enabled!");
        getCommand("pltest").setExecutor(new Testcmd_pltest(this));
        getCommand("whisp").setExecutor(new WhispChat(this));
        getCommand("cwhisp").setExecutor(new CmdBlkWhispChat(this));
        getCommand("invctrlr").setExecutor(new InvCtrlr(this));
        getCommand("nbtpull").setExecutor(new NbtPull(this));
        new NbtPullClickEvent(this);
        this.saveDefaultConfig();
        getLogger().info(this.getConfig().getString("message"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("plugin disabled!");
    }

}
