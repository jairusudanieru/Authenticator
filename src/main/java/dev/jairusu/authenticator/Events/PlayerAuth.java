package dev.jairusu.authenticator.Events;

import com.github.games647.fastlogin.core.PremiumStatus;
import dev.jairusu.authenticator.Methods.MessageClass;
import dev.jairusu.authenticator.Configuration.PasswordFile;
import dev.jairusu.authenticator.Authenticator;
import dev.jairusu.authenticator.Configuration.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.Objects;

public class PlayerAuth implements Listener {

   @EventHandler
   public void onPlayerJoin(PlayerJoinEvent event) {
      event.joinMessage(null);
      Player player = event.getPlayer();
      final BukkitTask[] task = new BukkitTask[1];
      Bukkit.getScheduler().runTaskLater(ConfigFile.plugin, ()-> task[0] = Bukkit.getScheduler().runTaskTimer(ConfigFile.plugin, () -> {
         if (!Authenticator.fastLoginBukkit.getStatus(player.getUniqueId()).equals(PremiumStatus.UNKNOWN)) {
            this.authenticatePlayer(player);
            task[0].cancel();
         }
      }, 1L, 1L), 1L);
   }

   private void authenticatePlayer(Player player) {
      Location spawnLocation = ConfigFile.getLocation("location.spawnLocation");
      if (ConfigFile.getBoolean("config.teleportOnSpawn") && spawnLocation != null) {
         player.teleport(spawnLocation);
      }
      if (Authenticator.fastLoginBukkit.getStatus(player.getUniqueId()).equals(PremiumStatus.CRACKED)) {
         if (!PasswordFile.getPlayerFile(player).exists() || PasswordFile.getPlayerFileConfig(player).get("data.password") == null) {
            this.registerPlayer(player);
            return;
         }

         player.setMetadata("unlogged", new FixedMetadataValue(ConfigFile.plugin, "unlogged"));
         player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, PotionEffect.INFINITE_DURATION, 1, true, true));
         player.setGameMode(GameMode.SPECTATOR);
         player.sendMessage(MessageClass.miniMessage("<gray>/login (password)"));
      } else {
         if (!PasswordFile.getPlayerFile(player).exists() || PasswordFile.getPlayerFileConfig(player).get("data.username") == null) {
            this.autoRegisterPlayer(player);
         }
      }
   }

   private void registerPlayer(Player player) {
      player.setMetadata("unlogged", new FixedMetadataValue(ConfigFile.plugin, "unlogged"));
      player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, PotionEffect.INFINITE_DURATION, 1, true, true));
      player.setGameMode(GameMode.SPECTATOR);
      player.sendMessage(MessageClass.miniMessage("<gray>/register (password) (confirmPassword)"));
   }

   private void autoRegisterPlayer(Player player) {
      PasswordFile.savePasswordFile(player, "data.username", player.getName());
      PasswordFile.savePasswordFile(player, "data.uuid", player.getUniqueId().toString());
      PasswordFile.savePasswordFile(player, "data.password", null);
      PasswordFile.savePasswordFile(player, "data.status", "premium");
   }

}
