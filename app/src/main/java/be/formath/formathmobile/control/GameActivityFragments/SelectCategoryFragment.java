package be.formath.formathmobile.control.GameActivityFragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.formath.formathmobile.control.GameActivity;

import be.formath.formathmobile.R;

public class SelectCategoryFragment extends Fragment {

    private static OnFragmentSelectCategoryInteractionListener mListener;

    public SelectCategoryFragment() {
        // Required empty public constructor
    }

    public static SelectCategoryFragment newInstance(GameActivity activity) {
        SelectCategoryFragment fragment = new SelectCategoryFragment();
        // No parameters for this fragment, maybe in final version
        mListener = activity;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CREATE", "Creation select_category_fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_category, container, false);
        view.findViewById(R.id.select_category_button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CLICK", "lancement onclick");
                if (mListener != null) {
                    try {
                        mListener.onFragmentSelectCategoryInteraction(Integer.parseInt(view.getTag().toString()));
                    }
                    catch (Exception ex) {
                        //TODO: Handle Exception
                    }
                }
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View view) {
        if (mListener != null) {
            try {
                mListener.onFragmentSelectCategoryInteraction(Integer.parseInt(view.getTag().toString()));
            }
            catch (Exception ex) {
                //TODO: Handle Exception
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentSelectCategoryInteractionListener) {
            mListener = (OnFragmentSelectCategoryInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentSelectCategoryInteractionListener {
        // TODO: Update argument type and name
        void onFragmentSelectCategoryInteraction(int category) throws Exception;
    }
}
