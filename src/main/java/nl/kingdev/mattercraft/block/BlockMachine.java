package nl.kingdev.mattercraft.block;

import net.minecraft.block.Block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public abstract class BlockMachine extends Block implements ITileEntityProvider {

	public BlockMachine(String unlocalizedName) {
		super(Material.IRON);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Reference.mod_id, unlocalizedName));
		this.setCreativeTab(MatterCraft.blocks);
		this.setHardness(3);
		this.setResistance(20.0F);
		this.isBlockContainer = true;
	}

}
