package colossali.Tools.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.WorldServer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * Our Packet Handler class.
 * Gets info from client and tells the server what to do with it.
 * @author Colossali
 *
 */
public class ToolsPacketHandler implements IPacketHandler { //Look up "Java interfaces"	
	/**
	 * This method handles our byte arrays of data
	 */
	
	@Override //Override means that we take the base method and make our own "implementation" of it
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {

		if (packet.data != null)
		{
            EntityPlayer entPlayer = (EntityPlayer)player; //Player, duh, make sure it's not EntityPlayerClient
            ByteArrayDataInput arrayInput = ByteStreams.newDataInput(packet.data); //The stream of data (how nice)
            WorldServer world; // The server instance (not made an instance yet) of the world.
    
		}
		
	}

}
