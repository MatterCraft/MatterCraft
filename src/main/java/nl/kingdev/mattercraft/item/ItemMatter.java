package nl.kingdev.mattercraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import nl.kingdev.mattercraft.info.Reference;

public class ItemMatter extends Item {

    public ItemMatter() {
        setUnlocalizedName("matter");
        setRegistryName(new ResourceLocation(Reference.mod_id, "matter"));
    }
}
