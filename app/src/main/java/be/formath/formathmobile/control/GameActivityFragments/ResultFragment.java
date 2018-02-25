package be.formath.formathmobile.control.GameActivityFragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import be.formath.formathmobile.model.Operation;
import be.formath.formathmobile.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResultFragment.OnFragmentResultInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    private ArrayList<Operation> listOper;

    private OnFragmentResultInteractionListener mListener;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param listOper Parameter 1.
     * @return A new instance of fragment ResultFragment.
     */
    public static ResultFragment newInstance(ArrayList<Operation> listOper) {
        ResultFragment fragment = new ResultFragment();
        fragment.setOperationArray(listOper);
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    public void setOperationArray(ArrayList<Operation> listOper) {
        this.listOper = listOper;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        Button button = (Button)view.findViewById(R.id.result_button_end);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onFragmentResultInteraction();
                }
            }
        });

        int note = 0;
        LinearLayout ll = (LinearLayout)view.findViewById(R.id.result_list_answer);

        for (Operation oper : listOper) {
            TableLayout tl = new TableLayout(getActivity().getApplicationContext());

            TableRow tr1 = new TableRow(getActivity().getApplicationContext());
            TableRow tr2 = new TableRow(getActivity().getApplicationContext());

            TextView tv_oper = new TextView(getActivity().getApplicationContext());
            tv_oper.setText(oper.getLabel());
            tv_oper.setTextColor(getResources().getColor(R.color.text_light_background));
            tv_oper.setTextSize(20);
            tr1.addView(tv_oper);

            TextView tv_given = new TextView(getActivity().getApplicationContext());
            tv_given.setText(oper.getGivenResponse());
            tv_given.setTextColor(getResources().getColor(R.color.text_light_background));
            tv_oper.setTextSize(15);
            tr2.addView(tv_given);

            TextView tv_answ = new TextView(getActivity().getApplicationContext());
            tv_answ.setText(oper.getResponse());
            tv_answ.setTextColor(getResources().getColor(R.color.text_light_background));
            tv_oper.setTextSize(15);
            tr2.addView(tv_answ);

            tl.addView(tr1);
            tl.addView(tr2);

            ll.addView(tl);
        }

        TextView t_note = (TextView)view.findViewById(R.id.result_note);
        t_note.setText(note + " / 10");
        return view;
    }

    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentResultInteraction();
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentResultInteractionListener) {
            mListener = (OnFragmentResultInteractionListener) context;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentResultInteractionListener {
        void onFragmentResultInteraction();
    }
}
