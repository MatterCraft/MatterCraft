package nl.kingdev.mattercraft.block;

import net.minecraft.block.Block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

/**
 * A regular machine
 * 
 * @author CJMinecraft
 *
 */
public abstract class BlockMachine extends Block implements ITileEntityProvider {

	/**
	 * The base bounding box for all rotations, in order Down, Up, North, South,
	 * East, West
	 */
	public static final AxisAlignedBB[] BASE_AABB = new AxisAlignedBB[] { new AxisAlignedBB(0, 0, 0, 1, 0.25D, 1),
			new AxisAlignedBB(1, 1, 1, 0, 0.75D, 0), new AxisAlignedBB(1, 1, 1, 0, 0, 0.75D),
			new AxisAlignedBB(0, 0, 0, 1, 1, 0.25D), new AxisAlignedBB(1, 1, 1, 0.75D, 0, 0),
			new AxisAlignedBB(0, 0, 0, 0.25D, 1, 1) };

	public BlockMachine(String unlocalizedName) {
		super(Material.IRON);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Reference.mod_id, unlocalizedName));
		this.setCreativeTab(MatterCraft.blocks);
		this.setHardness(3);
		this.setResistance(20.0F);
		this.isBlockContainer = true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return createNewTileEntity(world, getMetaFromState(state));
	}

}
