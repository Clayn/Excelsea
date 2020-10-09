package de.clayntech.excelsea.module.impl;


import de.clayntech.excelsea.module.ModuleHandle;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public final class ModuleHandleImpl<T> implements ModuleHandle<T> {

    private final Supplier<T> factory;
    private final BooleanSupplier refreshCheck;
    private final BooleanSupplier usableCheck;
    private T cachedModule;

    public ModuleHandleImpl(Supplier<T> factory, BooleanSupplier refreshCheck, BooleanSupplier usableCheck) {
        this.factory = factory;
        this.refreshCheck = refreshCheck;
        this.usableCheck=usableCheck;
    }


    /**
     * {@inheritDoc }
     * @return {@inheritDoc }
     */
    @Override
    public boolean isUsable() {
        return usableCheck.getAsBoolean();
    }

    /**
     * {@inheritDoc }
     * @return {@inheritDoc }
     */
    @Override
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
