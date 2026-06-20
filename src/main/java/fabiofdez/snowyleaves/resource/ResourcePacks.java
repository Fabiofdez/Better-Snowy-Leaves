package fabiofdez.snowyleaves.resource;

public class ResourcePacks {
  public static final BuiltInResourcePack DEFAULT;
  public static final BuiltInResourcePack STAY_TRUE_COMPAT;

  static {
    DEFAULT = BuiltInResourcePack.create("default", "Default Snowy leaves", true);
    STAY_TRUE_COMPAT = BuiltInResourcePack.create("stay_true_compat", "Snowy leaves X Stay True");
  }
}
