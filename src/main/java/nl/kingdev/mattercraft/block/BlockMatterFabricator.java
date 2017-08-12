package nl.kingdev.mattercraft.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import nl.kingdev.mattercraft.tileentity.TileEntityMatterFabricator;

/**
 * 
 * @author CJMinecraft
 *
 */
public class BlockMatterFabricator extends BlockMachine {

	public BlockMatterFabricator() {
		super("matter_fabricator");
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			if(world.getTileEntity(pos) == null)
				return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ); 
			if(heldItem.getItem() instanceof ItemBucket) {
				FluidUtil.tryFillContainer(heldItem, world.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side), 1000, player, true);
				if(FluidRegistry.isUniversalBucketEnabled() && heldItem.getItem() instanceof UniversalBucket)
					if(((UniversalBucket) heldItem.getItem()).getFluid(heldItem).getFluid() == FluidRegistry.WATER)
						FluidUtil.tryEmptyContainer(heldItem, world.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side), 1000, player, true);
				else
					if(heldItem.getItem() == Items.WATER_BUCKET)
						FluidUtil.tryEmptyContainer(heldItem, world.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side), 1000, player, true);
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityMatterFabricator();
	}

}
