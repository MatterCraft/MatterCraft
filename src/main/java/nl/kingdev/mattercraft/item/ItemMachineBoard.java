package nl.kingdev.mattercraft.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public class ItemMachineBoard extends Item {

    public ItemMachineBoard() {
        this.setUnlocalizedName("machine_board");
        this.setRegistryName(new ResourceLocation(Reference.mod_id, "machine_board"));
        this.setCreativeTab(MatterCraft.items);
    }

}
