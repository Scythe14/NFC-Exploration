package com.boucanova.nfc_exploration;

import android.nfc.tech.IsoDep;
import android.util.Log;

import com.github.devnied.emvnfccard.enums.SwEnum;
import com.github.devnied.emvnfccard.exception.CommunicationException;
import com.github.devnied.emvnfccard.parser.IProvider;
import com.github.devnied.emvnfccard.utils.TlvUtil;

import java.io.IOException;

import fr.devnied.bitlib.BytesUtils;

/**
 * Created by bouca-_d on 18/08/2017 for NFC-Exploration.
 */

public class Provider implements IProvider {

    private static final String TAG = Provider.class.getName();

    private StringBuffer log = new StringBuffer();

    private IsoDep mTagCom;

    @Override
    public byte[] transceive(final byte[] pCommand) throws CommunicationException {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "send: " + BytesUtils.bytesToString(pCommand));
        }
        log.append("<font color='green'><b>send:</b> " + BytesUtils.bytesToString(pCommand)).append("</font><br/>");

        byte[] response = null;
        try {
            // send command to emv card
            response = mTagCom.transceive(pCommand);
        } catch (IOException e) {
            throw new CommunicationException(e.getMessage());
        }

        log.append("<font color='blue'><b>resp:</b> " + BytesUtils.bytesToString(response)).append("</font><br/>");
        Log.d(TAG, "resp: " + BytesUtils.bytesToString(response));
        try {
            Log.d(TAG, "resp: " + TlvUtil.prettyPrintAPDUResponse(response));
            SwEnum val = SwEnum.getSW(response);
            if (val != null) {
                Log.d(TAG, "resp: " + val.getDetail());
            }
            log.append("<pre>").append(TlvUtil.prettyPrintAPDUResponse(response).replace("\n", "<br/>").replace(" ", "&nbsp;"))
                    .append("</pre><br/>");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public void setmTagCom(final IsoDep mTagCom) {
        this.mTagCom = mTagCom;
    }

    public StringBuffer getLog() {
        return log;
    }
}

