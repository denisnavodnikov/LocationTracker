package ru.navodnikov.denis.locationtracker.viewmodel;

public abstract class ScreenState<T extends FragmentContract.View> {

    public abstract void visit(T screen);
}
