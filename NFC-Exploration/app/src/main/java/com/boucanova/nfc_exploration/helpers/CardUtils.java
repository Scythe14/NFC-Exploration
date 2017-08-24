package com.boucanova.nfc_exploration.helpers;

import com.boucanova.nfc_exploration.R;
import com.github.devnied.emvnfccard.enums.EmvCardScheme;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bouca-_d on 18/08/2017 for NFC-Exploration.
 */

public class CardUtils {

    private CardUtils() {
    }

    public static String formatCardNumber(final String pCardNumber, final EmvCardScheme pType) {
        String ret = StringUtils.EMPTY;

        if (StringUtils.isNotBlank(pCardNumber)) {
            // format amex
            if (pType != null && pType == EmvCardScheme.AMERICAN_EXPRESS) {
                ret = StringUtils.deleteWhitespace(pCardNumber).replaceFirst("\\d{4}", "$0 ").replaceFirst("\\d{6}", "$0 ")
                        .replaceFirst("\\d{5}", "$0").trim();
            } else {
                ret = StringUtils.deleteWhitespace(pCardNumber).replaceAll("\\d{4}", "$0 ").trim();
            }
        } else {
            ret = "0000 0000 0000 0000";
        }
        return ret;
    }

    public static String formatAid(final String aid) {
        String ret = StringUtils.EMPTY;

        if (StringUtils.isNotBlank(aid)) {
            ret = StringUtils.deleteWhitespace(aid).replaceAll("[A-F0-9]{2}", "$0 ").trim();
        }
        return ret;
    }

    public static String formatExpireDate(Date expireDate) {
        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy", Locale.FRANCE);
        return format.format(expireDate);
    }

    public static int getResourceIdCardType(final EmvCardScheme type) {
        switch (type) {
            case AMERICAN_EXPRESS:
                return R.mipmap.amex;
            case MASTER_CARD:
                return R.mipmap.mastercard;
            case VISA:
                return R.mipmap.visa;
            case JCB:
                return R.mipmap.jcb;
            case UNIONPAY:
                return R.mipmap.unionpay;
            default:
                return -1;
        }
    }
}
