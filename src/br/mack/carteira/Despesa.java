package br.mack.carteira;

import br.mack.carteira.exceptions.ValorInvalidoException;

public class Despesa extends Transacao{
    public Despesa(String descricao, String data, double valor) throws ValorInvalidoException {
        super(descricao, data, valor);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Despesa: ");
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Valor: -" + getValor());
        System.out.println("Data: " + getData());
    }
}
