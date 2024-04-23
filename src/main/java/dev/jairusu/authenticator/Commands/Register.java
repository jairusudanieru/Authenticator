package dev.jairusu.authenticator.Commands;

import dev.jairusu.authenticator.Methods.MessageClass;
import dev.jairusu.authenticator.Configuration.PasswordFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Register implements CommandExecutor, TabCompleter {

   @Override
   public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
      return new ArrayList<>();
   }

   @Override
   public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage(MessageClass.miniMessage("Only players can use this command!"));
         return true;
      }

      Player player = (Player) sender;

      if (PasswordFile.getPlayerFile(player).exists()) {
         sender.sendMessage(MessageClass.miniMessage("You are already registered!"));
         return true;
      }

      if (player.hasMetadata("unlogged") && PasswordFile.getPlayerFileConfig(player).get("data.password") != null) {
         sender.sendMessage(MessageClass.miniMessage("You are already registered!"));
         return true;
      }

      if (!player.hasMetadata("unlogged")) {
         sender.sendMessage(MessageClass.miniMessage("You are already logged in!"));
         return true;
      }

      if (args.length != 2) {
         sender.sendMessage(MessageClass.miniMessage("Invalid Command Usage!"));
         return true;
      }

      if (!args[0].equals(args[1])) {
         sender.sendMessage(MessageClass.miniMessage("The password didn't match! Please try again."));
         return true;
      }

      String input = args[0];
      if (input.length() < 8) {
         sender.sendMessage(MessageClass.miniMessage("The minimum password length is 8 characters!"));
         return true;
      }

      PasswordFile.savePasswordFile(player, "data.username", player.getName());
      PasswordFile.savePasswordFile(player, "data.uuid", player.getUniqueId().toString());
      PasswordFile.savePasswordFile(player, "data.password", input);
      PasswordFile.savePasswordFile(player, "data.status", "cracked");
      player.kick(MessageClass.miniMessage("You are now Logged in! Please rejoin the game to continue."));
      return true;
   }
}
