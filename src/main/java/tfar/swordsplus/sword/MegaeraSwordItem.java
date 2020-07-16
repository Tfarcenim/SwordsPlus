package tfar.swordsplus.sword;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class MegaeraSwordItem extends BlazingSwordItem {
	public MegaeraSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder, int burnTime) {
		super(tier, attackDamageIn, attackSpeedIn, builder, burnTime);
	}

	/**
	 * @param stack
	 * @param worldIn
	 * @param entityIn
	 * @param itemSlot
	 * @param isSelected
	 * @see net.minecraft.block.DaylightDetectorBlock#updatePower(BlockState, World, BlockPos)
	 */

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isRemote) {

			if (worldIn.getWorldInfo().getGameTime() % 600 == 0) {

				int i = worldIn.getLightFor(LightType.SKY, entityIn.getPosition()) - worldIn.getSkylightSubtracted();
				float f = worldIn.getCelestialAngleRadians(1.0F);
				if (i > 0) {
					float f1 = f < (float) Math.PI ? 0.0F : (float) Math.PI * 2F;
					f = f + (f1 - f) * 0.2F;
					i = Math.round((float) i * MathHelper.cos(f));
				}
				i = MathHelper.clamp(i, 0, 15);
				if (i > 8) {
					stack.setDamage(stack.getDamage() - 1);
				}
			}

			if (worldIn.getWorldInfo().getGameTime() % 20 == 0 && entityIn.isInWater()) {
				if (entityIn instanceof LivingEntity) {
					stack.damageItem(1, (LivingEntity) entityIn, entity -> entity.sendBreakAnimation(EquipmentSlotType.MAINHAND));
				}
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

		ItemStack stack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);

		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			return ActionResult.resultPass(stack);
		} else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.resultPass(stack);
		} else {
			BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
			BlockPos blockpos = blockraytraceresult.getPos();
			Direction direction = blockraytraceresult.getFace();
			BlockPos blockpos1 = blockpos.offset(direction);
			if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos1, direction, stack)) {
				BlockState blockstate1 = worldIn.getBlockState(blockpos);
				if (blockstate1.getBlock() instanceof IBucketPickupHandler && blockstate1.getBlock() == Blocks.LAVA) {
					Fluid fluid = ((IBucketPickupHandler) blockstate1.getBlock()).pickupFluid(worldIn, blockpos, blockstate1);
					if (fluid != Fluids.EMPTY) {
						playerIn.addStat(Stats.ITEM_USED.get(this));
						playerIn.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, 1.0F, 1.0F);
						stack.setDamage(0);
						return ActionResult.resultSuccess(stack);
					}
				}
				return ActionResult.resultFail(stack);
			} else {
				return ActionResult.resultFail(stack);
			}
		}
	}
}
