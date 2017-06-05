package be.formath.formathmobile.control.GameActivityFragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import be.formath.formathmobile.control.GameActivity;
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
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_OPERATION_LABEL = "operationLabel";
    private static final String ARG_COUNTER = "operationCounter";

    private String operationLabel;
    private int operationCounter;
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
    public static PlayFieldFragment newInstance(GameActivity activity, String operationLabel, int operationCounter) {
        //mListener = activity;
        PlayFieldFragment fragment = new PlayFieldFragment();
        Bundle args = new Bundle();
        args.putString(ARG_OPERATION_LABEL, operationLabel);
        args.putInt(ARG_COUNTER, operationCounter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            operationLabel = getArguments().getString(ARG_OPERATION_LABEL);
            operationCounter = getArguments().getInt(ARG_COUNTER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mListener == null)
            mListener = (OnFragmentPlayFieldInteractionListener) container.getContext();
        View view = inflater.inflate(R.layout.fragment_play_field, container, false);
        TextView title = (TextView)view.findViewById(R.id.play_field_title);
        // TODO: Ajout du literal aux constantes string
        title.setText("Calcul n°"+operationCounter);
        TextView tv = (TextView)view.findViewById(R.id.play_field_label_operation);
        tv.setText(operationLabel);
        userResponse = (TextView)view.findViewById(R.id.play_field_user_response);
        userResponse.setText("0");
        Button btnOk = (Button)view.findViewById(R.id.play_field_btn_validate);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response = String.valueOf(userResponse.getText());
                if (Utils.isNumeric(response)) {
                    mListener.onFragmentPlayFieldInteraction(response);
                }
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
                onComaPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackSpacePressed(view);
            }
        });
        view.findViewById(R.id.play_field_clear_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClearPressed(view);
            }
        });
        view.findViewById(R.id.play_field_key_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMinusPressed(view);
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

    public void onComaPressed(View view) {
        //TextView userResponse = (TextView) view.findViewById(R.id.play_field_user_response);
        String response = userResponse.getText().toString();
        if (!response.contains(",")) {
            userResponse.setText(response += ",");
        }
    }

    public void onBackSpacePressed(View view) {
        //TextView userResponse = (TextView) view.findViewById(R.id.play_field_user_response);
        String response = userResponse.getText().toString();
        if (response.length() == 1) {
            userResponse.setText("0");
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
        if (response.equals("0")) {
            userResponse.setText(numeral);
        }
        else {
            userResponse.setText(response + numeral);
        }
    }

    public void onClearPressed(View view) {
        userResponse.setText("0");
    }

    public void onMinusPressed(View view) {
        String response = userResponse.getText().toString();
        if (response.startsWith("-")) {
            response = response.substring(1);
            userResponse.setText(response);
        }
        else{
            userResponse.setText("-"+response);
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
        // TODO: Update argument type and name
        void onFragmentPlayFieldInteraction(String userResponse);
    }
}
