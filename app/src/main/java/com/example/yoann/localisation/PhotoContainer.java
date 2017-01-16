package com.example.yoann.localisation;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Guillaume on 12/01/2017.
 */

public class PhotoContainer extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photocontainer);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, PhotoIntentActivity.newInstance())
                    .commit();
        }

    }
}
