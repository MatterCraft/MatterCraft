package nl.kingdev.mattercraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public class ItemMachineCore extends Item {

    public ItemMachineCore() {
        this.setUnlocalizedName("machine_core");
        this.setRegistryName(new ResourceLocation(Reference.mod_id,"machine_core"));
        this.setCreativeTab(MatterCraft.items);
    }
}
