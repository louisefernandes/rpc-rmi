package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;
    void cadastrarConta(String conta, double saldoInicial) throws RemoteException;
    double pesquisarConta(String conta) throws RemoteException;
    void removerConta(String conta) throws RemoteException;

    void adicionarConta(Conta conta) throws RemoteException;
    List<Conta> pesquisarContas() throws RemoteException;
    void removerConta(Conta conta) throws RemoteException;
}