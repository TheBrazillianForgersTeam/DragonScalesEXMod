package cf.brforgers.mods.DragonScalesEX.common;

import cf.brforgers.api.DragonScalesEX.DragonScalesAPI;
import cf.brforgers.mods.DragonScalesEX.common.world.DVUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import static cf.brforgers.mods.DragonScalesEX.common.DSEX.*;
import static net.minecraft.init.Blocks.*;
import static net.minecraft.init.Items.*;

public class DSEXRecipes {
    private static ItemStack toWildcardStack(Block block) {
        return new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE);
    }

    private static ItemStack toWildcardStack(Item item) {
        return new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE);
    }

    public static void registerOredict() {
        OreDictionary.registerOre("stone", toWildcardStack(DRAGON_STONE));
        OreDictionary.registerOre("cobblestone", toWildcardStack(DRAGON_STONE));
        OreDictionary.registerOre("dirt", toWildcardStack(DRAGON_DIRT));
        OreDictionary.registerOre("grass", toWildcardStack(DRAGON_GRASS));
        OreDictionary.registerOre("stickWood", toWildcardStack(INFUSED_STICK));
        OreDictionary.registerOre("plankWood", toWildcardStack(DRACONY_PLANKS));
        OreDictionary.registerOre("logWood", toWildcardStack(DRACONY_LOG));
        OreDictionary.registerOre("treeSapling", toWildcardStack(DRACONY_SAPLING));
        OreDictionary.registerOre("treeLeaves", toWildcardStack(DRACONY_LEAVES));
        OreDictionary.registerOre("leather", toWildcardStack(DRAGON_SCALE));

    }

    public static void registerRecipes() {
        DragonScalesAPI.cauldronRecipes.add(new DragonScalesAPI.CauldronRecipe(new ItemStack(LEATHER), 3, new ItemStack(DRAGON_SCALE)).registerDefaultDispenserBehaviour());
        DragonScalesAPI.cauldronRecipes.add(new DragonScalesAPI.CauldronRecipe(new ItemStack(GOLD_INGOT), 3, new ItemStack(DRAGON_METAL)).registerDefaultDispenserBehaviour());
        DragonScalesAPI.cauldronRecipes.add(new DragonScalesAPI.CauldronRecipe(new ItemStack(EMERALD), 3, new ItemStack(DRAGON_CRYSTAL)).registerDefaultDispenserBehaviour());
        DragonScalesAPI.cauldronRecipes.add(new DragonScalesAPI.CauldronRecipe(new ItemStack(GLASS_BOTTLE), 1, new ItemStack(DRAGON_ESSENCE_BOTTLE)).registerDefaultDispenserBehaviour());
        DragonScalesAPI.cauldronRecipes.add(new DragonScalesAPI.CauldronRecipe(new ItemStack(STICK), 0, new ItemStack(INFUSED_STICK)).registerDefaultDispenserBehaviour());
        DragonScalesAPI.cauldronRecipes.add(
                new DragonScalesAPI.CauldronRecipe(new ItemStack(BRICK_BLOCK), 1, new ItemStack(DRAGON_BRICKS)) {
                    public ItemStack getOutput(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
                        ItemStack output = super.getOutput(input, essentiaLevel, world, z, z, z, player);
                        output.stackSize = input.stackSize;
                        return output;
                    }

                    public int getEssentiaCost(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
                        return MathHelper.clamp_int((int) ((float) (input.stackSize / 64) * 3) + 1, 1, 3);
                    }

                    public int getItemCost(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
                        return input.stackSize;
                    }
                }.registerDefaultDispenserBehaviour()
        );
        DragonScalesAPI.cauldronRecipes.add(
                new DragonScalesAPI.CauldronRecipe(new ItemStack(SOUL_SAND), 1, new ItemStack(END_STONE)) {
                    public ItemStack getOutput(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
                        ItemStack output = super.getOutput(input, essentiaLevel, world, z, z, z, player);
                        output.stackSize = MathHelper.clamp_int(input.stackSize, 0, 16);
                        return output;
                    }

                    public int getEssentiaCost(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
                        return MathHelper.clamp_int((int) ((float) (input.stackSize / 16) * 3) + 1, 1, 3);
                    }

                    public int getItemCost(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
                        return MathHelper.clamp_int(input.stackSize, 0, 16);
                    }
                }.registerDefaultDispenserBehaviour()
        );

        DragonScalesAPI.cauldronRecipes.add(
                new DragonScalesAPI.CauldronRecipe(new ItemStack(DRAGON_ESSENCE_BLOCK), 3, new ItemStack(DRAGON_GRASS)) {
                    public ItemStack getOutput(ItemStack input, int essentiaLevel, World world, int x, int y, int z, EntityPlayer player) {
                        DVUtils.InfectBiomeAsync(world, x, y - 1, z, (7 + world.rand.nextInt(10)));
                        return null;
                    }
                }.registerDefaultDispenserBehaviour()
        );

        GameRegistry.addShapelessRecipe(new ItemStack(DRAGON_ESSENCE_BOTTLE), new ItemStack(POTIONITEM, 1, 0), new ItemStack(DRAGON_ESSENCE_SHARD));

        GameRegistry.addShapedRecipe(
                new ItemStack(DRAGON_SCALE_BLOCK, 1),
                "DDD", "DDD", "DDD",
                'D', DRAGON_SCALE
        );

        GameRegistry.addShapelessRecipe(new ItemStack(DRAGON_SCALE, 9), DRAGON_SCALE_BLOCK);
        GameRegistry.addShapelessRecipe(new ItemStack(DRAGON_ESSENCE_SHARD, 9), DRAGON_ESSENCE_BLOCK);
        GameRegistry.addShapelessRecipe(new ItemStack(DRACONY_PLANKS, 6), DRACONY_LOG);
        GameRegistry.addShapedRecipe(new ItemStack(INFUSED_STICK, 4),
                "P", "P",
                'P', DRACONY_PLANKS
        );

        GameRegistry.addShapedRecipe(
                new ItemStack(DRAGON_MULTI, 1),
                "MMM", "MSM", "DSD",
                'M', DRAGON_METAL,
                'S', INFUSED_STICK,
                'D', DRAGON_SCALE
        );

        GameRegistry.addShapedRecipe(
                new ItemStack(DRAGON_SWORD, 1),
                " M ", " MD", "DS ",
                'M', DRAGON_METAL,
                'S', INFUSED_STICK,
                'D', DRAGON_SCALE
        );

        GameRegistry.addShapedRecipe(
                new ItemStack(SCALES_HELM, 1),
                "DDD", "DED", "   ",
                'D', DRAGON_SCALE,
                'E', DRAGON_ESSENCE_BOTTLE.setContainerItem(GLASS_BOTTLE)
        );

        GameRegistry.addShapedRecipe(
                new ItemStack(SCALES_CHESTPLATE, 1),
                "DED", "DDD", "DDD",
                'D', DRAGON_SCALE,
                'E', DRAGON_ESSENCE_BOTTLE.setContainerItem(GLASS_BOTTLE)
        );

        GameRegistry.addShapedRecipe(
                new ItemStack(SCALES_LEGGINGS, 1),
                "DDD", "DED", "D D",
                'D', DRAGON_SCALE,
                'E', DRAGON_ESSENCE_BOTTLE.setContainerItem(GLASS_BOTTLE)
        );

        GameRegistry.addShapedRecipe(
                new ItemStack(SCALES_BOOTS, 1),
                "E E", "D D", "D D",
                'D', DRAGON_SCALE,
                'E', DRAGON_ESSENCE_BOTTLE.setContainerItem(GLASS_BOTTLE)
        );

        GameRegistry.addShapedRecipe(
                new ItemStack(DRAGON_SCEPTER, 1),
                "MMD", " SD", "DSE",
                'D', DRAGON_SCALE,
                'M', DRAGON_METAL,
                'S', INFUSED_STICK,
                'E', DRAGON_ESSENCE_BOTTLE.setContainerItem(GLASS_BOTTLE)
        );
    }
}
