package br.mack.carteira;
// implementação para reduzir a quantidade de try-catch da aplicação
class DadosTransacao {
    String tipo;
    String descricao;
    String data;
    double valor;

    public DadosTransacao(String tipo, String descricao, String data, double valor) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
    }
}
