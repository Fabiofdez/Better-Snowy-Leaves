package fabiofdez.snowyleaves.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class SnowyLeavesBehavior {

  public static final BooleanProperty SNOWY;

  public static BlockState update(BlockState state, BlockGetter level, BlockPos pos) {
    if (state == null || !isSnowyLeaves(state)) return state;

    BlockState above = level.getBlockState(pos.above());
    return state.setValue(SNOWY, isSnowySetting(above));
  }

  public static boolean isSnowySetting(BlockState above) {
    return above.is(BlockTags.SNOW);
  }

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  public static boolean isSnowyLeaves(BlockState state) {
    return state.hasProperty(SNOWY);
  }

  static {
    SNOWY = BlockStateProperties.SNOWY;
  }
}
