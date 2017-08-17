package nl.kingdev.mattercraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public class ItemEmptyTank extends Item {

    public ItemEmptyTank() {
        this.setUnlocalizedName("tank_empty");
        this.setRegistryName(new ResourceLocation(Reference.mod_id, "tank_empty"));
        this.setCreativeTab(MatterCraft.items);
    }
}
