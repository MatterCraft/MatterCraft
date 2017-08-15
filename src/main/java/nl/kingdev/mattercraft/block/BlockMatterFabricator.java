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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import nl.kingdev.mattercraft.tileentity.TileEntityMatterFabricator;
import nl.kingdev.mattercraft.util.Utils;

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
		if(!world.isRemote)
			return FluidUtil.interactWithFluidHandler(heldItem, world.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side), player);
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityMatterFabricator();
	}

}
