package tfar.swordsplus.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CaliburSwordItem extends SwordItem {
	private final int regenRate;

	public CaliburSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder, int regenRate) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		this.regenRate = regenRate;
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isRemote && isSelected) {
			if (worldIn.getWorldInfo().getGameTime() % regenRate == 0) {
				stack.setDamage(stack.getDamage() - 1);
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Aurum Series").applyTextStyle(TextFormatting.GRAY));
	}

}
