package com.expansion.lg.kimaru.expansion.fragment;
/**
 * Created by kimaru on 3/11/17.
 */

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.expansion.lg.kimaru.expansion.R;
import com.expansion.lg.kimaru.expansion.dbhelpers.Exam;
import com.expansion.lg.kimaru.expansion.dbhelpers.ExamTable;
import com.expansion.lg.kimaru.expansion.dbhelpers.Interview;
import com.expansion.lg.kimaru.expansion.dbhelpers.InterviewTable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewInterviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewInterviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewInterviewFragment extends Fragment implements OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText mMotivation, mCommunity, mMentality, mSelling, mHealth, mInvestment;
    EditText mInterpersonal, mCommitment;


    Button buttonSave, buttonList;


    private int mYear, mMonth, mDay;
    static final int DATE_DIALOG_ID = 100;


    Integer loggedInUser = 1;



    public NewInterviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewInterviewFragment newInstance(String param1, String param2) {
        NewInterviewFragment fragment = new NewInterviewFragment();
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
        View v =  inflater.inflate(R.layout.fragment_new_interview, container, false);
        //Initialize the UI Components
        mMotivation = (EditText) v.findViewById(R.id.editMotivation);
        mCommunity = (EditText) v.findViewById(R.id.editCommunity);
        mMentality = (EditText) v.findViewById(R.id.editMentality);
        mSelling = (EditText) v.findViewById(R.id.editSelling);
        mHealth = (EditText) v.findViewById(R.id.editHealth);
        mInvestment = (EditText) v.findViewById(R.id.editInvestment);
        mInterpersonal = (EditText) v.findViewById(R.id.editInterpersonal);
        mCommitment = (EditText) v.findViewById(R.id.editCommitment);
        buttonList = (Button) v.findViewById(R.id.buttonList);
        buttonList.setOnClickListener(this);

        buttonSave = (Button) v.findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.editDob:
                DialogFragment newFragment = new DatePickerFragment().newInstance(R.id.editDob);
                newFragment.show(getFragmentManager(), "DatePicker");
                break;

            case R.id.editRelocated:
                DialogFragment dateMovedFragment = new DatePickerFragment().newInstance(R.id.editRelocated);
                dateMovedFragment.show(getFragmentManager(), "Datepicker");
                break;
            case R.id.buttonSave:
                // set date as integers
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

                Toast.makeText(getContext(), "Validating and saving", Toast.LENGTH_SHORT).show();
                Integer currentDate =  (int) (new Date().getTime()/1000);

                Integer applicantId = 1;
                Integer recruitment = 1; //mMaths, mEnglish, mSelfAssessment

                Integer applicantMotivation = Integer.parseInt(mMotivation.getText().toString());
                Integer applicantCommunity = Integer.parseInt(mCommitment.getText().toString());
                Integer applicantMentality = Integer.parseInt(mMentality.getText().toString());
                Integer applicantSelling = Integer.parseInt(mSelling.getText().toString());
                Integer applicantHealth = Integer.parseInt(mHealth.getText().toString());
                Integer applicantInvestment = Integer.parseInt(mInvestment.getText().toString());
                Integer applicantInterpersonal = Integer.parseInt(mInterpersonal.getText().toString());
                Integer applicantCommitment = Integer.parseInt(mCommitment.getText().toString());

                String applicantComment = "";
                Integer applicantAddedBy = loggedInUser;
                Integer applicantDateAdded = currentDate;
                Integer applicantSync = 0;
                Integer applicant = 1;


                // Do some validations
                if (applicantMotivation.toString().trim().equals("")){
                    Toast.makeText(getContext(), "Enter the Score for Motivation", Toast.LENGTH_SHORT).show();
                }

                else if (applicantCommunity.toString().trim().equals("")){
                    Toast.makeText(getContext(), "Enter the Score for community involvement", Toast.LENGTH_SHORT).show();
                }

                else if(applicantMentality.toString().trim().equals("")){
                    Toast.makeText(getContext(), "Enter the Score for the applicant's mentality", Toast.LENGTH_SHORT).show();
                }
                else if(applicantSelling.toString().trim().equals("")){
                    Toast.makeText(getContext(), "Enter the Score for the applicant's selling skills", Toast.LENGTH_SHORT).show();
                }
                else if(applicantHealth.toString().trim().equals("")){
                    Toast.makeText(getContext(), "Enter the Score for the applicant's rating for interest in health", Toast.LENGTH_SHORT).show();
                }
                else if(applicantInvestment.toString().trim().equals("")){
                    Toast.makeText(getContext(), "Enter the Score for the applicant's ability to invest", Toast.LENGTH_SHORT).show();
                }
                else if(applicantInterpersonal.toString().trim().equals("")){
                    Toast.makeText(getContext(), "Enter the Score for the applicant's interpersonal skills", Toast.LENGTH_SHORT).show();
                }
                else if(applicantCommitment.toString().trim().equals("")){
                    Toast.makeText(getContext(), "Enter the Score for the applicant's commitment ability", Toast.LENGTH_SHORT).show();
                } else{
                    // Save Exam Details
                    Interview interview;
//                    interview = new Interview(applicant, )
                    interview = new Interview(applicant, recruitment, applicantMotivation,
                            applicantCommunity, applicantMentality, applicantSelling, applicantHealth,
                            applicantInvestment, applicantInterpersonal, applicantCommitment, 0,
                            applicantAddedBy, applicantDateAdded, 0, applicantComment);
                    InterviewTable interviewTable = new InterviewTable(getContext());
                    long id = interviewTable.addData(interview);

                    if (id ==-1){
                        Toast.makeText(getContext(), "Could not save the results", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(), "Saved successfully", Toast.LENGTH_SHORT).show();

                        // Clear boxes
                        mMotivation.setText("");
                        mCommunity.setText("");
                        mMentality.setText("");
                        mSelling.setText("");
                        mHealth.setText("");
                        mInvestment.setText("");
                        mInterpersonal.setText("");
                        mCommitment.setText("");
                    }

                }

        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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