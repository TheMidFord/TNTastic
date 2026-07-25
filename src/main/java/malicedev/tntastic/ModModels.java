package malicedev.tntastic;

import malicedev.tntastic.Entity.EntityPrimedShaftingTNT;
import malicedev.tntastic.Renderer.PrimedShaftingTNTRenderer;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.entity.EntityRendererFallingBlock;
import net.minecraft.client.render.entity.EntityRendererTNT;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.core.util.helper.Side;


public class ModModels {
	public void initBlockModels(BlockModelDispatcher blockModelDispatcher){
		blockModelDispatcher.addDispatch(new BlockModelStandard<>(ModBlocks.Shafting_TNT)
			.setAllTextures("tntastic:block/shafting_tnt/shafting_tnt_side")
			.setTex("tntastic:block/shafting_tnt/shafting_tnt_top", Side.TOP)
			.setTex("tntastic:block/shafting_tnt/shafting_tnt_bottom", Side.BOTTOM)
		);
	}
	public void initItemModels(ItemModelDispatcher itemModelDispatcher){

	}
	public void initEntityModels(EntityRendererDispatcher dispatcher){
		dispatcher.assignRenderer(EntityPrimedShaftingTNT.class,new PrimedShaftingTNTRenderer());
	}
}
