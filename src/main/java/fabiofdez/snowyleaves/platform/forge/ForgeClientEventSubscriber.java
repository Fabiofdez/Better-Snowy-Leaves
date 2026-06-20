package fabiofdez.snowyleaves.platform.forge;

//? forge {

/*import fabiofdez.snowyleaves.BetterSnowyLeaves;
import fabiofdez.snowyleaves.resource.BuiltInResourcePack;
import fabiofdez.snowyleaves.resource.ResourcePacks;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.resource.PathPackResources;

import java.nio.file.Path;

@Mod.EventBusSubscriber(modid = BetterSnowyLeaves.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClientEventSubscriber {

	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		BetterSnowyLeaves.onInitializeClient();
	}

	@SubscribeEvent
	public static void addFeaturePacks(final AddPackFindersEvent event) {
	  if (event.getPackType() != PackType.CLIENT_RESOURCES) return;

	  IModFileInfo modFileInfo = ModList.get().getModFileById(BetterSnowyLeaves.MOD_ID);
    if (modFileInfo == null) return;
    IModFile modFile = modFileInfo.getFile();

    addPack(modFile, event, ResourcePacks.DEFAULT);
    addPack(modFile, event, ResourcePacks.STAY_TRUE_COMPAT);
  }

  private static void addPack(IModFile modFile, final AddPackFindersEvent event, BuiltInResourcePack pack) {
    Path sourcePath = modFile.findResource("resourcepacks/" + pack.id());

    Pack createdPack = Pack.readMetaAndCreate(
        pack.id(),
        pack.name(),
        pack.defaultEnabled(),
        (id) -> new PathPackResources(id, true, sourcePath),
        PackType.CLIENT_RESOURCES,
        Pack.Position.TOP,
        PackSource.BUILT_IN
    );

    event.addRepositorySource((packConsumer) -> packConsumer.accept(createdPack));
  }
}
*///?}
