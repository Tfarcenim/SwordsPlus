package tfar.swordsplus.bow;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class SwiftBowItem extends RepairableBowItem {
	public SwiftBowItem(Properties builder) {
		super(builder);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	/**
	 * Called when the player stops using an Item (stops holding the right mouse button).
	 */
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);

		boolean flag = !playerIn.findAmmo(stack).isEmpty();

		ActionResult<ItemStack> ret = ForgeEventFactory.onArrowNock(stack, worldIn, playerIn,handIn, flag);
		if (ret != null) return ActionResult.resultFail(stack);

		if (!playerIn.abilities.isCreativeMode && !flag) {
			return ActionResult.resultFail(stack);
		} else {

			boolean isInfinite = playerIn.abilities.isCreativeMode;
			ItemStack ammo = playerIn.findAmmo(stack);

			int i = 0;
			i = ForgeEventFactory.onArrowLoose(stack, worldIn, playerIn, i, !ammo.isEmpty() || flag);

			if (!ammo.isEmpty() || isInfinite) {
				if (ammo.isEmpty()) {
					ammo = new ItemStack(Items.ARROW);
				}

				float scale = 1;
				if (!(scale < 0.1D)) {
					boolean flag2 = playerIn.abilities.isCreativeMode || (ammo.getItem() instanceof ArrowItem && ((ArrowItem) ammo.getItem()).isInfinite(ammo, stack, playerIn));
					if (!worldIn.isRemote) {
						ArrowItem arrowitem = (ArrowItem) (ammo.getItem() instanceof ArrowItem ? ammo.getItem() : Items.ARROW);
						AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, ammo, playerIn);
						abstractarrowentity = customeArrow(abstractarrowentity);
						abstractarrowentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, scale * 3.0F, 1.0F);
						abstractarrowentity.setIsCritical(true);

						int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
						if (j > 0) {
							abstractarrowentity.setDamage(abstractarrowentity.getDamage() + j * 0.5D + 0.5D);
						}

						int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
						if (k > 0) {
							abstractarrowentity.setKnockbackStrength(k);
						}

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
							abstractarrowentity.setFire(100);
						}

						stack.damageItem(1, playerIn, (p_220009_1_) -> {
							p_220009_1_.sendBreakAnimation(playerIn.getActiveHand());
						});
						if (flag2 || playerIn.abilities.isCreativeMode && (ammo.getItem() == Items.SPECTRAL_ARROW || ammo.getItem() == Items.TIPPED_ARROW)) {
							abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
						}

						worldIn.addEntity(abstractarrowentity);
					}

					worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + scale * 0.5F);
					if (!flag2 && !playerIn.abilities.isCreativeMode) {
						ammo.shrink(1);
						if (ammo.isEmpty()) {
							playerIn.inventory.deleteStack(ammo);
						}
					}

					playerIn.addStat(Stats.ITEM_USED.get(this));
				}
			}
		}
		playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(),8);
		return ActionResult.resultPass(playerIn.getHeldItem(handIn));
	}

	@Override
	public int getItemEnchantability(ItemStack stack) {
		return 0;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return false;
	}
}
