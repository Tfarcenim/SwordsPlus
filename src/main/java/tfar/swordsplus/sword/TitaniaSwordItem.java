package tfar.swordsplus.sword;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class TitaniaSwordItem extends GreatBladeItem {
	public TitaniaSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (attacker.getRNG().nextDouble() < .40) {
			target.addPotionEffect(new EffectInstance(Effects.SLOWNESS,200,1));
		}
		return super.hitEntity(stack, target, attacker);
	}
}
