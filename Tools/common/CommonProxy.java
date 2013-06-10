package colossali.Tools.common;

import java.util.EnumSet;

import colossali.Tools.client.ClientTickHandler;

import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.world.World;
/**
 * Our Common Proxy class, this handles things to do with the server
 * and passes them onto the client proxy (via inheritance)
 * Not much to see here...
 * @author Colossali
 *
 */

public class CommonProxy {
    

    public void preInit(){  
    }
	
	public void load(){		
    }

    public World getClientWorld(){
        return null;
    }
}
