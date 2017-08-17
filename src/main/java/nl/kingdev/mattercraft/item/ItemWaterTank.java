package nl.kingdev.mattercraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.handlers.EnumHandler;
import nl.kingdev.mattercraft.info.Reference;

import java.util.List;

public class ItemWaterTank extends Item {

    public ItemWaterTank() {
        this.setUnlocalizedName("tank_water");
        this.setRegistryName(new ResourceLocation(Reference.mod_id, "tank_water"));
        this.setHasSubtypes(true);
        this.setCreativeTab(MatterCraft.items);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        for (int i = 0; i < EnumHandler.WaterTankTiers.values().length; i++) {
            if(stack.getItemDamage() == i){
                return this.getUnlocalizedName() + "." + EnumHandler.WaterTankTiers.values()[i].getName();
            }
            else {
                continue;
            }
        }
        return this.getUnlocalizedName()  + "." + EnumHandler.WaterTankTiers.FIRST.getName();
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < EnumHandler.WaterTankTiers.values().length; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

}
