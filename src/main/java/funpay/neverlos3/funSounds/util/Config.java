package funpay.neverlos3.funSounds.util;

import funpay.neverlos3.funSounds.FunSounds;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.Arrays;

public class Config {
    private final FunSounds plugin;
    private FileConfiguration config;

    public Config(FunSounds plugin) {
        this.plugin = plugin;
        this.plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
        main();
    }

    private void main() {
        config.options().copyDefaults(true);
        config.addDefault("messages.no-console", "&cКоманда только для игроков!");
        config.addDefault("messages.usage", "&cИспользование: /funsounds toggle");
        config.addDefault("messages.sounds-enabled", "&aЗвуки включены!");
        config.addDefault("messages.sounds-disabled", "&cЗвуки отключены!");
        config.addDefault("sounds.enabled", true);
        config.addDefault("sounds.bow_hit.sounds", Arrays.asList("ENTITY_ARROW_HIT_PLAYER", "ENTITY_EXPERIENCE_ORB_PICKUP"));
        config.addDefault("sounds.bow_hit.volume", 10.0);
        config.addDefault("sounds.bow_hit.pitch", 2.0);
        config.addDefault("sounds.critical.sounds", Arrays.asList("ENTITY_PLAYER_HURT", "ENTITY_GHAST_SCREAM"));
        config.addDefault("sounds.critical.volume", 10.0);
        config.addDefault("sounds.critical.pitch", 2.0);
        plugin.saveConfig();
    }

    public FileConfiguration getMessages() {
        return config;
    }

    public FileConfiguration getSettingsConfig() {
        return config;
    }

    public boolean isSoundEnabledForPlayer(Player player) {
        return config.getBoolean("players." + player.getUniqueId() + ".soundsEnabled", true);
    }

    public void setSoundEnabledForPlayer(Player player, boolean enabled) {
        config.set("players." + player.getUniqueId() + ".soundsEnabled", enabled);
        plugin.saveConfig();
    }
}