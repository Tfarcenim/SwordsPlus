package tfar.swordsplus;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.network.rcon.ClientThread;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tfar.swordsplus.bow.RepairableBowItem;
import tfar.swordsplus.bow.SwiftBowItem;
import tfar.swordsplus.sword.*;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SwordsPlus.MODID)
public class SwordsPlus {
	// Directly reference a log4j logger.

	public static final String MODID = "swordsplus";

	private static final Logger LOGGER = LogManager.getLogger();

	public static Block tauvelite_ore;
	public static Item tauvelite_dust;

	public static Block ezralite_ore;
	public static Item ezralite_dust;

	public static Item sword_hilt;
	public static Item tempered_edge;

	public static Item goddess_gem;
	public static Item earth_elemental_gem;
	public static Item fire_elemental_gem;
	public static Item water_elemental_gem;
	public static Item holy_elemental_gem;
	public static Item shadow_elemental_gem;
	public static Item venol_rapier;
	public static Item dianus_blade;
	public static Item inoue;
	public static Item umbra_blade;
	public static Item duff_cake_sword;
	public static Item great_blade;
	public static Item titania_blade;
	public static Item calibur_blade;
	public static Item arturia_blade;
	public static Item arturia_excelsion;
	public static Item blazing_blade;
	public static Item megaera_blade;
	public static Item ayin_blade;
	public static Item goddess_blade;
	public static Item pure_goddess_blade;
	public static Item divine_goddess_blade;
	public static Item chosen_blade;
	public static Item hero_blade;
	public static Item swift_bow;
	public static Item diamant_bow;


	public SwordsPlus() {
		// Register the setup method for modloading
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);
		// Register the doClientStuff method for modloading
		bus.addListener(this::doClientStuff);

		bus.addGenericListener(Block.class,this::blocks);
		bus.addGenericListener(Item.class,this::items);
		EVENT_BUS.addListener(this::damage);
		EVENT_BUS.addListener(this::critical);
		EVENT_BUS.addListener(this::knockback);
		EVENT_BUS.addListener(this::hurt);

