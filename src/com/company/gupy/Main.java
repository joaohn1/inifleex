package com.company.gupy;

import com.company.gupy.Classes.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        //3.1
        adicionarFuncionarios(funcionarios);

        //3.2
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        //3.3
        Funcionario.imprimirFuncionarios(funcionarios);

        //3.4
        Funcionario.aplicarAumento(funcionarios, new BigDecimal("0.10"));

        //3.5
        Map<String, List<Funcionario>> funcionariosPorFuncao = Funcionario.agruparPorFuncao(funcionarios);

        //3.6
        Funcionario.imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

        //3.8
        Funcionario.imprimirAniversariantes(funcionarios, 10);
        Funcionario.imprimirAniversariantes(funcionarios, 12);

        //3.9
        Funcionario.imprimirFuncionarioMaiorIdade(funcionarios);

        //3.10
        Funcionario.ordenarPorNome(funcionarios);
        Funcionario.imprimirFuncionarios(funcionarios);

        //3.11
        Funcionario.imprimirTotalSalarios(funcionarios);

        //3.12
        Funcionario.imprimirSalariosMinimos(funcionarios);

    }

    private static void adicionarFuncionarios(ArrayList<Funcionario> funcionarios) {
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }
}
