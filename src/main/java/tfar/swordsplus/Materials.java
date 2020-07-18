package tfar.swordsplus;

import net.minecraft.item.crafting.Ingredient;

public class Materials {

	public static final ToolMaterial venol = new ToolMaterial(0,130,1,1,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial dianus = new ToolMaterial(0,145,1,2,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial inoue = new ToolMaterial(0,600,1,5,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial umbra = new ToolMaterial(0,300,1,4,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial duff_cake = new ToolMaterial(0,20,1,-2,10,
					() -> Ingredient.EMPTY);

	public static final ToolMaterial great_blade = new ToolMaterial(0,700,1,6,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial titania = new ToolMaterial(0,700,1,8,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial calibur = new ToolMaterial(0,16,1,10,10,
					() -> Ingredient.EMPTY);

	public static final ToolMaterial arturia = new ToolMaterial(0,25,1,12,10,
					() -> Ingredient.EMPTY);

	public static final ToolMaterial blazing = new ToolMaterial(0,130,1,1,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial megaera = new ToolMaterial(0,130,1,4,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial ayin = new ToolMaterial(0,10,1,16,10,
					() -> Ingredient.EMPTY);

	public static final ToolMaterial goddess = new ToolMaterial(0,300,1,2,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial pure_goddess = new ToolMaterial(0,500,1,8,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial divine_goddess = new ToolMaterial(0,1000,1,8,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial chosen = new ToolMaterial(0,2000,1,14,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));

	public static final ToolMaterial hero = new ToolMaterial(0,4000,1,14,10,
					() -> Ingredient.fromItems(SwordsPlus.tauvelite_dust));
}
