package de.ur.wi2_intern.ur_place.urplace;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M, V> implements BasePresenterInterface<M, V> {
    protected M model;
    private WeakReference<V> view;

    public void setView(V view) {
        resetState();
        this.view = new WeakReference<>(view);
    }

    public void setModel(M model) {
        resetState();
        this.model = model;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void resetState() {

    }

    public boolean setupDone() {
        return getView() != null && model != null;
    }

    protected V getView() {
        if (view == null) {
            return null;
        } else return view.get();
    }

    protected M getModel() {
        return this.model;
    }
}
