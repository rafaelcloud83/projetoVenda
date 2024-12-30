package model;

/**
 *
 * @author rafael
 */
public class Clientes extends Pessoa {
    private String cpf;
    private String celular;

    public Clientes() {
        super();
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Clientes{" + super.toString() + "cpf=" + cpf + ", celular=" + celular + " }";
    }
}
