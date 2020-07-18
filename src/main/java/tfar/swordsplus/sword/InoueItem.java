package tfar.swordsplus.sword;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class InoueItem extends SwordItem {
	public InoueItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public  void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isRemote && isSelected) {
			Vec3d pos = entityIn.getPositionVec();
			boolean mobsNearby = !worldIn.getEntitiesWithinAABB(MonsterEntity.class,new AxisAlignedBB(pos.x - 9,pos.y - 9,pos.z - 9,pos.x + 9,pos.y + 9,pos.z + 9)).isEmpty();
				stack.getOrCreateTag().putBoolean("mobsNearby",mobsNearby);
				if (entityIn instanceof LivingEntity) {
					((LivingEntity) entityIn).clearActivePotions();
				}
		}
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return stack.hasTag() && stack.getTag().getBoolean("mobsNearby") || super.hasEffect(stack);
	}



}
