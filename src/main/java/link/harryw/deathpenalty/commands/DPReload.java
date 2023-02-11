package link.harryw.deathpenalty.commands;

import link.harryw.deathpenalty.DeathPenalty;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class DPReload implements CommandExecutor {
    private final DeathPenalty plugin;

    public DPReload(DeathPenalty plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("prefix")) + ChatColor.translateAlternateColorCodes('&', config.getString("reloadConfig")));
        return true;
    }
}
