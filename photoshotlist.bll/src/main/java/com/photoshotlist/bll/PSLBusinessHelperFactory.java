package com.photoshotlist.bll;

import android.content.Context;

/**
 * Created by PhpDev on 2017/11/23.
 */

public class PSLBusinessHelperFactory implements IPSLBusinessHelperFactory {

    private static PSLBusinessHelperFactory instance;

    private PSLBusinessHelperFactory() { }

    public static PSLBusinessHelperFactory getInstance()
    {
        if (instance == null) {
            instance = new PSLBusinessHelperFactory();
        }
        return instance;
    }

    public PSLBusinessHelper create(Context context)
    {
        PSLBusinessHelper helper = null;

        try
        {
            helper = new PSLBusinessHelper(context);
        }
        catch (Exception ex) {
        }

        return helper;
    }
}
