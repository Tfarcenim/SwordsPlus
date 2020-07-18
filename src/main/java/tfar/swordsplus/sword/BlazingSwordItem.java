package tfar.swordsplus.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlazingSwordItem extends SwordItem {
	public final int burnTime;

	public BlazingSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder, int burnTime) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		this.burnTime = burnTime;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Incendia Series").applyTextStyle(TextFormatting.GRAY));
	}
}
