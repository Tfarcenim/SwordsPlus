package tfar.swordsplus.data;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import tfar.swordsplus.SwordsPlus;

public class ModBlockstateProvider extends BlockStateProvider {
	public ModBlockstateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, SwordsPlus.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {

		registerOreModel(SwordsPlus.ezralite_ore);
		registerOreModel(SwordsPlus.tauvelite_ore);

	}

	void registerOreModel(Block b) {
		String name = b.getRegistryName().getPath();
		ModelFile model = models().getBuilder(name)
						.parent(models().getExistingFile(mcLoc("block/cube_all")))
						.texture("all", new ResourceLocation(SwordsPlus.MODID, "block/" + name));
		getVariantBuilder(b).forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
	}
}