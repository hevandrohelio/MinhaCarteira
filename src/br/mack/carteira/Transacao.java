package br.mack.carteira;

import br.mack.carteira.exceptions.ValorInvalidoException;

import java.io.Serializable;

abstract public class Transacao implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descricao;
    private double valor;
    private String data;

    public Transacao(String descricao, String data, double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("O valor da transação deve ser maior que zero!");
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode ser vazia!");
        }
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("A data não pode ser vazia!");
        }
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public abstract void exibirDetalhes();
}
