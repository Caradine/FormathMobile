package be.formath.formathmobile.control.GameActivityFragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import be.formath.formathmobile.R;
import be.formath.formathmobile.utilities.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayFieldFragment.OnFragmentPlayFieldInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayFieldFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayFieldFragment extends Fragment {
    private static final String ARG_OPERATION_LABEL = "operationLabel";
    private static final String ARG_COUNTER = "operationCounter";
    private static final String ARG_TITLE = "operationTitle";
    private static final String TAG  = "PlayFieldFragment";

    private String operationLabel;
    private int operationCounter;
    private String operationTitle;
    TextView userResponse;

    private static OnFragmentPlayFieldInteractionListener mListener;

    public PlayFieldFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param operationLabel Parameter 1.
     * @param operationCounter Parameter 2.
     * @return A new instance of fragment PlayFieldFragment.
     */
    public static PlayFieldFragment newInstance(String operationLabel, int operationCounter, String operationTitle) {
        Log.d(TAG, "New instance");
        PlayFieldFragment fragment = new PlayFieldFragment();
        Bundle args = new Bundle();
        args.putString(ARG_OPERATION_LABEL, operationLabel);
        args.putInt(ARG_COUNTER, operationCounter);
        args.putString(ARG_TITLE, operationTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            operationLabel = getArguments().getString(ARG_OPERATION_LABEL);
            operationCounter = getArguments().getInt(ARG_COUNTER);
            operationTitle = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mListener == null)
            mListener = (OnFragmentPlayFieldInteractionListener)container.getContext();
        View view = inflater.inflate(R.layout.fragment_play_field_grid, container, false);
        TextView title = (TextView)view.findViewById(R.id.play_field_title);
        title.setText(operationTitle);
        TextView subtitle = (TextView)view.findViewById(R.id.play_field_subtitle);
        subtitle.setText("Calcul n°" + operationCounter);
        TextView tv = (TextView)view.findViewById(R.id.play_field_label_operation);
        tv.setText(operationLabel);
        userResponse = (TextView)view.findViewById(R.id.play_field_user_response);
        userResponse.setText("_");
        Button btnOk = (Button)view.findViewById(R.id.play_field_btn_validate);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response = String.valueOf(userResponse.getText());
                /*if (Utils.isNumeric(response)) { // Ne marche pas a cause de la virgule !!!
                    mListener.onFragmentPlayFieldInteraction(response);
                }*/
                mListener.onFragmentPlayFieldInteraction(response, operationTitle);
            }
        });
        view.findViewById(R.id.play_field_key_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumeralPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumeralPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumeralPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumeralPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumeralPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumeralPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumeralPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumeralPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumeralPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumeralPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_coma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onComaPressed();
            }
        });
        view.findViewById(R.id.play_field_key_backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackSpacePressed();
            }
        });
        view.findViewById(R.id.play_field_clear_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClearPressed();
            }
        });
        view.findViewById(R.id.play_field_key_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMinusPressed();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentPlayFieldInteractionListener) {
            mListener = (OnFragmentPlayFieldInteractionListener) context;
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

    public void onComaPressed() {
        String response = userResponse.getText().toString();
        if (!response.contains(",")) {
            String text = response + ",";
            userResponse.setText(text);
        }
    }

    public void onBackSpacePressed() {
        //TextView userResponse = (TextView) view.findViewById(R.id.play_field_user_response);
        String response = userResponse.getText().toString();
        if (response.length() == 1) {
            userResponse.setText("_");
        }
        else {
            String newResponse = response.substring(0, response.length() - 1);
            userResponse.setText(newResponse);
        }
    }

    public void onNumeralPressed(View view) {
        //TextView userResponse = (TextView) view.findViewById(R.id.play_field_user_response);
        String response = userResponse.getText().toString();
        String numeral = "0";
        switch (view.getId()) {
            case R.id.play_field_key_0:
                numeral = "0";
                break;
            case R.id.play_field_key_1:
                numeral = "1";
                break;
            case R.id.play_field_key_2:
                numeral = "2";
                break;
            case R.id.play_field_key_3:
                numeral = "3";
                break;
            case R.id.play_field_key_4:
                numeral = "4";
                break;
            case R.id.play_field_key_5:
                numeral = "5";
                break;
            case R.id.play_field_key_6:
                numeral = "6";
                break;
            case R.id.play_field_key_7:
                numeral = "7";
                break;
            case R.id.play_field_key_8:
                numeral = "8";
                break;
            case R.id.play_field_key_9:
                numeral = "9";
                break;
        }
        if (response.equals("_")) {
            userResponse.setText(numeral);
        }
        else {
            String text = response + numeral;
            userResponse.setText(text);
        }
    }

    public void onClearPressed() {
        userResponse.setText("_");
    }

    public void onMinusPressed() {
        String response = userResponse.getText().toString();
        if (response.startsWith("-")) {
            response = response.substring(1);
            userResponse.setText(response);
        }
        else{
            String text = "-" + response;
            userResponse.setText(text);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentPlayFieldInteractionListener {
        void onFragmentPlayFieldInteraction(String userResponse, String title);
    }
}
