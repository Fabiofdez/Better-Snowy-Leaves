package fabiofdez.snowyleaves.platform.neoforge;

//? neoforge {

/*import fabiofdez.snowyleaves.BetterSnowyLeaves;
import fabiofdez.snowyleaves.resource.BuiltInResourcePack;
import fabiofdez.snowyleaves.resource.ResourcePacks;
import net.neoforged.api.distmarker.Dist;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = BetterSnowyLeaves.MOD_ID, /^? if < 1.21.11 >> 'value' ^/ bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NeoforgeClientEventSubscriber {

	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		BetterSnowyLeaves.onInitializeClient();
	}

	@SubscribeEvent
	public static void addFeaturePacks(final AddPackFindersEvent event) {
    addPack(event, ResourcePacks.DEFAULT);
    addPack(event, ResourcePacks.STAY_TRUE_COMPAT);
  }

  private static void addPack(final AddPackFindersEvent event, BuiltInResourcePack pack) {
    event.addPackFinders(
        BetterSnowyLeaves.id("resourcepacks/" + pack.id()),
        PackType.CLIENT_RESOURCES,
        pack.name(),
        PackSource.BUILT_IN,
        pack.defaultEnabled(),
        Pack.Position.TOP
    );
  }
}
*///?}
