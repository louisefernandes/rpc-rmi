package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Double> saldoContas;
    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
        saldoContas.put("1", 100.0);
        saldoContas.put("2", 156.0);
        saldoContas.put("3", 950.0);

        contas = new ArrayList<>();
        contas.add(new Conta("1", 100.0));
        contas.add(new Conta("2", 156.0));
        contas.add(new Conta("3", 950.0));
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return saldoContas.getOrDefault(conta, 0.0);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size();
    }

    @Override
    public void cadastrarConta(String conta, double saldoInicial) throws RemoteException {
        if (!saldoContas.containsKey(conta)) {
            saldoContas.put(conta, saldoInicial);
            contas.add(new Conta(conta, saldoInicial));
        } else {
            throw new RemoteException("Conta já existente!");
        }
    }

    @Override
    public double pesquisarConta(String conta) throws RemoteException {
        if (saldoContas.containsKey(conta)) {
            return saldoContas.get(conta);
        } else {
            throw new RemoteException("Conta não encontrada!");
        }
    }

    @Override
    public void removerConta(String conta) throws RemoteException {
        if (saldoContas.containsKey(conta)) {
            saldoContas.remove(conta);
            contas.removeIf(c -> c.getNumero().equals(conta));
        } else {
            throw new RemoteException("Conta não encontrada!");
        }
    }

    @Override
    public void adicionarConta(Conta conta) throws RemoteException {
        if (!saldoContas.containsKey(conta.getNumero())) {
            saldoContas.put(conta.getNumero(), conta.getSaldo());
            contas.add(conta);
        } else {
            throw new RemoteException("Conta já existente!");
        }
    }

    @Override
    public List<Conta> pesquisarContas() throws RemoteException {
        return contas;
    }

    @Override
    public void removerConta(Conta conta) throws RemoteException {
        if (saldoContas.containsKey(conta.getNumero())) {
            saldoContas.remove(conta.getNumero());
            contas.remove(conta);
        } else {
            throw new RemoteException("Conta não encontrada!");
        }
    }
}