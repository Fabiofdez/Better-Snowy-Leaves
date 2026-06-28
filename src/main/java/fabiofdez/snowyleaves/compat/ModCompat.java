package fabiofdez.snowyleaves.compat;

import java.util.Arrays;

public class ModCompat {
  private static final String[] MOD_CONFLICTS = new String[]{
      "net.satisfy.alpinewhispers",
      "com.legacy.blue_skies",
      "net.zepalesque.redux"
  };

  public static boolean hasConflict(Class<?> blockClass) {
    String blockClasspath = blockClass.toString();
    return Arrays.stream(MOD_CONFLICTS).anyMatch(blockClasspath::contains);
  }
}
