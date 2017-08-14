package nl.kingdev.mattercraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.handlers.EnumHandler;
import nl.kingdev.mattercraft.info.Reference;

import java.util.List;

public class ItemCompressionStone extends Item {

    public ItemCompressionStone() {
        this.setUnlocalizedName("matter_compression_stone");
        this.setRegistryName(new ResourceLocation(Reference.mod_id, "matter_compression_stone"));
        this.setCreativeTab(MatterCraft.items);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i < EnumHandler.CompressionStoneTiers.values().length; i++) {
            if(stack.getItemDamage() == i){
                return this.getUnlocalizedName() + "." + EnumHandler.CompressionStoneTiers.values()[i].getName();
            }
             else {
                continue;
            }
        }
        return this.getUnlocalizedName()  + "." + EnumHandler.CompressionStoneTiers.JUNIOR.getName();
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < EnumHandler.CompressionStoneTiers.values().length; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
}

