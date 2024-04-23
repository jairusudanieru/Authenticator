package dev.jairusu.authenticator;

import com.github.games647.fastlogin.bukkit.FastLoginBukkit;
import dev.jairusu.authenticator.Methods.AuthPluginHook;
import dev.jairusu.authenticator.Configuration.MessageFile;
import dev.jairusu.authenticator.Methods.StartupClass;
import org.bukkit.plugin.java.JavaPlugin;

public final class Authenticator extends JavaPlugin {

    public static FastLoginBukkit fastLoginBukkit = JavaPlugin.getPlugin(FastLoginBukkit.class);

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        StartupClass.registerCommands();
        StartupClass.registerEvents();
        MessageFile.checkConfigFile();
        fastLoginBukkit.getCore().setAuthPluginHook(new AuthPluginHook());
        this.getLogger().info("Authentication Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Authentication Plugin has been disabled!");
    }
}
