package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab2.databinding.ActivityRoutersBinding;
import com.example.lab2.databinding.ActivitySwitchesBinding;
import com.example.lab2.databinding.CustomToolbarBinding;

import java.util.ArrayList;

public class SwitchesActivity extends AppCompatActivity {

    public static ArrayList<Switch> switches = new ArrayList<>();
    private ActivitySwitchesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySwitchesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.toolbarTitle.setText("Switches");
        binding.toolbar.btnBack.setOnClickListener(v -> onBackPressed());

        binding.fabAdd.setOnClickListener(v -> {
            if (switches.size() < 3) {
                startActivity(new Intent(this, SwitchFormActivity.class));
            } else {
                binding.containerSwitches.removeAllViews();
                TextView txt = new TextView(this);
                txt.setText("Ya se registraron los 3 switches permitidos");
                txt.setPadding(16, 16, 16, 16);
                binding.containerSwitches.addView(txt);
            }
        });

        mostrarSwitches();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarSwitches();
    }

    private void mostrarSwitches() {
        binding.containerSwitches.removeAllViews();

        if (switches.isEmpty()) {
            TextView txt = new TextView(this);
            txt.setText("No hay registros disponibles");
            txt.setPadding(16, 16, 16, 16);
            binding.containerSwitches.addView(txt);
        } else {
            for (int i = 0; i < switches.size(); i++) {
                Switch r = switches.get(i);
                int index = i;

                TextView item = new TextView(this);
                item.setText(
                        "Marca: " + r.getMarca() + "\n" +
                                "Modelo: " + r.getModelo() + "\n" +
                                "Cantidad de Puertos: " + r.getCantidad_puertosd() + "\n" +
                                "Tipo: " + r.getTipo() + "\n" +
                                "Estado: " + r.getEstado()
                );
                item.setPadding(16, 16, 16, 16);
                item.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
                item.setClickable(true);

                item.setOnClickListener(v -> {
                    Intent intent = new Intent(this, SwitchDetailActivity.class);
                    intent.putExtra("index", index);
                    startActivity(intent);
                });

                binding.containerSwitches.addView(item);

                // Separador visual
                View line = new View(this);
                line.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, 2));
                line.setBackgroundColor(0xFFCCCCCC);
                binding.containerSwitches.addView(line);
            }
        }
    }
}