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

import com.example.lab2.databinding.ActivityReporteBinding;
import com.example.lab2.databinding.ActivityRoutersBinding;
import com.example.lab2.databinding.CustomToolbarBinding;

public class ReporteActivity extends AppCompatActivity {

    private ActivityReporteBinding binding;
    private CustomToolbarBinding toolbarBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activar ViewBinding para activity_main.xml
        binding = ActivityReporteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbarBinding = binding.myToolbar;

        toolbarBinding.toolbarTitle.setText("Reporte");

        toolbarBinding.btnBack.setOnClickListener(v -> onBackPressed());

        setToolbarVersion1();

    }

    // --- Versión 1: Solo flecha + título ---
    private void setToolbarVersion1() {
        toolbarBinding.btnCheck.setVisibility(View.GONE);
        toolbarBinding.btnDelete.setVisibility(View.GONE);
    }

    // --- Versión 2: flecha + título + check ---
    private void setToolbarVersion2() {
        toolbarBinding.btnCheck.setVisibility(View.VISIBLE);
        toolbarBinding.btnDelete.setVisibility(View.GONE);

        toolbarBinding.btnCheck.setOnClickListener(v ->
                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
        );
    }

    // --- Versión 3: flecha + título + check + tacho ---
    private void setToolbarVersion3() {
        toolbarBinding.btnCheck.setVisibility(View.VISIBLE);
        toolbarBinding.btnDelete.setVisibility(View.VISIBLE);

        toolbarBinding.btnCheck.setOnClickListener(v ->
                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
        );

        toolbarBinding.btnDelete.setOnClickListener(v ->
                new AlertDialog.Builder(this)
                        .setTitle("Eliminar")
                        .setMessage("¿Seguro que deseas eliminar?")
                        .setPositiveButton("Sí", (dialog, which) ->
                                Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show()
                        )
                        .setNegativeButton("Cancelar", null)
                        .show()
        );
    }
}