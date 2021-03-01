package ru.navodnikov.denis.locationtracker.abstractions;

public abstract class ScreenState<T extends FragmentContract.View> {

    public abstract void visit(T screen);
}
