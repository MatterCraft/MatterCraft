package nl.kingdev.mattercraft.tileentity;

import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidTank;

public class TileEntityInfiniteMatterWater extends TileEntityBase implements ITickable {

    private FluidTank tank;

    public TileEntityInfiniteMatterWater() {
        tank = new FluidTank(FluidRegistry.WATER, 2048000, 2048000);
        tank.setCanDrain(true);
        tank.setCanFill(false);
    }

    @Override
    public void update() {
        if(tank.getFluidAmount() != 2048000) tank.setCapacity(2048000);
    }



}
