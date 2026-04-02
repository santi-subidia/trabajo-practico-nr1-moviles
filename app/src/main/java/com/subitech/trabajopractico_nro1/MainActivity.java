package com.subitech.trabajopractico_nro1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.subitech.trabajopractico_nro1.databinding.ActivityMainBinding;
import com.subitech.trabajopractico_nro1.model.Moneda;
import com.subitech.trabajopractico_nro1.model.VistaMainActivity;

import java.util.ArrayList;

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

        binding.etDolar.setEnabled(false);
        binding.etEuros.setEnabled(false);

        viewModel.getErrorMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getVistaMutable().observe(this, new Observer<VistaMainActivity>() {
            @Override
            public void onChanged(VistaMainActivity vistaMainActivity) {
                binding.etValorDeConversion.setText(String.valueOf(vistaMainActivity.getMoneda().getValorIntercambio()));
                binding.etDolar.setText(vistaMainActivity.getValorPrimerMoneda());
                binding.etEuros.setText(vistaMainActivity.getValorSegundaMoneda());
            }
        });


        binding.rbtnDolares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etEuros.setEnabled(true);
                binding.etDolar.setEnabled(false);
                binding.etDolar.setText("");
                viewModel.getValorConversion("dolar");
            }
        });

        binding.rbtnEuros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etDolar.setEnabled(true);
                binding.etEuros.setEnabled(false);
                binding.etEuros.setText("");
                viewModel.getValorConversion("euro");
            }
        });


        binding.btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean estadoPrimerMoneda = binding.etDolar.isEnabled();
                String valorPrimerMoneda = binding.etDolar.getText().toString();
                String valorSegundaMoneda = binding.etEuros.getText().toString();

                viewModel.convertirMoneda(estadoPrimerMoneda,valorPrimerMoneda,valorSegundaMoneda);
            }
        });

        binding.btnCambiarValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setValorConversion(binding.etValorDeConversion.getText().toString());
            }
        });
    }
}