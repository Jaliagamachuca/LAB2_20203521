package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2.databinding.ActivityRouterFormBinding;

public class RouterFormActivity extends AppCompatActivity {

    private ActivityRouterFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRouterFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.toolbarTitle.setText("Crear Router");

        binding.toolbar.btnCheck.setVisibility(View.VISIBLE);
        binding.toolbar.btnDelete.setVisibility(View.GONE);

        binding.toolbar.btnBack.setOnClickListener(v -> onBackPressed());

        binding.toolbar.btnCheck.setOnClickListener(v -> {
            String marca = binding.editMarca.getText().toString();
            String modelo = binding.editModelo.getText().toString();
            String velocidad = binding.editVelocidad.getText().toString();
            String estado = binding.editEstado.getText().toString();

            if (marca.isEmpty() || modelo.isEmpty() || velocidad.isEmpty() || estado.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            RoutersActivity.routers.add(new Router(marca, modelo, velocidad, estado));
            finish();
        });
    }
}