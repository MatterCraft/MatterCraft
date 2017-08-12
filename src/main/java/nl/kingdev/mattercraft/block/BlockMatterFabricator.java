package nl.kingdev.mattercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public class BlockMatterFabricator extends Block {


    public BlockMatterFabricator() {
        super(Material.IRON);
        setUnlocalizedName("matter_fabricator");
        setRegistryName(new ResourceLocation(Reference.mod_id, "matter_fabricator"));
        setCreativeTab(MatterCraft.blocks);

    }


}
