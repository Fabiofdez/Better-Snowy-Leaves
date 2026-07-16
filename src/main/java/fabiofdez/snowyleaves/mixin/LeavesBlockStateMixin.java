package fabiofdez.snowyleaves.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import fabiofdez.snowyleaves.resource.ResourcePacks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;

//? if < 1.21.5 {
/*import net.minecraft.client.renderer.block.model.BlockModelDefinition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
*///? } else {
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.resources.model.BlockStateModelLoader;
//? }

@SuppressWarnings("rawtypes")
//? < 1.21.5
//@Mixin(BlockModelDefinition.class)
//? >= 1.21.5
@Mixin(BlockStateModelLoader.class)
public class LeavesBlockStateMixin {

  @Unique
  private static final Gson gson = new Gson();

  @Unique
  private static final String DEFAULT_PACK_TITLE = ResourcePacks.DEFAULT.name().getString();

  @Unique
  private static boolean snowyLeavesEnabled;

  //? if < 1.21.5 {
  /*@Inject(method = "fromJsonElement", at = @At("HEAD"))
  private static void snowyleaves$modifyJson(BlockModelDefinition.Context ctx, JsonElement original, CallbackInfoReturnable cir) {
    checkPackEnabled();
    if (!snowyLeavesEnabled) return;

    Block targetBlock = ctx.getDefinition().getOwner();
    ResourceLocation id = BuiltInRegistries.BLOCK.getKey(targetBlock);
    if (!id.getPath().contains("leaves")) return;

    modifyBlockStates(original);
  }
  *///?} else {
  @Inject(method = "loadBlockStates", at = @At("HEAD"))
  private static void snowyleaves$checkResourcesEnabled(CallbackInfoReturnable cir) {
    checkPackEnabled();
  }

  @Inject(method = "method_65720", at = @At(value = "INVOKE", target = "Lcom/mojang/serialization/Codec;parse(Lcom/mojang/serialization/DynamicOps;Ljava/lang/Object;)Lcom/mojang/serialization/DataResult;"))
  private static void snowyleaves$modifyJson(CallbackInfoReturnable cir, @Local ResourceLocation id, @Local JsonElement original) {
    if (!snowyLeavesEnabled || !id.getPath().contains("leaves")) return;
    modifyBlockStates(original);
  }
  //?}

  @Unique
  private static void checkPackEnabled() {
    PackRepository packs = Minecraft.getInstance().getResourcePackRepository();
    snowyLeavesEnabled = packs.getSelectedPacks().stream().anyMatch(LeavesBlockStateMixin::matchesDefaultPackTitle);
  }

  @Unique
  private static boolean matchesDefaultPackTitle(Pack pack) {
    return pack.getTitle().getString().equals(DEFAULT_PACK_TITLE);
  }

  @Unique
  private static void modifyBlockStates(JsonElement original) {
    JsonObject blockstates = original.getAsJsonObject();
    JsonObject variants = blockstates.getAsJsonObject("variants");
    if (variants == null) return;

    String firstKey = variants.keySet().stream().findFirst().orElse("");
    JsonElement defaultState = variants.get(firstKey);
    if (defaultState == null) return;

    JsonObject newVariants = new JsonObject();
    newVariants.add("snowy=false", defaultState.deepCopy());

    JsonElement snowyState;
    if (defaultState.isJsonArray()) {
      List<JsonObject> snowyStates = defaultState
          .getAsJsonArray()
          .asList()
          .stream()
          .map(LeavesBlockStateMixin::getSnowyVariant)
          .toList();

      snowyState = gson.toJsonTree(snowyStates);
    } else {
      snowyState = getSnowyVariant(defaultState);
    }

    newVariants.add("snowy=true", snowyState);
    blockstates.add("variants", newVariants);
  }

  @Unique
  private static JsonObject getSnowyVariant(JsonElement elt) {
    JsonObject variant = elt.getAsJsonObject();
    String model = variant.get("model").getAsString();

    List<String> modelPathParts = Arrays.asList(model.split("/"));
    int lastIdx = modelPathParts.size() - 1;
    String modelName = modelPathParts.get(lastIdx);
    modelPathParts.set(lastIdx, "%s_snowy".formatted(modelName));

    variant.addProperty("model", String.join("/", modelPathParts));

    return variant;
  }
}
