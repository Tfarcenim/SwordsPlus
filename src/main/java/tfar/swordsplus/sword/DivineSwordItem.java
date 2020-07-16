package tfar.swordsplus.sword;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.world.World;

public class DivineSwordItem extends GoddessSwordItem {
	public DivineSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isRemote && worldIn.getWorldInfo().getGameTime() % 120 == 0 && entityIn instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)entityIn;
			if (player.getFoodStats().getFoodLevel() >= 20) {
				player.heal(1);
			}
		}
	}
}
