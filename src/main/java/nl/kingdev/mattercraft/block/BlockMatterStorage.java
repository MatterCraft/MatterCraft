package nl.kingdev.mattercraft.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.kingdev.mattercraft.tileentity.TileEntityMatterStorage;

public class BlockMatterStorage extends BlockMachine {

    public BlockMatterStorage() {
        super("matter_storage");
    }



    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityMatterStorage();
    }

}
