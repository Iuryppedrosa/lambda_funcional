package Exercicio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Funcionario {
    private String nome;
    private String email;
    private double salario;

    public Funcionario(String nome, String email, double salario) {
        this.nome = nome;
        this.email = email;
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public double getSalario() {
        return salario;
    }

    public String getNome() {
        return nome;
    }
}

public class Main {
    public static List<Funcionario> lerDadosCSV(String arquivo) throws IOException {
        List<Funcionario> funcionarios = new ArrayList<>();
        BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
        String linha;
        while ((linha = leitor.readLine()) != null) {
            String[] partes = linha.split(",");
            String nome = partes[0];
            String email = partes[1];
            double salario = Double.parseDouble(partes[2]);
            funcionarios.add(new Funcionario(nome, email, salario));
        }
        leitor.close();
        return funcionarios;
    }

    public static List<String> filtrarSalario(List<Funcionario> funcionarios, double salarioMinimo) {
        List<String> emails = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getSalario() > salarioMinimo) {
                emails.add(funcionario.getEmail());
            }
        }
        Collections.sort(emails);
        return emails;
    }

    public static double calcularSomaSalariosLetraM(List<Funcionario> funcionarios) {
        double soma = 0;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().startsWith("M")) {
                soma += funcionario.getSalario();
            }
        }
        return soma;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o caminho do arquivo CSV: ");
        String arquivoCSV = scanner.nextLine();
        double salarioMinimo = 1000; // Defina o valor mínimo do salário aqui

        try {
            List<Funcionario> funcionarios = lerDadosCSV(arquivoCSV);
            List<String> emailsFiltrados = filtrarSalario(funcionarios, salarioMinimo);
            double somaSalariosLetraM = calcularSomaSalariosLetraM(funcionarios);

            System.out.println("Emails dos funcionários com salário superior a " + salarioMinimo + ":");
            for (String email : emailsFiltrados) {
                System.out.println(email);
            }

            System.out.println("Soma dos salários dos funcionários com nome começando com 'M': " + somaSalariosLetraM);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}
