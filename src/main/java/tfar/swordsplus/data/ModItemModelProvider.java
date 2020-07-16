package tfar.swordsplus.data;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import tfar.swordsplus.SwordsPlus;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, SwordsPlus.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		SwordsPlus.items.forEach(item -> {
			String path = item.getRegistryName().getPath();
			if (item instanceof BlockItem)
				getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
			else if (item instanceof SwordItem) {
				getBuilder(path).parent(new ModelFile.UncheckedModelFile(mcLoc("item/handheld")));
			} else {
				getBuilder(path).parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated")))
								.texture("layer0",modLoc("item/"+path));
			}
		});

	}

	@Override
	public String getName() {
		return "Item Models";
	}
}