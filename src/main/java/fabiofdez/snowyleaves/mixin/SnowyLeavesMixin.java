package fabiofdez.snowyleaves.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
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

@Mixin(LeavesBlock.class)
public class SnowyLeavesMixin extends Block {
  @Unique
  private static final BooleanProperty SNOWY;

  public SnowyLeavesMixin(Properties properties) {
    super(properties);
  }

  @Inject(method = "<init>", at = @At("RETURN"))
  protected void SnowyLeaves$init(float f, BlockBehaviour.Properties properties, CallbackInfo ci) {
    this.registerDefaultState(this.defaultBlockState().setValue(SNOWY, false));
  }

  @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
  protected void SnowyLeaves$createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
    builder.add(SNOWY);
  }

  @Inject(method = "getStateForPlacement", at = @At("TAIL"), cancellable = true)
  protected void SnowyLeaves$getStateForPlacement(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir) {
    BlockState state = cir.getReturnValue();
    if (state == null) return;

    Level level = ctx.getLevel();
    BlockPos pos = ctx.getClickedPos();
    BlockState above = level.getBlockState(pos.above());

    cir.setReturnValue(state.setValue(SNOWY, above.is(BlockTags.SNOW)));
  }

  @Inject(method = "updateShape", at = @At("TAIL"), cancellable = true)
  protected void SnowyLeaves$updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction dir, BlockPos pos2, BlockState state2, RandomSource src, CallbackInfoReturnable<BlockState> cir) {
    if (dir != Direction.UP) return;
    cir.setReturnValue(cir.getReturnValue().setValue(SNOWY, state2.is(BlockTags.SNOW)));
  }

  static {
    SNOWY = BlockStateProperties.SNOWY;
  }
}