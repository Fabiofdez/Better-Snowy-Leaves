package fabiofdez.snowyleaves.resource;

public class ResourcePacks {
  public static final BuiltInResourcePack DEFAULT;
  public static final BuiltInResourcePack STAY_TRUE_COMPAT;
  public static final BuiltInResourcePack RAINBOW_FOLIAGE_COMPAT;

  static {
    DEFAULT = BuiltInResourcePack.create("default", "Default Snowy leaves", true);
    STAY_TRUE_COMPAT = BuiltInResourcePack.create("stay_true_compat", "Snowy leaves X Stay True");
    RAINBOW_FOLIAGE_COMPAT = BuiltInResourcePack.create("rainbow_foliage_compat", "Snowy leaves X Rainbow Foliage (Polytone)");
  }
}
