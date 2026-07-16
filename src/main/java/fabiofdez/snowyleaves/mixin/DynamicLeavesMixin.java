package fabiofdez.snowyleaves.mixin;

//? forge || (fabric && 1.21.1) || (neoforge && (1.21.1 || 26.1.2)) {

/*import com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? forge {
/^import fabiofdez.snowyleaves.block.SnowyLeavesBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
^///?}

@SuppressWarnings({"UnusedMixin", "unused"})
@Mixin(DynamicLeavesBlock.class)
public abstract class DynamicLeavesMixin extends LeavesBlock {

  public DynamicLeavesMixin(/^? if >= 1.21.5 >> 'Properties' ^/float f, Properties properties) {
    super(/^? if >= 1.21.5 >> 'properties' ^/f, properties);
  }

  //? < 26.1
   @Inject(method = "<init>(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V", at = @At("TAIL"))
  //? >= 26.1
  //@Inject(method = "<init>(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;F)V", at = @At("TAIL"))
  protected void snowyleaves$init(CallbackInfo ci) {
    this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.SNOWY, false));
  }

  //? forge {
  /^@Inject(method = "updateShape", at = @At("TAIL"), cancellable = true)
  protected void snowyleaves$updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos, CallbackInfoReturnable<BlockState> cir) {
    cir.setReturnValue(SnowyLeavesBehavior.update(state, level, pos));
  }
  ^///?}
}
*///?}
