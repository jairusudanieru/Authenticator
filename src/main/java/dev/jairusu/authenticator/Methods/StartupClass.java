package dev.jairusu.authenticator.Methods;

import dev.jairusu.authenticator.Commands.*;
import dev.jairusu.authenticator.Configuration.ConfigFile;
import dev.jairusu.authenticator.Events.PlayerAuth;
import dev.jairusu.authenticator.Events.PlayerMove;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

import java.util.Objects;

public class StartupClass {

   public static void registerCommands() {
      registerCommand("changepassword", new ChangePassword(), new ChangePassword());
      registerCommand("login", new Login(), new Login());
      registerCommand("register", new Register(), new Register());
      registerCommand("authreload", new Reload(), new Reload());
      registerCommand("setspawn", new SetSpawn(), new SetSpawn());
      registerCommand("spawn", new Spawn(), new Spawn());
   }

   public static void registerEvents() {
      Bukkit.getPluginManager().registerEvents(new PlayerAuth(), ConfigFile.plugin);
      Bukkit.getPluginManager().registerEvents(new PlayerMove(), ConfigFile.plugin);
   }

   private static void registerCommand(String command, CommandExecutor executor, TabCompleter completer) {
      PluginCommand pluginCommand = Objects.requireNonNull(Bukkit.getPluginCommand(command));
      pluginCommand.setExecutor(executor);
      pluginCommand.setTabCompleter(completer);
   }

}