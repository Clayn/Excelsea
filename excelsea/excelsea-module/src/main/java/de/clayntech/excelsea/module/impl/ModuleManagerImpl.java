package de.clayntech.excelsea.module.impl;

import de.clayntech.excelsea.module.ModuleHandle;
import de.clayntech.excelsea.module.ModuleManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ModuleManagerImpl implements ModuleManager {

    private final Map<Class<?>,Supplier<?>> factories=new HashMap<>();
    private final Map<Class<?>, ModuleHandle<?>> handles=new HashMap<>();
    private final Map<Class<?>, AtomicBoolean> refreshChecks=new HashMap<>();
    private boolean allowingFactoryOverride=false;

    public boolean isAllowingFactoryOverride() {
        return allowingFactoryOverride;
    }

    public void setAllowingFactoryOverride(boolean allowingFactoryOverride) {
        this.allowingFactoryOverride = allowingFactoryOverride;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> ModuleHandle<T> getModule(Class<T> moduleClass) {
        if(!handles.containsKey(moduleClass)) {
            handles.put(moduleClass, new ModuleHandleImpl<>((Supplier<Object>) () -> {
                T val = (T) Optional.ofNullable(factories.get(moduleClass)).map(Supplier::get)
                        .orElse(null);
                refreshChecks.get(moduleClass).set(val == null);
                return val;
            }, () -> refreshChecks.get(moduleClass).get(), () -> factories.containsKey(moduleClass)));
        }
        return (ModuleHandle<T>) handles.get(moduleClass);
    }

    @Override
    public <T> void register(Class<T> moduleClass, Supplier<T> factory) {
        if(factories.containsKey(moduleClass)&&!isAllowingFactoryOverride()) {
            throw new RuntimeException("Can't overwrite factory registered for "+moduleClass);
        }
        factories.put(moduleClass,factory);
        if(!refreshChecks.containsKey(moduleClass)) {
            refreshChecks.put(moduleClass,new AtomicBoolean(true));
        }
        refreshChecks.get(moduleClass).set(true);
    }
}
