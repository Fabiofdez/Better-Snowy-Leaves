package fabiofdez.snowyleaves.mixin;

import fabiofdez.snowyleaves.compat.ModCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
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
  @Unique
  private static final BooleanProperty SNOWY;

  public SnowyLeavesMixin(Properties properties) {
    super(properties);
  }

  @Inject(method = "<init>", at = @At("TAIL"))
  protected void SnowyLeaves$init(/*? if >= 1.21.5 >> 'BlockBehaviour' */float f, BlockBehaviour.Properties properties, CallbackInfo ci) {
    if (!this.isSnowyLeaves()) return;

    this.registerDefaultState(this.defaultBlockState().setValue(SNOWY, false));
  }

  @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
  protected void SnowyLeaves$createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
    if (ModCompat.hasConflict(this.getClass())) return;

    builder.add(SNOWY);
  }

  @Inject(method = "getStateForPlacement", at = @At("TAIL"), cancellable = true)
  protected void SnowyLeaves$getStateForPlacement(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir) {
    if (ctx.getLevel().isClientSide()) return;

    BlockState state = cir.getReturnValue();
    if (state == null || !isSnowyLeaves(state)) return;

    Level level = ctx.getLevel();
    BlockPos pos = ctx.getClickedPos();
    BlockState above = level.getBlockState(pos.above());

    cir.setReturnValue(state.setValue(SNOWY, above.is(BlockTags.SNOW)));
  }

  @Inject(method = "updateShape", at = @At("TAIL"), cancellable = true)
      //? <= 1.21.1
  //protected void SnowyLeaves$updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos, CallbackInfoReturnable<BlockState> cir) {
      //? > 1.21.1
  protected void SnowyLeaves$updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource src, CallbackInfoReturnable<BlockState> cir) {
    if (!isSnowyLeaves(state)) return;

    BlockState blockAbove = level.getBlockState(currentPos.above());
    cir.setReturnValue(cir.getReturnValue().setValue(SNOWY, blockAbove.is(BlockTags.SNOW)));
  }

  @Unique
  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  private boolean isSnowyLeaves() {
    return isSnowyLeaves(this.defaultBlockState());
  }

  @Unique
  private static boolean isSnowyLeaves(BlockState state) {
    return state.hasProperty(SNOWY);
  }

  static {
    SNOWY = BlockStateProperties.SNOWY;
  }
}
