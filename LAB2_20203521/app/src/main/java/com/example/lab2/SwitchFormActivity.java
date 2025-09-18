package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab2.databinding.ActivityRouterFormBinding;
import com.example.lab2.databinding.ActivitySwitchFormBinding;

public class SwitchFormActivity extends AppCompatActivity {

    private ActivitySwitchFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySwitchFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.toolbarTitle.setText("Crear Switch");

        binding.toolbar.btnCheck.setVisibility(View.VISIBLE);
        binding.toolbar.btnDelete.setVisibility(View.GONE);

        binding.toolbar.btnBack.setOnClickListener(v -> onBackPressed());

        binding.toolbar.btnCheck.setOnClickListener(v -> {
            String marca = binding.editMarca.getText().toString();
            String modelo = binding.editModelo.getText().toString();
            String cantidad_puertos = binding.editCantidadPuertos.getText().toString();
            String tipo = binding.editTipo.getText().toString();
            String estado = binding.editEstado.getText().toString();

            if (marca.isEmpty() || modelo.isEmpty() || cantidad_puertos.isEmpty() || tipo.isEmpty() || estado.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            SwitchesActivity.switches.add(new Switch(marca, modelo, cantidad_puertos, tipo, estado));
            finish();
        });
    }
}