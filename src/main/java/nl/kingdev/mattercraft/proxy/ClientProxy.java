package nl.kingdev.mattercraft.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nl.kingdev.mattercraft.init.ModItems;

public class ClientProxy extends CommonProxy {

    @Override
    public void onPreInit(FMLPreInitializationEvent e) {
        ModItems.registerRenders();
    }

    @Override
    public void onInit(FMLInitializationEvent e) {

    }

    @Override
    public void onPostInit(FMLPostInitializationEvent e) {

    }
}
