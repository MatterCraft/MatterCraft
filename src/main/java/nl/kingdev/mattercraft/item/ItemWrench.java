package nl.kingdev.mattercraft.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.capabilities.IBindable;
import nl.kingdev.mattercraft.info.Reference;
import nl.kingdev.mattercraft.init.ModCapabilities;
import nl.kingdev.mattercraft.network.PacketBindTileEntity;
import nl.kingdev.mattercraft.network.PacketHandler;
import nl.kingdev.mattercraft.util.Utils;

public class ItemWrench extends Item {

	public ItemWrench() {
		this.setUnlocalizedName("wrench");
		this.setRegistryName(new ResourceLocation(Reference.mod_id, "wrench"));
		this.setMaxStackSize(1);
		this.setCreativeTab(MatterCraft.items);
	}

	@Override
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos,
			EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		if (player.isSneaking()) {
			if (stack.getTagCompound().hasKey("TileEntityPos") && stack.getTagCompound().hasKey("TileEntitySide")) {
				int[] tileEntityPosInts = stack.getTagCompound().getIntArray("TileEntityPos");
				BlockPos tileEntityPos = new BlockPos(tileEntityPosInts[0], tileEntityPosInts[1], tileEntityPosInts[2]);
				EnumFacing tileEntitySide = EnumFacing.byName(stack.getTagCompound().getString("TileEntitySide"));
				TileEntity te = world.getTileEntity(tileEntityPos);
				if (te.hasCapability(ModCapabilities.CAPABILITIY_BINDABLE, tileEntitySide)
						&& te.getCapability(ModCapabilities.CAPABILITIY_BINDABLE, tileEntitySide).canBind(pos,
								world.getBlockState(pos), world.getTileEntity(pos))) {
					PacketHandler.INSTANCE.sendToServer(new PacketBindTileEntity(tileEntityPos, tileEntitySide, pos));
					player.addChatComponentMessage(new TextComponentTranslation("item.wrench.bind",
							world.getBlockState(tileEntityPos).getBlock()
									.getPickBlock(world.getBlockState(tileEntityPos),
											new RayTraceResult(RayTraceResult.Type.BLOCK, new Vec3d(0.5D, 0.5D, 0.5D),
													tileEntitySide, tileEntityPos),
											world, tileEntityPos, player)
									.getDisplayName(),
							tileEntityPos.getX(), tileEntityPos.getY(), tileEntityPos.getZ(),
							world.getBlockState(pos).getBlock()
									.getPickBlock(world.getBlockState(pos),
											new RayTraceResult(RayTraceResult.Type.BLOCK, new Vec3d(hitX, hitY, hitZ),
													side, pos),
											world, pos, player)
									.getDisplayName(),
							pos.getX(), pos.getY(), pos.getZ()));
					stack.setTagCompound(new NBTTagCompound());
					return EnumActionResult.SUCCESS;
				}
			}
			TileEntity te = world.getTileEntity(pos);
			if (te.hasCapability(ModCapabilities.CAPABILITIY_BINDABLE, side)) {
				stack.getTagCompound().setIntArray("TileEntityPos", new int[] { pos.getX(), pos.getY(), pos.getZ() });
				stack.getTagCompound().setString("TileEntitySide", side.getName2());
				player.addChatComponentMessage(new TextComponentTranslation("item.wrench.target",
						world.getBlockState(pos).getBlock()
								.getPickBlock(world.getBlockState(pos),
										new RayTraceResult(RayTraceResult.Type.BLOCK, new Vec3d(hitX, hitY, hitZ), side,
												pos),
										world, pos, player)
								.getDisplayName(),
						pos.getX(), pos.getY(), pos.getZ()));
				return EnumActionResult.SUCCESS;
			}
		} else {
			if (world.isAirBlock(pos))
				return EnumActionResult.PASS;
			if (world.getBlockState(pos).getBlock().rotateBlock(world, pos, side)) {
				player.swingArm(hand);
				return EnumActionResult.SUCCESS;
			}
			IBlockState state = Utils.rotateBlock(world, pos);
			if (state != world.getBlockState(pos)) {
				world.setBlockState(pos, state, 3);
				player.swingArm(hand);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.PASS;
	}

}
