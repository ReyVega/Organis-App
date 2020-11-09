package com.example.organisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.organisapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TasksMensualesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tasksmensuales, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fabMensuales);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskForm();
            }
        });

        ListView lv = (ListView) getView().findViewById(R.id.stMensuales);

        CustomListAdapter adapter = new CustomListAdapter(getActivity().getApplicationContext(), R.layout.custom_view, MainActivity.usuarioActual.getMensuales());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                openTaskFormModificar(position);
            }
        });
    }

    public void openTaskForm() {
        Intent intent = new Intent(getActivity(), TaskForm.class);
        startActivity(intent);
    }

    public void openTaskFormModificar(int position) {
        Intent intent = new Intent(getActivity(), TaskFormModificar.class);
        intent.putExtra("tipoTask","diaria");
        intent.putExtra("position", position);
        startActivity(intent);
    }
}