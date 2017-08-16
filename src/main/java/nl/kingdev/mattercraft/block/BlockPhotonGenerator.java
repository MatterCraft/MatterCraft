package nl.kingdev.mattercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import nl.kingdev.mattercraft.init.ModBlocks;
import nl.kingdev.mattercraft.tileentity.TileEntityPhotonGenerator;

/**
 * 
 * @author CJMinecraft
 *
 */
public class BlockPhotonGenerator extends BlockMachine {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public BlockPhotonGenerator() {
		super("photon_generator");
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.useNeighborBrightness = true;
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return BASE_AABB[getMetaFromState(state)].offset(pos);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BASE_AABB[getMetaFromState(state)];
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return BASE_AABB[getMetaFromState(state)];
	}
	
	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isVisuallyOpaque() {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState currentState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		IBlockState actualState = blockAccess.getBlockState(pos.offset(side));
        Block block = actualState.getBlock();

        if (this == ModBlocks.photonGenerator) {
            if (currentState != actualState) {
                return true;
            }

            if (block == this) {
                return false;
            }
        }

        return block == this ? false : super.shouldSideBeRendered(currentState, blockAccess, pos, side);
	}
	
	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, ItemStack stack) {
		return getDefaultState().withProperty(FACING, BlockPistonBase.getFacingFromEntity(pos, placer).getOpposite());
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.VALUES[meta]);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityPhotonGenerator();
	}

}
