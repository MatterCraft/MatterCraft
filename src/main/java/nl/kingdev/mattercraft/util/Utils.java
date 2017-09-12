package nl.kingdev.mattercraft.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nl.kingdev.mattercraft.info.Reference;

public class Utils {
	
	public static final int MAX_LIGHT_X = 0xF000F0;
    public static final int MAX_LIGHT_Y = 0xF000FF;
	
	public static final int[] SIDE_LEFT = { 4, 5, 5, 4, 2, 3 };
	public static final int[] SIDE_OPPOSITE = { 1, 0, 3, 2, 5, 4 };
	
    private static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = LogManager.getFormatterLogger(Reference.mod_id);
        }
        return logger;
    }
    
	/**
	 * Converts the given {@link ItemStack} to a nice looking string
	 * 
	 * @param stack
	 *            The {@link ItemStack} to convert to a string
	 * @return The {@link ItemStack} as a string
	 */
	public static String stackToString(ItemStack stack) {
		String string = (stack.stackSize / 64 != 0 ? stack.stackSize / 64 + "x64" : "x")
				+ ((stack.stackSize - ((stack.stackSize / 64) * 64)) != 0
						? " + " + (stack.stackSize - ((stack.stackSize / 64) * 64)) : "")
				+ " " + stack.getDisplayName();
		if ((stack.stackSize / 64) == 0)
			string = string.replace(" + ", "");
		return string;
	}
	
	public static IBlockState rotateBlock(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		int meta = block.getMetaFromState(state);
		if(block instanceof BlockBed || block instanceof BlockRailBase)
			return block.getStateFromMeta(meta);
		if(block instanceof BlockStairs)
			return block.getStateFromMeta(meta++ % 8);
		if(block instanceof BlockSlab)
			return block.getStateFromMeta((meta + 8) % 16);
		if(block instanceof BlockDirectional || block instanceof BlockDispenser)
			return state.cycleProperty(BlockDirectional.FACING);
		if(block instanceof BlockChest) {
			BlockPos offset;
			for(EnumFacing side : EnumFacing.HORIZONTALS) {
				offset = pos.offset(side);
				Block offsetBlock = world.getBlockState(offset).getBlock();
				if(block == offsetBlock || (block == null && offsetBlock == null) || block.equals(offsetBlock) || block.isAssociatedBlock(offsetBlock)) {
					world.setBlockState(offset, block.getStateFromMeta(SIDE_OPPOSITE[meta]), 1);
					return block.getStateFromMeta(SIDE_OPPOSITE[meta]); 
				}
			}
			return block.getStateFromMeta(SIDE_LEFT[block.getMetaFromState(state)]);
		}
		if(block instanceof BlockHorizontal || block instanceof BlockFurnace || block instanceof BlockEnderChest || block instanceof BlockDoor || block instanceof BlockTrapDoor || block instanceof BlockLadder)
			return state.cycleProperty(BlockHorizontal.FACING);
//		for(IProperty property : state.getProperties().keySet()) {
//			if(property instanceof PropertyDirection) {
//				PropertyDirection p = (PropertyDirection) property;
//				EnumFacing next = EnumFacing.VALUES[(((EnumFacing)state.getValue(property)).getIndex() + 1) % EnumFacing.VALUES.length];
//				return state.withProperty(p, next);
//			}
//		}
		return block.getStateFromMeta(meta);
	}
	
    @SideOnly(Side.CLIENT)
    public static void renderLaser(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ, float alpha, double beamWidth, float[] color){
    	Tessellator tessy = Tessellator.getInstance();
        VertexBuffer render = tessy.getBuffer();
        World world = Minecraft.getMinecraft().theWorld;

        float r = color[0];
        float g = color[1];
        float b = color[2];

        Vec3d vec1 = new Vec3d(firstX, firstY, firstZ);
        Vec3d vec2 = new Vec3d(secondX, secondY, secondZ);
        Vec3d combinedVec = vec2.subtract(vec1);

        double pitch = Math.atan2(combinedVec.yCoord, Math.sqrt(combinedVec.xCoord*combinedVec.xCoord+combinedVec.zCoord*combinedVec.zCoord));
        double yaw = Math.atan2(-combinedVec.zCoord, combinedVec.xCoord);

        double length = combinedVec.lengthVector();

        GlStateManager.pushMatrix();

        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
        int func = GL11.glGetInteger(GL11.GL_ALPHA_TEST_FUNC);
        float ref = GL11.glGetFloat(GL11.GL_ALPHA_TEST_REF);
        GlStateManager.alphaFunc(GL11.GL_ALWAYS, 0);
        GlStateManager.translate(firstX-TileEntityRendererDispatcher.staticPlayerX, firstY-TileEntityRendererDispatcher.staticPlayerY, firstZ-TileEntityRendererDispatcher.staticPlayerZ);
        GlStateManager.rotate((float)(180*yaw/Math.PI), 0, 1, 0);
        GlStateManager.rotate((float)(180*pitch/Math.PI), 0, 0, 1);
        
        GlStateManager.disableTexture2D();
        render.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
        for(double i = 0; i < 4; i++){
            double width = beamWidth*(i/4.0);
            render.pos(length, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(0, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(0, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(length, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();

            render.pos(length, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(0, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(0, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(length, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();

            render.pos(length, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(0, width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(0, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(length, width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();

            render.pos(length, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(0, -width, width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(0, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
            render.pos(length, -width, -width).tex(0, 0).lightmap(MAX_LIGHT_X, MAX_LIGHT_Y).color(r, g, b, alpha).endVertex();
        }
        tessy.draw();

        GlStateManager.enableTexture2D();

        GlStateManager.alphaFunc(func, ref);
        GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

}
