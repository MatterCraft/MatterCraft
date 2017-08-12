package nl.kingdev.mattercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public class BlockMatterFabricator extends Block {


    public BlockMatterFabricator() {
        super(Material.IRON);
        setUnlocalizedName("Matter Fabricator");
        setRegistryName(new ResourceLocation(Reference.mod_id, "matterfabricator"));
        setCreativeTab(MatterCraft.blocks);

    }


}
