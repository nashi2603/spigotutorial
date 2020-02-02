package com.github.nashi2603.spigotutorial;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvCtrlr implements CommandExecutor {
    private final Spigotutorial plugin;
    public InvCtrlr(Spigotutorial plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((args.length == 0) && (sender instanceof Player)) {
            Player openplayer = Bukkit.getPlayer(args[0]);
            Inventory inv = Bukkit.createInventory(openplayer.getInventory().getHolder(), openplayer.getInventory().getType(), openplayer.getDisplayName());
            inv.setStorageContents(openplayer.getInventory().getStorageContents());
            ((Player)sender).openInventory(inv);
            return true;
        } else return false;
    }
}
