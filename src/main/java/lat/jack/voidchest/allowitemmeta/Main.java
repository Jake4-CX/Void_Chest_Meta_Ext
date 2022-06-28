package lat.jack.voidchest.allowitemmeta;

import lat.jack.voidchest.allowitemmeta.Events.hookEconomy;
import lat.jack.voidchest.allowitemmeta.Hook.ShopGuiPlusHook;
import net.brcdev.shopgui.ShopGuiPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    ShopGuiPlusHook shopGuiPlusHook;
    ShopGuiPlugin shopGuiPlugin;

    @Override
    public void onEnable() {

        instance = this;

        if (!(setupShopGUIPlus())) {
            this.getLogger().warning("ShopGUIPlus not found - Disabling plugin");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.registerListeners();

    }
    
    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new hookEconomy(this), this);
    }

    //

    static {
        Main.instance = null;
    }

    public static Main getInstance() {
        return Main.instance;
    }


    // ShopGUI

    private boolean setupShopGUIPlus() {
        if (getServer().getPluginManager().getPlugin("ShopGUIPlus") != null) {
            this.shopGuiPlusHook = new ShopGuiPlusHook(this);
            Bukkit.getPluginManager().registerEvents(shopGuiPlusHook, this);

            return true;

        } else {
            Bukkit.getPluginManager().disablePlugin(this);
        }

        return false;
    }


    public ShopGuiPlugin getShopGuiPlugin() {
        return shopGuiPlugin;
    }

    public void setShopGuiPlugin(ShopGuiPlugin shopGuiPlugin) {
        this.shopGuiPlugin = shopGuiPlugin;
    }
}
