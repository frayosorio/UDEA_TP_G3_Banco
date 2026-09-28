package controladores;

import modelos.TipoCuenta;
import servicios.CuentaServicio;
import servicios.TransaccionServicio;
import vistas.BancoVista;

public class BancoControlador {

    private BancoVista vista;

    public BancoControlador(BancoVista vista) {
        this.vista = vista;

        this.vista.setGuardarCuentaClick(evento -> agregarCuenta());
        this.vista.setEliminarCuentaClick(evento -> eliminarCuenta());

        mostrarCuentas();
        mostrarTransacciones();
    }

    public void mostrarCuentas() {
        vista.mostrarCuentas(CuentaServicio.getDatos(), CuentaServicio.getEncabezados());
    }

    public void mostrarTransacciones() {
        vista.mostrarTransacciones(TransaccionServicio.getDatos(), TransaccionServicio.getEncabezados());
    }


    private void agregarCuenta() {
        var tipo = vista.getTipoCuentaSeleccionado();
        var titular = vista.getTitular();
        var numero = vista.getNumero();
        var tasaInteres = tipo == TipoCuenta.AHORROS || tipo == TipoCuenta.CREDITO ? vista.getTasaInteres() : 0;
        var sobregiro = tipo == TipoCuenta.CORRIENTE ? vista.getValor() : 0;
        var plazo = tipo == TipoCuenta.CREDITO ? vista.getPlazo() : 0;
        var valorPrestado = tipo == TipoCuenta.CREDITO ? vista.getValor() : 0;

        var cuentaAgregada=CuentaServicio.agregar(tipo,
                titular, numero, tasaInteres, sobregiro, plazo, valorPrestado);

        vista.setCuentaTransaccion(cuentaAgregada.toString());
        vista.ocultarEdicionCuenta();
        mostrarCuentas();
    }

    private void eliminarCuenta() {
        if (vista.getFilaCuentaSeleccionada() >= 0) {
            if(vista.confirmar("¿Está seguro de retirar la cuenta?")){
                CuentaServicio.eliminar(vista.getFilaCuentaSeleccionada());
                vista.quitarCuentaTransaccion(vista.getFilaCuentaSeleccionada());
                mostrarCuentas();
            }
        } else {
            vista.mostrarMensaje("Debe seleccionar una cuenta");
        }
    }
}
