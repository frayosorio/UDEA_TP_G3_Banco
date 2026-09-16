package modelos;

public class Credito extends Cuenta {

    private double valorPrestado;
    private double tasaInteres;
    private int plazo;
    private double valorRetirado;

    public Credito(String numero, String titular,
            double valorPrestado, double tasaInteres, int plazo) {
        super(numero, titular);
        this.valorPrestado = valorPrestado;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
        this.valorRetirado = 0;
    }

    @Override
    public boolean retirar(double valor) {

    }

}
