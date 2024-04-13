package net.vakror.registry.jamesregistryapi.forge;

import net.neoforged.fml.common.Mod;
import net.vakror.registry.jamesregistryapi.JamesRegistryAPI;

@Mod(JamesRegistryAPI.MOD_ID)
public class JamesRegistryAPINeoForge {
    public JamesRegistryAPINeoForge() {
		// Submit our event bus to let architectury register our content on the right time
        JamesRegistryAPI.init();
    }
}