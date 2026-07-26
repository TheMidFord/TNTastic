package malicedev.tntastic.Entity;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityPrimedTNT;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;

import java.util.HashSet;
import java.util.Set;

public class EntityPrimedShaftingTNT extends EntityPrimedTNT {
	public EntityPrimedShaftingTNT(World world) {
		super(world);

	}
	public int explosions;
	public static final Set<Block<?>> INDESTRUCTIBLES = new HashSet<>();
	static {
		INDESTRUCTIBLES.add(Blocks.OBSIDIAN);
		INDESTRUCTIBLES.add(Blocks.MOBSPAWNER);
		INDESTRUCTIBLES.add(Blocks.BEDROCK);
	}


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
		this.move(0, this.yd, 0);
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
				explodeVertical();
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

	public void explodeVertical(){
		TilePos queryPos = new TilePos();

		for (int rows = -2; rows <3; rows++){
			for (int columns = -2; columns<3; columns++){
				var block = world.getBlockType(queryPos.set(MathHelper.floor(this.x),MathHelper.floor(this.y),MathHelper.floor(this.z)).add(rows,-1,columns));
				if (block != Blocks.AIR && !block.isEntityTile && !INDESTRUCTIBLES.contains(block)){
					block.dropWithCause(world,EnumDropCause.EXPLOSION,queryPos,this.world.getBlockData(queryPos),null,null);
					this.world.setBlockTypeNotify(queryPos, Blocks.AIR);
					block.onDestroyedByExplosion(this.world,queryPos);
					this.world.spawnParticle("explode", (this.z+rows), (this.y-1),(this.z+columns),1,1,1, 0, false);
				}
			}

		}
		this.world.spawnParticle("smoke", (this.x), (this.y),(this.z), 1,1, 1, 0, false);
	}
}
