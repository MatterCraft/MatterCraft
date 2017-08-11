package nl.kingdev.mattercraft.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nl.kingdev.mattercraft.info.Reference;
import nl.kingdev.mattercraft.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    private static List<Item> items = new ArrayList();

    public static void register(){
        //registerItem(item = new ItemItem());
    }

    private static void registerItem(Item i){
        items.add(i);
        GameRegistry.register(i);
        Utils.getLogger().info("Registered Item " + i.getUnlocalizedName().substring(5));
    }

    public static void registerRenders(){
        for (Item item : items) {
            if(!item.getHasSubtypes()){
                registerRender(item);
            }
        }
    }

    private static void registerRender(Item i){
        ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(new ResourceLocation(Reference.mod_id, i.getUnlocalizedName().substring(5)), "inventory"));
        Utils.getLogger().info("Registered render for " + i.getUnlocalizedName().substring(5));
    }

}
