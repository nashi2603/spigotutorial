package com.github.nashi2603.spigotutorial;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

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
            if (args.length > 5) {
                sender.sendMessage("args is over 5");
            } else if (args.length < 5) {
                sender.sendMessage("args is smaller 5");
            } else {
                sender.sendMessage("args is 5");
            }
            return true;
        }
        return false;
    }
}
