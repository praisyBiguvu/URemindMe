package com.example.version3_355app.ui.to_do;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.version3_355app.DataBaseHelper;
import com.example.version3_355app.MainActivity;
import com.example.version3_355app.R;
import com.example.version3_355app.databinding.FormTodoBinding;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ToDoFormFragment extends Fragment {

    private FormTodoBinding binding;
    Button submit, backButton;
    EditText et_assignment, et_month, et_day, et_year, et_time, et_class;
    Switch et_cmsc257, et_cmsc303, et_cmsc355, et_chm101;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FormTodoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submit= getView().findViewById(R.id.submit);
//        backButton= getView().findViewById(R.id.backButton);
        et_assignment= getView().findViewById(R.id.et_assignment);
        et_month= getView().findViewById(R.id.et_month);
        et_day= getView().findViewById(R.id.et_day);
        et_year= getView().findViewById(R.id.et_year);
        et_time = getView().findViewById(R.id.et_time);
        et_cmsc257=getView().findViewById(R.id.et_cmsc257);
        et_cmsc303=getView().findViewById(R.id.et_cmsc303);
        et_cmsc355=getView().findViewById(R.id.et_cmsc355);
        et_chm101=getView().findViewById(R.id.et_chem101);

//        et_class= getView().findViewById(R.id.et_class);

        final String[] c = {"in beginning"};





        et_cmsc257.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                c[0]= "CMSC 257";
                Log.v("Switch state =", " "+ isChecked);
            }
        });

        et_cmsc303.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                c[0]= "CMSC 303";
                Log.v("Switch state =", " "+ isChecked);
            }
        });

        et_cmsc355.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                c[0]= "CMSC 303";
                Log.v("Switch state =", " "+ isChecked);
            }
        });

        et_chm101.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                c[0]= "Chem 101";
                Log.v("Switch state =", " "+ isChecked);
            }
        });
        //when submit button gets clicked
        submit.setOnClickListener((v) -> {
//            @Override

//            public void onClick(View view) {



                TodoModel to_do_form;
                try {
                    to_do_form= new TodoModel(-1,et_assignment.getText().toString(),
                            Integer.parseInt(et_month.getText().toString(),10),
                            Integer.parseInt(et_day.getText().toString(),10),
                            Integer.parseInt(et_year.getText().toString(), 10),
                            Time.valueOf(et_time.getText().toString()), c[0]);
                    DataBaseHelper dataBaseHelper= new DataBaseHelper(getContext());
                    boolean success= dataBaseHelper.addOne(to_do_form);
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(ToDoFormFragment.this)
                            .navigate(R.id.nav_action_form_to_todo);
                }
                catch (Exception e){
                    Toast.makeText(getContext(), "Date and Time Format Error. Try Again. ", Toast.LENGTH_SHORT).show();



                }





        });

        //when backbutton is clicked
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ToDoFormFragment.this)
                        .navigate(R.id.nav_action_form_to_todo);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}