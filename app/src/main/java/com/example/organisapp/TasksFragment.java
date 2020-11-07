package com.example.organisapp;

import android.content.DialogInterface;
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
import com.example.organisapp.Registro;
import com.example.organisapp.TaskForm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class TasksFragment extends Fragment {

    private FloatingActionButton fab;
    private ListView lv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tasks, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskForm();
            }
        });

        lv = (ListView) getView().findViewById(R.id.st);

        ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1,MainActivity.usuarioActual.getDiarias());
        adapter.addAll(MainActivity.usuarioActual.getSemanales());
        adapter.addAll(MainActivity.usuarioActual.getMensuales());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                openTaskForm();
            }
        });
    }

    public void openTaskForm() {
        Intent intent = new Intent(getActivity(), TaskForm.class);
        startActivity(intent);
    }

}