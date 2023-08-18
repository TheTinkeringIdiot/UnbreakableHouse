package com.tinkeringidiot.unbreakablehouse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class UnbreakableHouse  extends JavaPlugin {
    private String regionsFile = "\\protected_regions.json";
    private ArrayList<Region> regionsList = new ArrayList<Region>();
    @Override
    public void onEnable() {

        try {
            String parentPath = new File(UnbreakableHouse.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().toString();
            JsonObject jsonRegions = (JsonObject) new JsonParser().parse(new FileReader(parentPath + regionsFile));
            jsonRegions = (JsonObject) jsonRegions.get("regions");

            Set<Map.Entry<String, JsonElement>> entries = jsonRegions.entrySet();
            for(Map.Entry<String, JsonElement> entry: entries) {
                String regionName = entry.getKey();
                JsonObject region = entry.getValue().getAsJsonObject();
                JsonObject point1json = region.getAsJsonObject("point1");
                JsonObject point2json = region.getAsJsonObject("point2");

                Location point1 = new Location(Bukkit.getWorld("world"), point1json.get("X").getAsInt(), point1json.get("Y").getAsInt(), point1json.get("Z").getAsInt());
                Location point2 = new Location(Bukkit.getWorld("world"), point2json.get("X").getAsInt(), point2json.get("Y").getAsInt(), point2json.get("Z").getAsInt());

                regionsList.add(new Region(regionName, point1, point2));
            }

            getServer().getPluginManager().registerEvents(new BreakListener(regionsList), this);
            this.getCommand("protect").setExecutor(new ProtectCommand(regionsList));
        }
        catch (FileNotFoundException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        getLogger().info("UnbreakableHouse is online");
    }
}
