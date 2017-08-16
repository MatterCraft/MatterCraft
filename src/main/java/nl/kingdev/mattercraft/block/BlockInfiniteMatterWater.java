package nl.kingdev.mattercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.common.Mod;
import nl.kingdev.mattercraft.init.ModBlocks;
import nl.kingdev.mattercraft.tileentity.TileEntityInfiniteMatterWater;

import javax.annotation.Nullable;

//@Mod.EventBusSubscriber
public class BlockInfiniteMatterWater extends BlockMachine {

	public BlockInfiniteMatterWater() {
		super("infinite_water");
	}

	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isVisuallyOpaque() {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState currentState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		IBlockState actualState = blockAccess.getBlockState(pos.offset(side));
		Block block = actualState.getBlock();

		if (this == ModBlocks.infinteWater) {
			if (currentState != actualState) {
				return true;
			}

			if (block == this) {
				return false;
			}
		}

		return block == this ? false : super.shouldSideBeRendered(currentState, blockAccess, pos, side);
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityInfiniteMatterWater();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote)
			return FluidUtil.interactWithFluidHandler(heldItem,
					world.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side),
					player);
		return false;
	}

}