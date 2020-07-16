package tfar.swordsplus.sword;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ChosenBladeItem extends DivineSwordItem {
	public ChosenBladeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (worldIn.isRemote) {
			if (playerIn.getMaxHealth() == playerIn.getHealth()) {
				stack.getOrCreateTag().putBoolean("empowered", true);
				playerIn.addPotionEffect(new EffectInstance(Effects.SLOWNESS,200,2));
			}
		}
		return ActionResult.resultSuccess(stack);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return stack.hasTag() && stack.getTag().getBoolean("empowered") || super.hasEffect(stack);
	}

}
