package nl.kingdev.mattercraft.util;

import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;

/**
 * 
 * @author CJMinecraft
 *
 */
public class EnergyUtils {
	
	public static int getEnergyStored(@Nullable TileEntity te, @Nullable EnumFacing side) {
		if(te == null)
			return 0;
		if(te.hasCapability(CapabilityEnergy.ENERGY, side))
			return te.getCapability(CapabilityEnergy.ENERGY, side).getEnergyStored();
		return 0;
	}
	
	public static int getMaxEnergyStored(@Nullable TileEntity te, @Nullable EnumFacing side) {
		if(te == null)
			return 0;
		if(te.hasCapability(CapabilityEnergy.ENERGY, side))
			return te.getCapability(CapabilityEnergy.ENERGY, side).getMaxEnergyStored();
		return 0;
	}
	
	public static int giveEnergy(@Nullable TileEntity te, @Nullable EnumFacing side, int energy, boolean simulate) {
		if(te == null)
			return 0;
		if(te.hasCapability(CapabilityEnergy.ENERGY, side))
			return te.getCapability(CapabilityEnergy.ENERGY, side).receiveEnergy(energy, simulate);
		return 0;
	}
	
	public static int takeEnergy(@Nullable TileEntity te, @Nullable EnumFacing side, int energy, boolean simulate) {
		if(te == null)
			return 0;
		if(te.hasCapability(CapabilityEnergy.ENERGY, side))
			return te.getCapability(CapabilityEnergy.ENERGY, side).extractEnergy(energy, simulate);
		return 0;
	}

}
