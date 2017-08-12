package nl.kingdev.mattercraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.kingdev.mattercraft.tileentity.TileEntityPhotonGenerator;

/**
 * 
 * @author CJMinecraft
 *
 */
public class BlockPhotonGenerator extends BlockMachine {

	public BlockPhotonGenerator(String unlocalizedName) {
		super("photon_generator");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityPhotonGenerator();
	}

}
