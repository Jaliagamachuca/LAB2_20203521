package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.databinding.ActivityAccessPointsBinding;

import java.util.ArrayList;

public class AccessPointsActivity extends AppCompatActivity {

    public static ArrayList<AccessPoint> accessPoints = new ArrayList<>();
    private ActivityAccessPointsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccessPointsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.toolbarTitle.setText("Access Points");
        binding.toolbar.btnBack.setOnClickListener(v -> onBackPressed());

        binding.fabAdd.setOnClickListener(v -> {
            if (accessPoints.size() < 3) {
                startActivity(new Intent(this, AccessPointFormActivity.class));
            } else {
                binding.containerAccessPoints.removeAllViews();
                TextView txt = new TextView(this);
                txt.setText("Ya se registraron los 3 access points permitidos");
                txt.setPadding(16, 16, 16, 16);
                binding.containerAccessPoints.addView(txt);
            }
        });

        mostrarAccessPoints();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarAccessPoints();
    }

    private void mostrarAccessPoints() {
        binding.containerAccessPoints.removeAllViews();

        if (accessPoints.isEmpty()) {
            TextView txt = new TextView(this);
            txt.setText("No hay registros disponibles");
            txt.setPadding(16, 16, 16, 16);
            binding.containerAccessPoints.addView(txt);
        } else {
            for (int i = 0; i < accessPoints.size(); i++) {
                AccessPoint ap = accessPoints.get(i);
                int index = i;

                TextView item = new TextView(this);
                item.setText(
                        "Marca: " + ap.getMarca() + "\n" +
                                "Frecuencia: " + ap.getFrecuencia() + "\n" +
                                "Alcance: " + ap.getAlcance() + "\n" +
                                "Estado: " + ap.getEstado()
                );
                item.setPadding(16, 16, 16, 16);
                item.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
                item.setClickable(true);

                item.setOnClickListener(v -> {
                    Intent intent = new Intent(this, AccessPointDetailActivity.class);
                    intent.putExtra("index", index);
                    startActivity(intent);
                });

                binding.containerAccessPoints.addView(item);

                View line = new View(this);
                line.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, 2));
                line.setBackgroundColor(0xFFCCCCCC);
                binding.containerAccessPoints.addView(line);
            }
        }
    }
}
