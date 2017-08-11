package nl.kingdev.mattercraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nl.kingdev.mattercraft.info.Reference;
import nl.kingdev.mattercraft.init.ModItems;
import nl.kingdev.mattercraft.proxy.CommonProxy;

@Mod(modid = Reference.mod_id, name = Reference.mod_name, version = Reference.mod_version)
public class MatterCraft {

    public static CreativeTabs items = new CreativeTabs("mattercraft_items") {
        @Override
        public Item getTabIconItem() {
            return Items.BOOK;
        }
    };


    @SidedProxy(serverSide = Reference.proxy_server, clientSide = Reference.proxy_client)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent e) {
        ModItems.register();
        proxy.onPreInit(e);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent e) {
        proxy.onInit(e);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent e) {
        proxy.onPostInit(e);
    }


}
