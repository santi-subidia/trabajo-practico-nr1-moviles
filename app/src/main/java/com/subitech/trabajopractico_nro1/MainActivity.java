package com.subitech.trabajopractico_nro1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.subitech.trabajopractico_nro1.databinding.ActivityMainBinding;
import com.subitech.trabajopractico_nro1.model.Moneda;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private String monedaSeleccionada;

    private ArrayList<String> Inputs = new ArrayList<>();

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

        binding.rbtnDolares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etEuros.setEnabled(true);
                binding.etDolar.setEnabled(false);
                binding.etDolar.setText("");
                monedaSeleccionada = "dolar";
            }
        });

        binding.rbtnEuros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etDolar.setEnabled(true);
                binding.etEuros.setEnabled(false);
                binding.etEuros.setText("");
                monedaSeleccionada = "euro";
            }
        });


        binding.btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInputs();
                viewModel.convertirMoneda(monedaSeleccionada,Inputs);

            }
        });

        binding.btnCambiarValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void getInputs(){
        Inputs.clear();
        Inputs.add(binding.etDolar.getText().toString());
        Inputs.add(binding.etEuros.getText().toString());
    }


    private void resetVistas(){
        binding.etDolar.setEnabled(false);
        binding.etEuros.setEnabled(false);
        binding.btnCambiarValor.setEnabled(false);
        binding.btnConvertir.setEnabled(true);
    }
}