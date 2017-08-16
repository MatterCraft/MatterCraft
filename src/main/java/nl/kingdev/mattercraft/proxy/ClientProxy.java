package nl.kingdev.mattercraft.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nl.kingdev.mattercraft.client.tesr.TileEntityMatterFabricatorRenderer;
import nl.kingdev.mattercraft.init.ModBlocks;
import nl.kingdev.mattercraft.init.ModItems;
import nl.kingdev.mattercraft.tileentity.TileEntityMatterFabricator;

public class ClientProxy extends CommonProxy {

    @Override
    public void onPreInit(FMLPreInitializationEvent e) {
    	super.onPreInit(e);
        ModItems.registerRenders();
        ModBlocks.registerRenders();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMatterFabricator.class, new TileEntityMatterFabricatorRenderer());
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
