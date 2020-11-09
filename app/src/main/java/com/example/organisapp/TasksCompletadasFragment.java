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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_taskscompletadas, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        ListView lv = (ListView) getView().findViewById(R.id.stCompletadas);

        final CustomListAdapter adapter = new CustomListAdapter(getActivity().getApplicationContext(), R.layout.custom_view, MainActivity.usuarioActual.getCompletadas());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openTaskFormCompletada(position);
            }
        });

        Button vaciar = (Button) getView().findViewById(R.id.vaciar);
        vaciar.setOnClickListener(new View.OnClickListener() {
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
