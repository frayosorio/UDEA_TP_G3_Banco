package modelos;

public class Credito extends Cuenta {

    private double valorPrestado;
    private double tasaInteres;
    private int plazo;
    private double valorRetirado;

    public Credito(String titular, String numero, double valorPrestado, double tasaInteres, int plazo) {
        super(titular, numero);
        this.valorPrestado = valorPrestado;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
        this.valorRetirado = 0;
    }

    public double getValorPrestado() {
        return valorPrestado;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public int getPlazo() {
        return plazo;
    }

    public double getValorRetirado() {
        return valorRetirado;
    }

    public double getCuota() {
        double tasaReal = tasaInteres / 100;
        return valorPrestado * Math.pow(1 + tasaReal, plazo) * tasaReal / (Math.pow(1 + tasaReal, plazo) - 1);
    }

    public double getDisponibleRetiro() {
        return valorPrestado - valorRetirado;
    }

    public double getSaldoDeuda() {
        return valorPrestado - getSaldo();
    }

    @Override
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= getDisponibleRetiro()) {
            valorRetirado += cantidad;
            return true;
        }
        return false;
    }

    public boolean pagarCuota(double cantidad) {
        if (cantidad > 0 && getSaldo() < valorPrestado) {
            var intereses = getSaldoDeuda() * tasaInteres / 100;
            var abonoCapital = cantidad - intereses;
            if (abonoCapital > 0) {
                setSaldo(getSaldo() + abonoCapital);
                return true;
            }
        }
        return false;

    }

}
