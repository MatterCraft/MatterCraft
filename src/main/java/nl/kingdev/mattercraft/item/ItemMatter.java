package nl.kingdev.mattercraft.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import nl.kingdev.mattercraft.MatterCraft;
import nl.kingdev.mattercraft.info.Reference;

import java.util.ArrayList;
import java.util.Collection;

public class ItemMatter extends Item {

    public ItemMatter() {
        this.setUnlocalizedName("matter");
        this.setRegistryName(new ResourceLocation(Reference.mod_id, "matter"));
        this.setMaxStackSize(15);
        this.setCreativeTab(MatterCraft.items);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
      if(entityIn instanceof EntityPlayer) {
          EntityPlayer thePlayer = (EntityPlayer) entityIn;
          Collection<PotionEffect> effects = thePlayer.getActivePotionEffects();

          boolean hasWitherEffect = false;
          for(PotionEffect effect : effects) {
              if(effect.getEffectName().equals("Wither")) {
                hasWitherEffect = true;
              }
          }

          if(!hasWitherEffect) {
              thePlayer.addPotionEffect(new PotionEffect(Potion.getPotionById(20), 20, 1,false, true));
          }

      }
    }
}
