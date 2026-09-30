package modelos;

import java.text.DecimalFormat;

public class Corriente extends Cuenta {

    private double sobregiro;

    public Corriente(String titular, String numero, double sobregiro) {
        super(titular, numero);
        this.sobregiro = sobregiro;
    }

    public double getSobregiro() {
        return sobregiro;
    }

    @Override
    public boolean retirar(double valor) {
        if (valor > 0 && valor <= getSaldo() + sobregiro) {
            setSaldo(getSaldo() - valor);
            return true;
        }
        return false;
    }

    @Override
    public String[] getDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return new String[]{
                "CORRIENTE",
                getNumero(),
                getTitular(),
                "Sobregiro= $" + df.format(sobregiro),
                df.format(getSaldo())
        };
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

    @Override
    public String toString() {
        return "CORRIENTE #[" + getNumero() + "] Titular[" + getTitular() + "]";
    }


}