		if (FMLEnvironment.dist == Dist.CLIENT) {
			EVENT_BUS.addListener(this::clientTick);
		}
	}

	private static int bowHeld = 0;

	private void clientTick(TickEvent.ClientTickEvent e) {
		PlayerEntity player = Minecraft.getInstance().player;
		if (player != null && player.getActiveItemStack().getItem() instanceof SwiftBowItem) {
			bowHeld++;
			if (bowHeld > 10) {
				Minecraft.getInstance().playerController.onStoppedUsingItem(player);
				bowHeld = 0;
			}
		}
	}

	private void critical(CriticalHitEvent e) {
		PlayerEntity player = e.getPlayer();
		if (player.getHeldItemMainhand().getItem() == SwordsPlus.dianus_blade) {
			e.setDamageModifier(e.getDamageModifier() + 1);
		}
	}

	private void knockback(LivingKnockBackEvent e) {
		if (e.getOriginalAttacker() instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity)e.getOriginalAttacker();
			if (livingEntity.getHeldItemMainhand().getItem() == SwordsPlus.titania_blade) {
				e.setStrength(e.getStrength() + 1);
			}
		}
	}

	private void hurt(LivingHurtEvent e) {
		if (e.getSource().getTrueSource() instanceof LivingEntity) {
			LivingEntity attacker = (LivingEntity) e.getSource().getTrueSource();
			ItemStack sword = attacker.getHeldItemMainhand();
			if (sword.getItem() == arturia_excelsion && sword.getDamage() >= sword.getMaxDamage()) {
				e.setAmount(1);
			}
			if (sword.getItem() instanceof ChosenBladeItem) {
				e.setAmount(e.getAmount() + 4);
			}
		}
	}

	private void damage(LivingDamageEvent e) {
			LivingEntity victim = e.getEntityLiving();
			ItemStack vSword = victim.getHeldItemMainhand();
			if (vSword.getItem() instanceof ChosenBladeItem) {
				if (vSword.hasTag())
					vSword.getTag().remove("empowered");
		}
			Entity attacker = e.getSource().getTrueSource();
			if (attacker instanceof LivingEntity) {
				ItemStack sword = ((LivingEntity) attacker).getHeldItemMainhand();
				if (sword.getItem() instanceof BlazingSwordItem) {
					victim.setFireTimer(victim.getFireTimer() + ((BlazingSwordItem)sword.getItem()).burnTime);
				}
			}
	}

	//not threadsafe
	private void setup(final FMLCommonSetupEvent event) {



			for (BiomeManager.BiomeType biomeType : BiomeManager.BiomeType.values()) {

				for (BiomeManager.BiomeEntry biomeEntry : BiomeManager.getBiomes(biomeType)) {
					biomeEntry.biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
									Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
													ezralite_ore.getDefaultState(), 4))
													.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 16))));

					biomeEntry.biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
									Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
													tauvelite_ore.getDefaultState(), 4))
													.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(1, 0, 0, 16))));
			}
		}
	}



	private void doClientStuff(final FMLClientSetupEvent event) {
	}

	public void blocks(final RegistryEvent.Register<Block> e) {
		tauvelite_ore = register(new OreBlock(Block.Properties.from(Blocks.IRON_ORE)),"tauvelite_ore", e.getRegistry());
		ezralite_ore = register(new OreBlock(Block.Properties.from(Blocks.IRON_ORE)),"ezralite_ore", e.getRegistry());
	}

	public void items(final RegistryEvent.Register<Item> e) {
		register(new BlockItem(ezralite_ore,new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),"ezralite_ore", e.getRegistry());
		register(new BlockItem(tauvelite_ore,new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)),"tauvelite_ore", e.getRegistry());

		ezralite_dust = register(new Item(new Item.Properties().group(ItemGroup.MISC)),"ezralite_dust", e.getRegistry());
		tauvelite_dust = register(new Item(new Item.Properties().group(ItemGroup.MISC)),"tauvelite_dust", e.getRegistry());

		sword_hilt = register(new Item(new Item.Properties().group(ItemGroup.MISC)),"sword_hilt", e.getRegistry());

		tempered_edge = register(new Item(new Item.Properties().group(ItemGroup.MISC)),"tempered_edge", e.getRegistry());

		goddess_gem = register(new Item(new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)),"goddess_gem", e.getRegistry());

		fire_elemental_gem = register(new Item(new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)),"fire_elemental_gem", e.getRegistry());
		earth_elemental_gem = register(new Item(new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)),"earth_elemental_gem", e.getRegistry());
		holy_elemental_gem = register(new Item(new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)),"holy_elemental_gem", e.getRegistry());
		shadow_elemental_gem = register(new Item(new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)),"shadow_elemental_gem", e.getRegistry());
		water_elemental_gem = register(new Item(new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)),"water_elemental_gem", e.getRegistry());

		venol_rapier = register(new VenolRapier(Materials.venol,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT)),"venol_rapier", e.getRegistry());
		dianus_blade = register(new SwordItem(Materials.dianus,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT)),"dianus_blade", e.getRegistry());

		inoue = register(new InoueItem(Materials.inoue,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)),"inoue", e.getRegistry());
		umbra_blade = register(new UmbraBlade(Materials.umbra,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT)),"umbra_blade", e.getRegistry());

		duff_cake_sword = register(new DuffSwordItem(Materials.duff_cake,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT)),"duff_cake_sword", e.getRegistry());

		great_blade = register(new GreatBladeItem(Materials.great_blade,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)),"great_blade", e.getRegistry());

		titania_blade = register(new TitaniaSwordItem(Materials.titania,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)),"titania_blade", e.getRegistry());

		calibur_blade = register(new CaliburSwordItem(Materials.calibur,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON),15 * 20),"calibur_blade", e.getRegistry());

		arturia_blade = register(new CaliburSwordItem(Materials.arturia,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON),8 * 20),"arturia_blade", e.getRegistry());

		arturia_excelsion = register(new ArturiaExcelsionItem(Materials.arturia,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.EPIC),8 * 20),"arturia_excelsion", e.getRegistry());

		blazing_blade = register(new BlazingSwordItem(Materials.blazing,3,-2.2f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON),180),"blazing_blade", e.getRegistry());

		megaera_blade = register(new MegaeraSwordItem(Materials.megaera,3,-2.2f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON),360),"megaera_blade", e.getRegistry());

		ayin_blade = register(new AyinBladeItem(Materials.ayin,3,-2.2f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.RARE)),"ayin_blade", e.getRegistry());

		goddess_blade = register(new GoddessSwordItem(Materials.goddess,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT)),"goddess_blade", e.getRegistry());

		pure_goddess_blade = register(new GoddessSwordItem(Materials.pure_goddess,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)),"pure_goddess_blade", e.getRegistry());

		divine_goddess_blade = register(new DivineSwordItem(Materials.divine_goddess,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.RARE)),"divine_goddess_blade", e.getRegistry());

		chosen_blade = register(new ChosenBladeItem(Materials.chosen,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.RARE)),"chosen_blade", e.getRegistry());

		hero_blade = register(new ChosenBladeItem(Materials.hero,3,-2.4f,new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.EPIC)),"hero_blade", e.getRegistry());

		swift_bow = register(new SwiftBowItem(new Item.Properties().group(ItemGroup.COMBAT).setNoRepair()),"swift_bow", e.getRegistry());

		diamant_bow = register(new RepairableBowItem(new Item.Properties().group(ItemGroup.COMBAT)),"diamant_bow", e.getRegistry());



	}

	public static final List<Item> items = new ArrayList<>();

	private static <T extends IForgeRegistryEntry<T>> T register(T obj, String name, IForgeRegistry<T> registry) {
		registry.register(obj.setRegistryName(new ResourceLocation(MODID, name)));
		if (obj instanceof Item)
			items.add((Item) obj);
		return obj;
	}
}