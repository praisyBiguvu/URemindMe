package com.example.version3_355app.ui.to_do;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
//        submit= getView().findViewById(R.id.submit);
//        backButton= getView().findViewById(R.id.backButton);
        et_assignment= getView().findViewById(R.id.et_assignment);
        et_month= getView().findViewById(R.id.et_month);
        et_day= getView().findViewById(R.id.et_day);
        et_year= getView().findViewById(R.id.et_year);
        et_time = getView().findViewById(R.id.et_time);
//        et_class= getView().findViewById(R.id.et_class);

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                TodoModel to_do_form= new TodoModel(et_assignment.getText().toString(),
                        Integer.parseInt(et_month.getText().toString(),10),
                        Integer.parseInt(et_day.getText().toString(),10),
                        Integer.parseInt(et_year.getText().toString(), 10),
                        Time.valueOf(et_time.getText().toString()), "TBD");

                NavHostFragment.findNavController(ToDoFormFragment.this)
                        .navigate(R.id.nav_action_form_to_todo);
            }
        });

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