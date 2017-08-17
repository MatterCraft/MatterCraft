package nl.kingdev.mattercraft.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nl.kingdev.mattercraft.info.Reference;

public class Utils {
	
	public static final int[] SIDE_LEFT = { 4, 5, 5, 4, 2, 3 };
	public static final int[] SIDE_OPPOSITE = { 1, 0, 3, 2, 5, 4 };
	
    private static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = LogManager.getFormatterLogger(Reference.mod_id);
        }
        return logger;
    }
    
	/**
	 * Converts the given {@link ItemStack} to a nice looking string
	 * 
	 * @param stack
	 *            The {@link ItemStack} to convert to a string
	 * @return The {@link ItemStack} as a string
	 */
	public static String stackToString(ItemStack stack) {
		String string = (stack.stackSize / 64 != 0 ? stack.stackSize / 64 + "x64" : "x")
				+ ((stack.stackSize - ((stack.stackSize / 64) * 64)) != 0
						? " + " + (stack.stackSize - ((stack.stackSize / 64) * 64)) : "")
				+ " " + stack.getDisplayName();
		if ((stack.stackSize / 64) == 0)
			string = string.replace(" + ", "");
		return string;
	}
	
	public static IBlockState rotateBlock(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		int meta = block.getMetaFromState(state);
		if(block instanceof BlockBed || block instanceof BlockRailBase)
			return block.getStateFromMeta(meta);
		if(block instanceof BlockStairs)
			return block.getStateFromMeta(meta++ % 8);
		if(block instanceof BlockSlab)
			return block.getStateFromMeta((meta + 8) % 16);
		if(block instanceof BlockDirectional || block instanceof BlockDispenser)
			return state.cycleProperty(BlockDirectional.FACING);
		if(block instanceof BlockChest) {
			BlockPos offset;
			for(EnumFacing side : EnumFacing.HORIZONTALS) {
				offset = pos.offset(side);
				Block offsetBlock = world.getBlockState(offset).getBlock();
				if(block == offsetBlock || (block == null && offsetBlock == null) || block.equals(offsetBlock) || block.isAssociatedBlock(offsetBlock)) {
					world.setBlockState(offset, block.getStateFromMeta(SIDE_OPPOSITE[meta]), 1);
					return block.getStateFromMeta(SIDE_OPPOSITE[meta]); 
				}
			}
			return block.getStateFromMeta(SIDE_LEFT[block.getMetaFromState(state)]);
		}
		if(block instanceof BlockHorizontal || block instanceof BlockFurnace || block instanceof BlockEnderChest || block instanceof BlockDoor || block instanceof BlockTrapDoor || block instanceof BlockLadder)
			return state.cycleProperty(BlockHorizontal.FACING);
//		for(IProperty property : state.getProperties().keySet()) {
//			if(property instanceof PropertyDirection) {
//				PropertyDirection p = (PropertyDirection) property;
//				EnumFacing next = EnumFacing.VALUES[(((EnumFacing)state.getValue(property)).getIndex() + 1) % EnumFacing.VALUES.length];
//				return state.withProperty(p, next);
//			}
//		}
		return block.getStateFromMeta(meta);
	}

}
