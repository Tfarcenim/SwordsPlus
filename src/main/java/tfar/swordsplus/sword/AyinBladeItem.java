package tfar.swordsplus.sword;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class AyinBladeItem extends SwordItem {
	public AyinBladeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote) {
			if (getRemainingUses(stack) <= 0) {
				playerIn.attackEntityFrom(DamageSource.MAGIC, getSacrificeDamage(stack));
				setRemainingUses(stack,10);
			}
		}
		return ActionResult.resultSuccess(stack);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		return getRemainingUses(stack) > 0 || super.hitEntity(stack, target, attacker);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return getRemainingUses(stack) > 0 || super.hasEffect(stack);
	}

	public static int getRemainingUses(ItemStack stack) {
		return stack.hasTag() ? stack.getTag().getInt("remaining_uses") : 0;
	}

	public static void setRemainingUses(ItemStack stack,int uses) {
		stack.getOrCreateTag().putInt("remaining_uses",uses);
		stack.getTag().putInt("times_activated",stack.getTag().getInt("times_activated") + 1);
	}

	public static int getSacrificeDamage(ItemStack stack) {
		switch (getTimesActivated(stack)){
			case 0:return 8;
			case 1:return 10;
			case 2:return 14;
			case 3:return 18;
			case 4: default:return 20;
		}
	}

	public static int getTimesActivated(ItemStack stack) {
		return stack.hasTag() ? stack.getTag().getInt("times_activated") : 0;
	}
}
