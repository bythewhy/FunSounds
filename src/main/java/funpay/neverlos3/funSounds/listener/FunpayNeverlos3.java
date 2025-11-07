package funpay.neverlos3.funSounds.listener;

import funpay.neverlos3.funSounds.FunSounds;
import funpay.neverlos3.funSounds.util.Config;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import java.util.List;
import java.util.Random;

public class FunpayNeverlos3 implements Listener {
    private final FunSounds plugin;
    private final Config config;
    private final Random random = new Random();
    private final double soundRadius = 20.0;

    public FunpayNeverlos3(FunSounds plugin, Config config) {
        this.plugin = plugin;
        this.config = config;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();
                playRandomSound(player.getLocation(), "bow_hit");
            }
        } else if (event.getDamager() instanceof Player && config.getSettingsConfig().getBoolean("sounds.enabled", true)) {
            Player player = (Player) event.getDamager();
            if (event.getCause() == EntityDamageByEntityEvent.DamageCause.ENTITY_ATTACK) {
                if (isCriticalHit(player) && isNetheriteSwordWithLore(player)) {
                    playRandomSound(player.getLocation(), "critical");
                }
            }
        }
    }

    private boolean isNetheriteSwordWithLore(Player player) {
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand.getType() != Material.NETHERITE_SWORD || !itemInHand.hasItemMeta()) {
            return false;
        }
        ItemMeta meta = itemInHand.getItemMeta();
        if (!meta.hasLore()) {
            return false;
        }
        List<String> lore = meta.getLore();
        for (String line : lore) {
            if (line.contains("Яд III") || line.contains("Детекция III") || line.contains("Окисление III") || line.contains("Вампиризм III")) {
                return true;
            }
        }
        return false;
    }

    private boolean isCriticalHit(Player player) {
        return player.getFallDistance() > 0 && !player.isOnGround() && !player.isSprinting();
    }

    private void playRandomSound(org.bukkit.Location location, String type) {
        List<String> sounds = config.getSettingsConfig().getStringList("sounds." + type + ".sounds");
        if (!sounds.isEmpty()) {
            String randomSoundName = sounds.get(random.nextInt(sounds.size()));
            Sound sound = Sound.valueOf(randomSoundName);
            float volume = (float) config.getSettingsConfig().getDouble("sounds." + type + ".volume", 10);
            float pitch = (float) config.getSettingsConfig().getDouble("sounds." + type + ".pitch", 2.0);
            for (Player nearbyPlayer : location.getWorld().getPlayers()) {
                if (nearbyPlayer.getLocation().distance(location) <= soundRadius) {
                    if (config.isSoundEnabledForPlayer(nearbyPlayer)) {
                        nearbyPlayer.playSound(location, sound, volume, pitch);
                    }
                }
            }
        }
    }
}