import Exceptions.ListaVaziaException;
import Exceptions.UsuarioExistenteException;
import Exceptions.UsuarioNaoEncontrado;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private final List<Usuario> listaDeUsuarios = new ArrayList<>();

    public Usuario criarUsuario(String nome, String email)throws UsuarioExistenteException {
        boolean emailExistente = listaDeUsuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
        if (emailExistente){
            throw new UsuarioExistenteException("Erro: Email ja cadastrado");
        }
        Usuario usuario = new Usuario(nome, email);
        listaDeUsuarios.add(usuario);
        return usuario;
    }

    public Usuario buscarUsuario(String email)throws UsuarioNaoEncontrado {
        return listaDeUsuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new UsuarioNaoEncontrado("Erro: usuário não encontrado!"));
    }

    public Usuario atualizarEmail(String emailAntigo, String novoEmail)throws UsuarioNaoEncontrado, UsuarioExistenteException{
        Usuario usuario = buscarUsuario(emailAntigo);
        boolean emailExistente = listaDeUsuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(novoEmail));
        if (emailExistente){
            throw new UsuarioExistenteException("Erro: Email ja cadastrado");
        }
        usuario.setEmail(novoEmail);
        return usuario;
    }

    public void deletarUsuario(String email)throws UsuarioNaoEncontrado{
        Usuario usuario = buscarUsuario(email);
        listaDeUsuarios.remove(usuario);
    }

    public void listarUsuarios()throws ListaVaziaException {
        if (listaDeUsuarios.isEmpty()){
            throw new ListaVaziaException("A lista esta vazia!");
        }
        listaDeUsuarios
                .forEach(u -> System.out.println("Nome: "+ u.getNome() + " | Email: "+ u.getEmail() + " | Saldo: " + u.getSaldo()));
    }
}
