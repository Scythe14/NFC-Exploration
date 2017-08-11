package com.scythex14.nfcreader_writer.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import Activities.R;

import static com.scythex14.nfcreader_writer.Activities.MainActivity.nfcAdapter;

/**
 * Created by bouca-_d on 14/02/2017 for NFCReader-Writer.
 */

public class Home extends Fragment implements View.OnClickListener {

    public Home() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.nfc_on).setVisibility(View.VISIBLE);
        if (!(nfcAdapter != null && nfcAdapter.isEnabled())) {
            view.findViewById(R.id.nfc_off).setVisibility(View.VISIBLE);
            view.findViewById(R.id.nfc_off).setOnClickListener(this);
            view.findViewById(R.id.nfc_on).setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nfc_off:
                Toast.makeText(getContext(), R.string.toast_nfc_activation, Toast.LENGTH_LONG).show();
                startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                break ;
        }
    }
}
