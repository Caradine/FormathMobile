package be.formath.formathmobile.control.GameActivityFragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import be.formath.formathmobile.control.GameActivity;
import be.formath.formathmobile.R;
import be.formath.formathmobile.utilities.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectLevelFragment.OnFragmentSelectLevelInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectLevelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectLevelFragment extends Fragment {

    private int category;
    private int nbElem;

    private static OnFragmentSelectLevelInteractionListener mListener;

    public SelectLevelFragment() {}

    public static SelectLevelFragment newInstance(GameActivity activity, int category) throws Exception {
        mListener = activity;
        SelectLevelFragment fragment = new SelectLevelFragment();
        Bundle args = new Bundle();
        args.putInt("CATEGORY", category);
        switch (category){
            case 1:
                args.putInt("NB_ELEM", 6);
                break;
            case 2:
            case 3:
                args.putInt("NB_ELEM", 7);
                break;
            case 4:
                args.putInt("NB_ELEM", 5);
                break;
            default:
                throw new Exception("Unrecognized Category Number");
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getInt("CATEGORY");
            nbElem = getArguments().getInt("NB_ELEM");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setWeightSum(nbElem + 2);
        layout.setGravity(Gravity.LEFT|Gravity.TOP);

        TextView title = new TextView(getActivity());
        title.setId(Utils.generateViewId());
        //                                                 WIDTH                                HEIGHT                                Weight
        title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2));
        title.setText(R.string.select_level_title);
        //title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        title.setGravity(Gravity.CENTER); // Pour text alignment
        //title.setTextAppearance();
        layout.addView(title);

        for (int i = 0; i < nbElem; i++) {
            Button btn = new Button(getActivity());
            btn.setId(Utils.generateViewId());
            btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            btn.setMaxHeight(30);
            btn.setTag(Integer.toString(i + 1));
            //TODO: Get Strings from resource String
            //btn.setText(R.string.select_level_button_text_gen + " " + (i + 1));
            btn.setText("Niveau " + (i + 1));
            //TODO: Check for medal
            btn.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: Launch game here
                    mListener.onFragmentSelectLevelInteraction(category, Integer.parseInt(v.getTag().toString()));
                }
            });
            layout.addView(btn);
        }
        return layout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    // TODO: Define what to do with this
    public void onButtonPressed(View view) {
        if (mListener != null) {
            mListener.onFragmentSelectLevelInteraction(category, Integer.parseInt(view.getTag().toString()));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentSelectLevelInteractionListener) {
            mListener = (OnFragmentSelectLevelInteractionListener) context;
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
    public interface OnFragmentSelectLevelInteractionListener {
        // TODO: Update argument type and name
        void onFragmentSelectLevelInteraction(int category, int level);
    }
}
