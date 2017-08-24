package com.boucanova.nfc_exploration.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcA;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by bouca-_d on 04/08/2017 for NFC-Exploration.
 */

public class NfcUtils {

    private final NfcAdapter nfcAdapter;
    private final PendingIntent pendingIntent;
    private final Activity activity;
    private static final IntentFilter[] INTENT_FILTER = new IntentFilter[]{
            new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED),
            new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)};
    private static final String[][] TECH_LIST = new String[][]{{
            NfcA.class.getName(), IsoDep.class.getName()}};

    public NfcUtils(final Activity pActivity) {
        activity = pActivity;
        nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        pendingIntent = PendingIntent.getActivity(activity, 0,
                new Intent(activity, activity.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    public void disableDispatch() {
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(activity);
        }
    }

    public void enableDispatch() {
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(activity, pendingIntent, INTENT_FILTER, TECH_LIST);
        }
    }

    public boolean isNfcEnable(final Context pContext) {
        boolean ret = true;

        try {
            ret = nfcAdapter != null && nfcAdapter.isEnabled();
        } catch (UnsupportedOperationException e) {
            ret = false;
        }
        return ret;
    }

    public boolean isNfcAvailable(final Context pContext) {
        boolean nfcAvailable = true;
        try {
            if (nfcAdapter == null)
                nfcAvailable = false;
        } catch (UnsupportedOperationException e) {
            nfcAvailable = false;
        }
        return nfcAvailable;
    }

    public NfcAdapter getNfcAdapter() {
        return nfcAdapter;
    }

}
