package com.photoshotlist.infrastructure.helper;

import android.content.Context;

/**
 * Created by PhpDev on 2017/11/23.
 */

public interface IPSLDatabaseHelperFactory {
    public PSLDatabaseHelper create(Context context);
}
