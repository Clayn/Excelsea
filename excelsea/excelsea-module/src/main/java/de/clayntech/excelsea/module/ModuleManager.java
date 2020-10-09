package de.clayntech.excelsea.module;


import java.util.function.Supplier;

public interface ModuleManager {
    /**
     * Returns the module that is associated with the given class.
     * Implementations may return a handle even if that type is not yet known.
     * The returned handle must return whether or not it is usable.
     * @param moduleClass the class of the module to get
     * @param <T> the type of the module
     * @return the handle that holds the module that may get changed later.
     */
    <T> ModuleHandle<T> getModule(Class<T> moduleClass);

    /**
     * Registers a object factory for a given class. If a factory was already registered
     * all handles of this module will be updated accordingly. Implementations may restrict this overwriting
     * behaviour.
     * @param moduleClass the class of the module to register
     * @param factory the factory to create an instance of the module
     * @param <T> the type of the module.
     */
    <T> void register(Class<T> moduleClass, Supplier<T> factory);

    default <T> void registerModule(Class<T> moduleClass, T val) {
        register(moduleClass,()->val);
    }
}
