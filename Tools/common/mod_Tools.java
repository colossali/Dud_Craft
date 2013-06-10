package colossali.Tools.common;

import java.util.EnumSet;
import java.util.logging.Level;

import colossali.Tools.items.ItemGrapplingHook;

import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


/** Tell Forge that this is the main MOD class and that it's a network mod (Multiplayer) */
@Mod(modid="colossali_Tools", name="Tools!", version="0.1.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, /* clientSide = need mod to join a server, always true serverSide = server needs mod to allow client to join, always false*/
serverPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = {"SpiderMan_C"}, packetHandler = ToolsPacketHandler.class)) /* Class to send server packets (bit arrays of info) to so that the server can read them) */

public class mod_Tools {

	/* Make some item IDs */
	public static int GrapplingHookID = 7968;

	/* Make the actual items */
	public Item GrapplingHook;

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
		}
		catch(Exception e){
			//Where to log the error and what level, then print it out in the log
			FMLLog.log(Level.SEVERE, e, "Problems in config", new Object[0]);
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
		GrapplingHook = new ItemGrapplingHook(GrapplingHookID, "grapplinghook").setFull3D().setUnlocalizedName("Grappling Hook");
	}


	/** Making sure your code works with others **/
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}
