package nl.kingdev.mattercraft.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import nl.kingdev.mattercraft.info.Reference;
import nl.kingdev.mattercraft.tileentity.TileEntityInfiniteMatterWater;
import nl.kingdev.mattercraft.tileentity.TileEntityMatterFabricator;
import nl.kingdev.mattercraft.tileentity.TileEntityPhotonGenerator;

public class CommonProxy {

    public void onPreInit(FMLPreInitializationEvent e) {

    }

    public void onInit(FMLInitializationEvent e) {
    	GameRegistry.registerTileEntity(TileEntityMatterFabricator.class, Reference.mod_id + ":matter_fabricator");
    	GameRegistry.registerTileEntity(TileEntityPhotonGenerator.class, Reference.mod_id + ":photon_generator");
        GameRegistry.registerTileEntity(TileEntityInfiniteMatterWater.class, Reference.mod_id + ":infinite_water");
    }

    public void onPostInit(FMLPostInitializationEvent e) {

    }

}
