package malicedev.tntastic.Entity;

import net.minecraft.client.Minecraft;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityPrimedTNT;
import net.minecraft.core.world.World;

public class EntityPrimedShaftingTNT extends EntityPrimedTNT {
	public EntityPrimedShaftingTNT(World world) {
		super(world);
	}
	public int explosions;


	public EntityPrimedShaftingTNT(World world, double x, double y, double z) {
		super(world, x, y, z);
		this.explosions = 0;
		this.xd=this.zd=0;
		this.fuse = 40;
	}

	@Override
	public void tick() {
		this.checkOnWater(true);
		this.checkOnWater(true);
		this.pushTime *= 0.98F;
		if (this.pushTime < 0.05F || (double)this.pushTime < (double)0.25F && this.onGround) {
			this.pushTime = 0.0F;
		}

		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		this.yd -= 0.04;
		this.move(this.xd, this.yd, this.zd);
		this.xd *= 0.98;
		this.yd *= 0.98;
		this.zd *= 0.98;
		if (this.onGround) {
			this.xd *= 0.7;
			this.zd *= 0.7;
			this.yd *= (double)-0.5F;
		}

		if (this.fuse-- <= 0) {
			if (!this.world.isClientSide) {
				this.world.createExplosion((Entity)null, this.x, this.y + (double)0.5F, this.z, 4.0F);
				this.move(0,0.3,0);
				this.explosions++;
				this.fuse = 2;


			}
		} else {
			this.world.spawnParticle("smoke", this.x, this.y + (double)0.5F, this.z, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
		}

		if (this.explosions >= 64) {
			if (!this.world.isClientSide) {
				this.remove();
			} else {
				this.remove();
			}
		} else {
			this.world.spawnParticle("smoke", this.x, this.y + (double)0.5F, this.z, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
		}

	}
}
