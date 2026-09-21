package modelos;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDatos'");
    }






}
