package com.scythex14.nfcreader_writer.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import Activities.R;

/**
 * Created by bouca-_d on 14/02/2017 for NFCReader-Writer.
 */

public class Emulator extends Fragment {

    public Emulator() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emulator, container, false);

        return view;
    }
}