package br.mack.carteira;

import br.mack.carteira.exceptions.ValorInvalidoException;

public class Receita extends Transacao implements Tributavel{
    public Receita(String descricao, String data, double valor) throws ValorInvalidoException {
        super(descricao, data, valor);
    }

    @Override
    public double calcularImposto() {
        return getValor() * 0.10;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Receita: ");
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Valor: " + getValor());
        System.out.println("Imposto: " + calcularImposto());
        System.out.println("Data: " + getData());

    }
}
