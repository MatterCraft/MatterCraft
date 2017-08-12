package nl.kingdev.mattercraft.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nl.kingdev.mattercraft.block.BlockMatterFabricator;
import nl.kingdev.mattercraft.info.Reference;
import nl.kingdev.mattercraft.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    private static List<Block> blocks = new ArrayList();

    public static Block blockMatterFabricator;

    public static void register() {
        registerBlock(blockMatterFabricator = new BlockMatterFabricator());
    }

    public static void registerBlock(Block block) {

        blocks.add(block);
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        Utils.getLogger().info("Registered Block " + block.getUnlocalizedName().substring(5));
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        for (Block b : blocks) {
            registerRender(b);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(Reference.mod_id, block.getUnlocalizedName().substring(5)), "inventory"));
        Utils.getLogger().info("Registered Render " + block.getUnlocalizedName().substring(5));
    }

}
