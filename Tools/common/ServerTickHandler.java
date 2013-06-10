package colossali.Tools.common;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * Server Tick Handler. We don't want it to handle much to reduce lag/ increase performance
 * Need to process code that involves modifying player attributes and variables such as health, fall damage
 * and potion/beacon buffs. If these are done in the client tick handler they are considered to be cheats and
 * the server won't excecute them.
 * @author Colossali
 *	Check out Client Tick Handler class for more info.
 */
public class ServerTickHandler implements ITickHandler{

	private final EnumSet ticksToGet;
	
	public ServerTickHandler(EnumSet tickType){
		this.ticksToGet = tickType;
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return this.ticksToGet;
	}

	@Override
	public String getLabel() {
		return null;
	}

}
