package com.example.version3_355app.ui.to_do;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.version3_355app.DataBaseHelper;
import com.example.version3_355app.R;
import com.example.version3_355app.databinding.FragmentToDoBinding;
import com.example.version3_355app.ui.calendar.CalendarFragment;
import com.example.version3_355app.ui.to_do.ToDoFormFragment;

import java.util.List;

public class TodoFragment extends Fragment {

    private FragmentToDoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TodoViewModel todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        binding = FragmentToDoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTodo;
        todoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ListView lv_todoList;
        lv_todoList = getView().findViewById(R.id.todoList);

        DataBaseHelper dataBaseHelper= new DataBaseHelper(getContext());
        List<TodoModel> everything= dataBaseHelper.everything();

        ArrayAdapter todoArrayAdapter= new ArrayAdapter<TodoModel>(getActivity(), android.R.layout.simple_expandable_list_item_1,everything);
        lv_todoList.setAdapter(todoArrayAdapter);

//        ListView lv_todolist= getView().findViewById(R.id.todoList);
//        List<String> assignments=dataBaseHelper.assignments();
//
//        ArrayAdapter todoArrayAdapter= new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1,
//                assignments);
//        lv_todolist.setAdapter(todoArrayAdapter);
//
//        ListView lv_todolist2= getView().findViewById(R.id.todoList2);
//        List<String> dates=dataBaseHelper.dates();
//
//        ArrayAdapter todoArrayAdapter2= new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1,
//                dates);
//        lv_todolist2.setAdapter(todoArrayAdapter2);

//        Toast.makeText(getContext(), everything.toString(), Toast.LENGTH_SHORT).show();

        binding.todoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TodoFragment.this)
                        .navigate(R.id.nav_action_todo_to_form);
            }
        });

        lv_todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TodoModel clickedTodo= (TodoModel) adapterView.getItemAtPosition(i);
                dataBaseHelper.deleteOne(clickedTodo);

                ArrayAdapter todoArrayAdapter= new ArrayAdapter<TodoModel>(getActivity(), android.R.layout.simple_expandable_list_item_1,dataBaseHelper.everything());
                lv_todoList.setAdapter(todoArrayAdapter);
                Toast.makeText(getActivity(), "Deleted " + clickedTodo.toString(), Toast.LENGTH_SHORT).show();

            }
        });




    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}