package dev.jairusu.authenticator.Configuration;

import dev.jairusu.authenticator.Authenticator;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigFile {

   public static JavaPlugin plugin = JavaPlugin.getPlugin(Authenticator.class);

   public static String getString(String path) {
      return plugin.getConfig().getString(path);
   }

   public static Boolean getBoolean(String path) {
      return plugin.getConfig().getBoolean(path);
   }

   public static Location getLocation (String path) {
      return plugin.getConfig().getLocation(path);
   }

   public static void setLocation(String path, Location location) {
      plugin.getConfig().set(path, location);
      plugin.saveConfig();
   }

}
