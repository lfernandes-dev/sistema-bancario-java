import Exceptions.SaldoInsuficienteException;
import Exceptions.ValorInvalidoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BancoService {

    private List<Transacao> listaDeTransacoes = new ArrayList<>();

    public void depositar(Usuario titular, double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("Erro: Coloque apenas números positivos!");
        }
        titular.setSaldo(titular.getSaldo() + valor);
        Transacao deposito = new Transacao(valor, "Depósito", titular);
        listaDeTransacoes.add(deposito);
    }

    public void sacar(Usuario titular ,double valor) throws SaldoInsuficienteException, ValorInvalidoException{
        if (valor <= 0) {
            throw new ValorInvalidoException("Erro: Coloque apenas números positivos!");
        }
        else if (valor > titular.getSaldo()){
            throw new SaldoInsuficienteException("Erro: Saldo insuficiente!");
        }
        titular.setSaldo(titular.getSaldo() - valor);
        Transacao saque = new Transacao(-valor, "Saque", titular);
        listaDeTransacoes.add(saque);
    }

    public void transferir(Usuario remetente, Usuario destinatario, double valor) throws SaldoInsuficienteException, ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("Erro: Coloque apenas números positivos!");
        }
        else if (valor > remetente.getSaldo()){
            throw new SaldoInsuficienteException("Erro: Saldo insuficiente!");
        }
        remetente.setSaldo(remetente.getSaldo() - valor);
        destinatario.setSaldo(destinatario.getSaldo() + valor);
        Transacao transacaoRemetente = new Transacao(-valor, "Transferencia", remetente);
        Transacao transacaoDestinatario = new Transacao(valor, "Transferencia", destinatario);
        listaDeTransacoes.add(transacaoRemetente);
        listaDeTransacoes.add(transacaoDestinatario);
    }

    public void listarTransacoes(String nome){
        listaDeTransacoes.stream()
                .filter(t -> t.getUsuario().getNome().equalsIgnoreCase(nome))
                .forEach(t -> System.out.println("Tipo: "+ t.getTipoTransacao() + " | Valor: "+ t.getValorTransacao()+ " | Usuario: "+ t.getUsuario().getNome()));
    }

    public void buscarPorValorMaior(double valor){
        listaDeTransacoes.stream()
                .filter(t -> t.getValorTransacao() >= valor)
                .forEach(t -> System.out.println("Tipo: "+ t.getTipoTransacao() + " | Valor: "+ t.getValorTransacao()+ " | Usuario: "+ t.getUsuario().getNome()));
    }

    public Map<String, List<Transacao>> agruparPorTipo(){
        return listaDeTransacoes.stream()
                .collect(Collectors.groupingBy(t -> t.getTipoTransacao()));
    }

    public double totalPorUsuario(String nome){
        return listaDeTransacoes.stream()
                .filter(t -> t.getUsuario().getNome().equalsIgnoreCase(nome))
                .mapToDouble(t -> t.getValorTransacao())
                .sum();
    }
}
