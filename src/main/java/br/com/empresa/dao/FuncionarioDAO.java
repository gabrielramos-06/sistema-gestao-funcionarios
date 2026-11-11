package br.com.empresa.dao;

import br.com.empresa.model.Caixa;
import br.com.empresa.model.Funcionario;
import br.com.empresa.model.Gerente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private static final String URL = "jdbc:sqlite:empresa.db";

    public FuncionarioDAO() {
        criarTabela();
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS funcionarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome VARCHAR(100) NOT NULL,
                salario_base DECIMAL(10, 2) NOT NULL,
                cargo VARCHAR(30) NOT NULL,
                detalhe_especifico VARCHAR(100) NOT NULL
            );
            """;
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }


    public void cadastrar(Funcionario f) {
        String sql = "INSERT INTO funcionarios (nome, salario_base, cargo, detalhe_especifico) VALUES (?, ?, ?, ?)";

        String detalhe = "";
        if (f instanceof Gerente g) {
            detalhe = g.getDepartamento();
        } else if (f instanceof Caixa c) {
            detalhe = c.getTurno();
        }

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, f.getNome());
            pstmt.setDouble(2, f.getSalarioBase());
            pstmt.setString(3, f.getCargo());
            pstmt.setString(4, detalhe);
            pstmt.executeUpdate();
            System.out.println("\n[SUCESSO] Funcionário cadastrado no banco de dados!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    
    public List<Funcionario> listarTodos() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double salarioBase = rs.getDouble("salario_base");
                String cargo = rs.getString("cargo");
                String detalhe = rs.getString("detalhe_especifico");

               
                if ("GERENTE".equalsIgnoreCase(cargo)) {
                    lista.add(new Gerente(id, nome, salarioBase, detalhe));
                } else if ("CAIXA".equalsIgnoreCase(cargo)) {
                    lista.add(new Caixa(id, nome, salarioBase, detalhe));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        }
        return lista;
    }

    
    public boolean excluir(int id) {
        String sql = "DELETE FROM funcionarios WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir funcionário: " + e.getMessage());
            return false;
        }
    }
}
