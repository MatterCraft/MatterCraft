package nl.kingdev.mattercraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public class ItemInfinityCore extends Item {

    public ItemInfinityCore() {
        this.setUnlocalizedName("infinity_core");
        this.setRegistryName(new ResourceLocation(Reference.mod_id, "infinity_core"));
        this.setCreativeTab(MatterCraft.items);
    }
}
