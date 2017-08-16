package nl.kingdev.mattercraft.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nl.kingdev.mattercraft.info.Reference;
import nl.kingdev.mattercraft.item.ItemCompressionStone;
import nl.kingdev.mattercraft.item.ItemDebugWrench;
import nl.kingdev.mattercraft.item.ItemMatter;
import nl.kingdev.mattercraft.item.ItemWrench;
import nl.kingdev.mattercraft.util.LoggerUtils;
import nl.kingdev.mattercraft.util.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModItems {

    private static List<Item> items = new ArrayList();
    
    public static Item matter = new ItemMatter();
    public static Item compressionStone = new ItemCompressionStone();
    public static Item debugWrench = new ItemDebugWrench();
    public static Item wrench = new ItemWrench();
    
    public static void register() {

        for(Field field : ModItems.class.getFields()) {
            try {
                Item item = (Item) field.get(Item.class);
                registerItem(item);
                LoggerUtils.riabfmc++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerItem(Item i) {

        items.add(i);
        GameRegistry.register(i);
        Utils.getLogger().info("Registered Item " + i.getUnlocalizedName().substring(5));
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        for (Item item : items) {
            if (!item.getHasSubtypes()) {
                registerRender(item);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item i) {
        ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(new ResourceLocation(Reference.mod_id, i.getUnlocalizedName().substring(5)), "inventory"));
        Utils.getLogger().info("Registered Render " + i.getUnlocalizedName().substring(5));
    }

}
