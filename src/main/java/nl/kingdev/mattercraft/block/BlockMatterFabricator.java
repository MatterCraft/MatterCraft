package nl.kingdev.mattercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import nl.kingdev.mattercraft.init.ModBlocks;
import nl.kingdev.mattercraft.tileentity.TileEntityMatterFabricator;
import nl.kingdev.mattercraft.util.Utils;

/**
 * 
 * @author CJMinecraft
 *
 */
public class BlockMatterFabricator extends BlockMachine {

	public static final AxisAlignedBB[] CENTRE = new AxisAlignedBB[] {
			new AxisAlignedBB(0.1875D, 0.25D, 0.1875D, 0.8125D, 0.875D, 0.8125D),
			new AxisAlignedBB(0.1875D, 0.875D, 0.1875D, 0.8125D, 0.25D, 0.8125D),
			new AxisAlignedBB(0.25D, 0.1875D, 0.1875D, 0.875D, 0.8125D, 0.8125D),
			new AxisAlignedBB(0.8125D, 0.1875D, 0.1875D, 0.875D, 0.25D, 0.8125D),
			new AxisAlignedBB(0.1875D, 0.1875D, 0.25D, 0.8125D, 0.8125D, 0.875D),
			new AxisAlignedBB(0.1875D, 0.1875D, 0.875D, 0.8125D, 0.8125D, 0.25D) };

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public BlockMatterFabricator() {
		super("matter_fabricator");
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.useNeighborBrightness = true;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return BASE_AABB[getMetaFromState(state)].union(CENTRE[getMetaFromState(state)]).offset(pos);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BASE_AABB[getMetaFromState(state)].union(CENTRE[getMetaFromState(state)]);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return BASE_AABB[getMetaFromState(state)].union(CENTRE[getMetaFromState(state)]);
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
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockState currentState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		IBlockState actualState = blockAccess.getBlockState(pos.offset(side));
        Block block = actualState.getBlock();
        if (this == ModBlocks.matterFabricator) {
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
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return side != EnumFacing.UP && side != EnumFacing.DOWN;
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			if(heldItem == null) {
				player.setHeldItem(hand, world.getTileEntity(pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).extractItem(0, 1, false));
				world.notifyBlockUpdate(pos, state, state, 3);
			}
			return FluidUtil.interactWithFluidHandler(heldItem,
					world.getTileEntity(pos).getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side),
					player);
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityMatterFabricator();
	}

}
