package nl.kingdev.mattercraft.client.tesr;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import nl.kingdev.mattercraft.tileentity.TileEntityMatterFabricator;

public class TileEntityMatterFabricatorRenderer extends TileEntitySpecialRenderer<TileEntityMatterFabricator> {
	
	@Override
	public void renderTileEntityAt(TileEntityMatterFabricator te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		GlStateManager.pushMatrix();
		ItemStack stack = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).getStackInSlot(0);
		if(stack != null) {
			GlStateManager.translate(x + 0.5D, y + 0.55D, z + 0.5D);
            GlStateManager.rotate((float)(((Minecraft.getSystemTime()/800D*40D)%360)), 0, 1, 0);
			
			float scale = stack.getItem() instanceof ItemBlock ? 0.45F : 0.25F;
            GlStateManager.scale(scale, scale, scale);
            
			GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, TransformType.FIXED);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
		}
		GlStateManager.popMatrix();
	}
	
	@Override
	public void renderTileEntityFast(TileEntityMatterFabricator te, double x, double y, double z, float partialTicks,
			int destroyStage, VertexBuffer buffer) {
		renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
	}

}
