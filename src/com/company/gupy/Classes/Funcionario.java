package com.company.gupy.Classes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    // Getters e Setters
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    // Métodos estáticos relacionados aos funcionários
    public static void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentualAumento) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal aumento = salarioAtual.multiply(percentualAumento.divide(BigDecimal.valueOf(100)));
            BigDecimal novoSalario = salarioAtual.add(aumento);
            funcionario.setSalario(novoSalario);
        }
    }

    public static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }

        return funcionariosPorFuncao;
    }

    public static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.println("\tNome: " + f.getNome());
                System.out.println("\tData de Nascimento: " + f.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("\tSalário: " + formatarValor(f.getSalario()));
                System.out.println("\tFunção: " + f.getFuncao());
                System.out.println();
            }
        }
    }

    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        for (Funcionario funcionario : funcionarios) {
            String dataFormatada = funcionario.getDataNascimento().format(formatter);
            String salarioFormatado = currencyFormatter.format(funcionario.getSalario());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + dataFormatada);
            System.out.println("Salário: " + salarioFormatado);
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        }
    }

    public static void imprimirAniversariantes(List<Funcionario> funcionarios, int mes) {
        System.out.println("Funcionários que fazem aniversário no mês " + mes + ":");

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().getMonthValue() == mes) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("Função: " + funcionario.getFuncao());
                System.out.println();
            }
        }

        System.out.println();
    }

    public static void imprimirFuncionarioMaiorIdade(List<Funcionario> funcionarios) {
        Funcionario maisVelho = encontrarFuncionarioMaisVelho(funcionarios);

        if (maisVelho != null) {
            int idade = calcularIdade(maisVelho.getDataNascimento());
            System.out.println("Funcionário mais velho:");
            System.out.println("Nome: " + maisVelho.getNome());
            System.out.println("Idade: " + idade);
        } else {
            System.out.println("Não há funcionários na lista.");
        }
    }

    public static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = BigDecimal.ZERO;

        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }

        System.out.println("Total dos salários dos funcionários: " + formatarValor(totalSalarios));
    }

    public static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.DOWN);
            System.out.println(funcionario.getNome() + " ganha " + salariosMinimos + " salários mínimos");
        }
    }

    // Métodos de instância relacionados aos funcionários
    public int calcularIdade() {
        LocalDate dataAtual = LocalDate.now();
        return Period.between(getDataNascimento(), dataAtual).getYears();
    }

    private static Funcionario encontrarFuncionarioMaisVelho(List<Funcionario> funcionarios) {
        Funcionario maisVelho = null;

        for (Funcionario funcionario : funcionarios) {
            if (maisVelho == null || funcionario.calcularIdade() > maisVelho.calcularIdade()) {
                maisVelho = funcionario;
            }
        }

        return maisVelho;
    }
    public static void ordenarPorNome(List<Funcionario> funcionarios) {
        Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNome));
    }

    private static String formatarValor(BigDecimal valor) {
        return String.format("R$ %.2f", valor);
    }

    private static int calcularIdade(LocalDate dataNascimento) {
        LocalDate dataAtual = LocalDate.now();
        return Period.between(dataNascimento, dataAtual).getYears();
    }
}
