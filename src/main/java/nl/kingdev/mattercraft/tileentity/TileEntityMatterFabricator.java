package nl.kingdev.mattercraft.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import nl.kingdev.mattercraft.block.BlockMatterFabricator;
import nl.kingdev.mattercraft.init.ModItems;
import nl.kingdev.mattercraft.util.Utils;

/**
 * 
 * @author CJMinecraft
 *
 */
public class TileEntityMatterFabricator extends TileEntityBase implements ITickable {

	public boolean acceptingPhotons = false;
	public int photons = 0;
	private ItemStackHandler handler;
	private FluidTank tank;

	private int errors;

	public TileEntityMatterFabricator() {
		this.handler = new ItemStackHandler(1) {
			@Override
			protected int getStackLimit(int slot, ItemStack stack) {
				return 1;
			}
			
			@Override
			public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
				return stack;
			}
			
			@Override
			public ItemStack extractItem(int slot, int amount, boolean simulate) {
				IBlockState state = worldObj.getBlockState(pos);
				worldObj.notifyBlockUpdate(pos, state, state, 3);
				return super.extractItem(slot, amount, simulate);
			}
		};
		this.tank = new FluidTank(FluidRegistry.WATER, 0, 16000);
		this.tank.setCanDrain(false);
		this.tank.setCanFill(true);
	}

	@Override
	public void update() {
		if (this.worldObj != null) {
			if (!this.worldObj.isRemote) {
				if ((this.worldObj.isBlockPowered(this.pos)
						|| this.worldObj.isBlockIndirectlyGettingPowered(this.pos) > 0)
						&& this.handler.getStackInSlot(0) == null && !this.acceptingPhotons) {
					this.acceptingPhotons = true;
					this.errors = 0;
					IBlockState state = this.worldObj.getBlockState(this.pos);
					this.worldObj.notifyBlockUpdate(this.pos, state, state, 3);
					this.markDirty();
				}

				if (this.acceptingPhotons && this.tank.getFluidAmount() < 1000) {
					this.errors++;
					this.markDirty();
				}

				if (this.acceptingPhotons && this.photons != 0 && this.photons % 20 == 0
						&& this.tank.getFluidAmount() >= 1000) {
					this.tank.drainInternal(1000, true);
					this.markDirty();
				}

				if (this.photons >= 30000) { // 25 * 60 * 20
					this.handler.setStackInSlot(0, new ItemStack(ModItems.matter));
					IBlockState state = this.worldObj.getBlockState(this.pos);
					this.worldObj.notifyBlockUpdate(this.pos, state, state, 3);
					this.acceptingPhotons = false;
					this.photons = 0;
					this.markDirty();
				}

				if (this.acceptingPhotons && this.errors >= 5) {
					this.worldObj.createExplosion(null, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 25, true);
					this.acceptingPhotons = false;
					this.errors = 0;
					this.markDirty();
				}
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.handler.deserializeNBT(nbt.getCompoundTag("Inventory"));
		this.tank.readFromNBT(nbt);
		this.acceptingPhotons = nbt.getBoolean("AcceptingPhotons");
		this.photons = nbt.getInteger("Photons");
		this.errors = nbt.getInteger("Errors");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("Inventory", this.handler.serializeNBT());
		this.tank.writeToNBT(nbt);
		nbt.setBoolean("AcceptingPhotons", this.acceptingPhotons);
		nbt.setInteger("Photons", this.photons);
		nbt.setInteger("Errors", this.errors);
		return super.writeToNBT(nbt);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if ((capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
				&& facing == this.worldObj.getBlockState(this.pos).getValue(BlockMatterFabricator.FACING))
				|| capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) this.tank;
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler;
		return super.getCapability(capability, facing);
	}

}
