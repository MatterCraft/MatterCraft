package nl.kingdev.mattercraft.tileentity;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileEntityInfiniteMatterWater extends TileEntityBase implements ITickable {

    private FluidTank tank;

    public TileEntityInfiniteMatterWater() {
    	this.tank = new FluidTank(FluidRegistry.WATER, 2048000, 2048000);
        this.tank.setCanDrain(true);
        this.tank.setCanFill(false);
    }

    @Override
    public void update() {
    	if(this.worldObj != null) {
    		if(!this.worldObj.isRemote) {
    			if(this.tank.getFluidAmount() != 2048000)
    				this.tank.fillInternal(new FluidStack(FluidRegistry.WATER, this.tank.getCapacity()), true);
    			for(EnumFacing side : EnumFacing.VALUES)
    				if(this.worldObj.getTileEntity(this.pos.offset(side)) != null && this.worldObj.getTileEntity(this.pos.offset(side)).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite()))
    					FluidUtil.tryFluidTransfer(this.worldObj.getTileEntity(this.pos.offset(side)).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side.getOpposite()), this.tank, 4000, true);
    		}
    	}
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
    	if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
    		return true;
    	return super.hasCapability(capability, facing);
    }
    
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
    	if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
    		return (T) this.tank;
    	return super.getCapability(capability, facing);
    }

}
