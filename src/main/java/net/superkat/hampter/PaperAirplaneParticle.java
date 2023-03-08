package net.superkat.hampter;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class PaperAirplaneParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    int totalDelay = this.random.nextBetween(1, 20);
    int delay = this.random.nextBetween(1, 10);
    int gravityDelay = this.random.nextBetween(1, 40);
    boolean gravityUp = true;

    PaperAirplaneParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;
        this.maxAge = this.random.nextBetween(20, 1200);
        this.scale = 0.20F + this.random.nextFloat() / 12;
        this.velocityX = velocityX + this.random.nextFloat() / 8;
//        this.velocityY = velocityY - 0.03 - this.random.nextFloat() / 16;
        this.velocityY = 0.05F;
        this.velocityZ = velocityZ + this.random.nextFloat() / 8;
        this.x = x + this.random.nextFloat();
        this.y = y + this.random.nextFloat();
        this.z = z + this.random.nextFloat();
        this.angle = 0F;
        this.setSpriteForAge(spriteProvider);
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge || this.scale <= 0) {
            this.markDead();
        } else {
            if(this.age >= gravityDelay && gravityUp) {
                this.velocityY = this.random.nextBetweenExclusive(1, 10) * 0.007;
                gravityDelay = gravityDelay + this.random.nextBetween(1, 40);
                gravityUp = false;
            }
            if(this.age >= gravityDelay && !gravityUp) {
                this.velocityY = this.random.nextBetweenExclusive(1, 10) * -0.007;
                gravityDelay = gravityDelay + this.random.nextBetween(1, 40);
                gravityUp = true;
            }
            if(this.age == totalDelay) {
                totalDelay += delay;
                this.changeVel();
//                this.changeVel();
//                this.changeVel();
            }
            if(this.age + 10 == this.maxAge) {
                this.scale *= 0.85;
            }
            this.move(this.velocityX, this.velocityY, this.velocityZ);
        }
    }

    public void changeVel() {
        boolean multiplyOrDivideX = this.random.nextBoolean();
//        boolean multiplyOrDivideY = this.random.nextBoolean();
        boolean multiplyOrDivideZ = this.random.nextBoolean();
        if(multiplyOrDivideX) {
            this.velocityX += this.random.nextDouble() / 16;
        } else {
            this.velocityX -= this.random.nextDouble() / 16;
        }
//        if(multiplyOrDivideY) {
//            this.velocityY *= this.random.nextDouble();
//        } else {
//            this.velocityY /= this.random.nextDouble();
//        }
        if(multiplyOrDivideZ) {
            this.velocityZ += this.random.nextDouble() / 16;
        } else {
            this.velocityZ -= this.random.nextDouble() / 16;
        }
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new PaperAirplaneParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
