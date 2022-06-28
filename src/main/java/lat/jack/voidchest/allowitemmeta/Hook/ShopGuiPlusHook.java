package lat.jack.voidchest.allowitemmeta.Hook;

import lat.jack.voidchest.allowitemmeta.Main;
import net.brcdev.shopgui.ShopGuiPlusApi;
import net.brcdev.shopgui.event.ShopGUIPlusPostEnableEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ShopGuiPlusHook implements Listener {

    private Main plugin;

    public ShopGuiPlusHook(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onShopGUIPlusPostEnable(ShopGUIPlusPostEnableEvent event) {
        plugin.setShopGuiPlugin(ShopGuiPlusApi.getPlugin());
        plugin.getLogger().info("Registered ShopGUI+!");
    }
}
