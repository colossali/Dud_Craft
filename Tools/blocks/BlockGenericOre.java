package colossali.Tools.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGenericOre extends Block{
	
	public static String blockTexture;

	public BlockGenericOre(int itemID, Material material, String texture, StepSound stepSound) {		
		super(itemID, material);
		this.blockTexture = texture;
		this.blockHardness = 15; //hits to break the block
		this.setStepSound(stepSound);

	}
	

    /**
     * Called when the block is destroyed by an explosion.
     * Useful for allowing the block to take into account tile entities,
     * metadata, etc. when exploded, before it is removed.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @param Explosion The explosion instance affecting the block
     */
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
    {
        world.setBlockToAir(x, y, z);
        world.createExplosion(null, x, y, z, 4, true);
        onBlockDestroyedByExplosion(world, x, y, z, explosion);
    }

    //This will tell minecraft not to render any side of our cube.
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
       return true;
    }

    //And this tell it that you can see through this block, and neighbor blocks should be rendered.
    public boolean isOpaqueCube()
    {
       return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
             this.blockIcon = par1IconRegister.registerIcon("Tools:" + blockTexture);
    }
    
}
