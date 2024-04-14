package net.vakror.registry.jamesregistryapi;

import com.google.common.base.Stopwatch;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import net.vakror.registry.jamesregistryapi.api.RegistryAPI;
import net.vakror.registry.jamesregistryapi.api.event.RegistryEvents;
import net.vakror.registry.jamesregistryapi.api.event.SetupRegistryEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JamesRegistryAPI {
	public static final String MOD_ID = "james_registry_api";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static void init() {
		LifecycleEvent.SERVER_BEFORE_START.register((server) -> {
			RegistryEvents.SETUP_REGISTRY_EVENT.invoker().post(new SetupRegistryEvent());
			register();
		});
		ClientLifecycleEvent.CLIENT_STARTED.register((server) -> {
			RegistryEvents.SETUP_REGISTRY_EVENT.invoker().post(new SetupRegistryEvent());
			register();
		});
	}

	public static void register() {
		JamesRegistryAPI.LOGGER.info("Started Registration For Registry API");
		Stopwatch apiStopwatch = Stopwatch.createStarted();
		RegistryAPI.onRegister();
		RegistryAPI.onRegistrationDone();
		apiStopwatch.stop();
		JamesRegistryAPI.LOGGER.info("Finished All Registration, \033[0;31mTook {}\033[0;0m", apiStopwatch);
	}
}
