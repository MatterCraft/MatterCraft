package nl.kingdev.mattercraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nl.kingdev.mattercraft.info.Reference;
import nl.kingdev.mattercraft.init.ModBlocks;
import nl.kingdev.mattercraft.init.ModItems;
import nl.kingdev.mattercraft.proxy.CommonProxy;
import nl.kingdev.mattercraft.util.LoggerUtils;
import nl.kingdev.mattercraft.util.Utils;

@Mod(modid = Reference.mod_id, name = Reference.mod_name, version = Reference.mod_version)
public class MatterCraft {

    public static CreativeTabs items = new CreativeTabs("mattercraft_items") {
        @Override
        public Item getTabIconItem() {
            return ModItems.matter;
        }
    };
    public static CreativeTabs blocks = new CreativeTabs("mattercraft_blocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.matterFabricator);
        }
    };


    @SidedProxy(serverSide = Reference.proxy_server, clientSide = Reference.proxy_client)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent e) {
        ModItems.register();
        ModBlocks.register();
        proxy.onPreInit(e);
    }


    @Mod.EventHandler
    public void onInit(FMLInitializationEvent e) {
        proxy.onInit(e);
        Utils.getLogger().info("*MatterCraft* Total registrations of Items And Blocks : " + LoggerUtils.riabfmc);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent e) {
        proxy.onPostInit(e);
    }


}
