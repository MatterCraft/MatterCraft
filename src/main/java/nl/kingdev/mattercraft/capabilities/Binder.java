package nl.kingdev.mattercraft.capabilities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

public class Binder implements IBindable, INBTSerializable<NBTTagCompound> {
	
	private BlockPos targetPos;
	private IBlockState targetState;

	public Binder(IBlockState targetState) {
		this.targetState = targetState;
	}
	
	@Override
	public BlockPos getTargetPosition() {
		return this.targetPos;
	}

	@Override
	public IBindable setTargetPosition(BlockPos pos) {
		this.targetPos = pos;
		return this;
	}

	@Override
	public IBlockState getTargetState() {
		return this.targetState;
	}

	@Override
	public boolean canBind(BlockPos pos, IBlockState state, TileEntity te) {
		return this.targetState.getBlock() == state.getBlock();
	}

	@Override
	public boolean isBound() {
		return this.targetPos != null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		if(this.targetPos != null)
			nbt.setIntArray("TargetPos", new int[] { this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ() });
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		if(!nbt.hasKey("TargetPos"))
			return;
		int[] pos = nbt.getIntArray("TargetPos");
		this.targetPos = new BlockPos(pos[0], pos[1], pos[2]);
	}

}
