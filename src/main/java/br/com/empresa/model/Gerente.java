package br.com.empresa.model;

public class Gerente extends Funcionario {
    private String departamento;

    public Gerente(int id, String nome, double salarioBase, String departamento) {
        super(id, nome, salarioBase);
        this.departamento = departamento;
    }

    public Gerente(String nome, double salarioBase, String departamento) {
        super(nome, salarioBase);
        this.departamento = departamento;
    }

    @Override
    public double calcularBonificacao() {
        
        return getSalarioBase() * 0.20;
    }

    @Override
    public String getCargo() {
        return "GERENTE";
    }

    @Override
    public String getDetalheEspecifico() {
        return "Depto: " + departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
