package me.parsa.depositplugin.Manager;

import me.parsa.depositplugin.Configs.ArenasConfig;
import me.parsa.depositplugin.DepositPlugin;
import org.bukkit.World;

import java.util.List;

public class MainConfig {
    public static List<String> getArenaChests(World arena) {
        DepositPlugin.debug("Ran a method for api : getArenaChests");
        String path = "worlds." + arena.getName() + ".chestLocations";
        return ArenasConfig.get().getStringList(path);
    }
}