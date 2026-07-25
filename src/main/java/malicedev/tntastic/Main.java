package malicedev.tntastic;

import malicedev.tntastic.Entity.EntityPrimedShaftingTNT;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.util.collection.NamespaceID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.Event;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.dependency.Key;

import javax.naming.Name;
import java.util.Properties;

public class Main implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("tntastic", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static int itemId;
	public static int blockId;
	static {
		Properties prop = new Properties();
		prop.setProperty("starting_block_id","9100");
		prop.setProperty("starting_item_id","30000");
		ConfigHandler config = new ConfigHandler(MOD_ID,prop);

		blockId = config.getInt("starting_block_id");
		itemId = config.getInt("starting_item_id");

		config.updateConfig();
	}

	@Override
	public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), this::beforeGameStart);
		CommonEvents.AFTER_GAME_START.listen(Key.of(MOD_ID),this::afterGameStart);

		LOGGER.info("TNTastic! initialized.");
	}

	public void beforeGameStart() {
		ModBlocks.init();
		ModItems.init();
		EntityDispatcher.getInstance().addMapping(EntityPrimedShaftingTNT.class,new NamespaceID(MOD_ID,"shafting_tnt"),EntityPrimedShaftingTNT::new);

	}

	public void afterGameStart() {

	}
}
