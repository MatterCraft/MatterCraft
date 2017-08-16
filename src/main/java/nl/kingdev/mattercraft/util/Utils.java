package nl.kingdev.mattercraft.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import nl.kingdev.mattercraft.info.Reference;

public class Utils {
	
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
	
	public static IBlockState rotateBlock(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		int meta = block.getMetaFromState(state);
		if(block instanceof BlockStairs)
			return block.getStateFromMeta(++meta % 8);
		if(block instanceof BlockSlab)
			return block.getStateFromMeta((meta + 8) % 16);
		for(IProperty property : state.getProperties().keySet()) {
			if(property instanceof PropertyDirection) {
				PropertyDirection p = (PropertyDirection) property;
				EnumFacing next = EnumFacing.VALUES[(((EnumFacing)state.getValue(property)).getIndex() + 1) % EnumFacing.VALUES.length];
				while(!p.getAllowedValues().contains(next))
					next = EnumFacing.VALUES[(((EnumFacing)state.getValue(property)).getIndex() + 1) % EnumFacing.VALUES.length];
				return state.withProperty(p, next);
			}
		}
		return block.getStateFromMeta(meta);
	}

}
