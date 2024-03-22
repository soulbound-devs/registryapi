package net.vakror.registryapi.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.vakror.registry.jamesregistryapi.JamesRegistryAPI;

@Mod(JamesRegistryAPI.MOD_ID)
public class JamesRegistryForge {
    public JamesRegistryForge() {
		// Submit our event bus to let architectury post our content on the right time
        EventBuses.registerModEventBus(JamesRegistryAPI.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        JamesRegistryAPI.init();
    }
}