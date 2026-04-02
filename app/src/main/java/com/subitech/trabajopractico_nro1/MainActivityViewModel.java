package com.subitech.trabajopractico_nro1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.subitech.trabajopractico_nro1.model.Moneda;
import com.subitech.trabajopractico_nro1.model.VistaMainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private List<Moneda> billetera;
    private VistaMainActivity vistaMainActivity;
    private MutableLiveData<VistaMainActivity> vistaMutable;
    private MutableLiveData<String> errorMensaje;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        initMonedas();
        vistaMainActivity = new VistaMainActivity();
    }

    public LiveData<VistaMainActivity> getVistaMutable (){
        if(vistaMutable == null){
            vistaMutable = new MutableLiveData<>();
        }
        return vistaMutable;
    }

    public LiveData<String> getErrorMensaje (){
        if(errorMensaje == null){
            errorMensaje = new MutableLiveData<>();
        }
        return errorMensaje;
    }

    public void convertirMoneda (boolean estadoPrimeraMoneda, String valorPrimeraMoneda, String valorSegundaMoneda){
        double valor;
        Moneda m = vistaMainActivity.getMoneda();
        if(m == null){
            errorMensaje.setValue("Debe seleccionar una moneda");
            return;
        }
        if(estadoPrimeraMoneda){
            valor = Double.parseDouble(valorPrimeraMoneda);
            valor = m.getConversion(valor);
            valorSegundaMoneda = String.valueOf(valor);
        }else {
            valor = Double.parseDouble(valorSegundaMoneda);
            valor = m.getConversion(valor);
            valorPrimeraMoneda = String.valueOf(valor);
        }

        vistaMainActivity.setValorPrimerMoneda(valorPrimeraMoneda);
        vistaMainActivity.setValorSegundaMoneda(valorSegundaMoneda);
        vistaMutable.setValue(vistaMainActivity);
    }

    public void getValorConversion(String nombre){
        Moneda monedaEncontrada = billetera.stream()
                .filter(moneda -> moneda.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);

        if(monedaEncontrada == null){
            errorMensaje.setValue("La moneda no se encuentra en la billetera del sistema");
            return;
        }
        vistaMainActivity.setMoneda(monedaEncontrada);
        vistaMutable.setValue(vistaMainActivity);
    }

    public void setValorConversion(String valor){
        Moneda m = vistaMainActivity.getMoneda();
        if(m == null){
            errorMensaje.setValue("Debe seleccionar una moneda antes de cambiar el valor");
            return;
        }
        m.setValorIntercambio(Double.parseDouble(valor));
        vistaMutable.setValue(vistaMainActivity);
    }

    private void initMonedas (){
        billetera = new ArrayList<>();
        Moneda dolar = new Moneda("Dolar", 200);
        Moneda euro = new Moneda("Euro", 250);

        billetera.add(dolar);
        billetera.add(euro);
    }
}
