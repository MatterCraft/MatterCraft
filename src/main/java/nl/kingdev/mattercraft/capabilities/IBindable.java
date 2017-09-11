package nl.kingdev.mattercraft.capabilities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public interface IBindable {
	
	BlockPos getTargetPosition();
	
	IBindable setTargetPosition(BlockPos pos);
	
	IBlockState getTargetState();
	
	boolean canBind(BlockPos pos, IBlockState state, TileEntity te);
	
	boolean isBound();

}
