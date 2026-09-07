package fabiofdez.snowyleaves.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import fabiofdez.snowyleaves.block.SnowyLeavesBehavior;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(StateDefinition.Builder.class)
public class StateDefinitionBuilderMixin<O, S extends StateHolder<O, S>> {

  @Shadow
  @Final
  private O owner;

  @Shadow
  @Final
  private Map<String, Property<?>> properties;

  @Inject(method = "add", at = @At("HEAD"))
  private void add(CallbackInfoReturnable<StateDefinition.Builder<O, S>> cir, @Local(argsOnly = true) LocalRef<Property<?>[]> propertiesRef) {
    if (!(owner instanceof Block)) return;

    Property<?> snowy = this.properties.get(SnowyLeavesBehavior.SNOWY.getName());
    if (!(snowy instanceof BooleanProperty)) return;

    List<Property<?>> properties = new ArrayList<>(List.of(propertiesRef.get()));
    if (!properties.contains(BlockStateProperties.SNOWY)) return;

    properties.remove(BlockStateProperties.SNOWY);
    propertiesRef.set(properties.toArray(Property[]::new));
  }
}
