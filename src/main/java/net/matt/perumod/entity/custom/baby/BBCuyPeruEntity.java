package net.matt.perumod.entity.custom.baby;

import net.matt.perumod.entity.ModEntities;
import net.matt.perumod.entity.custom.CuyPeruEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class BBCuyPeruEntity extends Animal {
    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CARROT, Items.APPLE, Items.WHEAT, Items.SUNFLOWER);
    private boolean isFed = false;
    private AgeableMob parent;

    public  BBCuyPeruEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setBaby(true);
    }
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        super.mobInteract(player, hand);

        ItemStack itemStack = player.getItemInHand(hand);

        if (isFood(itemStack)) {
            this.isFed = true;
            this.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
            itemStack.shrink(1);
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }

        if (isFed && this.isBaby()) {
            transformToAdult();
        }
    }
    public void setParent(AgeableMob parent) {
        this.parent = parent;
    }

    public AgeableMob getParent() {
        return this.parent;
    }


    private void transformToAdult() {

        if (!this.isBaby()) {
            return;
        }

        this.setBaby(false);


        CuyPeruEntity adultEntity = ModEntities.CUY_PERUANO.get().create(this.level());
        if (adultEntity != null) {
            adultEntity.moveTo(this.position()); //
            this.level().addFreshEntity(adultEntity);
        }

        this.remove(RemovalReason.DISCARDED);
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }



    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.1D) {
            @Override
            public boolean canUse() {
                return super.canUse() && getParent() instanceof CuyPeruEntity;
            }
        });
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D);

    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        BBCuyPeruEntity offspring = new BBCuyPeruEntity(ModEntities.BB_CUY_PERUANO.get(), pLevel);
        offspring.setParent(this);
        return offspring;
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return FOOD_ITEMS.test(pStack);
    }
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.RABBIT_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.RABBIT_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.RABBIT_DEATH;
    }
}
