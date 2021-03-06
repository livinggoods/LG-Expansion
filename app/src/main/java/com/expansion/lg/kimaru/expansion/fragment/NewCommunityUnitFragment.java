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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.expansion.lg.kimaru.expansion.R;
import com.expansion.lg.kimaru.expansion.activity.MainActivity;
import com.expansion.lg.kimaru.expansion.activity.SessionManagement;
import com.expansion.lg.kimaru.expansion.mzigos.CommunityUnit;
import com.expansion.lg.kimaru.expansion.mzigos.Mapping;
import com.expansion.lg.kimaru.expansion.mzigos.SubCounty;
import com.expansion.lg.kimaru.expansion.tables.CommunityUnitTable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewCommunityUnitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewCommunityUnitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewCommunityUnitFragment extends Fragment implements OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText editName, editAreaChiefName, editAreaChiefPhone, editWard;
    EditText editPrivateFacilityForAct, editPrivateFacilityForMrdt, editNumberOfChvs, editChvHouseHold;
    EditText editNumberOfHouseHolds, editMohPopulation, editPopulationDensity, editNumberOfVillages;
    EditText editDistanceToBranch, editTransportCost, editDistanceToMainRoad, editDistanceToHealthFacility;
    EditText editLinkFacility, editDistributors, editCHVsTrained;

    Spinner editEconomicStatus;

    RadioGroup editPresenceOfFactories, editPresenceEstates, editPresenceOfTraderMarket, editPresenceOfSuperMarket;
    RadioGroup editNgosGivingFreeDrugs;

    Button buttonSave, buttonList;


    private int mYear, mMonth, mDay;
    static final int DATE_DIALOG_ID = 100;

    SessionManagement session;
    Mapping mapping;
    SubCounty subCounty;
    String name, email;
    HashMap<String, String> user;


    String latitude = "0";
    String longitude = "0";



    public NewCommunityUnitFragment() {
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
    public static NewCommunityUnitFragment newInstance(String param1, String param2) {
        NewCommunityUnitFragment fragment = new NewCommunityUnitFragment();
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
        View v =  inflater.inflate(R.layout.fragment_new_sublocation, container, false);
        MainActivity.CURRENT_TAG =MainActivity.TAG_NEW_COMMUNITY_UNIT;
        MainActivity.backFragment = new CommunityUnitsFragment();

                session = new SessionManagement(getContext());
        mapping = session.getSavedMapping();
        subCounty = session.getSavedSubCounty();
        user = session.getUserDetails();

        //Initialize the UI Components

        editName = (EditText) v.findViewById(R.id.editName);
        editAreaChiefName = (EditText) v.findViewById(R.id.editAreaChiefName);
        editAreaChiefPhone = (EditText) v.findViewById(R.id.editAreaChiefPhone);
        editWard = (EditText) v.findViewById(R.id.editWard);
        editEconomicStatus = (Spinner) v.findViewById(R.id.editEconomicStatus);

        editPrivateFacilityForAct = (EditText) v.findViewById(R.id.editPrivateFacilityForAct);
        editPrivateFacilityForMrdt = (EditText) v.findViewById(R.id.editPrivateFacilityForMrdt);
        editNumberOfChvs = (EditText) v.findViewById(R.id.editNumberOfChvs);
        editChvHouseHold = (EditText) v.findViewById(R.id.editChvHouseHold);

        editNumberOfHouseHolds = (EditText) v.findViewById(R.id.editNumberOfHouseHolds);
        editMohPopulation = (EditText) v.findViewById(R.id.editMohPopulation);
        editPopulationDensity = (EditText) v.findViewById(R.id.editPopulationDensity);
        editNumberOfVillages = (EditText) v.findViewById(R.id.editNumberOfVillages);

        editDistanceToBranch = (EditText) v.findViewById(R.id.editDistanceToBranch);
        editTransportCost = (EditText) v.findViewById(R.id.editTransportCost);
        editDistanceToMainRoad = (EditText) v.findViewById(R.id.editDistanceToMainRoad);
        editDistanceToHealthFacility = (EditText) v.findViewById(R.id.editDistanceToHealthFacility);

        editLinkFacility = (EditText) v.findViewById(R.id.editLinkFacility);
        editDistributors = (EditText) v.findViewById(R.id.editDistributors);
        editCHVsTrained = (EditText) v.findViewById(R.id.editCHVsTrained);


        editPresenceOfFactories = (RadioGroup) v.findViewById(R.id.editPresenceOfFactories);
        editPresenceEstates = (RadioGroup) v.findViewById(R.id.editPresenceEstates);
        editPresenceOfTraderMarket = (RadioGroup) v.findViewById(R.id.editPresenceOfTraderMarket);
        editPresenceOfSuperMarket = (RadioGroup) v.findViewById(R.id.editPresenceOfSuperMarket);
        editNgosGivingFreeDrugs = (RadioGroup) v.findViewById(R.id.editNgosGivingFreeDrugs);

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

                String name = editName.getText().toString();
                String areaChiefName = editAreaChiefName.getText().toString();
                String areaChiefPhone = editAreaChiefPhone.getText().toString();
                String ward = editWard.getText().toString();
                String economicStatus = String.valueOf(editEconomicStatus.getSelectedItemId());

                String privateFacilityForAct = editPrivateFacilityForAct.getText().toString();
                String privateFacilityForMrdt = editPrivateFacilityForMrdt.getText().toString();
                Long numberOfChvs = Long.valueOf(editNumberOfChvs.getText().toString());
                Long chvHouseHold = Long.valueOf(editChvHouseHold.getText().toString());

                Long numberOfHouseHolds = Long.valueOf(editNumberOfHouseHolds.getText().toString());
                Long mohPopulation = Long.valueOf(editMohPopulation.getText().toString());
                Long populationDensity = Long.valueOf(editPopulationDensity.getText().toString());
                Long numberOfVillages = Long.valueOf(editNumberOfVillages.getText().toString());

                Long distanceToBranch = Long.valueOf(editDistanceToBranch.getText().toString());
                Long transportCost = Long.valueOf(editTransportCost.getText().toString());
                Long distanceToMainRoad = Long.valueOf(editDistanceToMainRoad.getText().toString());
                Long distanceToHealthFacility = Long.valueOf(editDistanceToHealthFacility.getText().toString());

                String linkFacility = editLinkFacility.getText().toString();
                Long distributors = Long.valueOf(editDistributors.getText().toString());
                boolean cHVsTrained = (editCHVsTrained.getText().toString() == "Yes");


                Integer factoriesPresent = editPresenceOfFactories.getCheckedRadioButtonId();
                RadioButton selectedFactoryOption =(RadioButton) editPresenceOfFactories.findViewById(factoriesPresent);
                boolean presenceOfFactories = (selectedFactoryOption.getText().toString() != "");

                Integer estatesPresent = editPresenceEstates.getCheckedRadioButtonId();
                RadioButton selectedEstateOption =(RadioButton) editPresenceEstates.findViewById(estatesPresent);
                boolean presenceEstates = (selectedEstateOption.getText().toString() != "");

                Integer tradeMarketsPresent = editPresenceOfTraderMarket.getCheckedRadioButtonId();
                RadioButton selectedTradeMarketOption =(RadioButton) editPresenceOfTraderMarket.findViewById(tradeMarketsPresent);
                boolean presenceOfTraderMarket = (selectedTradeMarketOption.getText().toString() != "");


                Integer superMarketPresent = editPresenceOfSuperMarket.getCheckedRadioButtonId();
                RadioButton selectedSuperMarketOption =(RadioButton) editPresenceOfSuperMarket.findViewById(superMarketPresent);
                boolean presenceOfSuperMarket = (selectedSuperMarketOption.getText().toString() != "");


                Integer ngosDrugs = editNgosGivingFreeDrugs.getCheckedRadioButtonId();
                RadioButton ngosGivingFreeDrugsSelected =(RadioButton) editNgosGivingFreeDrugs.findViewById(ngosDrugs);
                boolean ngosGivingFreeDrugs = (ngosGivingFreeDrugsSelected.getText().toString() != "");

                // Do some validations

                if (name.toString().trim().equals("")){
                    Toast.makeText(getContext(), "Enter name of the Sublocation", Toast.LENGTH_SHORT).show();
                    editName.requestFocus();
                }

                else if (areaChiefName.toString().trim().equals("")){
                    Toast.makeText(getContext(), "Enter the name of the Chief", Toast.LENGTH_SHORT).show();
                    editAreaChiefName.requestFocus();
                }

                else if(areaChiefPhone.toString().trim().equals("")){
                    Toast.makeText(getContext(), "Enter the contact details of the chief", Toast.LENGTH_SHORT).show();
                    editAreaChiefPhone.requestFocus();
                } else{
                    Toast.makeText(getContext(), "saved", Toast.LENGTH_SHORT).show();
                    String id = UUID.randomUUID().toString();
                    long userId = Long.valueOf(user.get(SessionManagement.KEY_USERID));
                    String country = user.get(SessionManagement.KEY_USER_COUNTRY);

                    CommunityUnit communityUnit = new CommunityUnit(id,name, mapping.getId(), latitude, longitude,
                            country, subCounty.getId(),linkFacility , areaChiefName,
                            ward, economicStatus, privateFacilityForAct, privateFacilityForMrdt,
                            "", "", currentDate, userId, numberOfChvs, chvHouseHold, numberOfVillages,
                            distanceToBranch, transportCost, distanceToMainRoad,
                            numberOfHouseHolds, mohPopulation, populationDensity,
                            distanceToHealthFacility, 0, 0, 0, 0, distributors, cHVsTrained,
                            presenceEstates, presenceOfFactories, presenceEstates, presenceOfTraderMarket,
                            presenceOfSuperMarket, ngosGivingFreeDrugs, false, false);
                    CommunityUnitTable communityUnitTable = new CommunityUnitTable(getContext());
                    // long cid = communityUnitTable.addData(communityUnit);
                    //if (cid != -1){
                        Toast.makeText(getContext(), "Community Unit saved successfuly", Toast.LENGTH_SHORT).show();
                    //}
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
