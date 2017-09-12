package nl.kingdev.mattercraft.client.tesr;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.BlockPos;
import nl.kingdev.mattercraft.capabilities.IBindable;
import nl.kingdev.mattercraft.init.ModCapabilities;
import nl.kingdev.mattercraft.tileentity.TileEntityPhotonGenerator;
import nl.kingdev.mattercraft.util.Utils;

public class TileEntityPhotonGeneratorRenderer extends TileEntitySpecialRenderer<TileEntityPhotonGenerator> {

	private float r = 0.1F, g = 0.3F, b = 0.5F;
	private boolean increaseR = true, increaseG = true, increaseB = true;
	
	@Override
	public void renderTileEntityAt(TileEntityPhotonGenerator te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		//Utils.getLogger().info(te.fireing);
		if (!te.hasCapability(ModCapabilities.CAPABILITIY_BINDABLE, null)/* || !te.fireing*/)
			return;
		IBindable binder = te.getCapability(ModCapabilities.CAPABILITIY_BINDABLE, null);
		if (!binder.isBound())
			return;
		BlockPos pos = binder.getTargetPosition();
		if(increaseR)
			r += 0.001F;
		else
			r -= 0.001F;
		if(increaseG)
			g += 0.001F;
		else
			g -= 0.001F;
		if(increaseB)
			b += 0.001F;
		else
			b -= 0.001F;
		if(r >= 0.7F)
			this.increaseR = false;
		if(r <= 0.2F)
			this.increaseR = true;
		if(g >= 0.7F)
			this.increaseG = false;
		if(g <= 0.2F)
			this.increaseG = true;
		if(b >= 0.7F)
			this.increaseB = false;
		if(b <= 0.2F)
			this.increaseB = true;
		Utils.renderLaser(te.getPos().getX() + 0.5D, te.getPos().getY() + 0.5D, te.getPos().getZ() + 0.5D,
				pos.getX() + 0.5D, pos.getY() + 0.6D, pos.getZ() + 0.5D, 1F, 0.1F, new float[] {r, g, b});
	}

	@Override
	public void renderTileEntityFast(TileEntityPhotonGenerator te, double x, double y, double z, float partialTicks,
			int destroyStage, VertexBuffer buffer) {
		renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
	}

}
