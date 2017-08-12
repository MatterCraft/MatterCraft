package nl.kingdev.mattercraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPhotonGenerator extends BlockMachine {

	public BlockPhotonGenerator(String unlocalizedName) {
		super("photon_generator");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return null;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return super.createTileEntity(world, state);
	}

}
