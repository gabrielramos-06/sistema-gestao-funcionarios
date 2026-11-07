package br.com.empresa.model;

public class Caixa extends Funcionario {
    private String turno;

    public Caixa(int id, String nome, double salarioBase, String turno) {
        super(id, nome, salarioBase);
        this.turno = turno;
    }

    public Caixa(String nome, double salarioBase, String turno) {
        super(nome, salarioBase);
        this.turno = turno;
    }

    @Override
    public double calcularBonificacao() {
        // Regra polimórfica do Caixa: 10% de bônus sobre o salário base
        return getSalarioBase() * 0.10;
    }

    @Override
    public String getCargo() {
        return "CAIXA";
    }

    @Override
    public String getDetalheEspecifico() {
        return "Turno: " + turno;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}