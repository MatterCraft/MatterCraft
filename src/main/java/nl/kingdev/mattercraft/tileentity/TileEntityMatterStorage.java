package nl.kingdev.mattercraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMatterStorage extends TileEntity implements ICapabilityProvider{

    public static ItemStackHandler handler;

    public TileEntityMatterStorage() {
        handler = new ItemStackHandler(2);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("handler", handler.serializeNBT());
        return super.writeToNBT(compound);
    }


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        handler.deserializeNBT(compound.getCompoundTag("handler"));
        super.readFromNBT(compound);
    }


}
