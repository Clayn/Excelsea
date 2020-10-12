package de.clayntech.excelsea.common;

public abstract class ReadOnlyStub<T> extends Stub<T>{
    public ReadOnlyStub(T delegate) {
        super(delegate);
    }

    @Override
    public final void setDelegate(T delegate) {

    }
}
