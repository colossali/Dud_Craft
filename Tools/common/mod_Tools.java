package colossali.Tools.common;

import java.util.EnumSet;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import colossali.Tools.blocks.BlockGenericOre;
import colossali.Tools.blocks.GenericOreGenerator;
import colossali.Tools.items.ItemGrapplingHook;
import colossali.Tools.items.ItemToolComponents;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


/** Tell Forge that this is the main MOD class and that it's a network mod (Multiplayer) */
@Mod(modid="colossali_Tools", name="Tools!", version="0.1.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, /* clientSide = need mod to join a server, always true serverSide = server needs mod to allow client to join, always false*/
serverPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = {"SpiderMan_C"}, packetHandler = ToolsPacketHandler.class)) /* Class to send server packets (bit arrays of info) to so that the server can read them) */

public class mod_Tools {

	/* Make some item IDs */
	public static int GrapplingHookID = 7968;
	public static int HookID = 7969;
	
	/* Make some block IDs */
	public static int GenericOreBlockID = 7410;

	/* Make the actual items */
	public static Item ItemGrapplingHook;
	public static Item ItemHook;
	
	/* Make the actual blocks */
	public static Block GenericOreBlock;

	/*Make a custom creative tab */
	public static CreativeTabs tabCustomTools = new CreativeTabs("tabCustomTools") { //makes a creative tab with the name "tabCustomTools"
		public ItemStack getIconItemStack() {
			return new ItemStack(mod_Tools.ItemGrapplingHook, 1, 0); //sets the icon for the tab. Check any item class to see how to use it. Also check client proxy for how to set the name
		}
	};

	/** Instance of this mod class that forge uses. Look up "Object Oriented Programming" and then "Java Singleton" **/
	@Instance("Tools!")
	public static mod_Tools instance;

	/**Says where the client and server proxies are located, look up "Proxies" **/
	@SidedProxy(clientSide = "colossali.Tools.client.ClientProxy", serverSide="colossali.Tools.common.CommonProxy")
	public static CommonProxy proxy;


	/** Before Loading the actual Mod, set configuration files and whatnot **/
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// you will be able to find the config file in .minecraft/configs/ and it will be named MODNAME.cfg
		// here our Configuration has been instantiated, and saved under the variable name "config"
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		try{
			//load the config file
			config.load();

			//set item and such values, you can do almost anything, names, vales, etc. Doesn't have to be IDs
			GrapplingHookID = config.getItem("Moving Tools", "Grappling Hook Item", 7968).getInt();
			HookID = config.getItem("Moving Tools", "Hook Item", 7969).getInt();
			GenericOreBlockID = config.getBlock("Tool Ores", "Generic Ore", 7410).getInt();

		}
		catch(Exception e){
			//Where to log the error and what level, then print it out in the log
			FMLLog.log(Level.SEVERE, e, "Tools: Problems loading the config!", new Object[0]);
			FMLLog.severe(e.getMessage(), new Object[0]);
		}
		finally{
			//save the config file
			config.save();
		}
	}

	/** Loading up your mod Items, Recipes, Entities, Spawning, etc
	 *  This is the main method we'll be using here
	 **/
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.load(); //load up our proxies
		TickRegistry.registerTickHandler(new ServerTickHandler(EnumSet.of(TickType.CLIENT)), Side.SERVER); //Server tick can be loaded here

		//Make items
		ItemGrapplingHook = new ItemGrapplingHook(GrapplingHookID, "grapplinghook").setFull3D().setUnlocalizedName("Grappling Hook");
		ItemHook = new ItemToolComponents(HookID, "hook").setUnlocalizedName("Hook");
		
		//Make blocks
		GenericOreBlock = new BlockGenericOre(GenericOreBlockID, Material.iron, "redore", Block.soundAnvilFootstep).setUnlocalizedName("Red Ore");

		//Making Custom Recipes

		ModLoader.addRecipe(new ItemStack(ItemHook, 1), new Object[]{ //What to give and how much of, then an array of items to craft it
			"i i", " i ", "lsl", 'i', Item.ingotIron, 'l', Item.leather, 's', Item.silk //imagine the "space between these" as rows in crafting bench. Spaces mean empty
		});
		
		ModLoader.addRecipe(new ItemStack(ItemGrapplingHook, 1), new Object[]{
			"ihi", "iri", "iss", 'i', Item.ingotIron, 'h', ItemHook, 'r', Item.redstone, 's', Item.stick
		});
		
		//Shapeless recipe, useful for stuff like making ammo or crappy items to make better items (I use to make bullets in SHIM)
		//ModLoader.addShapelessRecipe(new ItemStack(Block.sponge, 1), new ItemStack(Item.dyePowder, 1, 0), Item.reed);
		//If you want to use more than one item in a slot in the crafting bench, use > new ItemStack(Yadda yadda) < instead of Item/Block, whatever.
		
		//Generate ores!
		GameRegistry.registerWorldGenerator(new GenericOreGenerator()); //easy!
	}


	/** Making sure your code works with others **/
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
