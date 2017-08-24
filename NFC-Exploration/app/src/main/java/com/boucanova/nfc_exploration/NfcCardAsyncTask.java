package com.boucanova.nfc_exploration;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.AsyncTask;
import android.util.Log;

import com.boucanova.nfc_exploration.interfaces.INfcCard;
import com.boucanova.nfc_exploration.models.CreditCard;
import com.github.devnied.emvnfccard.model.EmvCard;
import com.github.devnied.emvnfccard.parser.EmvParser;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Created by bouca-_d on 06/08/2017 for NFC-Exploration.
 */

public class NfcCardAsyncTask extends AsyncTask<Void, Void, Object> {

    private final static String NFC_A_TAG = "TAG: Tech [android.nfc.tech.IsoDep, android.nfc.tech.NfcA]";
    private final static String NFC_B_TAG = "TAG: Tech [android.nfc.tech.IsoDep, android.nfc.tech.NfcB]";

    private final String TAG = getClass().getName();
    private Provider prov = new Provider();
    private boolean exception;
    private EmvCard card;
    private INfcCard INfc;
    private Tag tag;
    private CreditCard creditCard = new CreditCard();

    public static class Builder {
        private Tag mTag;
        private INfcCard INfc;


        public Builder(INfcCard i, Intent intent) {
            INfc = i;
            mTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }

        public NfcCardAsyncTask build() {
            return new NfcCardAsyncTask(this);
        }
    }

    private NfcCardAsyncTask(Builder b) {
        tag = b.mTag;
        if (tag != null) {
            INfc = b.INfc;
            try {
                if (tag.toString().equals(NFC_A_TAG) || tag.toString().equals(NFC_B_TAG))
                    execute();
                else
                    clearAll();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        INfc.startNfcReadCard();
        prov.getLog().setLength(0);
    }

    @Override
    protected Object doInBackground(final Void... params) {
        Object result = null;

        try {
            doInBackground();
        } catch (Exception e) {
            result = e;
            Log.e(TAG, e.getMessage(), e);
        }
        return result;
    }

    @Override
    protected void onPostExecute(final Object result) {
        if (!exception) {
            if (card != null) {
                if (StringUtils.isNotBlank(card.getCardNumber())) {
                    creditCard.setExpireDate(card.getExpireDate());
                    creditCard.setAid(card.getAid());
                    creditCard.setPinTryLeft(card.getLeftPinTry());
                    creditCard.setPrimaryAccountNumber(card.getCardNumber());
                    creditCard.setType(card.getType());
                    creditCard.setHolderFirstname(card.getHolderFirstname());
                    creditCard.setHolderLastname(card.getHolderLastname());
                    INfc.cardIsReadyToRead();
                }
            }
        } else {
            INfc.doNotMoveCardSoFast();
        }
        INfc.finishNfcReadCard();
        clearAll();
    }

    private void doInBackground() {
        IsoDep mIsoDep = IsoDep.get(tag);
        if (mIsoDep == null) {
            INfc.doNotMoveCardSoFast();
            return;
        }
        exception = false;

        try {
            mIsoDep.connect();

            prov.setmTagCom(mIsoDep);

            final EmvParser parser = new EmvParser(prov, true);
            card = parser.readEmvCard();
        } catch (IOException e) {
            exception = true;
        } finally {
            IOUtils.closeQuietly(mIsoDep);
        }
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
    public StringBuffer getLog() {
        return prov.getLog();
    }

    private void clearAll() {
        INfc = null;
        prov = null;
        card = null;
        creditCard = null;
        tag = null;
    }
}