package com.gugawag.rpc.banco;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) {
        try {
            // procura o serviço no RMI Registry local. Perceba que o cliente não conhece a implementação do servidor,
            // apenas a interface
            Registry registry = LocateRegistry.getRegistry();
            BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

            menu();
            Scanner entrada = new Scanner(System.in);
            int opcao = entrada.nextInt();

            while (opcao != 9) {
                switch (opcao) {
                    case 1: {
                        System.out.println("Digite o número da conta:");
                        String conta = entrada.next();
                        // chamada ao método remoto, como se fosse executar localmente
                        try {
                            System.out.println("Saldo da conta: " + banco.saldo(conta));
                        } catch (RemoteException e) {
                            System.err.println("Erro ao obter saldo da conta: " + e.getMessage());
                        }
                        break;
                    }
                    case 2: {
                        // chamada ao método remoto, como se fosse executar localmente
                        try {
                            System.out.println("Quantidade de contas: " + banco.quantidadeContas());
                        } catch (RemoteException e) {
                            System.err.println("Erro ao obter quantidade de contas: " + e.getMessage());
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("Digite o número da nova conta:");
                        String novaContaNum = entrada.next();
                        System.out.println("Digite o saldo inicial da nova conta:");
                        double novoSaldo = entrada.nextDouble();
                        Conta novaConta = new Conta(novaContaNum, novoSaldo);
                        try {
                            banco.adicionarConta(novaConta);
                            System.out.println("Nova conta adicionada com sucesso!");
                        } catch (RemoteException e) {
                            System.err.println("Erro ao adicionar nova conta: " + e.getMessage());
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("Digite o número da conta a pesquisar:");
                        String contaPesquisar = entrada.next();
                        try {
                            double saldoEncontrado = banco.pesquisarConta(contaPesquisar);
                            System.out.println("Saldo da conta " + contaPesquisar + ": " + saldoEncontrado);
                        } catch (RemoteException e) {
                            System.err.println("Erro ao pesquisar conta: " + e.getMessage());
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("Digite o número da conta a remover:");
                        String contaRemover = entrada.next();
                        Conta contaParaRemover = new Conta(contaRemover, 0.0);
                        try {
                            banco.removerConta(contaParaRemover);
                            System.out.println("Conta removida com sucesso!");
                        } catch (RemoteException e) {
                            System.err.println("Erro ao remover conta: " + e.getMessage());
                        }
                        break;
                    }
                    case 6: {
                        System.out.println("Digite o número da nova conta:");
                        String novaContaNum = entrada.next();
                        System.out.println("Digite o saldo inicial da nova conta:");
                        double novoSaldo = entrada.nextDouble();
                        Conta novaConta = new Conta(novaContaNum, novoSaldo);
                        try {
                            banco.adicionarConta(novaConta);
                            System.out.println("Nova conta adicionada com sucesso!");
                        } catch (RemoteException e) {
                            System.err.println("Erro ao adicionar nova conta: " + e.getMessage());
                        }
                        break;
                    }
                    case 7: {
                        try {
                            List<Conta> listaContas = banco.pesquisarContas();
                            System.out.println("Lista de Contas:");
                            for (Conta c : listaContas) {
                                System.out.println("Número: " + c.getNumero() + ", Saldo: " + c.getSaldo());
                            }
                        } catch (RemoteException e) {
                            System.err.println("Erro ao pesquisar contas: " + e.getMessage());
                        }
                        break;
                    }
                    case 8: {
                        System.out.println("Digite o número da conta a remover:");
                        String contaRemover = entrada.next();
                        Conta contaParaRemover = new Conta(contaRemover, 0.0);
                        try {
                            banco.removerConta(contaParaRemover);
                            System.out.println("Conta removida com sucesso!");
                        } catch (RemoteException e) {
                            System.err.println("Erro ao remover conta: " + e.getMessage());
                        }
                        break;
                    }
                }
                menu();
                opcao = entrada.nextInt();
            }
        } catch (RemoteException | NotBoundException e) {
            System.err.println("Erro ao conectar ao servidor: " + e.getMessage());
        }
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI (ou FMI?!) - Louise Fernandes ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Cadastrar nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("6 - Adicionar conta");
        System.out.println("7 - Pesquisar todas as contas");
        System.out.println("8 - Remover conta");
        System.out.println("9 - Sair");
        System.out.println("Escolha uma opção:");
    }
}