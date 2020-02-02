package com.github.nashi2603.spigotutorial;

import com.google.common.base.Splitter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Spliterator;

public class NbtPull implements CommandExecutor {
    private final Spigotutorial plugin;
    public NbtPull(Spigotutorial plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
//                ItemStack pullitem = plugin.getConfig().getItemStack(args[0]);
//                ((Player)sender).getInventory().addItem(pullitem);
//                sender.sendMessage("Pulled " + args[0]);
                ConnSqlite connection = new ConnSqlite();
                Connection conn = connection.connectsqlite();
                try {
                    PreparedStatement psst = conn.prepareStatement("select * from testtable1 where id = ?;");
                    psst.setInt(1, Integer.valueOf(args[0]));
                    ResultSet rs = psst.executeQuery();
//                    Map<String, Object> pullitem = new HashMap<>();
                    Gson gson = new Gson();
                    Type listtype = new TypeToken<HashMap<String, Object>>() {} .getType();
                    Map<String, Object> pullitem = gson.fromJson(rs.getString("itemdata"), listtype);
                    ((Player)sender).sendMessage(String.valueOf(pullitem));
                    ItemStack item = ItemStack.deserialize(pullitem);
                    ((Player)sender).getInventory().addItem(item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
            Inventory baseinv = Bukkit.createInventory(null, 27, "NBTPuller");
            ItemStack panel = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta pitemeta = panel.getItemMeta();
            pitemeta.setDisplayName(" ");
            panel.setItemMeta(pitemeta);
            int[] slotnum = {0, 1, 2, 9, 11, 18, 19, 20};
            for (int i : slotnum) {
                baseinv.setItem(i, panel);
            }
            ItemStack pullbtn = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
            ItemMeta pullbtnmeta = pullbtn.getItemMeta();
            pullbtnmeta.setDisplayName("Pull");
            pullbtn.setItemMeta(pullbtnmeta);
            baseinv.setItem(16, pullbtn);
            ((Player)sender).openInventory(baseinv);
            return true;
        } else {
            sender.sendMessage("Do not use " + sender.getName() + " ! use Player only.");
            return false;
        }
    }
}
