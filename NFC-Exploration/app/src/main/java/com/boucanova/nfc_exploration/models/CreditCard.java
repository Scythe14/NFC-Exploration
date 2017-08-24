package com.boucanova.nfc_exploration.models;

import com.github.devnied.emvnfccard.enums.EmvCardScheme;

import java.util.Date;

/**
 * Created by bouca-_d on 16/08/2017 for NFC-Exploration.
 */

public class CreditCard {
    private String holderFirstname;
    private String holderLastname;
    private Date expireDate;
    private String primaryAccountNumber;
    private String aid;
    private EmvCardScheme type;
    private int pinTryLeft;

    public CreditCard() {
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getPrimaryAccountNumber() {
        return primaryAccountNumber;
    }

    public void setPrimaryAccountNumber(String primaryAccountNumber) {
        this.primaryAccountNumber = primaryAccountNumber;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public EmvCardScheme getType() {
        return type;
    }

    public void setType(EmvCardScheme type) {
        this.type = type;
    }

    public int getPinTryLeft() {
        return pinTryLeft;
    }

    public void setPinTryLeft(int pinTryLeft) {
        this.pinTryLeft = pinTryLeft;
    }

    public String getHolderFirstname() {
        return holderFirstname;
    }

    public void setHolderFirstname(String holderFirstname) {
        this.holderFirstname = holderFirstname;
    }

    public String getHolderLastname() {
        return holderLastname;
    }

    public void setHolderLastname(String holderLastname) {
        this.holderLastname = holderLastname;
    }
}
