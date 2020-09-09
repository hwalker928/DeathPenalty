package org.harrydev.deathpenalty;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class DeathPenalty extends JavaPlugin {

    public static Economy econ = null;
    private static final Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
        if(getConfig().getBoolean("sponsorMessage")) {
            Bukkit.getConsoleSender().sendMessage("DeathPenalty has loaded.\n\n" + ChatColor.WHITE + "" + ChatColor.BOLD + "===========================\n" + ChatColor.RESET + "" + ChatColor.AQUA + "Sponsored by " + ChatColor.GREEN + ChatColor.BOLD + "AccurateNode" + ChatColor.RESET + ChatColor.AQUA + "!\n" + ChatColor.GOLD + "Use code ENDERTWEAKS for 15% off your first month of hosting!\n" + ChatColor.WHITE + "" + ChatColor.BOLD + "===========================\n\n");
        }
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

}
