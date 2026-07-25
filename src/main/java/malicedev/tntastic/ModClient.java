package malicedev.tntastic;

import net.fabricmc.api.ClientModInitializer;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.util.dependency.Key;

import static malicedev.tntastic.Main.LOGGER;
import static malicedev.tntastic.Main.MOD_ID;

public class ModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		LOGGER.info("Initializing Client-Specific Stuff!");
		ClientEvents.BLOCK_MODEL_RELOAD.listen(Key.of(MOD_ID),(t)->new ModModels().initBlockModels(t));
		ClientEvents.ITEM_MODEL_RELOAD.listen(Key.of(MOD_ID),(t)->new ModModels().initItemModels(t));
		ClientEvents.ENTITY_RENDERER_RELOAD.listen(Key.of(MOD_ID),(t)->new ModModels().initEntityModels(t));
	}
}
