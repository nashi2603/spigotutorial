package com.github.nashi2603.spigotutorial;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.configuration.file.*;
import org.bukkit.plugin.Plugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

public class NbtPullClickEvent implements Listener {
    private final Spigotutorial plugin;
    public NbtPullClickEvent(Spigotutorial plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void nbtpullClickEvent(InventoryClickEvent event) {
        try {
            if (event.getView().getTitle().equals("NBTPuller") && event.getCurrentItem().getItemMeta().getDisplayName().equals("Pull")) {
                if (event.getInventory().getItem(10) == null) {
                    event.setCancelled(true);
                    ((Player) event.getWhoClicked()).updateInventory();
                    ((Player) event.getWhoClicked()).sendMessage("Item is not set!");
                } else {
                    ItemStack targetitem = event.getInventory().getItem(10);
                    event.getWhoClicked().getInventory().addItem(targetitem);
                    ((Player) event.getWhoClicked()).sendMessage(String.valueOf(event.getInventory().getItem(10).getItemMeta()));
                    ((Player)event.getWhoClicked()).sendMessage(String.valueOf(event.getInventory().getItem(10).getData()));
                    ((Player) event.getWhoClicked()).sendMessage("Item NBT Pulled!");
                    ItemMeta targetMeta = targetitem.getItemMeta();
                    ((Player) event.getWhoClicked()).sendMessage(String.valueOf(targetitem.serialize()));
                    FileConfiguration config = plugin.getConfig();
                    ((Player)event.getWhoClicked()).sendMessage(config.getString("message"));
                    ((Player)event.getWhoClicked()).sendMessage(String.valueOf(targetitem.serialize()));
                    config.set("testitemvalue", targetitem);
                    plugin.saveConfig();
                    Gson gson = new Gson();
                    String jsonitemdata = gson.toJson(targetitem.serialize());
//                    ((Player)event.getWhoClicked()).sendMessage("Json -> " + String.valueOf(jsonitemdata));
                    ConnSqlite connection = new ConnSqlite();
                    Connection conn = connection.connectsqlite();
                    PreparedStatement psst = conn.prepareStatement("insert into testtable1(itemdata) VALUES(?)");
//                    psst.setInt(1, 1);
                    psst.setString(1, jsonitemdata);
                    psst.executeUpdate();
                    ((Player) event.getWhoClicked()).updateInventory();
                    event.getView().close();
                }
//            event.getView().close();
            }
        } catch (Exception e) {
            event.getWhoClicked().sendMessage("Error: " + e);
        }
    }
}
