package de.clayntech.excelsea.module;

public interface ModuleHandle<T> {
    /**
     * Returns whether or not this handle can be used to get a module.
     * If it returns {@code false} that means no factory was registered for the requested module.
     * @return {@code true} if and only if a module can be accessed by this handle, {@code false}
     * otherwise.
     */
    boolean isUsable();
    /**
     * Returns the module accessible by this handle. The module may have changed in the meantime
     * but the API should keep the same.
     * @return the current module of this handle.
     */
    T get();
}
