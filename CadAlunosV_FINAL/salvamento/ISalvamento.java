package salvamento;

/**
 * Interface para a implementação da Persistencia do cadastro
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */

public interface ISalvamento
{
    public void gravarArq(Object objeto, String nomeArq);
    public Object lerArq(String nomeArq);
}
