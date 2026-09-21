package controladores;

import servicios.CuentaServicio;
import vistas.BancoVista;

public class BancoControlador {

    private BancoVista vista;

    public BancoControlador(BancoVista vista) {
        this.vista = vista;
    }

    public void mostrarCuentas(){
        vista.mostrarCuentas(CuentaServicio.getDatos(), CuentaServicio.getEncabezados());
    }
}
