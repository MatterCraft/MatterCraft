package nl.kingdev.mattercraft.tileentity;

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

/**
 * 
 * @author CJMinecraft
 *
 */
public class TileEntityMatterFabricator extends TileEntityBase implements ITickable {

	private ItemStackHandler handler;
	private FluidTank tank;
	
	public TileEntityMatterFabricator() {
		this.handler = new ItemStackHandler(1){
			@Override
			protected int getStackLimit(int slot, ItemStack stack) {
				return 1;
			}
		};
		this.tank = new FluidTank(FluidRegistry.WATER, 0, 8000);
	}
	
	@Override
	public void update() {
		if(this.worldObj != null) {
			if(!this.worldObj.isRemote) {
				if(this.worldObj.isBlockPowered(this.pos)) {
					
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.handler.deserializeNBT(nbt.getCompoundTag("Inventory"));
		this.tank.readFromNBT(nbt);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("Inventory", this.handler.serializeNBT());
		this.tank.writeToNBT(nbt);
		return super.writeToNBT(nbt);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) this.tank;
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler;
		return super.getCapability(capability, facing);
	}

}
