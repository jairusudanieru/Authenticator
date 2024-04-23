package dev.jairusu.authenticator.Methods;

import com.github.games647.fastlogin.core.hooks.AuthPlugin;
import org.bukkit.entity.Player;

public class AuthPluginHook implements AuthPlugin<Player> {

   @Override
   public boolean forceLogin(Player player) {
      return false;
   }

   @Override
   public boolean forceRegister(Player player, String s) {
      return false;
   }

   @Override
   public boolean isRegistered(String s) throws Exception {
      return false;
   }
}
