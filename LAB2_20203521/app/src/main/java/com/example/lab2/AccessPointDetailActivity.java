package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.databinding.ActivityAccessPointDetailBinding;

public class AccessPointDetailActivity extends AppCompatActivity {

    private ActivityAccessPointDetailBinding binding;
    private int apIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccessPointDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apIndex = getIntent().getIntExtra("index", -1);
        if (apIndex == -1) finish();

        AccessPoint ap = AccessPointsActivity.accessPoints.get(apIndex);

        binding.toolbar.toolbarTitle.setText("Actualizar Access Point");

        binding.toolbar.btnCheck.setVisibility(View.VISIBLE);
        binding.toolbar.btnDelete.setVisibility(View.VISIBLE);

        binding.toolbar.btnBack.setOnClickListener(v -> onBackPressed());
        binding.toolbar.btnCheck.setOnClickListener(v -> guardarCambios());
        binding.toolbar.btnDelete.setOnClickListener(v -> confirmarBorrar());

        binding.editMarca.setText(ap.getMarca());
        binding.editFrecuencia.setText(ap.getFrecuencia());
        binding.editAlcance.setText(ap.getAlcance());
        binding.editEstado.setText(ap.getEstado());
    }

    private void guardarCambios() {
        AccessPoint ap = AccessPointsActivity.accessPoints.get(apIndex);
        ap.setMarca(binding.editMarca.getText().toString());
        ap.setFrecuencia(binding.editFrecuencia.getText().toString());
        ap.setAlcance(binding.editAlcance.getText().toString());
        ap.setEstado(binding.editEstado.getText().toString());
        Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void confirmarBorrar() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Está seguro que desea borrar?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    AccessPointsActivity.accessPoints.remove(apIndex);
                    finish();
                })
                .show();
    }
}
