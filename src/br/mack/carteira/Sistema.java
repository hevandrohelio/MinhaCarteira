package br.mack.carteira;

import br.mack.carteira.exceptions.ValorInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Sistema {

    public static void main(String[] args) {
        List<Transacao> transacoes = new ArrayList<>();

        // implementação para reduzir a quantidade de try-catch da aplicação
        List<DadosTransacao> dados = List.of(
                new DadosTransacao("despesa", "Conta de Luz", "20/02/2026", 150.00),
                new DadosTransacao("despesa", "Multa", "20/02/2026", -150.00),
                new DadosTransacao("despesa", "Coisas", "", 90),
                new DadosTransacao("receita", "Salário", "20/02/2026", 1600.00)
        );

        // implementação para reduzir a quantidade de try-catch da aplicação
        for (DadosTransacao d : dados) {
            try {
                Transacao t;
                if (d.tipo.equalsIgnoreCase("despesa")) {
                    t = new Despesa(d.descricao, d.data, d.valor);
                } else if (d.tipo.equalsIgnoreCase("receita")) {
                    t = new Receita(d.descricao, d.data, d.valor);
                } else {
                    throw new IllegalArgumentException("Tipo de transação inválido: " + d.tipo);
                }

                transacoes.add(t);

            } catch (ValorInvalidoException | IllegalArgumentException e) {
                System.out.println("Erro na transação [" + d.descricao + "]: " + e.getMessage());
            }
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