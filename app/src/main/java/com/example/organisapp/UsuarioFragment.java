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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.organisapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class UsuarioFragment extends Fragment {

    private TextView correo;
    private EditText nombre,
                     apellidos,
                     password,
                     confirmacion;
    private Button botonModificar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_usuario, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        this.correo = (TextView) getView().findViewById(R.id.correoUsuario);
        this.nombre = (EditText) getView().findViewById(R.id.nombreModificar);
        this.apellidos = (EditText) getView().findViewById(R.id.apellidosModificar);
        this.password = (EditText) getView().findViewById(R.id.passwordModificar);
        this.confirmacion = (EditText) getView().findViewById(R.id.confirmacionModificar);

        this.correo.setText(MainActivity.usuarioActual.getCorreo());
        this.nombre.setText(MainActivity.usuarioActual.getNombre());
        this.apellidos.setText(MainActivity.usuarioActual.getApellidos());

        this.botonModificar = (Button) getView().findViewById(R.id.botonModificar);

        this.botonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(nombre.getText().toString().trim().equals("") ||
                        apellidos.getText().toString().trim().equals("") ||
                        password.getText().toString().trim().equals("") ||
                        confirmacion.getText().toString().trim().equals(""))) {
                    if(password.getText().toString().equals(confirmacion.getText().toString())) {
                        if(Pattern.compile("[0-9]").matcher(password.getText().toString()).find() &&
                                Pattern.compile("[a-zA-Z]").matcher(password.getText().toString()).find() &&
                                password.getText().toString().length() > 6) {
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
                            Toast.makeText(getActivity().getApplicationContext(), "La contraseña debe contener al menos 7 carácteres y al menos un numero o letra", Toast.LENGTH_SHORT).show();
                        }
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