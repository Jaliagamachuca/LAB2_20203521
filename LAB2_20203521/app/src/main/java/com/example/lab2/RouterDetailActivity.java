package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2.databinding.ActivityRouterFormBinding;

public class RouterDetailActivity extends AppCompatActivity {

    private ActivityRouterFormBinding binding;
    private int routerIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRouterFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        routerIndex = getIntent().getIntExtra("index", -1);
        if (routerIndex == -1) finish();

        Router r = RoutersActivity.routers.get(routerIndex);

        binding.toolbar.toolbarTitle.setText("Actualizar Router");

        binding.toolbar.btnCheck.setVisibility(View.VISIBLE);
        binding.toolbar.btnDelete.setVisibility(View.VISIBLE);

        binding.toolbar.btnBack.setOnClickListener(v -> onBackPressed());
        binding.toolbar.btnCheck.setOnClickListener(v -> guardarCambios());
        binding.toolbar.btnDelete.setOnClickListener(v -> confirmarBorrar());

        binding.editMarca.setText(r.getMarca());
        binding.editModelo.setText(r.getModelo());
        binding.editVelocidad.setText(r.getVelocidad());
        binding.editEstado.setText(r.getEstado());
    }

    private void guardarCambios() {
        Router r = RoutersActivity.routers.get(routerIndex);
        r.setMarca(binding.editMarca.getText().toString());
        r.setModelo(binding.editModelo.getText().toString());
        r.setVelocidad(binding.editVelocidad.getText().toString());
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
                    RoutersActivity.routers.remove(routerIndex);
                    finish();
                })
                .show();
    }
}
