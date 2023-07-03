package conversordemoeda;

import javax.swing.JOptionPane;

public class ConversorMoeda {
    private static final String[] OPCOES_MOEDAS = {
        "Reais -> Dólar",
        "Reais -> Euro",
        "Reais -> Libras Esterlinas",
        "Reais -> Peso argentino",
        "Reais -> Peso Chileno",
        "Dólar -> Reais",
        "Euro -> Reais",
        "Libras Esterlinas -> Reais",
        "Peso argentino -> Reais",
        "Peso Chileno -> Reais"
    };

    public void executar() {
        boolean continuar = true;

        while (continuar) {
            String opcaoSelecionada = exibirMenuPrincipal();

            if (opcaoSelecionada == null) {
                JOptionPane.showMessageDialog(null, "Programa finalizado", "Finalizado", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            String[] moedas = opcaoSelecionada.split(" -> ");
            String moedaOrigem = moedas[0];
            String moedaDestino = moedas[1];

            double valorConversao = obterValor("Digite o valor em " + moedaOrigem + ":");
            double valorConvertido;

            if (moedaOrigem.contains("Reais")) {
                valorConvertido = realizarConversao(valorConversao, moedaOrigem, moedaDestino, true);
            } else {
                valorConvertido = realizarConversao(valorConversao, moedaOrigem, moedaDestino, false);
            }

            exibirResultado(valorConvertido, moedaDestino);

            int opcaoContinuar = JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Continuar?", JOptionPane.YES_NO_CANCEL_OPTION);

            switch (opcaoContinuar) {
                case JOptionPane.YES_OPTION -> continuar = true;
                case JOptionPane.NO_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Programa finalizado", "Finalizado", JOptionPane.INFORMATION_MESSAGE);
                    continuar = false;
                }
                case JOptionPane.CANCEL_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Programa concluído", "Concluído", JOptionPane.INFORMATION_MESSAGE);
                    continuar = false;
                }
                default -> {
                }
            }
        }
    }

    private String exibirMenuPrincipal() {
        return (String) JOptionPane.showInputDialog(null, "Selecione uma opção:", "Conversor de Moeda", JOptionPane.PLAIN_MESSAGE, null, OPCOES_MOEDAS, OPCOES_MOEDAS[0]);
    }

    private double obterValor(String mensagem) {
        while (true) {
            String valorStr = JOptionPane.showInputDialog(null, mensagem, "Valor", JOptionPane.PLAIN_MESSAGE);

            try {
                double valor = Double.parseDouble(valorStr);
                return valor;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido! Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double realizarConversao(double valor, String moedaOrigem, String moedaDestino, boolean conversaoDireta) {
        double taxaConversao = 0.0;

        if (conversaoDireta) {
            switch (moedaDestino) {
                case "Dólar" -> taxaConversao = 0.2; // Taxa de conversão Reais -> Dólar
                case "Euro" -> taxaConversao = 0.18; // Taxa de conversão Reais -> Euro
                case "Libras Esterlinas" -> taxaConversao = 0.16; // Taxa de conversão Reais -> Libras Esterlinas
                case "Peso argentino" -> taxaConversao = 25.0; // Taxa de conversão Reais -> Peso argentino
                case "Peso Chileno" -> taxaConversao = 150.0; // Taxa de conversão Reais -> Peso Chileno
            }
        } else {
            switch (moedaOrigem) {
                case "Dólar" -> taxaConversao = 5.0; // Taxa de conversão Dólar -> Reais
                case "Euro" -> taxaConversao = 5.5; // Taxa de conversão Euro -> Reais
                case "Libras Esterlinas" -> taxaConversao = 6.0; // Taxa de conversão Libras Esterlinas -> Reais
                case "Peso argentino" -> taxaConversao = 0.04; // Taxa de conversão Peso argentino -> Reais
                case "Peso Chileno" -> taxaConversao = 0.007; // Taxa de conversão Peso Chileno -> Reais
            }
        }

        return valor * taxaConversao;
    }

    private void exibirResultado(double valorConvertido, String moedaDestino) {
        JOptionPane.showMessageDialog(null, "Valor convertido: " + valorConvertido + " " + moedaDestino, "Resultado da Conversão", JOptionPane.INFORMATION_MESSAGE);
    }
}