package net.vakror.registryapi.fabric;

import net.fabricmc.api.ModInitializer;
import net.vakror.registry.jamesregistryapi.JamesRegistryAPI;

public class JamesRegistryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        JamesRegistryAPI.init();
    }
}