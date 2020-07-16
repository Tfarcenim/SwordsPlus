package tfar.swordsplus.sword;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class VenolRapier extends SwordItem {
	public VenolRapier(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (attacker.getRNG().nextDouble() < .25) {
			target.addPotionEffect(new EffectInstance(Effects.POISON,100));
		}
		return super.hitEntity(stack, target, attacker);
	}
}
