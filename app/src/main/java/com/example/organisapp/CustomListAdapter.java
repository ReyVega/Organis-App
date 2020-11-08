package com.example.organisapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Task> {
    private Context context;
    private int resource;



    public CustomListAdapter(@NonNull Context context, int resource, ArrayList<Task> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String nombreActividad = getItem(position).getNombreActividad();
        String fecha = getItem(position).getFecha();
        String hora = getItem(position).getHora();

        Task task = new Task(nombreActividad,fecha,hora);

        LayoutInflater li = LayoutInflater.from(this.context);
        convertView = li.inflate(this.resource,parent,false);

        TextView nombreAc = (TextView) convertView.findViewById(R.id.nombreAc);
        TextView fechaAc = (TextView) convertView.findViewById(R.id.fechaAc);
        TextView horaAc = (TextView) convertView.findViewById(R.id.horaAc);

        nombreAc.setText(nombreActividad);
        fechaAc.setText(fecha);
        horaAc.setText(hora);

        return convertView;
    }
}
