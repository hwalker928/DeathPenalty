package org.harrydev.deathpenalty;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

public class DPReload implements CommandExecutor {

    private FileConfiguration config;
    private DeathPenalty plugin;


    public DPReload(DeathPenalty plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("deathpenalty.reload")) {
            plugin.reloadConfig();
            commandSender.sendMessage(Objects.requireNonNull(config.getString("prefix")).replace("&", "§") + Objects.requireNonNull(config.getString("reloadConfig")).replace("&", "§"));
        }
        return false;
    }
}
