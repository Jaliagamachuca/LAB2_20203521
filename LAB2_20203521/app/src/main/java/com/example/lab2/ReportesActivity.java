package com.example.lab2;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.databinding.ActivityReportesBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportesActivity extends AppCompatActivity {

    private ActivityReportesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.toolbarTitle.setText("Reportes");
        binding.toolbar.btnBack.setOnClickListener(v -> onBackPressed());

        mostrarReporte();
    }

    private void mostrarReporte() {
        binding.containerReportes.removeAllViews();

        // 3 Estados a manejar indicados en el enunciado del laboratoiro, en este caso asumo que el usuario los pondra bien
        String[] estados = {"Operativo", "En reparacion", "Dado de baja"};

        for (String estado : estados) {
            ArrayList<String> listaEquipos = new ArrayList<>();


            for (Router r : RoutersActivity.routers) {
                if (r.getEstado().equalsIgnoreCase(estado)) {
                    listaEquipos.add("Router: " + r.getMarca() + " " + r.getModelo());
                }
            }


            for (Switch s : SwitchesActivity.switches) {
                if (s.getEstado().equalsIgnoreCase(estado)) {
                    listaEquipos.add("Switch: " + s.getMarca() + " " + s.getModelo());
                }
            }


            for (AccessPoint ap : AccessPointsActivity.accessPoints) {
                if (ap.getEstado().equalsIgnoreCase(estado)) {
                    listaEquipos.add("AP: " + ap.getMarca() + " " + ap.getFrecuencia());
                }
            }


            TextView titulo = new TextView(this);
            titulo.setText(estado + " (" + listaEquipos.size() + ")");
            titulo.setTextSize(18);
            titulo.setPadding(16, 24, 16, 8);
            binding.containerReportes.addView(titulo);


            if (listaEquipos.isEmpty()) {
                TextView vacio = new TextView(this);
                vacio.setText("   - Ninguno");
                vacio.setPadding(32, 8, 16, 8);
                binding.containerReportes.addView(vacio);
            } else {
                for (String equipo : listaEquipos) {
                    TextView item = new TextView(this);
                    item.setText("   - " + equipo);
                    item.setPadding(32, 8, 16, 8);
                    binding.containerReportes.addView(item);
                }
            }
        }
    }
}
