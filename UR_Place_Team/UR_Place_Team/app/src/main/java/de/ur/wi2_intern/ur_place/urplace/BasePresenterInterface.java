package de.ur.wi2_intern.ur_place.urplace;

import java.lang.ref.WeakReference;

public interface BasePresenterInterface<M, V> {
    void resetState();

    void updateView();

    void unbindView();

    void setView(V view);

    public void setModel(M model);
}
