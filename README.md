# Trabajo Práctico Nro 1 - Programación de Dispositivos Móviles

## Descripción de la App
Esta aplicación es un **Conversor de Monedas** diseñado para Android. Permite realizar conversiones entre Dólares y Euros de manera dinámica. El usuario puede seleccionar la moneda base, ingresar el monto a convertir y visualizar el resultado. Además, ofrece la funcionalidad de ajustar manualmente la tasa de intercambio (valor de conversión) para adaptarse a diferentes contextos económicos.

## Integrantes del Grupo
* **Lucero Gontero, Martin** - DNI: 38.419.062
* **Sosa, Gaston Oscar** - DNI: 37.090.426
* **Subidia, Santiago** - DNI: 45.801.536

## Implementación de MVVM (Model-View-ViewModel)
La aplicación sigue el patrón de arquitectura **MVVM** para separar la lógica de negocio de la interfaz de usuario:

1. **Model (Modelo):** Representado por las clases `Moneda` (que contiene el valor de intercambio y la lógica de cálculo) y `VistaMainActivity` (que encapsula el estado de los datos que se muestran en pantalla).
2. **ViewModel:** La clase `MainActivityViewModel` actúa como intermediaria. Se encarga de procesar los cálculos de conversión y gestionar la lógica de selección de monedas. Utiliza `LiveData` para "avisar" a la vista cuando los datos cambian.
3. **View (Vista):** La `MainActivity` es responsable únicamente de mostrar la información al usuario y capturar sus interacciones (clicks en botones). No realiza cálculos; en su lugar, observa los cambios en el ViewModel y actualiza los campos de texto automáticamente.
