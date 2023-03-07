package net.superkat.hampter.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.superkat.hampter.HampterMain;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class HampterEntity extends HostileEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    int shinyChance = this.random.nextBetween(1, 4000);

    public HampterEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 10.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6f);
    }

    @Override
    protected void initGoals() {
        //Movement
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new BreatheAirGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(3, new RevengeGoal(this));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));

        //Monsters
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, SpiderEntity.class, true));
        this.targetSelector.add(5, new ActiveTargetGoal<>(this, WardenEntity.class, true));

        //Villagers
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, MerchantEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, WanderingTraderEntity.class, true));

        //Pillagers
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PillagerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, VindicatorEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, EvokerEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, VexEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, IllusionerEntity.class, true));

        //Other
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, SnowGolemEntity.class, true));
    }

    public void tickMovement() {
        super.tickMovement();
        int airplaneChance = this.random.nextBetween(1, 1000);
        if(airplaneChance == 1 || shinyChance == 1) {
            this.world.addParticle(ParticleTypes.END_ROD, this.getParticleX(0.6), this.getRandomBodyY(), this.getParticleZ(0.6), 0.0, 0.0, 0.0);
            HampterMain.LOGGER.info("AIRPLANE SPAWNED!!!!!!");
        }
        if(shinyChance == 1) {
            this.world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, this.getParticleX(0.6), this.getRandomBodyY(), this.getParticleZ(0.6), 0.0, 0.0, 0.0);
        }

    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hampter.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hampter.idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.hampter.attack", false));
            this.handSwinging = false;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
        animationData.addAnimationController(new AnimationController(this, "attackController",
                0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_AXOLOTL_IDLE_AIR;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ENDER_DRAGON_GROWL;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ENDER_DRAGON_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PARROT_STEP, 0.15f, 1.0f);
    }

}
