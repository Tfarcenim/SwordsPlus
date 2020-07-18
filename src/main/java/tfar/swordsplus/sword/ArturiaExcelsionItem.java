package tfar.swordsplus.sword;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ArturiaExcelsionItem extends CaliburSwordItem {
	public ArturiaExcelsionItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder, int regenRate) {
		super(tier, attackDamageIn, attackSpeedIn, builder, regenRate);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		return stack.getDamage() < stack.getMaxDamage() - 1 && super.hitEntity(stack, target, attacker);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		return stack.getDamage() < stack.getMaxDamage() - 2 && super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}
}
