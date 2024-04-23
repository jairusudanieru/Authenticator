package dev.jairusu.authenticator.Commands;

import dev.jairusu.authenticator.Configuration.ConfigFile;
import dev.jairusu.authenticator.Configuration.MessageFile;
import dev.jairusu.authenticator.Methods.MessageClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Reload implements CommandExecutor, TabCompleter {

   @Override
   public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
      return new ArrayList<>();
   }

   @Override
   public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
      if (args.length != 0) {
         sender.sendMessage(MessageClass.miniMessage("Invalid command Usage!"));
         return true;
      }

      ConfigFile.plugin.reloadConfig();
      MessageFile.reloadFile();
      sender.sendMessage("Plugin successfully reloaded!");
      return true;
   }

}
