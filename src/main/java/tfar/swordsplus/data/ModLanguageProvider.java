package tfar.swordsplus.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import org.codehaus.plexus.util.StringUtils;
import tfar.swordsplus.SwordsPlus;

public class ModLanguageProvider extends LanguageProvider {
	public ModLanguageProvider(DataGenerator gen) {
		super(gen, SwordsPlus.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		for (Item item : SwordsPlus.items) {
			add(item.getTranslationKey(), getNameFromItem(item));
		}
	}

	public static String getNameFromItem(Item item){
		return StringUtils.capitaliseAllWords(item.getTranslationKey().split("\\.")[2].replace("_", " "));
	}
}