package de.clayntech.excelsea.module;

import de.clayntech.excelsea.common.Stub;
import de.clayntech.excelsea.module.impl.ModuleManagerImpl;

import java.util.function.Supplier;

/**
 * Stub that wraps a {@link ModuleManager} implementation. This is used
 * to provide a default implementation (by using the no args constructor) without exposing the API for it.
 */
public class ModuleManagerStub extends Stub<ModuleManager> implements ModuleManager {

    public ModuleManagerStub() {
        this(new ModuleManagerImpl());
    }

    public ModuleManagerStub(ModuleManager delegate) {
        super(delegate);
    }

    @Override
    public <T> ModuleHandle<T> getModule(Class<T> moduleClass) {
        return delegate.getModule(moduleClass);
    }

    @Override
    public <T> void register(Class<T> moduleClass, Supplier<T> factory) {
        delegate.register(moduleClass, factory);
    }
}
