package nl.kingdev.mattercraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public class ItemMatter extends Item {

    public ItemMatter() {
        this.setUnlocalizedName("matter");
        this.setRegistryName(new ResourceLocation(Reference.mod_id, "matter"));
        this.setCreativeTab(MatterCraft.items);
    }
}
