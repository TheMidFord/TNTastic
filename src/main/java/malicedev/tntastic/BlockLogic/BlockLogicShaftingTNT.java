package malicedev.tntastic.BlockLogic;


import malicedev.tntastic.Entity.EntityPrimedShaftingTNT;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicTNT;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityPrimedTNT;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemFireStriker;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockLogicShaftingTNT extends BlockLogicTNT {
	public BlockLogicShaftingTNT(@NotNull Block<?> block) {
		super(block);
	}

	@Override
	public void ignite(@NotNull World world, @Nullable Player player, @NotNull TilePosc tilePos, boolean sound) {
		if (world.isClientSide) {
			if (player != null && player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemFireStriker) {
				player.inventory.getCurrentItem().damageItem(1, player);
			}

		} else {
			world.setBlockTypeNotify(tilePos, Blocks.AIR);
			EntityPrimedShaftingTNT entityPrimedShaftingTNT = new EntityPrimedShaftingTNT(world, (double)((float)tilePos.x() + 0.5F), (double)((float)tilePos.y() + 0.5F), (double)((float)tilePos.z() + 0.5F));
			world.entityJoinedWorld(entityPrimedShaftingTNT);
			if (sound) {
				world.playSoundAtEntity((Entity)null, entityPrimedShaftingTNT, "tile.tnt.fuse", 1.0F, 1.0F);
			}

			if (player != null && player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemFireStriker) {
				player.inventory.getCurrentItem().damageItem(1, player);
			}

		}
	}
}
