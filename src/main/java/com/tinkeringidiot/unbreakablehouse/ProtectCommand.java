package com.tinkeringidiot.unbreakablehouse;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ProtectCommand implements CommandExecutor {
    private ArrayList<Region> regionList;

    public ProtectCommand(ArrayList<Region> regionList) { this.regionList = regionList; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            if(player.getName().equals("tinkeringidiot")) {
                if(args.length == 1) {
                    for(Region reg: regionList) {
                        if(reg.getName().equalsIgnoreCase(args[0])) {
                            if(reg.isProtected()) {
                                reg.setProtected(false);
                                player.sendMessage(String.format("%s region is UNPROTECTED", reg.getName()));
                            }
                            else {
                                reg.setProtected(true);
                                player.sendMessage(String.format("%s region is PROTECTED", reg.getName()));
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
