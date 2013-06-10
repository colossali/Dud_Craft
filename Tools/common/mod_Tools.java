package colossali.Tools.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;


/** Tell Forge that this is the main MOD class and that it's a network mod (Multiplayer) */
@Mod(modid="colossali_Tools", name="Tools!", version="0.1.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, /* clientSide = need mod to join a server, always true serverSide = server needs mod to allow client to join, always false*/
		serverPacketHandlerSpec = @NetworkMod.SidedPacketHandler(channels = {"SpiderMan_C"}, packetHandler = ToolsPacketHandler.class)) /* Class to send server packets (bit arrays of info) to so that the server can read them) */

public class mod_Tools {
	
	/** Instance of this mod class that forge uses. Look up "Object Oriented Programming" and then "Java Singleton" **/
	@Instance("Tools!")
    public static mod_Tools instance;
    
    /**Says where the client and server proxies are located, look up "Proxies" **/
    @SidedProxy(clientSide = "colossali.Tools.client.ClientProxy", serverSide="colossali.Tools.common.CommonProxy")
    public static CommonProxy proxy;
    
    
    /** Before Loading the actual Mod, set configuration files and whatnot **/
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
    	//config code goes here
    }
    
    /** Loading up your mod Items, Recipes, Entities, Spawning, etc
     *  This is the main method we'll be using here
     **/
    @Init
    public void load(FMLInitializationEvent event) {
            proxy.load(); //load up our proxies
    }

    
    /** Making sure your code works with others **/
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
            // Stub Method
    }
}
