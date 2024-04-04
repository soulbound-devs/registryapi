package net.vakror.registry.jamesregistryapi.fabric;

import net.vakror.registry.jamesregistryapi.JamesRegistryAPI;
import net.fabricmc.api.ModInitializer;

public class JamesRegistryAPIFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        JamesRegistryAPI.init();
    }
}