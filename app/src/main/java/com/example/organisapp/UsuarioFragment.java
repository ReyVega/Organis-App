package com.example.organisapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.organisapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UsuarioFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_usuario, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        EditText nombre2 = (EditText) getView().findViewById(R.id.nombreModificar);
        EditText apellidos2 = (EditText) getView().findViewById(R.id.apellidosModificar);

        nombre2.setText(MainActivity.usuarioActual.getNombre());
        apellidos2.setText(MainActivity.usuarioActual.getApellidos());

        Button botonM = (Button) getView().findViewById(R.id.botonModificar);

        botonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nombre = (EditText) getView().findViewById(R.id.nombreModificar);
                EditText apellidos = (EditText) getView().findViewById(R.id.apellidosModificar);
                EditText password = (EditText) getView().findViewById(R.id.passwordModificar);
                EditText confirmacion = (EditText) getView().findViewById(R.id.confirmacionModificar);

                if(!(nombre.getText().toString().trim().equals("") ||
                        apellidos.getText().toString().trim().equals("") ||
                        password.getText().toString().trim().equals("") ||
                        confirmacion.getText().toString().trim().equals(""))) {
                    if(password.getText().toString().equals(confirmacion.getText().toString())) {
                        MainActivity.usuarioActual.setNombre(nombre.getText().toString().trim());
                        MainActivity.usuarioActual.setApellidos(apellidos.getText().toString().trim());
                        MainActivity.usuarioActual.setContrasena(password.getText().toString());
                        for(int i = 0; i < MainActivity.usuarios.size(); i++) {
                            if(MainActivity.usuarios.get(i).getID() == MainActivity.usuarioActual.getID()) {
                                MainActivity.usuarios.set(i, MainActivity.usuarioActual);
                            }
                        }
                        Toast.makeText(getActivity().getApplicationContext(), "Datos modificados", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "La contraseña no coincide", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Rellenar campos faltantes", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}