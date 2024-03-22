package net.vakror.registry.jamesregistryapi.api.event;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;

@SuppressWarnings("all")
public class RegistryEvents {
    //@James usually in a fabric/architectury sense registries are static and non frozen anyways, so the registry wouldnt be event based
    //but instead be just a map you can add yourself to, i am keeping the event wrapper for now
    public static final Event<SetupRegistry> SETUP_REGISTRY_EVENT = EventFactory.createEventResult();

    public interface SetupRegistry {
        EventResult post(SetupRegistryEvent event);
    }
}