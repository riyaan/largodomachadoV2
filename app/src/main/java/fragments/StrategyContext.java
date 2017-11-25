package fragments;

import android.support.v4.app.FragmentManager;

/**
 * Created by PhpDev on 2017/11/23.
 */

public class StrategyContext {
    private FragmentStrategy strategy;

    public StrategyContext(FragmentStrategy strategy)
    {
        this.strategy = strategy;
    }

    public void FragmentStrategyInterface(FragmentManager manager)
    {
        this.strategy.Load(manager);
    }
}
