package com.subitech.trabajopractico_nro1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.subitech.trabajopractico_nro1.databinding.ActivityMainBinding;
import com.subitech.trabajopractico_nro1.model.Moneda;
import com.subitech.trabajopractico_nro1.viewModel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private String monedaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        resetVistas();

        viewModel.getErrorMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                resetVistas();
            }
        });


        viewModel.getMonedaMutable().observe(this, new Observer<Moneda>() {
            @Override
            public void onChanged(Moneda moneda) {
                binding.tvConversor.setText("1$ = " + moneda.getValorConvertir());
                resetVistas();
            }
        });

        binding.btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idSeleccionado = binding.rgMonedas.getCheckedRadioButtonId();
                if(idSeleccionado == R.id.rbtnDolares){
                    binding.etDolar.setEnabled(true);
                    monedaSeleccionada = "Dolar";
                } else if (idSeleccionado == R.id.rbtnEuros) {
                    binding.etEuros.setEnabled(true);
                    monedaSeleccionada = "Euro";
                }else {
                    Toast.makeText(MainActivity.this, "Seleccione una moneda de conversión", Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.btnConvertir.setEnabled(false);
                binding.btnCambiarValor.setEnabled(true);
            }
        });

        binding.btnCambiarValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.convertirMoneda(monedaSeleccionada,Double.parseDouble(binding.etEuros.getText().toString()));
            }
        });
    }

    private void resetVistas(){
        binding.etDolar.setEnabled(false);
        binding.etEuros.setEnabled(false);
        binding.btnCambiarValor.setEnabled(false);
        binding.btnConvertir.setEnabled(true);
    }
}