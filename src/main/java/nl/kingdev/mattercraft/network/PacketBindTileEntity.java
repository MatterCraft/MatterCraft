package nl.kingdev.mattercraft.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import nl.kingdev.mattercraft.capabilities.IBindable;
import nl.kingdev.mattercraft.init.ModCapabilities;
import nl.kingdev.mattercraft.util.Utils;

public class PacketBindTileEntity implements IMessage {

	private boolean messageValid;
	private EnumFacing tileEntitySide;
	private BlockPos tileEntityPos;
	private BlockPos targetPos;

	public PacketBindTileEntity() {
		this.messageValid = false;
	}

	public PacketBindTileEntity(BlockPos tileEntityPos, EnumFacing tileEntitySide, BlockPos targetPos) {
		this.tileEntityPos = tileEntityPos;
		this.tileEntitySide = tileEntitySide;
		this.targetPos = targetPos;
		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.tileEntityPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
			String side = ByteBufUtils.readUTF8String(buf);
			if (side.equalsIgnoreCase("null"))
				this.tileEntitySide = null;
			else
				this.tileEntitySide = EnumFacing.byName(side);
			this.targetPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		} catch (IndexOutOfBoundsException ioe) {
			Utils.getLogger().catching(ioe);
			return;
		}
		this.messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid)
			return;
		buf.writeInt(this.tileEntityPos.getX());
		buf.writeInt(this.tileEntityPos.getY());
		buf.writeInt(this.tileEntityPos.getZ());
		if (this.tileEntitySide == null)
			ByteBufUtils.writeUTF8String(buf, "null");
		else
			ByteBufUtils.writeUTF8String(buf, this.tileEntitySide.getName2());
		buf.writeInt(this.targetPos.getX());
		buf.writeInt(this.targetPos.getY());
		buf.writeInt(this.targetPos.getZ());
	}

	public static class Handler implements IMessageHandler<PacketBindTileEntity, IMessage> {

		@Override
		public IMessage onMessage(PacketBindTileEntity message, MessageContext ctx) {
			if (!message.messageValid || ctx.side != Side.SERVER)
				return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
				processMessage(message, ctx);
			});
			return null;
		}

		void processMessage(PacketBindTileEntity message, MessageContext ctx) {
			TileEntity te = ctx.getServerHandler().playerEntity.getServerWorld().getTileEntity(message.tileEntityPos);
			if (!te.hasCapability(ModCapabilities.CAPABILITIY_BINDABLE, message.tileEntitySide))
				return;
			IBindable binder = te.getCapability(ModCapabilities.CAPABILITIY_BINDABLE, message.tileEntitySide);
			if (binder.canBind(message.targetPos,
					ctx.getServerHandler().playerEntity.getServerWorld().getBlockState(message.targetPos), te))
				binder.setTargetPosition(message.targetPos);
		}

	}

}
