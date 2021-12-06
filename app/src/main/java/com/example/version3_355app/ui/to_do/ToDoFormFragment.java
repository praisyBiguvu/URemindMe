package com.example.version3_355app.ui.to_do;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.version3_355app.R;
import com.example.version3_355app.databinding.FragmentFormTodoBinding;


public class ToDoFormFragment extends Fragment {

    private FragmentFormTodoBinding binding;
    Button buttonSecond, backButton;
    EditText et_assignment, et_date, et_time, et_class;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFormTodoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonSecond= getView().findViewById(R.id.button_second);
        backButton= getView().findViewById(R.id.backButton);
        et_assignment= getView().findViewById(R.id.et_assignment);
        et_date= getView().findViewById(R.id.et_date);
        et_time = getView().findViewById(R.id.et_time);
//        et_class= getView().findViewById(R.id.et_class);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TodoModel to_do_form= new TodoModel(et_assignment.getText().toString(), et_date., et_time.getText().toString(), "TBD");
                NavHostFragment.findNavController(ToDoFormFragment.this)
                        .navigate(R.id.nav_action_form_to_cal);
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