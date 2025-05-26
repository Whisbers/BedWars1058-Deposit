package me.parsa.depositplugin;

import me.parsa.depositapi.DepositApi;
import me.parsa.depositplugin.Configs.ArenasConfig;
import me.parsa.depositplugin.Listeners.GameStartListener;
import me.parsa.depositplugin.Manager.MainConfig;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class API implements DepositApi {

    ConfigManager configManager = new ConfigManager() {
        @Override
        public List<String> getArenaChests(World arena) {
            return MainConfig.getArenaChests(arena);
        }

        @Override
        public FileConfiguration getArenasConfig() {
            return ArenasConfig.get();
        }

    };

    HologramUtil hologramUtil = new HologramUtil() {
        @Override
        public void reloadHolograms(Player player) {
            new GameStartListener(DepositPlugin.plugin, ArenasConfig.get()).createHolograms(player);
        }

        @Override
        public boolean doesHologramsWorked() {
            GameStartListener gameStartListener = new GameStartListener(DepositPlugin.plugin, ArenasConfig.get());
            return gameStartListener.succesGameState && gameStartListener.successGameAssgin || gameStartListener.isReloaded;
        }

        @Override
        public void deleteHolograms(Player player) {
            GameStartListener gameStartListener = new GameStartListener(DepositPlugin.plugin, ArenasConfig.get());
            DepositPlugin.debug("Trying to delete hd for " + player.getName());
            gameStartListener.deleteHolograms(player);
        }
    };

    @Override
    public ConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public HologramUtil getHologramUtil() {
        return hologramUtil;
    }

}