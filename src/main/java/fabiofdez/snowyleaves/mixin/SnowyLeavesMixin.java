package fabiofdez.snowyleaves.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import fabiofdez.snowyleaves.block.SnowyLeavesBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
//? <= 1.21.1
//import net.minecraft.world.level.LevelAccessor;
//? > 1.21.1
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public class SnowyLeavesMixin extends Block {

  public SnowyLeavesMixin(Properties properties) {
    super(properties);
  }

  @Inject(method = "<init>", at = @At("TAIL"))
  protected void snowyleaves$init(/*? if >= 1.21.5 >> 'BlockBehaviour' */float f, BlockBehaviour.Properties properties, CallbackInfo ci) {
    BlockState initialState = this.defaultBlockState();
    if (!SnowyLeavesBehavior.isSnowyLeaves(initialState)) return;

    this.registerDefaultState(initialState.setValue(SnowyLeavesBehavior.SNOWY, false));
  }

  @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
  protected void snowyleaves$createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
    builder.add(SnowyLeavesBehavior.SNOWY);
  }

  @Inject(method = "getStateForPlacement", at = @At("TAIL"), cancellable = true)
  protected void snowyleaves$getStateForPlacement(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir) {
    Level level = ctx.getLevel();
    if (level.isClientSide()) return;

    BlockPos pos = ctx.getClickedPos();
    BlockState state = cir.getReturnValue();
    cir.setReturnValue(SnowyLeavesBehavior.update(state, level, pos));
  }

  @Inject(method = "updateShape", at = @At("TAIL"), cancellable = true)
  protected void snowyleaves$updateShape(CallbackInfoReturnable<BlockState> cir, @Local(argsOnly = true, ordinal = 0) BlockState state, @Local(argsOnly = true) /*? if <= 1.21.1 { *//*LevelAccessor*//*? } else { */LevelReader/*? } */ level, @Local(argsOnly = true, ordinal = 0) BlockPos pos) {
    cir.setReturnValue(SnowyLeavesBehavior.update(state, level, pos));
  }
}
