package nl.kingdev.mattercraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import nl.kingdev.mattercraft.init.ModBlocks;
import nl.kingdev.mattercraft.util.CustomEnergyStorage;

/**
 * 
 * @author CJMinecraft
 *
 */
public class TileEntityPhotonGenerator extends TileEntityBase implements ITickable {

	private CustomEnergyStorage storage;
	private BlockPos matterFabricatorPos = BlockPos.ORIGIN;

	public TileEntityPhotonGenerator() {
		this.storage = new CustomEnergyStorage(2500000, 25000, 0);
	}

	@Override
	public void update() {
		if (this.worldObj != null) {
			if (!this.worldObj.isRemote) {
				if (this.worldObj.getBlockState(this.matterFabricatorPos)
						.getBlock() == ModBlocks.matterFabricator || findMatterFabricator()) {
					//SHOULD FIRE BEAM
				}
			}
		}
	}

	private boolean findMatterFabricator() {
		for (BlockPos pos : BlockPos.getAllInBox(
				new BlockPos(this.pos.getX() - 6, this.pos.getY() - 6, this.pos.getZ() - 6),
				new BlockPos(this.pos.getX() + 6, this.pos.getY() + 6, this.pos.getZ() + 6))) {
			if(this.worldObj.getBlockState(pos).getBlock() == ModBlocks.matterFabricator) {
				this.matterFabricatorPos = pos;
				return true;
			}
		}
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.storage.readFromNBT(nbt);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		this.storage.writeToNBT(nbt);
		return super.writeToNBT(nbt);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY)
			return (T) this.storage;
		return super.getCapability(capability, facing);
	}

}
