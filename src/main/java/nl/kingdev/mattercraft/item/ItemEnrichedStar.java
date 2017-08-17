package nl.kingdev.mattercraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public class ItemEnrichedStar extends Item{

    public ItemEnrichedStar() {
        this.setUnlocalizedName("enriched_star");
        this.setRegistryName(new ResourceLocation(Reference.mod_id, "enriched_star"));
        this.setCreativeTab(MatterCraft.items);
    }
}
