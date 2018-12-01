package com.example.ksenia.ituproject.ui.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.model.Category;
import com.example.ksenia.ituproject.model.Operation;
import com.example.ksenia.ituproject.model.Status;
import com.example.ksenia.ituproject.model.Wallet;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import static java.lang.Float.max;
import static java.lang.Math.abs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatisticsGraph2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatisticsGraph2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticsGraph2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StatisticsGraph2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticsGraph2.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticsGraph2 newInstance(String param1, String param2) {
        StatisticsGraph2 fragment = new StatisticsGraph2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Nacitani XML layoutu.
        View v = inflater.inflate(R.layout.fragment_statistics_graph2, container, false);

        // Nacitani statistickych udaju.
        float incomes = 0;
        float outcomes = 0;

        // Prochazeni veskerych wallets.
        for(Wallet w : Status.getWallets())
        {
            // Nacitani operaci z wallet.
            for(Operation o : w.getOperations())
            {
                // Hodnota vydaje.
                float amount = o.getAmount();

                if (amount > 0)
                {
                    incomes = incomes + amount;
                }
                else {
                    outcomes = outcomes + amount;
                }
            }
        }

        // Formatovani grafu.
        GraphView graph = (GraphView) v.findViewById(R.id.graph2);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);// remove horizontal x labels and line
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);// remove vertical labels and lines
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(2);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(max(abs(incomes),abs(outcomes)));
        graph.getViewport().setYAxisBoundsManual(true);

        // Vizualizace sloupce s prijmy.
        BarGraphSeries<DataPoint> in = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(2, abs(incomes)),

        });
        graph.addSeries(in);
        in.setColor(Color.BLUE);
        in.setSpacing(15);
        in.setDrawValuesOnTop(true);
        in.setValuesOnTopColor(Color.BLACK);

        // Vizualizace sloupce s vydaji.
        BarGraphSeries<DataPoint> out = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, abs(outcomes)),
        });
        graph.addSeries(out);
        out.setColor(Color.RED);
        out.setSpacing(15);
        out.setDrawValuesOnTop(true);
        out.setValuesOnTopColor(Color.BLACK);

        // Celkovy layout.
        return v;
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
            //throw new RuntimeException(context.toString()
            //        + " must implement OnFragmentInteractionListener");
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
}
