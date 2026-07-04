package fabiofdez.snowyleaves.platform;

public interface Platform {
	boolean isModLoaded(String modId);

  boolean isModLoading(String modId);

	ModLoader loader();

	String mcVersion();

	boolean isDevelopmentEnvironment();

	default boolean isDebug() {
		return isDevelopmentEnvironment();
	}

	enum ModLoader {
		FABRIC, NEOFORGE, FORGE, QUILT
	}
}
