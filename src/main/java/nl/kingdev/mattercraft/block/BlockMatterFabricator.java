package nl.kingdev.mattercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public class BlockMatterFabricator extends BlockMachine {

	public BlockMatterFabricator() {
		super("matter_fabricator");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return null;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		// TODO Auto-generated method stub
		return super.createTileEntity(world, state);
	}

}
