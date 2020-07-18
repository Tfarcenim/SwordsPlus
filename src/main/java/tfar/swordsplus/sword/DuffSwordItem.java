package tfar.swordsplus.sword;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DuffSwordItem extends SwordItem {
	public DuffSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

		if (!worldIn.isRemote) {
			playerIn.heal(2);
			playerIn.getFoodStats().addStats(4,6);
			playerIn.getHeldItem(handIn).damageItem(2,playerIn,playerEntity -> playerEntity.sendBreakAnimation(handIn));
		}
		return ActionResult.resultConsume(playerIn.getHeldItem(handIn));
	}
}
