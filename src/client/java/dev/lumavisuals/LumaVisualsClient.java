package dev.lumavisuals;

import dev.lumavisuals.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LumaVisualsClient implements ClientModInitializer {
    public static final String MOD_ID = "lumavisuals";
    public static final Logger LOGGER = LoggerFactory.getLogger("Luma Visuals");
    private boolean announced;

    @Override
    public void onInitializeClient() {
        ConfigManager.load();
        LOGGER.info("Luma Visuals Alpha loaded with {} quality", ConfigManager.get().quality);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!announced && client.player != null) {
                announced = true;
                client.player.sendMessage(Text.literal("§aLuma Visuals Alpha запущен §7[" + ConfigManager.get().quality + "]"), false);
            }
            if (client.player == null) announced = false;
        });
    }
}
