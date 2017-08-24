package com.boucanova.nfc_exploration.interfaces;

/**
 * Created by bouca-_d on 02/08/2017 for NFC-Exploration.
 */

public interface INfcCard {

    void startNfcReadCard();

    void cardIsReadyToRead();

    void doNotMoveCardSoFast();

    void finishNfcReadCard();
}