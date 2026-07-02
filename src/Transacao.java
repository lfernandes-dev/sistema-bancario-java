import Exceptions.ValorInvalidoException;

public class Transacao {
    private double valorTransacao;
    private String tipoTransacao;
    private Usuario usuario;

    public Transacao(double valorTransacao, String tipoTransacao, Usuario usuario) {
        this.valorTransacao = valorTransacao;
        this.tipoTransacao = tipoTransacao;
        this.usuario = usuario;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
