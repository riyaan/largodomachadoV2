package com.photoshotlist.infrastructure.helper;

import android.content.Context;

/**
 * Created by PhpDev on 2017/11/23.
 */

public class PSLDatabaseHelperFactory implements IPSLDatabaseHelperFactory{

    private static PSLDatabaseHelperFactory instance;

    private PSLDatabaseHelperFactory() { }

    public static PSLDatabaseHelperFactory getInstance()
    {
        if (instance == null) {
            instance = new PSLDatabaseHelperFactory();
        }
        return instance;
    }
    public PSLDatabaseHelper create(Context context)
    {
        PSLDatabaseHelper helper = null;

        try
        {
            helper = new PSLDatabaseHelper(context);
        }
        catch (Exception ex) {
        }

        return helper;
    }
}
