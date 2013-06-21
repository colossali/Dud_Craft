package colossali.Tools.items;

import colossali.Tools.common.mod_DudCraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemToolComponents extends Item{

	private String itemTexture;
	
	/**
	 * Construct out "generic" item
	 * @param itemID
	 * @param texture name of item
	 */
	
	public ItemToolComponents(int itemID, String texture) {
		super(itemID); //register item ID with instance of item
		setMaxStackSize(64); //as it says
		setCreativeTab(mod_DudCraft.tabCustomTools); //also self explanatory
		this.itemTexture = texture; //pass texture name onto icon renderer method
	}
	
	/**
	 * Register an icon for our items
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon){
		
		itemIcon = icon.registerIcon("Tools:" + itemTexture);
	}

}
