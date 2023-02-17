package link.harryw.deathpenalty;

import link.harryw.deathpenalty.commands.DPReload;
import link.harryw.deathpenalty.events.RespawnEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class DeathPenalty extends JavaPlugin {

    public static Economy econ = null;
    private static final Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new RespawnEvent(this), this);
        this.getCommand("dpreload").setExecutor(new DPReload(this));
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
