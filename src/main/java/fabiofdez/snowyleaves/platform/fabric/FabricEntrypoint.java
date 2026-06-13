package fabiofdez.snowyleaves.platform.fabric;

//? fabric {

import fabiofdez.snowyleaves.BetterSnowyLeaves;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ModInitializer;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		BetterSnowyLeaves.onInitialize();
		FabricEventSubscriber.registerEvents();
	}
}
//?}
