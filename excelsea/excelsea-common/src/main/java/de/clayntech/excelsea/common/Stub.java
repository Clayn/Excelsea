package de.clayntech.excelsea.common;

import java.util.Objects;

/**
 * Abstract class to easily manage a stub of another interface or class.
 * Stub classes aren't required to extends this class.
 * @param <T> the stub type.
 */
public abstract class Stub<T> {
    protected T delegate;

    public Stub(T delegate) {
        this.delegate = delegate;
    }

    public void setDelegate(T delegate) {
        this.delegate= Objects.requireNonNull(delegate,"Can't set delegate of stub to 'null'");
    }
}
