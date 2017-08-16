package nl.kingdev.mattercraft.item;

import net.minecraft.block.Block;
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
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

public class ItemDebugWrench extends Item {

    public ItemDebugWrench() {
        this.setUnlocalizedName("debug_wrench");
        this.setRegistryName(new ResourceLocation(Reference.mod_id, "debug_wrench"));
        this.setMaxStackSize(1);
        this.setCreativeTab(MatterCraft.items);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Style blue = new Style().setColor(TextFormatting.AQUA);
        Style green = new Style().setColor(TextFormatting.GREEN);
        if (!worldIn.isRemote) {
            if(!playerIn.isSneaking() || playerIn.isSpectator()) return EnumActionResult.PASS;
            if (worldIn.getBlockState(pos).getBlock() != null) {
                Block b = worldIn.getBlockState(pos).getBlock();
                playerIn.addChatMessage(new TextComponentString("[DEBUG] Block " + b.getRegistryName() + " @ " + pos.toString()).setStyle(blue));
                if (worldIn.getTileEntity(pos) != null) {
                    playerIn.addChatMessage(new TextComponentString("TileEntity Info:").setStyle(blue));

                    TileEntity t = worldIn.getTileEntity(pos);
                    if (t.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
                        FluidTank tank = null;
                        try {
                            tank = (FluidTank) t.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing);
                        } catch (Exception e) {

                        }
                        if (tank != null){
                            playerIn.addChatMessage(new TextComponentString("FluidTank Info:").setStyle(blue));
                            playerIn.addChatMessage(new TextComponentString("Tank Fluid: " + tank.getFluid().getLocalizedName()).setStyle(green));
                            playerIn.addChatMessage(new TextComponentString("Tank Amount: " + tank.getFluidAmount() + "mb / " + tank.getCapacity() + "mb").setStyle(green));
                            playerIn.addChatMessage(new TextComponentString("Can Extract: " + tank.canDrain() + ", Can Insert: " + tank.canFill()).setStyle(green));
                        }

                    }
                    if(t.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing)){
                        ItemStackHandler handler = null;
                        try {
                            handler = (ItemStackHandler) t.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
                        } catch (Exception e){

                        }
                        if(handler != null){
                            playerIn.addChatMessage(new TextComponentString("ItemStackHandler Info:").setStyle(blue));
                            playerIn.addChatMessage(new TextComponentString("Slots: " + handler.getSlots()).setStyle(green));
                            for (int i = 0; i < handler.getSlots(); i++) {
                                ItemStack s = handler.getStackInSlot(i);
                                if(s != null) playerIn.addChatMessage(new TextComponentString("Slot " + i + ": " + s.toString()).setStyle(green));
                                else playerIn.addChatMessage(new TextComponentString("Slot " + i + " is empty!").setStyle(green));
                            }
                        }
                    }


                }
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
}
