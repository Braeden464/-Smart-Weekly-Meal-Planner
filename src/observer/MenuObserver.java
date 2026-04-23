package observer;

import composite.WeekMenu;

public interface MenuObserver {
    void onMenuChanged(WeekMenu menu);
}