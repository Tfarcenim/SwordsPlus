package tfar.swordsplus.sword;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class UmbraBlade extends SwordItem {
	public UmbraBlade(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (attacker.getRNG().nextDouble() < .10) {
			attacker.heal(4);
			stack.damageItem(4, attacker, livingEntity -> livingEntity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
		}
		return super.hitEntity(stack, target, attacker);
	}
}
