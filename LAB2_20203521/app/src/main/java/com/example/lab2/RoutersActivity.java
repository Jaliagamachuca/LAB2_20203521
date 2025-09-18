package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.databinding.ActivityRoutersBinding;

import java.util.ArrayList;

public class RoutersActivity extends AppCompatActivity {

    public static ArrayList<Router> routers = new ArrayList<>();
    private ActivityRoutersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoutersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.toolbarTitle.setText("Routers");
        binding.toolbar.btnBack.setOnClickListener(v -> onBackPressed());

        binding.fabAdd.setOnClickListener(v -> {
            if (routers.size() < 4) {
                startActivity(new Intent(this, RouterFormActivity.class));
            } else {
                binding.containerRouters.removeAllViews();
                TextView txt = new TextView(this);
                txt.setText("Ya se registraron los 4 routers permitidos");
                txt.setPadding(16, 16, 16, 16);
                binding.containerRouters.addView(txt);
            }
        });

        mostrarRouters();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarRouters();
    }

    private void mostrarRouters() {
        binding.containerRouters.removeAllViews();

        if (routers.isEmpty()) {
            TextView txt = new TextView(this);
            txt.setText("No hay registros disponibles");
            txt.setPadding(16, 16, 16, 16);
            binding.containerRouters.addView(txt);
        } else {
            for (int i = 0; i < routers.size(); i++) {
                Router r = routers.get(i);
                int index = i;

                TextView item = new TextView(this);
                item.setText(
                        "Marca: " + r.getMarca() + "\n" +
                                "Modelo: " + r.getModelo() + "\n" +
                                "Velocidad soportada: " + r.getVelocidad() + "\n" +
                                "Estado: " + r.getEstado()
                );
                item.setPadding(16, 16, 16, 16);
                item.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
                item.setClickable(true);

                item.setOnClickListener(v -> {
                    Intent intent = new Intent(this, RouterDetailActivity.class);
                    intent.putExtra("index", index);
                    startActivity(intent);
                });

                binding.containerRouters.addView(item);

                // Separador visual
                View line = new View(this);
                line.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, 2));
                line.setBackgroundColor(0xFFCCCCCC);
                binding.containerRouters.addView(line);
            }
        }
    }
}
