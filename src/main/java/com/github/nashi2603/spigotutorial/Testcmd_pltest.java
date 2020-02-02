package com.github.nashi2603.spigotutorial;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class Testcmd_pltest implements CommandExecutor {

    private final Spigotutorial plugin;
    public Testcmd_pltest(Spigotutorial plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pltest")) {
            sender.sendMessage("Command Output.");
//            for(int i = 0; i < args.length; i++) {
//                sender.sendMessage(args[i]);
//            }
//            sender.sendMessage(String.valueOf(Bukkit.selectEntities(sender, args[0])));
//            Player target = Bukkit.getPlayerExact(String.valueOf(args[0]));
            try {
                List<Entity> entity = Bukkit.selectEntities(sender, args[0]);
                sender.sendMessage(String.valueOf(entity));
                for (int i = 0; i < entity.size(); i++) {
                    Player target = (Player)entity.get(i);
                    target.getPlayer().sendMessage(args[1]);
                }
            } catch (Exception e) {
                sender.sendMessage(String.valueOf(e));
            }
//            target.sendMessage(args[1]);
//            if (args.length > 5) {
//                sender.sendMessage("args is over 5");
//            } else if (args.length < 5) {
//                sender.sendMessage("args is smaller 5");
//            } else {
//                sender.sendMessage("args is 5");
//            }
            return true;
        }
        return false;
    }
}
