package servicios;

import modelos.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class TransaccionServicio {

    private static String[] encabezados = new String[]{"Cuenta", "Tipo", "ValorTransaccion", "Saldo"};

    private static List<Transaccion> transacciones=new ArrayList();

    public static String[] getEncabezados() {
        return encabezados;
    }

    public static String[][] getDatos(){
        String[][] datos=new String[transacciones.size()][encabezados.length];


        return datos;
    }

}
