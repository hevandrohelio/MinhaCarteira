package br.mack.carteira;

import br.mack.carteira.exceptions.ValorInvalidoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {
    public void Salvar(Transacao t) throws SQLException {
        String sql = "INSERT INTO transacoes (descricao, valor, data, tipo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getDescricao());
            stmt.setDouble(2, t.getValor());
            stmt.setString(3, t.getData());
            if (t instanceof Receita) {
                stmt.setString(4, "RECEITA");
            } else if (t instanceof Despesa) {
                stmt.setString(4, "DESPESA");
            } else {
                throw new RuntimeException("Tipo de transação desconhecido");
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar transação", e);
        }
    }

    public List<Transacao> listar() {
        List<Transacao> lista = new ArrayList<>();

        String sql = "SELECT * FROM transacoes";

        try (Connection conn = ConexaoFactory.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                String data = rs.getString("data");
                String tipo = rs.getString("tipo");
                Transacao t;
                if ("RECEITA".equalsIgnoreCase(tipo)) {
                    t = new Receita(descricao, data, valor);
                } else if ("DESPESA".equalsIgnoreCase(tipo)) {
                    t = new Despesa(descricao, data, valor);
                } else {
                    throw new RuntimeException("Tipo inválido no banco: " + tipo);
                }

                lista.add(t);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar transações", e);
        } catch (ValorInvalidoException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

}