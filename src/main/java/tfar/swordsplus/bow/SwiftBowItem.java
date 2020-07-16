package tfar.swordsplus.bow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class SwiftBowItem extends RepairableBowItem {
	public SwiftBowItem(Properties builder) {
		super(builder);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}



	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		if (count >= 10) {
			player.stopActiveHand();
			onItemRightClick(player.world, (PlayerEntity) player, Hand.MAIN_HAND);
		}
	}

	@Override
	public int getItemEnchantability(ItemStack stack) {
		return 0;
	}
}
