package nl.kingdev.mattercraft.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

/**
 * Everything you need for energy
 * 
 * @author CJMinecraft
 *
 */
public class EnergyUtils {

	/**
	 * Get the energy stored from the given {@link TileEntity}
	 * 
	 * @param te
	 *            The {@link TileEntity} which holds the energy
	 * @param side
	 *            The side of the {@link TileEntity} for use with
	 *            {@link Capability}
	 * @return The amount of energy stored in the {@link TileEntity}
	 */
	public static int getEnergyStored(@Nullable TileEntity te, @Nullable EnumFacing side) {
		if (te == null)
			return 0;
		if (te.hasCapability(CapabilityEnergy.ENERGY, side))
			return te.getCapability(CapabilityEnergy.ENERGY, side).getEnergyStored();
		return 0;
	}

	/**
	 * Get the capacity from the given {@link TileEntity}
	 * 
	 * @param te
	 *            The {@link TileEntity} which holds the energy
	 * @param side
	 *            The side of the {@link TileEntity} for use with
	 *            {@link Capability}
	 * @return The maximum amount of energy in the {@link TileEntity}
	 */
	public static int getMaxEnergyStored(@Nullable TileEntity te, @Nullable EnumFacing side) {
		if (te == null)
			return 0;
		if (te.hasCapability(CapabilityEnergy.ENERGY, side))
			return te.getCapability(CapabilityEnergy.ENERGY, side).getMaxEnergyStored();
		return 0;
	}

	/**
	 * Give energy to the given {@link TileEntity}
	 * 
	 * @param te
	 *            The {@link TileEntity} which will receive energy
	 * @param side
	 *            The side of the {@link TileEntity} for use with
	 *            {@link Capability}
	 * @param energy
	 *            The energy to be given
	 * @param simulate
	 *            Whether or not it is a simulation. If so, no energy is
	 *            actually given
	 * @return The amount of energy which was given (or would have been given if
	 *         it is simulated)
	 */
	public static int giveEnergy(@Nullable TileEntity te, @Nullable EnumFacing side, int energy, boolean simulate) {
		if (te == null)
			return 0;
		if (te.hasCapability(CapabilityEnergy.ENERGY, side))
			return te.getCapability(CapabilityEnergy.ENERGY, side).receiveEnergy(energy, simulate);
		return 0;
	}

	/**
	 * Take energy from the given {@link TileEntity}
	 * 
	 * @param te
	 *            The {@link TileEntity} which will have extracted from
	 * @param side
	 *            The side of the {@link TileEntity} for use with
	 *            {@link Capability}
	 * @param energy
	 *            The energy to be taken
	 * @param simulate
	 *            Whether or not it is a simulation. If so, no energy is
	 *            actually taken
	 * @return The amount of energy which was taken (or would have been taken if
	 *         it is simulated)
	 */
	public static int takeEnergy(@Nullable TileEntity te, @Nullable EnumFacing side, int energy, boolean simulate) {
		if (te == null)
			return 0;
		if (te.hasCapability(CapabilityEnergy.ENERGY, side))
			return te.getCapability(CapabilityEnergy.ENERGY, side).extractEnergy(energy, simulate);
		return 0;
	}
	
	/**
	 * Gives energy to all connecting energy handlers surrounding the target
	 * block
	 * 
	 * @param world
	 *            The world to get the {@link TileEntity} from
	 * @param pos
	 *            The center position
	 * @param energy
	 *            The energy to give altogether. Will be distributed evenly
	 *            between the {@link TileEntity}s
	 * @param simulate
	 *            Whether it is a simulation or not. If so, the energy won't
	 *            actually be given
	 * @return The amount of energy given
	 */
	public static long giveEnergyAllFaces(@Nonnull World world, BlockPos pos, int energy, boolean simulate) {
		HashMap<EnumFacing, TileEntity> tiles = new HashMap<EnumFacing, TileEntity>();
		for (EnumFacing side : EnumFacing.VALUES) {
			TileEntity te = world.getTileEntity(pos.offset(side));
			if (te == null)
				continue;
			if (te.hasCapability(CapabilityEnergy.ENERGY, side))
				tiles.put(side, te);
		}
		if (tiles.size() <= 0)
			return 0;
		int energyPerSide = energy / tiles.size();
		Iterator<Entry<EnumFacing, TileEntity>> tilesIterator = tiles.entrySet().iterator();
		int energyGiven = 0;
		int extraEnergy = 0;
		while (tilesIterator.hasNext()) {
			Entry<EnumFacing, TileEntity> entry = tilesIterator.next();
			EnumFacing side = entry.getKey();
			TileEntity te = entry.getValue();
			int eg = giveEnergy(te, side, energyPerSide + extraEnergy, simulate);
			energyGiven += eg;
			if (eg < energyPerSide)
				extraEnergy = energyPerSide - eg;
			else
				extraEnergy = 0;
		}
		return energyGiven;
	}
	
	/**
	 * Takes energy from all connecting energy handlers surrounding the target
	 * block
	 * 
	 * @param world
	 *            The world to get the {@link TileEntity} from
	 * @param pos
	 *            The center position
	 * @param energy
	 *            The energy to take altogether. Will be distributed evenly
	 *            between the {@link TileEntity}s
	 * @param simulate
	 *            Whether it is a simulation or not. If so, the energy won't
	 *            actually be taken
	 * @return The amount of energy taken
	 */
	public static long takeEnergyAllFaces(@Nonnull World world, BlockPos pos, int energy, boolean simulate) {
		HashMap<EnumFacing, TileEntity> tiles = new HashMap<EnumFacing, TileEntity>();
		for (EnumFacing side : EnumFacing.VALUES) {
			TileEntity te = world.getTileEntity(pos.offset(side));
			if (te == null)
				continue;
			if (te.hasCapability(CapabilityEnergy.ENERGY, side))
				tiles.put(side, te);
		}
		if (tiles.size() <= 0)
			return 0;
		int energyPerSide = energy / tiles.size();
		Iterator<Entry<EnumFacing, TileEntity>> tilesIterator = tiles.entrySet().iterator();
		int energyTaken = 0;
		int extraEnergy = 0;
		while (tilesIterator.hasNext()) {
			Entry<EnumFacing, TileEntity> entry = tilesIterator.next();
			EnumFacing side = entry.getKey();
			TileEntity te = entry.getValue();
			int et = takeEnergy(te, side, energyPerSide + extraEnergy, simulate);
			energyTaken += et;
			if (et < energyPerSide)
				extraEnergy = energyPerSide - et;
			else
				extraEnergy = 0;
		}
		return energyTaken;
	}

}
