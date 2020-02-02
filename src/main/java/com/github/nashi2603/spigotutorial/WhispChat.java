package com.github.nashi2603.spigotutorial;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class WhispChat implements CommandExecutor {
    private final Spigotutorial plugin;
    public WhispChat(Spigotutorial plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            String msg;
            try {
                msg = args[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
            if (args.length > 1) {
                for (int i = 2; i < args.length; i++) {
                    msg += (" " + args[i]);
                }
            }
            try {
                List<Entity> whistargets = Bukkit.selectEntities(sender, args[0]);
                for (int i = 0; i < whistargets.size(); i++) {
                    Player target = (Player) whistargets.get(i);
                    String targetName = target.getName();
                    sender.sendMessage(targetName);
                    if (!(Bukkit.getServer().getOnlinePlayers().contains(target))) {
                        sender.sendMessage("Target player not found.");
                    } else {
                        if (target.getName() != sender.getName()) {
                            target.sendMessage("[" + sender.getName() + " -> " + target.getName() + "] " + msg);
                            sender.sendMessage("[" + sender.getName() + " -> " + target.getName() + "] " + msg);
                        }
                    }
                }
                return true;
            } catch (Exception e) {
                sender.sendMessage("Error." + e);
                return true;
            }
        } else {
            return false;
        }
    }
}
