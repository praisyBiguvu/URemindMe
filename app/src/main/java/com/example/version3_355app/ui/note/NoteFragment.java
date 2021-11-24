package com.example.version3_355app.ui.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.version3_355app.R;

import com.example.version3_355app.databinding.FragmentNoteBinding;

public class NoteFragment extends Fragment {


    private NoteViewModel noteViewModel;
    private FragmentNoteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        noteViewModel =
                new ViewModelProvider(this).get(NoteViewModel.class);

        binding = FragmentNoteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNote;
        noteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.nfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NoteFragment.this)
                        .navigate(R.id.nav_action_note_to_noteform);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}