package nl.kingdev.mattercraft.item;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;
import nl.kingdev.mattercraft.util.EnergyUtils;
import nl.kingdev.mattercraft.util.Utils;

import java.text.NumberFormat;

public class ItemDebugWrench extends Item {

	public ItemDebugWrench() {
		this.setUnlocalizedName("debug_wrench");
		this.setRegistryName(new ResourceLocation(Reference.mod_id, "debug_wrench"));
		this.setMaxStackSize(1);
		this.setCreativeTab(MatterCraft.items);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Style blue = new Style().setColor(TextFormatting.AQUA);
		Style green = new Style().setColor(TextFormatting.GREEN);
		if (!worldIn.isRemote) {
			if (!playerIn.isSneaking() || playerIn.isSpectator())
				return EnumActionResult.PASS;
			if (worldIn.getBlockState(pos).getBlock() != null) {
				Block b = worldIn.getBlockState(pos).getBlock();
				playerIn.addChatMessage(new TextComponentString(
						I18n.format("item.debug_wrench.info.block", b.getRegistryName(), pos.toString()))
								.setStyle(blue));
				playerIn.addChatMessage(
						new TextComponentString(I18n.format("item.debug_wrench.info.redstone1")).setStyle(blue));
				playerIn.addChatMessage(new TextComponentString(
						I18n.format("item.debug_wrench.info.redstone2", worldIn.isBlockPowered(pos),
								worldIn.getRedstonePower(pos, facing), b.canProvidePower(worldIn.getBlockState(pos))))
										.setStyle(green));
				if (worldIn.getTileEntity(pos) != null) {
					playerIn.addChatMessage(
							new TextComponentString(I18n.format("item.debug_wrench.info.tileentity")).setStyle(blue));

					TileEntity t = worldIn.getTileEntity(pos);
					if (t.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
						IFluidHandler ta = t.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing);
						if (ta != null) {
							IFluidTankProperties tank = ta.getTankProperties()[0];
							playerIn.addChatMessage(
									new TextComponentString(I18n.format("item.debug_wrench.info.fluid1"))
											.setStyle(blue));
							playerIn.addChatMessage(new TextComponentString(
									I18n.format("item.debug_wrench.info.fluid2", tank.getContents().getLocalizedName()))
											.setStyle(green));
							playerIn.addChatMessage(new TextComponentString(I18n.format("item.debug_wrench.info.fluid3",
									NumberFormat.getInstance().format(tank.getContents().amount),
									NumberFormat.getInstance().format(tank.getCapacity()))).setStyle(green));
							playerIn.addChatMessage(new TextComponentString(
									I18n.format("item.debug_wrench.info.fluid4", tank.canDrain(), tank.canFill()))
											.setStyle(green));
						}

					}
					if (t.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing)) {
						IItemHandler handler = t.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
						if (handler != null) {
							playerIn.addChatMessage(
									new TextComponentString(I18n.format("item.debug_wrench.info.inventory1"))
											.setStyle(blue));
							playerIn.addChatMessage(new TextComponentString(
									I18n.format("item.debug_wrench.info.inventory2", handler.getSlots()))
											.setStyle(green));
							for (int i = 0; i < handler.getSlots(); i++) {
								ItemStack s = handler.getStackInSlot(i);
								if (s != null)
									playerIn.addChatMessage(new TextComponentString(
											I18n.format("item.debug_wrench.info.inventory3", i, Utils.stackToString(s)))
													.setStyle(green));
								else
									playerIn.addChatMessage(
											new TextComponentString(I18n.format("item.debug_wrench.info.inventory4", i))
													.setStyle(green));
							}
						}
					}
					if (t.hasCapability(CapabilityEnergy.ENERGY, facing)) {
						playerIn.addChatMessage(
								new TextComponentString(I18n.format("item.debug_wrench.info.energy1")).setStyle(blue));
						playerIn.addChatMessage(new TextComponentString(I18n.format("item.debug_wrench.info.energy2",
								NumberFormat.getInstance().format(EnergyUtils.getEnergyStored(t, facing)),
								NumberFormat.getInstance().format(EnergyUtils.getMaxEnergyStored(t, facing))))
										.setStyle(green));
						playerIn.addChatMessage(new TextComponentString(I18n.format("item.debug_wrench.info.energy3",
								EnergyUtils.canExtract(t, facing), EnergyUtils.canReceive(t, facing))).setStyle(green));
					}
				}
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}
}
