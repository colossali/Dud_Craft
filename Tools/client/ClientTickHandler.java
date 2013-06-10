package colossali.Tools.client;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * Ahh, the client tick handler. Lovely!
 * This class is awesome! 
 * Here you can pretty much do anything in terms of game logic related to the player.
 * Bear in mind, this stuff is handled by the client! Server doesn't see it.
 * Therefore if you make your player fly when wearing a suit or something, the server
 * will think the player is cheating and going to kick him/her.
 * @author Mama
 *
 */
public class ClientTickHandler implements ITickHandler{
	
    private final EnumSet ticksToGet;
    
    /**
     * Constructor to set the type of tick used (Render or game)
     * @param set
     */
    public ClientTickHandler(EnumSet set)
    {
        this.ticksToGet = set;
    }

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		//Stuff to do when a tick is fired
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		//Stuff to do when a tick ends
	}

	@Override
	public EnumSet<TickType> ticks() {
		//The type of tick
        return this.ticksToGet;
	}

	@Override
	public String getLabel() {
		//Label for this tick handler
		return "Tools Client Tick";
	}

}
