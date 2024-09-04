package entrada;
import entidade.*;
/**
 * Interface IEntradaAluno classe Pai das classes de entrada por JOptionPane e Console 
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */

public interface IEntradaAluno
{
    /**
     * Método registrar
     *
     * @return Um objeto do tipo Aluno
     */
    public Aluno registrar();
}
