package lat.jack.voidchest.allowitemmeta.Economy;

import lat.jack.voidchest.allowitemmeta.Main;
import me.shin1gamix.voidchest.configuration.FileManager;
import me.shin1gamix.voidchest.ecomanager.customeco.copy.AVoidChestEconomy;
import me.shin1gamix.voidchest.utilities.Utils;
import net.brcdev.shopgui.exception.shop.ShopsNotLoadedException;
import org.bukkit.inventory.ItemStack;

import net.brcdev.shopgui.shop.ShopItem;
import net.brcdev.shopgui.shop.Shop;

import java.util.Iterator;

public class VoidChestShopPlusEconomyItemMeta extends AVoidChestEconomy {

    public VoidChestShopPlusEconomyItemMeta() {
        super(Main.getInstance().getDescription());
    }

    @Override
    public double getProfit(ItemStack itemStack, final int n) {
        if (itemStack == null) {
            return 0.0;
        }
        itemStack = itemStack.clone();
        itemStack.setAmount(1);
        if (FileManager.getInstance().getOptions().getFileConfiguration().getBoolean("Sell.ignore-item-meta", false)) {
            itemStack = Utils.resetItemMeta(itemStack);
        }
        double max = 0.0;
        final Iterator<Shop> iterator;


        try {
            iterator = Main.getInstance().getShopGuiPlugin().getShopManager().getShops().iterator();
        } catch (ShopsNotLoadedException e) {
            e.printStackTrace();
            return 0.00;
        }


        while (iterator.hasNext()) {
            for (final ShopItem shopItem : iterator.next().getShopItems()) {

                if (!(itemStack.isSimilar(shopItem.getItem()))) {
                    // If the item does not have the same meta data - skip
                    continue;
                }

                /*
                if (!itemStack.isSimilar(Utils.resetItemMeta(shopItem.getItem()))) {
                    continue;
                }
                */

                final double sellPriceForAmount = shopItem.getSellPriceForAmount(n);
                if (sellPriceForAmount <= 0.0) {
                    continue;
                }
                if (sellPriceForAmount <= max) {
                    continue;
                }
                max = Math.max(sellPriceForAmount, 0.0);
            }
        }
        return max;
    }
}
