package com.tinkeringidiot.unbreakablehouse;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.ArrayList;
import java.util.List;

public class BreakListener implements Listener {

    private ArrayList<Region> regionsList;

    public BreakListener(ArrayList<Region> regionsList) {
        this.regionsList = regionsList;
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent evt){
        for(Region reg: regionsList) {
            if(reg.isProtected()) {
                if(reg.contains(evt.getBlock().getLocation())) { evt.setCancelled(true); }
            }
        }
    }

    @EventHandler
    public void onExplodeBlock(BlockExplodeEvent evt) {
        for(Region reg: regionsList) {
            if(reg.isProtected()) {
                if(reg.contains(evt.getBlock().getLocation())) { evt.setCancelled(true); }
            }
        }
    }

    @EventHandler
    public void onBurnBlock(BlockBurnEvent evt) {
        for(Region reg: regionsList) {
            if(reg.isProtected()) {
                if(reg.contains(evt.getBlock().getLocation())) { evt.setCancelled(true); }
            }
        }
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent evt) {
        for(Region reg: regionsList) {
            if(reg.isProtected()) {
                if(reg.contains(evt.getBlock().getLocation())) { evt.setCancelled(true); }
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent evt) {
        if(evt.isCancelled()) {
            return;
        }

        List<Block> blockList = new ArrayList<Block>(evt.blockList());
        for(Block block : blockList) {
            for(Region reg: regionsList) {
                if(reg.isProtected()) {
                    if(reg.contains(block.getLocation())) { evt.setCancelled(true); }
                }
            }
        }
    }
}
