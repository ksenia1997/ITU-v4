package com.example.ksenia.ituproject.ui.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ksenia.ituproject.MyApp;
import com.example.ksenia.ituproject.R;
import com.example.ksenia.ituproject.model.Category;
import com.example.ksenia.ituproject.ui.activities.MainActivity;
import com.example.ksenia.ituproject.ui.listadapters.CategoriesAdapter;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Listener} interface
 * to handle interaction events.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class    CategoriesFragment extends Fragment {

    private RecyclerView listCategories;
    private FloatingActionButton fabAddCateogry;
    private CategoriesAdapter categoriesAdapter;
    EditText create_category;
    String txt_category;
    int selectedColour;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Listener mListener;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_categories, container, false);
        listCategories = myView.findViewById(R.id.list_categories);
        fabAddCateogry = myView.findViewById(R.id.fab_add_category);
        // set up adapter & list
        categoriesAdapter = new CategoriesAdapter();

        categoriesAdapter.insert(MyApp.status.loadCategories());

        listCategories.setAdapter(categoriesAdapter);
        listCategories.setLayoutManager((new LinearLayoutManager(getContext())));

        fabAddCateogry.setOnClickListener(fabAddCategoryClickListener);

        return myView;
    }

    private View.OnClickListener fabAddCategoryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog();

        }
    };

    public void showDialog () {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Create new category");

        create_category = new EditText(getContext());
        int padding = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        create_category.setPadding(padding, 0, padding, 0);
        builder.setView(create_category);
        //set positive button
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txt_category = create_category.getText().toString();
                if (txt_category.isEmpty()) {
                    txt_category = "New category";
                }
                showColorPicker();


            }
        });

        //negative button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //Create dialog
        AlertDialog ad = builder.create();
        ad.show();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            mListener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Listener");
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
    public interface Listener {
        void onFragmentInteraction();
    }

    public void showColorPicker() {
        ColorPickerDialogBuilder
                .with(getContext())
                .setTitle("Choose color")
                .initialColor(Color.BLACK)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        selectedColour = selectedColor;
                        setCategory();
                    }
                })
                .setNegativeButton("cancel", null)
                .build()
                .show();

    }

    public void setCategory() {
        Category category = new Category(txt_category, selectedColour);
        categoriesAdapter.insert(category);
        listCategories.smoothScrollToPosition(categoriesAdapter.getItemCount() - 1);
    }



}
