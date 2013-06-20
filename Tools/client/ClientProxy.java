package colossali.Tools.client;

import java.util.EnumSet;
import java.util.Map;

import net.minecraft.world.World;

import colossali.Tools.common.CommonProxy;
import colossali.Tools.common.mod_Tools;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/** Our Client Proxy Class where we do stuff that only the player sees (server doesn't)
 * 	What we'll do here is render entities, set the names of items and other things we don't want to
 *  burden the server with (otherwise there'd be tons of lag)
 *  
 *  NEVER DO CLIENT THINGS ON THE SERVER, YOUR MOD WON'T WORK ONLINE!!!
 *  
 * 	@author Colossali *
 */
public class ClientProxy extends CommonProxy{
	/* Look up "Inheritance Java" We're using our CommonProxy class as a base 
	 * so we don't have to write out the code again, this is like "Cherry on top" 
	 * extra stuff */

	/**
	 * Client stuff to do when preInitiating 
	 * (used to be needed to load files, pictures and file paths - Minecraft 1.4.7 and lower)
	 */
	public void preInit(){
		//Example, MinecraftForgeClient.preloadTexture("filepath");
	}

	/**
	 * Called when the mod loads. Initiate Client tick handlers (render, tick, etc.)
	 * Load renderers and item/entity names
	 * ALL HANDLED ON THE CLIENT!!! WON'T BE SEEN BY SERVER!!!!!!!!
	 */
	public void load(){
		//Loading up our tick handlers
		TickRegistry.registerTickHandler(new ClientTickHandler(EnumSet.of(TickType.CLIENT)), Side.CLIENT);

		//Load up our names
		LanguageRegistry.addName(mod_Tools.GrapplingHookID, "\u00a78Grappling Hook"); //the \u00a7 is a § and 8 is colour gray search "minecraft formatting codes" but it can't be compiles so we have to use the "escape sequence"
		LanguageRegistry.addName(mod_Tools.HookID, "\u00a78Hook");
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabCustomTools", "en_US", "Tools Mod"); //set name of tabs. first is itemGroup.+name of your tab, then en_US, then the actual name you want to display
		//Load up our renderes
		//RenderingRegistry.registerEntityRenderingHandler(EntityWebBall.class, new RenderWebBall(mod_spiderman.WebBallItem));
		//MinecraftForgeClient.registerItemRenderer(mod_spiderman.WebShooter.itemID,((IItemRenderer) new RenderWebShooter()));
	}

	/**
	 * Another way to render entities 
	 * (The other is more straight forward, just showing that this exists)
	 * @param var1 - mouse over the "Map" if in eclipse and read
	 */
	public void addRenderer(Map var1){
		//var1.put(RadioactiveSpider.class, new RenderSpider());
	}

	/**
	 * Get the client world
	 */
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

}
