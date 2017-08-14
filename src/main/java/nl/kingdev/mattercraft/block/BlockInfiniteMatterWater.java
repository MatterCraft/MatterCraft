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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (heldItem != null) {
            if (heldItem.getItem() == Items.BUCKET) {
                if (heldItem.stackSize == 1) {
                    playerIn.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
                    playerIn.swingArm(hand);
                } else {
                    playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET, heldItem.stackSize - 1));
                    playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET));
                    playerIn.swingArm(hand);
                }
            }
        }
        super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
        return true;
    }


}