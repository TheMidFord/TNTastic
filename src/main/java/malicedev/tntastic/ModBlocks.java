package malicedev.tntastic;

import malicedev.tntastic.BlockLogic.BlockLogicShaftingTNT;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicTNT;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;

import static malicedev.tntastic.Main.MOD_ID;
import static malicedev.tntastic.Main.blockId;

public class ModBlocks {
	private ModBlocks(){}

	public static Block<?> Shafting_TNT;
	public static Block<?> Tunneling_TNT;


	public static void init(){
		Shafting_TNT = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.SAND)
			.setHardness(0.5F)

			.build("shafting_tnt","shafting_tnt",blockId++,(block)->new BlockLogicShaftingTNT(block));

	}

}
