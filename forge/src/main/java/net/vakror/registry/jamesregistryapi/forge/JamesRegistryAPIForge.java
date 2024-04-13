package net.vakror.registry.jamesregistryapi.forge;

import dev.architectury.platform.forge.EventBuses;
import net.vakror.registry.jamesregistryapi.JamesRegistryAPI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JamesRegistryAPI.MOD_ID)
public class JamesRegistryAPIForge {
    public JamesRegistryAPIForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventHandler.init();

        EventBuses.registerModEventBus(JamesRegistryAPI.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        JamesRegistryAPI.init();
    }
}