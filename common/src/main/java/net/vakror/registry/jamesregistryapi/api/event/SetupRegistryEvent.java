package net.vakror.registry.jamesregistryapi.api.event;

import net.vakror.registry.jamesregistryapi.api.AbstractExtension;
import net.vakror.registry.jamesregistryapi.api.RegistryAPI;
import net.vakror.registry.jamesregistryapi.api.context.IRegistrationContext;

public class SetupRegistryEvent {
    public void addRegistry(AbstractExtension<?> extension) {
        RegistryAPI.registerExtension(extension);
    }

    public <CONTEXT extends IRegistrationContext> void addRegistry(AbstractExtension<CONTEXT> extension, CONTEXT context) {
        RegistryAPI.registerExtension(extension, context);
    }
}
