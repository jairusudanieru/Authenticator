package dev.jairusu.authenticator.Configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PasswordFile {

   public static File getPlayerFile(Player player) {
      return new File(ConfigFile.plugin.getDataFolder()  + File.separator + "Passwords", player.getName() + ".yml");
   }

   public static FileConfiguration getPlayerFileConfig(Player player) {
      File file = getPlayerFile(player);
      if (!file.exists()) {
         file.getParentFile().mkdirs();
         try {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) throw new IOException("Unable to create file!");
         } catch (IOException error) {
            throw new RuntimeException(error);
         }
      }

      return YamlConfiguration.loadConfiguration(file);
   }

   public static void savePasswordFile(Player player, String path, String value) {
      File file = getPlayerFile(player);
      FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
      try {
         fileConfiguration.set(path, value);
         fileConfiguration.save(file);
      } catch (IOException exception) {
         ConfigFile.plugin.getLogger().info("Unable to save file!");
      }
   }

   public static void reloadFile(Player player) {
      File file = getPlayerFile(player);
      YamlConfiguration.loadConfiguration(file);
   }


}
