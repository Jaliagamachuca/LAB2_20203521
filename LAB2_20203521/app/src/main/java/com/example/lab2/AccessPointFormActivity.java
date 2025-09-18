package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.databinding.ActivityAccessPointFormBinding;

public class AccessPointFormActivity extends AppCompatActivity {

    private ActivityAccessPointFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccessPointFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.toolbarTitle.setText("Crear Access Point");

        binding.toolbar.btnCheck.setVisibility(View.VISIBLE);
        binding.toolbar.btnDelete.setVisibility(View.GONE);
        binding.toolbar.btnBack.setOnClickListener(v -> onBackPressed());

        binding.toolbar.btnCheck.setOnClickListener(v -> {
            String marca = binding.editMarca.getText().toString();
            String frecuencia = binding.editFrecuencia.getText().toString();
            String alcance = binding.editAlcance.getText().toString();
            String estado = binding.editEstado.getText().toString();

            if (marca.isEmpty() || frecuencia.isEmpty() || alcance.isEmpty() || estado.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            AccessPointsActivity.accessPoints.add(new AccessPoint(marca, frecuencia, alcance, estado));
            finish();
        });
    }
}
