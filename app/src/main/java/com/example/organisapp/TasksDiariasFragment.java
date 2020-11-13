package com.example.organisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TasksDiariasFragment extends Fragment {

    private FloatingActionButton fabDiarias;
    private ListView lv;
    private CustomListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tasksdiarias, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        this.fabDiarias = (FloatingActionButton) getView().findViewById(R.id.fabDiarias);
        this.fabDiarias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskForm();
            }
        });

        this.lv = (ListView) getView().findViewById(R.id.stDiarias);

        this.adapter = new CustomListAdapter(getActivity().getApplicationContext(), R.layout.custom_view, MainActivity.usuarioActual.getDiarias());
        this.lv.setAdapter(this.adapter);
        this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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