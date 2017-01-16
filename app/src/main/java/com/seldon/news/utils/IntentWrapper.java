package com.seldon.news.utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

public class IntentWrapper {
    private static String ARG_PARCELABLE = "arg_parcelable";
    private static String ARG_STRING = "arg_string";

    public static void putString(Intent intent, String string) {
        intent.putExtra(ARG_STRING, string);
    }

    public static String getString(Intent intent) {
        return intent.getStringExtra(ARG_STRING);
    }

    public static void putParcelable(Bundle bundle, Parcelable parcelable) {
        bundle.putParcelable(ARG_PARCELABLE, parcelable);
    }

    public static Parcelable getParcelable(Bundle bundle) {
        return bundle.getParcelable(ARG_PARCELABLE);
    }
}
