package nl.kingdev.mattercraft.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;
import nl.kingdev.mattercraft.util.Utils;

public class ItemWrench extends Item {
	
	public  ItemWrench() {
		this.setUnlocalizedName("wrench");
		this.setRegistryName(new ResourceLocation(Reference.mod_id, "wrench"));
		this.setMaxStackSize(1);
		this.setCreativeTab(MatterCraft.items);
	}
	
	@Override
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos,
			EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		if (world.isAirBlock(pos))
			return EnumActionResult.PASS;
		if(world.getBlockState(pos).getBlock().rotateBlock(world, pos, side)) {
			player.swingArm(hand);
			return EnumActionResult.SUCCESS;
		}
		IBlockState state = Utils.rotateBlock(world, pos);
		if(state != world.getBlockState(pos)) {
			world.setBlockState(pos, state, 3);
			player.swingArm(hand);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}

}
