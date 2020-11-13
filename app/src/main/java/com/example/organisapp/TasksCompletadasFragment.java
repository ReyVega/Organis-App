package com.example.organisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TasksCompletadasFragment extends Fragment {

    private  ListView lv;
    private CustomListAdapter adapter;
    private Button vaciar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_taskscompletadas, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        this.lv = (ListView) getView().findViewById(R.id.stCompletadas);

        this.adapter = new CustomListAdapter(getActivity().getApplicationContext(), R.layout.custom_view, MainActivity.usuarioActual.getCompletadas());
        this.lv.setAdapter(this.adapter);
        this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openTaskFormCompletada(position);
            }
        });

        this.vaciar = (Button) getView().findViewById(R.id.vaciar);
        this.vaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.usuarioActual.getCompletadas().clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void openTaskFormCompletada(int position) {
        Intent intent = new Intent(getActivity(), TaskFormCompletadas.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
