package nl.kingdev.mattercraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipies {

    public static void registerCrafting() {
        GameRegistry.addRecipe(new ItemStack(ModItems.enrichedStar), new Object[]{"DDD", "DSD", "DDD", 'D', Blocks.DIAMOND_BLOCK, 'S', Items.NETHER_STAR});
        GameRegistry.addRecipe(new ItemStack(ModItems.machineCore), new Object[]{"MMM", "MSM", "MMM", 'M', ModItems.machinePlate, 'S', ModItems.machineBoard});
        GameRegistry.addRecipe(new ItemStack(ModItems.emptyTank, 4), new Object[]{"MMM", "MGM", "MMM", 'M', ModItems.machinePlate, 'G', Blocks.GLASS});
        GameRegistry.addRecipe(new ItemStack(ModItems.machineBoard), new Object[]{"XXX", "XXX", "XXX", 'X', ModItems.machinePlate});
        GameRegistry.addRecipe(new ItemStack(ModItems.waterTank), new Object[]{"WWW", "WTW", "WWW", 'W', Items.WATER_BUCKET, 'T', ModItems.emptyTank});
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.infinityCore), ModItems.enrichedStar, ModItems.machineCore);

        GameRegistry.addRecipe(new ItemStack(ModItems.waterTank, 1, 1), new Object[]{"WWW", "WTW", "WWW", 'W', Items.WATER_BUCKET, 'T', new ItemStack(ModItems.waterTank,1, 0)});
        GameRegistry.addRecipe(new ItemStack(ModItems.waterTank, 1, 2), new Object[]{"WWW", "WTW", "WWW", 'W', Items.WATER_BUCKET, 'T', new ItemStack(ModItems.waterTank,1, 1)});
        GameRegistry.addRecipe(new ItemStack(ModItems.waterTank, 1, 3), new Object[]{"WWW", "WTW", "WWW", 'W', Items.WATER_BUCKET, 'T', new ItemStack(ModItems.waterTank,1, 2)});
        GameRegistry.addRecipe(new ItemStack(ModItems.waterTank, 1, 4), new Object[]{"WWW", "WTW", "WWW", 'W', Items.WATER_BUCKET, 'T', new ItemStack(ModItems.waterTank,1, 3)});
        GameRegistry.addRecipe(new ItemStack(ModItems.waterTank, 1, 5), new Object[]{"WWW", "WTW", "WWW", 'W', Items.WATER_BUCKET, 'T', new ItemStack(ModItems.waterTank,1, 4)});
        GameRegistry.addRecipe(new ItemStack(ModItems.waterTank, 1, 6), new Object[]{"WWW", "WTW", "WWW", 'W', Items.WATER_BUCKET, 'T', new ItemStack(ModItems.waterTank,1, 5)});
        GameRegistry.addRecipe(new ItemStack(ModItems.waterTank, 1, 7), new Object[]{"WWW", "WTW", "WWW", 'W', Items.WATER_BUCKET, 'T', new ItemStack(ModItems.waterTank,1, 6)});
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.waterTank, 4, 8), ModItems.enrichedStar, new ItemStack(ModItems.waterTank, 1, 7));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.infinteWater), new Object[]{"PWP","WCW","PWP",'P', ModItems.machinePlate, 'W', new ItemStack(ModItems.waterTank, 1, 8), 'C', ModItems.infinityCore});

    }

    public static void registerFurnace() {
        GameRegistry.addSmelting(new ItemStack(Blocks.IRON_BLOCK), new ItemStack(ModItems.machinePlate), 0.2F);
    }


}
