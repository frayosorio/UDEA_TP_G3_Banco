package modelos;

import java.text.DecimalFormat;

import interfaces.IConsignable;
import interfaces.IGeneraIntereses;
import interfaces.IMostrable;
import interfaces.IRetirable;
import interfaces.ITransaccionable;

public class Ahorro extends Cuenta implements IConsignable, IRetirable, IGeneraIntereses, IMostrable, ITransaccionable {

    private double tasaInteres;

    public Ahorro(String titular, String numero, double tasaInteres) {
        super(titular, numero);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    @Override
    public boolean consignar(double cantidad) {
        return depositar(cantidad);
    }

     public double getDisponibleRetiro() {
        return getSaldo();
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= getSaldo()) {
            setSaldo(getSaldo() - cantidad);
            return true;
        }
        return false;
    }

    @Override
    public double calcularIntereses() {
        return getSaldo() * getTasaInteres() / 100;
    }

    @Override
    public void aplicarIntereses() {
        setSaldo(getSaldo() * (1 + getTasaInteres() / 100));
    }

    @Override
    public String[] getDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[] {
                "Ahorro",
                getNumero(),
                getTitular(),
                df.format(getSaldo()),
                "Tasa interés: " + df.format(tasaInteres) + " %"
        };
    }

    @Override
    public String toString() {
        return "Ahorros [Numero=" + getNumero() + ", Titular=" + getTitular() + "]";
    }

    @Override
    public boolean procesarTransaccion(TipoTransaccion tipo, double valor) {
        switch (tipo) {
            case DEPOSITO:
                return depositar(valor);
            case RETIRO:
                return retirar(valor);
        }
        return false;
    }

}
