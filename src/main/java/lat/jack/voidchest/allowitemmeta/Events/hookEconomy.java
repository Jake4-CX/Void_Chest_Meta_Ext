package lat.jack.voidchest.allowitemmeta.Events;

import lat.jack.voidchest.allowitemmeta.Economy.VoidChestShopPlusEconomyItemMeta;
import lat.jack.voidchest.allowitemmeta.Main;
import me.shin1gamix.voidchest.ecofacemanager.IEconomy;
import me.shin1gamix.voidchest.ecomanager.IVoidEconomy;
import me.shin1gamix.voidchest.events.EconomyHookEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class hookEconomy implements Listener {

    private final Main plugin;

    public hookEconomy(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void hookEconomyEvent(EconomyHookEvent event) {
        plugin.getLogger().warning("Attempting to hook into VoidChest as Economy");
        IVoidEconomy economy = new VoidChestShopPlusEconomyItemMeta();
        event.setEconomy((IEconomy) economy);
        plugin.getLogger().warning("Hooked into VoidChest Economy");

    }
}
