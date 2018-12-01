package com.example.ksenia.ituproject.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.ksenia.ituproject.MyApp;
import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.model.Category;
import com.example.ksenia.ituproject.model.Currency;
import com.example.ksenia.ituproject.model.Wallet;
import com.example.ksenia.ituproject.ui.listadapters.OperationsAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OperationsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OperationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OperationsFragment extends Fragment {
    private static final String ARG_WALLET_IDX = "wallet";
    private Wallet wallet;

    private OperationsAdapter adapter;
    private RecyclerView listOperationsView;

    private OnFragmentInteractionListener mListener;

    public OperationsFragment() {
        // Required empty public constructor
    }

    public static OperationsFragment newInstance(int walletIdx) {
        OperationsFragment fragment = new OperationsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_WALLET_IDX, walletIdx);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            wallet = MyApp.status.getWallets().get(getArguments().getInt(ARG_WALLET_IDX));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_operations, container, false);
        listOperationsView = view.findViewById(R.id.operationsRecyclerView);
        adapter = new OperationsAdapter(wallet);
        listOperationsView.setAdapter(adapter);
        listOperationsView.setLayoutManager(new LinearLayoutManager(getContext()));

        // add listener to `add button`
        view.findViewById(R.id.AddOperationFloatingActionButton).setOnClickListener(
                this.AddOperationFloatingActionButtonOnClickListener
        );

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
            // TODO
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
        void onFragmentInteraction(Uri uri);
    }

    private View.OnClickListener AddOperationFloatingActionButtonOnClickListener = new View.OnClickListener() {

        private View bodyView;
        private RadioGroup radioGroup;
        private Spinner categorySpinner;
        private Spinner currencySpinner;

        private Currency selectedCurrency = null;
        private Category selectedCategory = null;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {

            bodyView = LayoutInflater.from(getContext()).inflate(R.layout.new_operation_dialog_body, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Add Operation");
            builder.setView(bodyView);
            builder.setPositiveButton("Add", PositiveButtonOnClickListener);
            builder.setNegativeButton("Cancel", NegativeButtonOnClickListener);

            radioGroup = bodyView.findViewById(R.id.operationTypeRadioGroup);
            currencySpinner = bodyView.findViewById(R.id.currencySpinner);
            categorySpinner = bodyView.findViewById(R.id.categorySpinner);

            prepareRadioGroup();
            prepareCurrencySpinner();
            prepareCategorySpinner();

            AlertDialog dialog = builder.create();
            dialog.show();

        }

        private void prepareRadioGroup() {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    categorySpinner.setEnabled(
                            checkedId == R.id.outcomeRadioButton
                    );
                }
            });
        }

        private void prepareCurrencySpinner() {
            ArrayAdapter<Currency> adapter = new ArrayAdapter<>(
                    getContext(),
                    android.R.layout.simple_spinner_item,
                    MyApp.status.getCurrencies()
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            currencySpinner.setAdapter(adapter);

            currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedCurrency = (Currency) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    selectedCurrency = null;
                }
            });
        }

        private void prepareCategorySpinner() {
            List<Category> categories = MyApp.status.loadCategories();
            ArrayAdapter<Category> adapter = new ArrayAdapter<>(
                    getContext(),
                    android.R.layout.simple_spinner_item,
                    categories
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorySpinner.setAdapter(adapter);

            categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedCategory = (Category) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    selectedCategory = null;
                }
            });
        }

        private DialogInterface.OnClickListener PositiveButtonOnClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog alertDialog = (AlertDialog) dialog;

                RadioGroup radioGroup = alertDialog.findViewById(R.id.operationTypeRadioGroup);
                int selectedRadioId = radioGroup.getCheckedRadioButtonId();

                String amountString = ((EditText)bodyView.findViewById(R.id.amountEditText)).getText().toString();
                float amountValue = amountString.isEmpty() ? 0 : Float.parseFloat(amountString);

                if (amountValue == 0) {
                    return;
                }

                String description = ((EditText)bodyView.findViewById(R.id.operationDescriptionEditText)).getText().toString();

                Log.d("type=income", (selectedRadioId == R.id.incomeRadioButton) + "");
                Log.d("type=outcome", (selectedRadioId == R.id.outcomeRadioButton) + "");

                Log.d("amount", amountValue + "");
                Log.d("currency", selectedCurrency != null ? selectedCurrency.getName() : "null");
                Log.d("category", selectedCategory != null ? selectedCategory.toString() : "null");
                Log.d("description", description);

                if (selectedRadioId == R.id.incomeRadioButton) {
                    wallet.addIncomeOperation(amountValue, selectedCurrency, description);
                    adapter.notifyDataSetChanged();
                    listOperationsView.smoothScrollToPosition(0);
                } else if (selectedRadioId == R.id.outcomeRadioButton) {
                    wallet.addOutcomeOperation(amountValue, selectedCurrency, selectedCategory, description);
                    adapter.notifyDataSetChanged();
                    listOperationsView.smoothScrollToPosition(0);
                } else {
                    Log.e("radio button", "No radio button selected");
                }

            }
        };

        private DialogInterface.OnClickListener NegativeButtonOnClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
    };
}
