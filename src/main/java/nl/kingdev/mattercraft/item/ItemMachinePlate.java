package nl.kingdev.mattercraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public class ItemMachinePlate extends Item {

    public ItemMachinePlate() {
        this.setUnlocalizedName("machine_plate");
        this.setRegistryName(new ResourceLocation(Reference.mod_id, "machine_plate"));
        this.setCreativeTab(MatterCraft.items);
    }

}
