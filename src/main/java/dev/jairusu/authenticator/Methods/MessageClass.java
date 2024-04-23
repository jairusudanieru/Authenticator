package dev.jairusu.authenticator.Methods;

import dev.jairusu.authenticator.Configuration.MessageFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class MessageClass {

   public static Component miniMessage(String text) {
      return MiniMessage.miniMessage().deserialize(text);
   }

   public static Component loginMessage(Player player) {
      String message = MessageFile.getFileConfig().getString("message.loginMessage");
      if (message == null) return null;
      message = message.replace("%player%",player.getName());
      return miniMessage(message);
   }

   public static Component logoutMessage(Player player) {
      String message = MessageFile.getFileConfig().getString("message.logoutMessage");
      if (message == null) return null;
      message = message.replace("%player%",player.getName());
      return miniMessage(message);
   }

   public static Component reloadMessage() {
      String message = MessageFile.getFileConfig().getString("message.reloadMessage");
      if (message == null) return null;
      return miniMessage(message);
   }

}
