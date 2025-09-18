package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab2.databinding.ActivityRouterFormBinding;
import com.example.lab2.databinding.ActivitySwitchDetailBinding;

public class SwitchDetailActivity extends AppCompatActivity {

    private ActivitySwitchDetailBinding binding;
    private int switchIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySwitchDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        switchIndex = getIntent().getIntExtra("index", -1);
        if (switchIndex == -1) finish();

        Switch r = SwitchesActivity.switches.get(switchIndex);

        binding.toolbar.toolbarTitle.setText("Actualizar Switch");

        binding.toolbar.btnCheck.setVisibility(View.VISIBLE);
        binding.toolbar.btnDelete.setVisibility(View.VISIBLE);

        binding.toolbar.btnBack.setOnClickListener(v -> onBackPressed());
        binding.toolbar.btnCheck.setOnClickListener(v -> guardarCambios());
        binding.toolbar.btnDelete.setOnClickListener(v -> confirmarBorrar());

        binding.editMarca.setText(r.getMarca());
        binding.editModelo.setText(r.getModelo());
        binding.editCantidadPuertos.setText(r.getCantidad_puertosd());
        binding.editTipo.setText(r.getTipo());
        binding.editEstado.setText(r.getEstado());
    }

    private void guardarCambios() {
        Switch r = SwitchesActivity.switches.get(switchIndex);
        r.setMarca(binding.editMarca.getText().toString());
        r.setModelo(binding.editModelo.getText().toString());
        r.setCantidad_puertos(binding.editCantidadPuertos.getText().toString());
        r.setEstado(binding.editTipo.getText().toString());
        r.setEstado(binding.editEstado.getText().toString());
        Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void confirmarBorrar() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar")
                .setMessage("¿Está seguro que desea borrar?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    SwitchesActivity.switches.remove(switchIndex);
                    finish();
                })
                .show();
    }
}