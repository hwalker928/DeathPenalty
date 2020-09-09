package org.harrydev.deathpenalty;

import me.clip.placeholderapi.PlaceholderAPI;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class DeathEvent implements Listener {

    private final Economy econ;
    private final FileConfiguration config;


    public DeathEvent(DeathPenalty plugin) {

        this.config = plugin.getConfig();
        this.econ = (Economy) DeathPenalty.econ;
    }

    @EventHandler
    public void deathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        assert p != null;
        double currentBalance = econ.getBalance(p.getName());
        //p.sendMessage(Objects.requireNonNull(config.getString("deathMessage")).replace("&", "§").replace("%amount%", "0"));
        if(Objects.requireNonNull(config.getString("lossType")).equalsIgnoreCase("percentage")) {
            int percent = config.getInt("percentageLost");
            double moneyLost = currentBalance * percent;
            double k = (double)(currentBalance*(percent/100.0f));
            EconomyResponse r = econ.withdrawPlayer(p, k);
            //p.sendMessage(String.valueOf(k));
            String old1 = "%vault_eco_balance_formatted%";
            String newlevel = PlaceholderAPI.setPlaceholders(p, old1);
            p.sendMessage(Objects.requireNonNull(config.getString("deathMessage")).replace("&", "§").replace("%amount%", String.valueOf(newlevel)));
        }
        else if(Objects.requireNonNull(config.getString("lossType")).equalsIgnoreCase("fixed")) {
            int percent = config.getInt("fixedLost");
            EconomyResponse r = econ.withdrawPlayer(p, percent);
            //p.sendMessage(String.valueOf(k));
            String old1 = "%vault_eco_balance_formatted%";
            String newlevel = PlaceholderAPI.setPlaceholders(p, old1);
            p.sendMessage(Objects.requireNonNull(config.getString("deathMessage")).replace("&", "§").replace("%amount%", String.valueOf(newlevel)));
        }
    }

}
