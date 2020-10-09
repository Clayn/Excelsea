package de.clayntech.excelsea.module;


import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public final class ModuleHandle<T> {

    private final Supplier<T> factory;
    private final BooleanSupplier refreshCheck;
    private final BooleanSupplier usableCheck;
    private T cachedModule;

    ModuleHandle(Supplier<T> factory, BooleanSupplier refreshCheck,BooleanSupplier usableCheck) {
        this.factory = factory;
        this.refreshCheck = refreshCheck;
        this.usableCheck=usableCheck;
    }

    /**
     * Returns whether or not this handle can be used to get a module.
     * If it returns {@code false} that means no factory was registered for the requested module.
     * @return {@code true} if and only if a module can be accessed by this handle, {@code false}
     * otherwise.
     */
    public boolean isUsable() {
        return usableCheck.getAsBoolean();
    }

    /**
     * Returns the module accessible by this handle. The module may have changed in the meantime
     * but the API should keep the same.
     * @return the current module of this handle.
     */
    public T get() {
        if(!refreshCheck.getAsBoolean()&&cachedModule!=null) {
            return cachedModule;
        }
        if(refreshCheck.getAsBoolean()||cachedModule==null) {
            cachedModule=factory.get();
        }
        return cachedModule;
    }
}
