package com.example.ksenia.ituproject.ui.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ksenia.ituproject.MyApp;
import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.model.Wallet;
import com.example.ksenia.ituproject.ui.listadapters.WalletsAdapter;


public class WalletsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private WalletsAdapter walletsAdapter;

    public WalletsFragment() {}

    public static WalletsFragment newInstance(String param1, String param2) {
        WalletsFragment fragment = new WalletsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wallets, container, false);
        RecyclerView listWalletsView = view.findViewById(R.id.list_wallets);
        walletsAdapter = new WalletsAdapter();
        listWalletsView.setAdapter(walletsAdapter);
        listWalletsView.setLayoutManager(new LinearLayoutManager(getContext()));

        // add listener to `add button`
        view.findViewById(R.id.AddWalletFloatingActionButton).setOnClickListener(
                this.AddWalletFloatingActionButtonOnClickListener
        );

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }

    // show dialog
    private View.OnClickListener AddWalletFloatingActionButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Create New Wallet");

            final EditText newWalletEditText = new EditText(getContext());
            builder.setView(newWalletEditText);

            builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String value = newWalletEditText.getText().toString();
                    if (value.isEmpty()) {
                        value = "New Wallet";
                    }

                    // add new wallet to Status and notify adapter
                    MyApp.status.getWallets().add(new Wallet(value));
                    walletsAdapter.notifyDataSetChanged();

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.create().show();
        }
    };

}
