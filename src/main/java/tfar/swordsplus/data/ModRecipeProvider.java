package tfar.swordsplus.data;


import net.minecraft.block.Blocks;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import tfar.swordsplus.SwordsPlus;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
	public ModRecipeProvider(DataGenerator p_i48262_1_) {
		super(p_i48262_1_);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.goddess_gem)
						.key('a', SwordsPlus.ezralite_dust)
						.key('b', Blocks.DIAMOND_BLOCK)
						.patternLine("aaa")
						.patternLine("aba")
						.patternLine("aaa")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.tempered_edge)
						.key('a', Tags.Items.INGOTS_IRON)
						.patternLine("  a")
						.patternLine(" a ")
						.patternLine("a  ")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.sword_hilt)
						.key('a', Tags.Items.INGOTS_IRON)
						.key('b', Tags.Items.LEATHER)
						.patternLine("aa")
						.patternLine("ba")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.earth_elemental_gem)
						.key('a', ItemTags.SAPLINGS)
						.key('b', SwordsPlus.goddess_gem)
						.patternLine("aaa")
						.patternLine("aba")
						.patternLine("aaa")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.fire_elemental_gem)
						.key('a', Items.LAVA_BUCKET)
						.key('b', SwordsPlus.goddess_gem)
						.patternLine("aaa")
						.patternLine("aba")
						.patternLine("aaa")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.water_elemental_gem)
						.key('a', Items.WATER_BUCKET)
						.key('b', SwordsPlus.goddess_gem)
						.patternLine("aaa")
						.patternLine("aba")
						.patternLine("aaa")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.holy_elemental_gem)
						.key('a', Items.GLOWSTONE)
						.key('b', SwordsPlus.goddess_gem)
						.patternLine("aaa")
						.patternLine("aba")
						.patternLine("aaa")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.shadow_elemental_gem)
						.key('a', Items.ENDER_PEARL)
						.key('b', Blocks.SOUL_SAND)
						.key('c', SwordsPlus.goddess_gem)
						.patternLine("aba")
						.patternLine("bcb")
						.patternLine("aba")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.venol_rapier)
						.key('a', Tags.Items.INGOTS_IRON)
						.key('b', SwordsPlus.tempered_edge)
						.key('c', Items.FERMENTED_SPIDER_EYE)
						.key('d', SwordsPlus.sword_hilt)
						.patternLine("a b")
						.patternLine(" c ")
						.patternLine("d a")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.dianus_blade)
						.key('a', Tags.Items.STORAGE_BLOCKS_GOLD)
						.key('b', SwordsPlus.tempered_edge)
						.key('c', Items.OBSIDIAN)
						.key('d', SwordsPlus.sword_hilt)
						.patternLine("a b")
						.patternLine(" c ")
						.patternLine("d a")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.inoue)
						.key('a', Items.GHAST_TEAR)
						.key('b', SwordsPlus.tempered_edge)
						.key('c', Items.DIAMOND)
						.key('d', SwordsPlus.sword_hilt)
						.patternLine("a b")
						.patternLine(" c ")
						.patternLine("d a")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.umbra_blade)
						.key('a', Tags.Items.STORAGE_BLOCKS_LAPIS)
						.key('b', SwordsPlus.tempered_edge)
						.key('c', Items.SOUL_SAND)
						.key('d', SwordsPlus.sword_hilt)
						.patternLine("a b")
						.patternLine(" c ")
						.patternLine("d a")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.duff_cake_sword)
						.key('a', Items.CAKE)
						.key('b', SwordsPlus.tempered_edge)
						.key('d', SwordsPlus.sword_hilt)
						.patternLine("a b")
						.patternLine(" a ")
						.patternLine("d a")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.great_blade)
						.key('a', Tags.Items.STORAGE_BLOCKS_IRON)
						.key('b', SwordsPlus.tempered_edge)
						.key('c', Items.DIAMOND)
						.key('d', SwordsPlus.sword_hilt)
						.patternLine("a b")
						.patternLine(" c ")
						.patternLine("d a")
						.build(consumer);

		ShapelessRecipeBuilderNoCriteria.shapelessRecipe(SwordsPlus.titania_blade)
						.addIngredient(SwordsPlus.great_blade)
						.addIngredient(SwordsPlus.earth_elemental_gem)
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.calibur_blade)
						.key('a', Tags.Items.STORAGE_BLOCKS_GOLD)
						.key('b', SwordsPlus.tempered_edge)
						.key('c', Items.DIAMOND)
						.key('d', SwordsPlus.sword_hilt)
						.patternLine("a b")
						.patternLine(" ca")
						.patternLine("d a")
						.build(consumer);

		ShapelessRecipeBuilderNoCriteria.shapelessRecipe(SwordsPlus.arturia_blade)
						.addIngredient(SwordsPlus.calibur_blade)
						.addIngredient(SwordsPlus.water_elemental_gem)
						.build(consumer);

		ShapelessRecipeBuilderNoCriteria.shapelessRecipe(SwordsPlus.arturia_excelsion)
						.addIngredient(SwordsPlus.arturia_blade)
						.addIngredient(SwordsPlus.holy_elemental_gem)
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.blazing_blade)
						.key('a', Items.BLAZE_ROD)
						.key('b', SwordsPlus.tempered_edge)
						.key('c', Items.FLINT)
						.key('d', SwordsPlus.sword_hilt)
						.patternLine("a b")
						.patternLine(" c ")
						.patternLine("d a")
						.build(consumer);

		ShapelessRecipeBuilderNoCriteria.shapelessRecipe(SwordsPlus.megaera_blade)
						.addIngredient(SwordsPlus.blazing_blade)
						.addIngredient(SwordsPlus.fire_elemental_gem)
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.ayin_blade)
						.key('a', Tags.Items.STORAGE_BLOCKS_DIAMOND)
						.key('b', SwordsPlus.tempered_edge)
						.key('c', Items.ENDER_EYE)
						.key('d', SwordsPlus.sword_hilt)
						.patternLine("a b")
						.patternLine(" c ")
						.patternLine("d a")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.goddess_blade)
						.key('a', Tags.Items.DYES_CYAN)
						.key('b', SwordsPlus.tempered_edge)
						.key('c', Items.GLOWSTONE)
						.key('d', SwordsPlus.sword_hilt)
						.patternLine("a b")
						.patternLine(" c ")
						.patternLine("d a")
						.build(consumer);

		ShapelessRecipeBuilderNoCriteria.shapelessRecipe(SwordsPlus.pure_goddess_blade)
						.addIngredient(SwordsPlus.goddess_blade)
						.addIngredient(SwordsPlus.earth_elemental_gem)
						.build(consumer);

		ShapelessRecipeBuilderNoCriteria.shapelessRecipe(SwordsPlus.divine_goddess_blade)
						.addIngredient(SwordsPlus.pure_goddess_blade)
						.addIngredient(SwordsPlus.water_elemental_gem)
						.build(consumer);

		ShapelessRecipeBuilderNoCriteria.shapelessRecipe(SwordsPlus.chosen_blade)
						.addIngredient(SwordsPlus.divine_goddess_blade)
						.addIngredient(SwordsPlus.fire_elemental_gem)
						.build(consumer);

		ShapelessRecipeBuilderNoCriteria.shapelessRecipe(SwordsPlus.hero_blade)
						.addIngredient(SwordsPlus.divine_goddess_blade)
						.addIngredient(SwordsPlus.shadow_elemental_gem)
						.addIngredient(SwordsPlus.holy_elemental_gem)
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.swift_bow)
						.key('a', Tags.Items.FEATHERS)
						.key('b', Items.STICK)
						.key('c', Tags.Items.STRING)
						.patternLine("abc")
						.patternLine("bac")
						.patternLine("abc")
						.build(consumer);

		ShapedRecipeBuilderNoCriteria.shapedRecipe(SwordsPlus.diamant_bow)
						.key('a', Tags.Items.INGOTS_IRON)
						.key('b', Items.DIAMOND)
						.key('c', Tags.Items.STRING)
						.patternLine(" ac")
						.patternLine("b c")
						.patternLine(" ac")
						.build(consumer);

		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(SwordsPlus.ezralite_ore),SwordsPlus.ezralite_dust,1,200);
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(SwordsPlus.tauvelite_ore),SwordsPlus.tauvelite_dust,1,200);

	}
}