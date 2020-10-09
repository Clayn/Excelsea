package de.clayntech.excelsea.common;

public abstract class Stub<T> {
    protected final T delegate;

    public Stub(T delegate) {
        this.delegate = delegate;
    }
}
