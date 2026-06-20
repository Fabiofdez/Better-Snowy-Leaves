package fabiofdez.snowyleaves.platform.fabric;

//? fabric {

import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import fabiofdez.snowyleaves.BetterSnowyLeaves;
import fabiofdez.snowyleaves.resource.BuiltInResourcePack;
import fabiofdez.snowyleaves.resource.ResourcePacks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    BetterSnowyLeaves.onInitializeClient();

    FabricLoader.getInstance().getModContainer(BetterSnowyLeaves.MOD_ID).ifPresent((container) -> {
      addPack(container, ResourcePacks.DEFAULT);
      addPack(container, ResourcePacks.STAY_TRUE_COMPAT);
    });
  }

  private static void addPack(ModContainer container, BuiltInResourcePack pack) {
    ResourceManagerHelper.registerBuiltinResourcePack(
        BetterSnowyLeaves.id(pack.id()),
        container,
        pack.name(),
        activationFor(pack)
    );
  }

  private static ResourcePackActivationType activationFor(BuiltInResourcePack pack) {
    return pack.defaultEnabled() ? ResourcePackActivationType.DEFAULT_ENABLED : ResourcePackActivationType.NORMAL;
  }
}
//?}
