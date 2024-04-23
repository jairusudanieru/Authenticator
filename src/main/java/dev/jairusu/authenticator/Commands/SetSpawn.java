package dev.jairusu.authenticator.Commands;

import dev.jairusu.authenticator.Configuration.ConfigFile;
import dev.jairusu.authenticator.Methods.MessageClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SetSpawn implements CommandExecutor, TabCompleter {

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

      if (!sender.isOp()) {
         sender.sendMessage(MessageClass.miniMessage("You don't have the permission to use this command!"));
         return true;
      }

      if (args.length != 0) {
         sender.sendMessage(MessageClass.miniMessage("Invalid command Usage!"));
         return true;
      }

      Block block = player.getLocation().getBlock().getRelative(0, -1, 0);
      Material material = block.getType();
      if (material.equals(Material.WATER) || material.equals(Material.LAVA) || material.equals(Material.AIR)) {
         sender.sendMessage(MessageClass.miniMessage("This spawn location is unsafe!"));
         return true;
      }

      Location location = player.getLocation();
      ConfigFile.setLocation("location.spawnLocation", location);
      player.sendMessage(MessageClass.miniMessage("Spawn location successfully set!"));
      return true;
   }
}
