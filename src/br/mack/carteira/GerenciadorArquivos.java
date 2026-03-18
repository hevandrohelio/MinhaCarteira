package br.mack.carteira;

import java.io.*;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class GerenciadorArquivos {
    private static final String ARQUIVO_BIN = "transacoes.bin";
    private static final String ARQUIVO_CSV = "extrato.csv";

    // Salva lista de transações em arquivo binário
    public static void salvarDados(List<Transacao> transacoes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_BIN))) {
            oos.writeObject(transacoes);
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    // Carrega lista de transações de arquivo binário
    @SuppressWarnings("unchecked")
    public static List<Transacao> carregarDados() {
        File arquivo = new File(ARQUIVO_BIN);
        if (!arquivo.exists()) {
            return new ArrayList<>();  // primeira execução: retorna lista vazia
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_BIN))) {
            return (List<Transacao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Gera extrato em CSV
    public static void gerarExtrato(List<Transacao> transacoes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CSV))) {
            writer.write("Descrição;Valor;Data");
            writer.newLine();
            for (Transacao t : transacoes) {
                writer.write(t.getDescricao() + ";" + t.getValor() + ";" + t.getData());
                writer.newLine();
            }
            System.out.println("Extrato gerado em " + ARQUIVO_CSV);
        } catch (IOException e) {
            System.out.println("Erro ao gerar extrato: " + e.getMessage());
        }
    }
}
