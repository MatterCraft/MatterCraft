package nl.kingdev.mattercraft.init;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import nl.kingdev.mattercraft.capabilities.Binder;
import nl.kingdev.mattercraft.capabilities.IBindable;

public class ModCapabilities {
	
	@CapabilityInject(IBindable.class)
	public static Capability<IBindable> CAPABILITIY_BINDABLE = null;
	
	public static void registerCapabilities() {
		CapabilityManager.INSTANCE.register(IBindable.class, new IStorage<IBindable>() {

			@Override
			public NBTBase writeNBT(Capability<IBindable> capability, IBindable instance, EnumFacing side) {
				return null;
			}

			@Override
			public void readNBT(Capability<IBindable> capability, IBindable instance, EnumFacing side, NBTBase nbt) {
			}}, Binder.class);
	}

}
