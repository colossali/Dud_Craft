package colossali.Tools.blocks;

import java.util.Random;

import colossali.Tools.common.mod_DudCraft;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * A generic ore generator class
 * @author Colossali
 *
 */
public class GenericOreGenerator implements IWorldGenerator{ 

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) { //Decide what to do in which world
		switch(world.provider.dimensionId){
		case -1:
		    generateNether(world, random, chunkX * 16, chunkZ * 16); //Generate stuff in the nether
		    break;
		case 0:
		    generateSurface(world, random, chunkX * 16, chunkZ * 16); //Generate stuff in the Overworld
		    break;
		case 1:
		    generateEnd(world, random, chunkX * 16, chunkZ * 16); //Generate stuff in the end
		    break;
		}
		
	}

	private void generateEnd(World world, Random random, int i, int j) {
		
	}

	private void generateSurface(World world, Random random, int i, int j) {
		
		for(int k = 0; k < 10; k++) { //a loop, generated 'k' amount of veins of ore PER CHUNK. change the '10' to whatever (more = more ore)
			
			int firstBlockXCoord = i + random.nextInt(16); //chunk width DON'T CHANGE
			int firstBlockYCoord = random.nextInt(64); //Height to which ore spawns, example, diamonds stop at 35
			int firstBlockZCoord = j + random.nextInt(16); //chunk width DON'T CHANGE
			
        	(new WorldGenMinable(mod_DudCraft.GenericOreBlock.blockID, 13))/* Block ID and num. of blocks per vein */.generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord); //Generate the block in given coords.

		}
		
		//Generating more than one ore, copy and paste above and chage the variables in 'CAPITALS' and between those thingies
		/*
		  		for(int 'K' = 0; 'K' < '10'; 'K'++) { //a loop, generated 'k' amount of veins of ore PER CHUNK. change the '10' to whatever (more = more ore)
			
			int firstBlockXCoord = 'I' + random.nextInt(16);
			int firstBlockYCoord = random.nextInt('64');
			int firstBlockZCoord = 'J' + random.nextInt(16);
			
        	(new WorldGenMinable('MOD_TOOLS.GENERICOREBLOCK.BLOCKID', '13')).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);

		}*/
		
	}

	private void generateNether(World world, Random random, int i, int j) {
		
	}

}
