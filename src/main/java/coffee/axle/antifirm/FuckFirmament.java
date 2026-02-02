package coffee.axle.antifirm;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FuckFirmament implements ClientModInitializer {
	public static final String MOD_ID = "fuckfirmament";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("Meow! (Prime debugging)");
	}
}