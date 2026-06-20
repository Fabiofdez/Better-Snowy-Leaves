package fabiofdez.snowyleaves.resource;

import net.minecraft.network.chat.Component;

public class BuiltInResourcePack {
  private final String id;
  private final Component name;
  private final boolean defaultEnabled;

  private BuiltInResourcePack(String id, Component name, boolean defaultEnabled) {
    this.id = id;
    this.name = name;
    this.defaultEnabled = defaultEnabled;
  }

  public static BuiltInResourcePack create(String id, Component name, boolean defaultEnabled) {
    return new BuiltInResourcePack(id, name, defaultEnabled);
  }

  public static BuiltInResourcePack create(String id, String name, boolean defaultEnabled) {
    return create(id, Component.literal(name), defaultEnabled);
  }

  public static BuiltInResourcePack create(String id, Component name) {
    return create(id, name, false);
  }

  public static BuiltInResourcePack create(String id, String name) {
    return create(id, Component.literal(name));
  }

  public String id() {
    return id;
  }

  public Component name() {
    return name;
  }

  public boolean defaultEnabled() {
    return defaultEnabled;
  }
}
