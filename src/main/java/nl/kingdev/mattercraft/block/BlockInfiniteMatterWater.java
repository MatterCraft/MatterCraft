package nl.kingdev.mattercraft.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import nl.kingdev.mattercraft.tileentity.TileEntityInfiniteMatterWater;

import javax.annotation.Nullable;

public class BlockInfiniteMatterWater extends BlockMachine {

    public BlockInfiniteMatterWater() {
        super("infinite_water");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityInfiniteMatterWater();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (heldItem != null) {
        	return FluidUtil.interactWithFluidHandler(heldItem, world.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side), player);
//            if (heldItem.getItem() == Items.BUCKET) {
//                if (heldItem.stackSize == 1) {
//                    player.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
//                    player.swingArm(hand);
//                } else {
//                    player.setHeldItem(hand, new ItemStack(Items.BUCKET, heldItem.stackSize - 1));
//                    player.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET));
//                    player.swingArm(hand);
//                }
//            }
        }
        super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
        return false;
    }


}