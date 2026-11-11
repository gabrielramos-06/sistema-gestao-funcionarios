package br.com.empresa.model;

public abstract class Funcionario {
    private int id;
    private String nome;
    private double salarioBase;

    public Funcionario(int id, String nome, double salarioBase) {
        this.id = id;
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    
    public abstract double calcularBonificacao();

    
    public abstract String getCargo();

    
    public abstract String getDetalheEspecifico();

    
    public double calcularSalarioFinal() {
        return this.salarioBase + calcularBonificacao();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %d | Cargo: %-8s | Nome: %-18s | Detalhe: %-15s | Salário Base: R$ %8.2f | Bônus: R$ %7.2f | Salário Final: R$ %8.2f",
            id, getCargo(), nome, getDetalheEspecifico(), salarioBase, calcularBonificacao(), calcularSalarioFinal()
        );
    }
}
