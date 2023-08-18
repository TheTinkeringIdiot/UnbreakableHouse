package com.tinkeringidiot.unbreakablehouse;

import org.bukkit.Location;

public class Region {

    private String name;
    private Location point1;
    private Location point2;
    private boolean protect = true;

    public Region(String name, Location point1, Location point2) {
        this.name = name;
        this.point1 = point1;
        this.point2 = point2;
    }

    public String getName() { return this.name; }
    public boolean isProtected() { return this.protect; }
    public void setProtected(boolean protect) { this.protect = protect; }

    public boolean contains(Location loc) {
        int x1 = Math.min(point1.getBlockX(), point2.getBlockX());
        int y1 = Math.min(point1.getBlockY(), point2.getBlockY());
        int z1 = Math.min(point1.getBlockZ(), point2.getBlockZ());
        int x2 = Math.max(point1.getBlockX(), point2.getBlockX());
        int y2 = Math.max(point1.getBlockY(), point2.getBlockY());
        int z2 = Math.max(point1.getBlockZ(), point2.getBlockZ());

        return loc.getBlockX() >= x1 && loc.getBlockX() <= x2 && loc.getBlockY() >= y1 && loc.getBlockY() <= y2 && loc.getBlockZ() >= z1 && loc.getBlockZ() <= z2;
    }
}
