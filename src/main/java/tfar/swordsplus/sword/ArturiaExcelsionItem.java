package tfar.swordsplus.sword;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class ArturiaExcelsionItem extends CaliburSwordItem {
	public ArturiaExcelsionItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder, int regenRate) {
		super(tier, attackDamageIn, attackSpeedIn, builder, regenRate);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		return stack.getDamage() < stack.getMaxDamage() - 1 && super.hitEntity(stack, target, attacker);
	}
}
