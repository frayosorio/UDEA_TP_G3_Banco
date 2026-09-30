package modelos;

public abstract class Cuenta {

    private String titular;
    private String numero;
    private double saldo;

    public Cuenta() {
    }

    public Cuenta(String titular, String numero) {
        this.titular = titular;
        this.numero = numero;
        this.saldo = 0;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    // metodo disponible solo para las clases HIJAs
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // metodo que se obliga a implementar a las clases HIJAs
    public abstract boolean retirar(double valor);

    public abstract boolean procesarTransaccion(TipoTransaccion tipo, double valor);

    public boolean depositar(double valor) {
        if (valor > 0) {
            setSaldo(saldo + valor);
            return true;
        }
        return false;
    }

    // metodo que cada clase HIJA llenará con los datos a mostrar
    public abstract String[] getDatos();

    public double getSaldoTransaccion(TipoTransaccion tipo){
        return saldo;
    }

}
