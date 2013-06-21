package colossali.Tools.util;

import colossali.Tools.common.mod_DudCraft;
import cpw.mods.fml.common.ICraftingHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/**
 * This class handles stuff when you craft or smelt (I.e. adds achievements, powers, whatever you want!)
 * @author Colossali
 *
 */
public class DCraftHandler implements ICraftingHandler {

	@Override
	/**
	 * Does stuff when crafting an item
	 */
	public void onCrafting(EntityPlayer player, ItemStack item,	IInventory craftMatrix) {
		if (item.itemID == mod_DudCraft.ItemHook.itemID){//Check item ID
			player.addStat(mod_DudCraft.getHook, 1);//Add achievement
		}		
		else if (item.itemID == mod_DudCraft.ItemGrapplingHook.itemID){//Ditto
			player.addStat(mod_DudCraft.getGrapple, 1);
		}
	}

	@Override
	/**
	 * Does stuff when smelting something
	 */
	public void onSmelting(EntityPlayer player, ItemStack item) {
		// TODO Auto-generated method stub

	}
}
