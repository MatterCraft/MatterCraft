package nl.kingdev.mattercraft.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nl.kingdev.mattercraft.init.ModBlocks;
import nl.kingdev.mattercraft.init.ModItems;

public class ClientProxy extends CommonProxy {

    @Override
    public void onPreInit(FMLPreInitializationEvent e) {
    	super.onPreInit(e);
        ModItems.registerRenders();
        ModBlocks.registerRenders();
    }

    @Override
    public void onInit(FMLInitializationEvent e) {
    	super.onInit(e);
    }

    @Override
    public void onPostInit(FMLPostInitializationEvent e) {
    	super.onPostInit(e);
    }
}
