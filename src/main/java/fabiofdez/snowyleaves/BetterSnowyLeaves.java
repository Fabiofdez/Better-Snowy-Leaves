package fabiofdez.snowyleaves;

import fabiofdez.snowyleaves.platform.Platform;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import fabiofdez.snowyleaves.platform.fabric.FabricPlatform;
//?} neoforge {
/*import fabiofdez.snowyleaves.platform.neoforge.NeoforgePlatform;
 *///?} forge {
/*import fabiofdez.snowyleaves.platform.forge.ForgePlatform;
 *///?}

@SuppressWarnings("LoggingSimilarMessage")
public class BetterSnowyLeaves {

	public static final String MOD_ID = /*$ mod_id*/ "snowyleaves";
	public static final String MOD_VERSION = /*$ mod_version*/ "1.2.1";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "Better Snowy Leaves";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {
		LOGGER.info("Initializing {} on {}", MOD_ID, BetterSnowyLeaves.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}

	public static void onInitializeClient() {
		LOGGER.info("Initializing {} Client on {}", MOD_ID, BetterSnowyLeaves.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}

	static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?} forge {
		/*return new ForgePlatform();
		 *///?}
	}

	public static ResourceLocation id(String path) {
		//? >= 1.21 {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
		 //?} < 1.21 {
		/*return new ResourceLocation(MOD_ID, path);
		*///?}
	}

	public static ResourceLocation id(String namespace, String path) {
		//? >= 1.21 {
		return ResourceLocation.fromNamespaceAndPath(namespace, path);
		 //?} < 1.21 {
		/*return new ResourceLocation(namespace, path);
		*///?}
	}
}
