package com.subitech.trabajopractico_nro1.viewModel;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.snackbar.Snackbar;
import com.subitech.trabajopractico_nro1.MainActivity;
import com.subitech.trabajopractico_nro1.model.Moneda;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private List<Moneda> billetera;
    private MutableLiveData<Moneda> monedaMutable;
    private MutableLiveData<String> errorMensaje;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        initMonedas();
    }

    public LiveData<Moneda> getMonedaMutable (){
        if(monedaMutable == null){
            monedaMutable = new MutableLiveData<>();
        }
        return monedaMutable;
    }

    public LiveData<String> getErrorMensaje (){
        if(errorMensaje == null){
            errorMensaje = new MutableLiveData<>();
        }
        return errorMensaje;
    }

    public void convertirMoneda (String nombre, double valor){
        Moneda monedaEncontrada = billetera.stream()
                                    .filter(moneda -> moneda.getNombre().equalsIgnoreCase(nombre))
                                    .findFirst()
                                    .orElse(null);

        if(monedaEncontrada == null){
            errorMensaje.setValue("La moneda no se encuentra en la billetera del sistema");
        }

        monedaEncontrada.setValorConvertir(valor);
        monedaMutable.setValue(monedaEncontrada);
    }

    private void initMonedas (){
        billetera = new ArrayList<>();
        Moneda dolar = new Moneda("Dolar", 2000);
        Moneda euro = new Moneda("Euro", 2500);

        billetera.add(dolar);
        billetera.add(euro);
    }
}
