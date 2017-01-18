package com.example.kamelot.projectcomics;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Kamelot on 17/01/2017.
 */

public class ContentProvider extends CupboardContentProvider {

    public static final String authority = BuildConfig.APPLICATION_ID + ".provider";

    static {

        cupboard().register(Episode.class);
        cupboard().register(Serie.class);

    }

    protected ContentProvider() {
        super(authority, 1);
    }
}
