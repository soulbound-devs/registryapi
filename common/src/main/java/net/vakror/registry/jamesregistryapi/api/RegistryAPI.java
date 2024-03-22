package net.vakror.registry.jamesregistryapi.api;

import com.google.common.base.Stopwatch;
import net.vakror.registry.jamesregistryapi.JamesRegistryAPI;
import net.vakror.registry.jamesregistryapi.api.context.IRegistrationContext;
import org.objectweb.asm.Type;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * This is the main API class that all extensions are stored in and all direct API calls are made through
 * The only methods you should call are {@link #registerExtension} and {@link #setRegistrationContext(AbstractExtension, IRegistrationContext) setRegistrationContext()}
 * All other calls may be unstable and result in crashes, glitches, or permanent corruption!
 */
public class RegistryAPI {
    private static final List<AbstractExtension<?>> EXTENSIONS = new ArrayList<>();

    /**
     * Registers an extension manually; this method is not recommended, instead opt for the annotation method
     * <b>MUST</b> be called before commonSetup fires
     * If this method is used, you cannot specify a priority like you can in {@link Extension @Extension}
     * This is one of the many reasons using {@link Extension @Extension} is preferred
     *
     * @param extension the extension instance you want to register
     */
    public static <T extends AbstractExtension<?>> void registerExtension(T extension) {
        JamesRegistryAPI.LOGGER.info("Registering Extension Manually: {}", extension.getExtensionName());
        extension.setContext(extension.getDefaultContext());
        EXTENSIONS.add(extension);
        JamesRegistryAPI.LOGGER.info("Finished Registering Extension Manually: {}", extension.getExtensionName());
    }

    /**
     * Registers an extension manually; this method is not recommended, instead opt for the annotation method
     * <b>MUST</b> be called before commonSetup fires
     * If this method is used, you cannot specify a priority like you can in {@link Extension @Extension}
     * This is one of the many reasons using {@link Extension @Extension} is preferred
     *
     * @param extension the extension instance you want to register
     */
    public static <P extends IRegistrationContext, T extends AbstractExtension<P>> void registerExtension(T extension, P context) {
        JamesRegistryAPI.LOGGER.info("Registering Extension Manually: {}", extension.getExtensionName());
        extension.setContext(context);
        EXTENSIONS.add(extension);
        JamesRegistryAPI.LOGGER.info("Finished Registering Extension Manually: {}", extension.getExtensionName());
    }

    protected static <T extends IRegistrationContext> void setRegistrationContext(AbstractExtension<T> extension, T context) {
        JamesRegistryAPI.LOGGER.info("Adding Registry Context '{}' for extension '{}'", context.getName(), extension);
        Stopwatch stopwatch = Stopwatch.createStarted();
        extension.setContext(context);
        EXTENSIONS.add(extension);
        JamesRegistryAPI.LOGGER.info("Finished Adding Registry Context, \033[0;31mTook {}\033[0;0m", stopwatch);
    }

    public static void onRegister() {
        JamesRegistryAPI.LOGGER.info("Beginning Registration");
        Stopwatch stopwatch = Stopwatch.createStarted();

        EXTENSIONS.forEach((extension) -> {
            if (extension.getAllowedContexts().isEmpty() && extension.shouldRegister()) {
                JamesRegistryAPI.LOGGER.info("Registering for extension '{}' using context '{}'", extension.getExtensionName(), extension.context.getName());
                Stopwatch stopwatch1 = Stopwatch.createStarted();
                extension.register();
                stopwatch1.stop();
                JamesRegistryAPI.LOGGER.info("Finished Registration for extension '{}' with context '{}', \033[0;31mTook {}\033[0;0m", extension.getExtensionName(), extension.context.getName(), stopwatch1);
                extension.onExtensionDoneRegistering();
                onRegister(extension, extension.context);
            }
        });
        stopwatch.stop();
        JamesRegistryAPI.LOGGER.info("Finished Registering, \033[0;31mTook {}\033[0;0m", stopwatch);
    }

    public static void onRegistrationDone() {
        JamesRegistryAPI.LOGGER.info("Starting On Registration Done Tasks");
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (AbstractExtension<?> extension : EXTENSIONS) {
            JamesRegistryAPI.LOGGER.info("Starting On Registration Done Tasks For Extension '{}'", extension.getExtensionName());
            Stopwatch stopwatch1 = Stopwatch.createStarted();
            extension.onRegistrationDone();
            JamesRegistryAPI.LOGGER.info("Finished On Registration Done Tasks For Extension '{}', \033[0;31mTook {}\033[0;0m", extension.getExtensionName(), stopwatch1);
        }
        JamesRegistryAPI.LOGGER.info("Finished On Registration Done Tasks, \033[0;31mTook {}\033[0;0m", stopwatch);
    }

    public static void onRegister(AbstractExtension<?> extension, IRegistrationContext context) {
        JamesRegistryAPI.LOGGER.info("Starting On Extension Registration Done Tasks Because Extension {} Finished Registration.", extension.getExtensionName());
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (AbstractExtension<?> extension1 : EXTENSIONS) {
            if (!extension.equals(extension1)) {
                JamesRegistryAPI.LOGGER.info("Starting On Extension Registration Done Tasks Because Extension {} Finished Registration For Extension {}.", extension.getExtensionName(), extension1.getExtensionName());
                Stopwatch stopwatch1 = Stopwatch.createStarted();
                extension1.onRegister(extension, context);
                JamesRegistryAPI.LOGGER.info("Finished On Extension Registration Done Tasks Because Extension {} Finished Registration For Extension {}, \033[0;31mTook {}\033[0;0m.", extension.getExtensionName(), extension1.getExtensionName(), stopwatch1);
            }
        }
        JamesRegistryAPI.LOGGER.info("Starting On Extension Registration Done Tasks Because Extension {} Finished Registration, \033[0;31mTook {}\033[0;0m.", extension.getExtensionName(), stopwatch);
    }
}