package modelos;

import java.text.DecimalFormat;

public class Ahorro extends Cuenta {

    private double tasaInteres;

    public Ahorro(String titular, String numero, double tasaInteres) {
        super(titular, numero);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= getSaldo()) {
            setSaldo(getSaldo() - cantidad);
            return true;
        }
        return false;
    }

    public void abonarInteres() {
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
