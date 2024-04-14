package net.vakror.registry.jamesregistryapi.api;

import net.minecraft.resources.ResourceLocation;
import net.vakror.registry.jamesregistryapi.api.context.IRegistrationContext;

import java.util.List;
import java.util.Optional;

/**
 * the base class that all extensions inherit from. This is where all registers are received
 * if an automatically registered extensions (auto-registered by annotating with {@link Extension}) has a constructor with more than zero arguments, it must declare another one with zero arguments or be manually registered by {@link RegistryAPI#registerExtension(AbstractExtension) registerExtension}
 */
public abstract class AbstractExtension<T extends IRegistrationContext> {
    public T context;

    /**
     *
     * @return The id of this extension. <b>MAKE IT UNIQUE!</b>. This will be used for overriding and removing extensions, as well as for debug logs
     */
    public abstract ResourceLocation getExtensionName();

    public void onExtensionDoneRegistering() {}

    /**
     * This is called when registration  is done for ALL extensions.
     * Use this to do other stuff that has to run after all objects have been registered but before the registration step is done
     * DO NOT use this to register new objects
     */
    public void onRegistrationDone() {}

    public abstract void register();

    /**
     * Use this for whatever you need to do right after the extension has been registered
     * Runs only once
     */
    public void onRegistered() {}

    public abstract T getDefaultContext();

    public void setContext(IRegistrationContext context) {
        this.context = (T) context;
    }

    /**
     * called when ANY {@link AbstractExtension Extension} is done registering, not just an extension of this same object type.
     * This method is not called when this extension finishes, for that, use {@link #onExtensionDoneRegistering()}
     * @param extension the extension which finished registration
     * @param context the context that the extension used to register
     */
    public <X extends IRegistrationContext> void onRegister(AbstractExtension<?> extension, IRegistrationContext context) {}

    /**
     * @return whether the extension should be registered. This only is called during automatic registration
     */
    public boolean shouldRegister() {return true;}

    @Override
    public String toString() {
        return getExtensionName().toString();
    }
}
