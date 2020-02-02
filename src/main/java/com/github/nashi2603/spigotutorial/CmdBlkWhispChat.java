package com.github.nashi2603.spigotutorial;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBlkWhispChat implements CommandExecutor {
    private final Spigotutorial plugin;
    public CmdBlkWhispChat(Spigotutorial plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Player target = Bukkit.getPlayer(args[0]);
            target.sendMessage(args[1]);
            return true;
        } catch (Exception e) {
            sender.sendMessage("Error: " + e);
            return true;
        }
    }
}
