package fabiofdez.snowyleaves.platform.fabric;

//? fabric {

import fabiofdez.snowyleaves.BetterSnowyLeaves;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.network.chat.Component;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		BetterSnowyLeaves.onInitializeClient();

    ModContainer container = FabricLoader
        .getInstance()
        .getModContainer(BetterSnowyLeaves.MOD_ID)
        .orElseThrow();

    ResourceManagerHelper.registerBuiltinResourcePack(
        BetterSnowyLeaves.id("default"),
        container,
        Component.literal("Default Snowy leaves"),
        ResourcePackActivationType.DEFAULT_ENABLED
    );
    ResourceManagerHelper.registerBuiltinResourcePack(
        BetterSnowyLeaves.id("stay_true_compat"),
        container,
        Component.literal("Snowy leaves X Stay True"),
        ResourcePackActivationType.NORMAL
    );
	}
}
//?}
