package com.boucanova.nfc_exploration;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boucanova.nfc_exploration.helpers.CardUtils;
import com.boucanova.nfc_exploration.helpers.NfcUtils;
import com.boucanova.nfc_exploration.interfaces.INfcCard;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bouca-_d on 26/07/2017 for NFC-Exploration.
 */

public class MainActivity extends AppCompatActivity implements INfcCard {

    private String TAG = getClass().getName();
    private NfcUtils nfcUtils;
    private NfcCardAsyncTask nfcTask;
    private ProgressDialog progress;
    private AlertDialog alertDialog;

    /**
     * String buffer
     */
    private StringBuffer mBuffer;
    @BindView(R.id.constraint_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.scan)
    TextView scan;
    @BindView(R.id.cardNumber)
    TextView primaryAccountNumber;
    @BindView(R.id.type)
    ImageView cardType;
    @BindView(R.id.cardValidity)
    TextView expireDate;
    @BindView(R.id.holderName)
    TextView holderName;
    @BindView(R.id.credit_card)
    RelativeLayout creditCard;
    @BindView(R.id.scanning)
    LinearLayout scanning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        nfcUtils = new NfcUtils(this);

        if (nfcUtils.getNfcAdapter() != null) {
            progress = new ProgressDialog(MainActivity.this);
            progress.setTitle(getText(R.string.progress_title));
            progress.setMessage(getText(R.string.progress_message));
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            setFonts();
            onNewIntent(getIntent());
        } else {
            nfcModuleAbsent();
        }
    }

    private void setFonts() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/cb.ttf");

        primaryAccountNumber.setTypeface(tf);
        expireDate.setTypeface(tf);
        scan.setTypeface(tf);
        holderName.setTypeface(tf);
    }

    @Override
    protected void onResume() {
        nfcUtils.enableDispatch();
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.cancel();

        if (!nfcUtils.isNfcAvailable(getApplicationContext()))
            nfcModuleAbsent();
        if (!nfcUtils.isNfcEnable(getApplicationContext()))
            nfcNotEnable();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        nfcUtils.disableDispatch();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (nfcUtils.getNfcAdapter() != null && nfcUtils.getNfcAdapter().isEnabled())
            nfcTask = new NfcCardAsyncTask.Builder(this, intent).build();
    }

    public void nfcModuleAbsent() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle("Information");
        alertbox.setMessage("Your Device is not equiped with an NFC module");
        alertbox.setPositiveButton("Quit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
                finish();
            }
        });
        alertbox.setCancelable(false);
        alertDialog = alertbox.show();
    }

    public void nfcNotEnable() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle("Information");
        alertbox.setMessage("Please enable NFC module and return to the application");
        alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
            }
        });
        alertbox.setCancelable(false);
        alertDialog = alertbox.show();
    }

    @Override
    public void startNfcReadCard() {
        progress.show();
    }

    @Override
    public void cardIsReadyToRead() {
        creditCard.setVisibility(View.VISIBLE);
        scanning.setVisibility(View.GONE);
        primaryAccountNumber.setText(CardUtils.formatCardNumber(nfcTask.getCreditCard().getPrimaryAccountNumber(), nfcTask.getCreditCard().getType()));
        expireDate.setText(CardUtils.formatExpireDate(nfcTask.getCreditCard().getExpireDate()));
        cardType.setImageResource(CardUtils.getResourceIdCardType(nfcTask.getCreditCard().getType()));
    }

    @Override
    public void doNotMoveCardSoFast() {
        progress.dismiss();

        Snackbar snackbar = Snackbar.make(coordinatorLayout, getText(R.string.move_card), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void finishNfcReadCard() {
        progress.dismiss();
    }
}
