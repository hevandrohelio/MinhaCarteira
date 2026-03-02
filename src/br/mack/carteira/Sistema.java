package br.mack.carteira;

import br.mack.carteira.exceptions.ValorInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

    public static void main(String[] args) {
        List<Transacao> transacoes = new ArrayList<>();

        try {
            Transacao despesa1 = new Despesa("Conta de Luz", "20/02/2026", 150.00);
            Transacao receita1 = new Receita("Salário Mensal", "05/02/2026", 5000.00);
            Transacao despesa2 = new Despesa("Supermercado", "15/02/2026", 600.00);
            transacoes.add(despesa1);
            transacoes.add(receita1);
            transacoes.add(despesa2);
        } catch (ValorInvalidoException | IllegalArgumentException e) {
            System.out.println("Erro ao registrar transação: " + e.getMessage());
        }

        for (Transacao t : transacoes) {

            t.exibirDetalhes();

            if (t instanceof Tributavel) {
                Tributavel tributavel = (Tributavel) t;
                System.out.println("Imposto: " + tributavel.calcularImposto());
            }

            System.out.println("------------------------");
        }
    }
}