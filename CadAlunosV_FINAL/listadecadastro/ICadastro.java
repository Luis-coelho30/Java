package listadecadastro;
import entidade.*;

/**
 * Interface para a implementação da lista de cadastro dos alunos
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */

public interface ICadastro
{
    public int buscarLista(String ra);
    public void adicionar(Aluno novoAluno);
    public void remover(String ra);
    public boolean estaVazia();
    public void operarCadastro(int op);
}
