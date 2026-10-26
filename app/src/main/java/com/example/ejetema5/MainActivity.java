 package com.example.ejetema5;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.materialswitch.MaterialSwitch;

public class MainActivity extends AppCompatActivity {

    EditText txtCorreo, txtPassword;
    MaterialSwitch swRecordar;
    Button btnContinuar;
    TextView txtMensaje;
    int intentos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtCorreo = findViewById(R.id.txtCorreo);
        txtPassword = findViewById(R.id.txtPassword);
        swRecordar = findViewById(R.id.swRecordar);
        btnContinuar = findViewById(R.id.btnContinuar);
        txtMensaje = findViewById(R.id.txtMensaje);

        btnContinuar.setOnClickListener(v -> {

            String correo = txtCorreo.getText().toString();
            String password = txtPassword.getText().toString();

            if (correo.isEmpty()) {
                txtCorreo.setError("Escribe tu correo");
                return;
            }

            if (password.isEmpty()) {
                txtPassword.setError("Escribe la contraseña");
                return;
            }

            if (correo.equals("marcelo@correo.com") &&
                    password.equals("1234")) {

                txtMensaje.setTextColor(ContextCompat.getColor(this, R.color.verde));

                if (swRecordar.isChecked()) {
                    txtMensaje.setText(
                            "Usuario y contraseña correctos. Almacenados para siguientes accesos"
                    );
                } else {
                    txtMensaje.setText("Usuario y contraseña correctos");
                }

            } else {

                txtMensaje.setText("Usuario y/o contraseña incorrectos");
                txtMensaje.setTextColor(ContextCompat.getColor(this, R.color.rojo));
                intentos++;
            }

            if(intentos==3){
                btnContinuar.setEnabled(false);
                btnContinuar.setBackgroundColor(Color.GRAY);
            }

        });
    }
}