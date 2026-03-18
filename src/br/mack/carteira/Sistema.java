package br.mack.carteira;

import br.mack.carteira.exceptions.ValorInvalidoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    public static void main(String[] args) {
        List<Transacao> transacoes = GerenciadorArquivos.carregarDados();
        System.out.println("Total de transações carregadas: " +transacoes.size());

        Scanner sc = new Scanner(System.in);

        System.out.println("Cadastrar nova transação:");
        System.out.print("Tipo (despesa/receita): ");
        String tipo = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Data (dd/mm/yyyy): ");
        String data = sc.nextLine();
        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        sc.nextLine(); // consumir quebra de linha


        try {
            Transacao t;
            if (tipo.equalsIgnoreCase("despesa")) {
                t = new Despesa(descricao, data, valor);
            } else if (tipo.equalsIgnoreCase("receita")) {
                t = new Receita(descricao, data, valor);
            } else {
                throw new IllegalArgumentException("Tipo inválido");
            }

            transacoes.add(t);
            System.out.println("Transação adicionada com sucesso!");

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
        // Salvar dados e gerar extrato
        GerenciadorArquivos.salvarDados(transacoes);
        GerenciadorArquivos.gerarExtrato(transacoes);

        sc.close();
    }
}