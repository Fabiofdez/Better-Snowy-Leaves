package fabiofdez.snowyleaves.mixin;

import fabiofdez.snowyleaves.block.SnowyLeavesBehavior;
import fabiofdez.snowyleaves.compat.ModCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
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

//? if > 1.21.1 {
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
//? } else {
/*import net.minecraft.world.level.LevelAccessor;
    *///? }

@Mixin(LeavesBlock.class)
public class SnowyLeavesMixin extends Block {

  public SnowyLeavesMixin(Properties properties) {
    super(properties);
  }

  @Inject(method = "<init>", at = @At("TAIL"))
  protected void SnowyLeaves$init(/*? if >= 1.21.5 >> 'BlockBehaviour' */float f, BlockBehaviour.Properties properties, CallbackInfo ci) {
    BlockState initialState = this.defaultBlockState();
    if (!SnowyLeavesBehavior.isSnowyLeaves(initialState)) return;

    this.registerDefaultState(initialState.setValue(SnowyLeavesBehavior.SNOWY, false));
  }

  @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
  protected void SnowyLeaves$createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
    if (ModCompat.hasConflict(this.getClass())) return;

    builder.add(SnowyLeavesBehavior.SNOWY);
  }

  @Inject(method = "getStateForPlacement", at = @At("TAIL"), cancellable = true)
  protected void SnowyLeaves$getStateForPlacement(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir) {
    Level level = ctx.getLevel();
    if (level.isClientSide()) return;

    BlockPos pos = ctx.getClickedPos();
    BlockState state = cir.getReturnValue();
    cir.setReturnValue(SnowyLeavesBehavior.update(state, level, pos));
  }

  @Inject(method = "updateShape", at = @At("TAIL"), cancellable = true)
      //? <= 1.21.1
  //protected void SnowyLeaves$updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos, CallbackInfoReturnable<BlockState> cir) {
    //? > 1.21.1
    protected void SnowyLeaves$updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource src, CallbackInfoReturnable<BlockState> cir) {
    cir.setReturnValue(SnowyLeavesBehavior.update(state, level, pos));
  }
}
