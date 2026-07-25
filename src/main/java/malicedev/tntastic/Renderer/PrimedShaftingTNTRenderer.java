package malicedev.tntastic.Renderer;

import malicedev.tntastic.Entity.EntityPrimedShaftingTNT;
import malicedev.tntastic.ModBlocks;
import net.minecraft.client.render.block.model.BlockModel;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererTNT;
import net.minecraft.client.render.renderer.BlendFactor;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.client.render.renderer.Shaders;
import net.minecraft.client.render.renderer.State;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class PrimedShaftingTNTRenderer extends EntityRenderer<EntityPrimedShaftingTNT> {


	@Override
	public void render(@NotNull TessellatorGeneral tessellator, @NotNull EntityPrimedShaftingTNT tnt, double x, double y, double z, float yaw, float partialTick) {
		GLRenderer.pushFrame();
		GLRenderer.modelM4f().translate((float)x, (float)y, (float)z);
		if ((float)tnt.fuse - partialTick + 1.0F < 10.0F) {
			float f2 = 1.0F - ((float)tnt.fuse - partialTick + 1.0F) / 10.0F;
			if (f2 < 0.0F) {
				f2 = 0.0F;
			}

			if (f2 > 1.0F) {
				f2 = 1.0F;
			}

			f2 *= f2;
			f2 *= f2;
			float f4 = 1.0F + f2 * 0.3F;
			GLRenderer.modelM4f().scale(f4, f4, f4);
		}

		float f3 = (1.0F - ((float)tnt.fuse - partialTick + 1.0F) / 100.0F) * 0.8F;
		TextureRegistry.worldAtlas.bind();
		BlockModel<?> model = (BlockModel) BlockModelDispatcher.getInstance().getDispatch(ModBlocks.Shafting_TNT);
		model.renderStandalone(tessellator, 0, tnt.getLightIndex(partialTick));
		if (tnt.fuse / 5 % 2 == 0) {
			GLRenderer.pushFrame();
			GLRenderer.setShader(Shaders.COLOR_WORLD);
			GLRenderer.globalSetLightEnabled(false);
			GLRenderer.enableState(State.BLEND);
			GLRenderer.setBlendFunc(BlendFactor.SRC_ALPHA, BlendFactor.DST_ALPHA);
			GLRenderer.setColor4f(1.0F, 1.0F, 1.0F, f3);
			model.renderStandalone(tessellator, 0, tnt.getLightIndex(partialTick));
			GLRenderer.setColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GLRenderer.disableState(State.BLEND);
			GLRenderer.globalSetLightEnabled(true);
			GLRenderer.popFrame();
		}

		GLRenderer.popFrame();
	}}

