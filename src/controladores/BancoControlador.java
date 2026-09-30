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
        this.vista.setGuardarTransaccionClick(evento -> agregarTransaccion());

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

        var cuentaAgregada = CuentaServicio.agregar(tipo,
                titular, numero, tasaInteres, sobregiro, plazo, valorPrestado);
        if (cuentaAgregada != null) {
            vista.setCuentaTransaccion(cuentaAgregada.toString());
            mostrarCuentas();
            vista.ocultarEdicionCuenta();
        } else {
            vista.mostrarMensaje("La cuenta no pudo ser agregada");
        }

    }

    private void eliminarCuenta() {
        if (vista.getFilaCuentaSeleccionada() >= 0) {
            if (vista.confirmar("¿Está seguro de retirar la cuenta?")) {
                CuentaServicio.eliminar(vista.getFilaCuentaSeleccionada());
                vista.quitarCuentaTransaccion(vista.getFilaCuentaSeleccionada());
                mostrarCuentas();
            }
        } else {
            vista.mostrarMensaje("Debe seleccionar una cuenta");
        }
    }

    private void agregarTransaccion() {
        var tipo = vista.getTipoTransaccionSeleccionado();
        var cuenta = vista.getIndiceCuentaSeleccionado() >= 0 ?
                CuentaServicio.get(vista.getIndiceCuentaSeleccionado()) : null;
        var valor = vista.getValorTransaccion();

        if (cuenta == null) {
            vista.mostrarMensaje("Debe seleccionar una duenta");
            return;
        }
        var transaccion = TransaccionServicio.agregar(cuenta, tipo, valor);
        if (transaccion != null) {
            vista.ocultarEdicionTransaccion();
            mostrarTransacciones();
        } else {
            vista.mostrarMensaje("La transacción no pudo ser agregada");
        }
    }
}
