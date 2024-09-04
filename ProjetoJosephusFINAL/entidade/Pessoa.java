package entidade;

/**
 * A classe Pessoa atua como uma entidade que compõe a lista duplamente ligada circular para o problema de Josephus
 * 
 *  RA: 00333095 NOME Matheus Heimrath Barbosa 
 *  RA: 00331675 NOME Luís Augusto Coelho de Souza
 *  RA: 00332850 NOME João Guilherme Costa Couto 
 *  @version 19/06/2024
 */
public class Pessoa
{
    private boolean viva; //Indica se uma pessoa esta viva ou não
    private int id; //O id funciona como o identificador para uma pessoa, para o caso desse programa, terá como finalidade indicar sua posição na lista ligada circular

    /**
     * Pessoa Construtor
     * Cria uma pessoa e a qualifica como viva
     */
    public Pessoa() {
        this.viva = true; 
    }

    /**
     * Método setId:
     * 
     * Configura o Id da pessoa em questão
     *
     * @param id Configura a posição de uma Pessoa na lista ligada circular
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método getId:
     * 
     * Recolhe o Id da pessoa em questão 
     *  
     * @return O id, que indica a posição de uma Pessoa na lista ligada circular.
     */
    public int getId(){
        return this.id;
    }

    /**
     * Método estaMorta: 
     * 
     * Verifica se a pessoa em questão está morta
     *
     * @return O valor invertido do estado da pessoa no momento. Se estiver viva, retorna false, senão retorna true.
     */
    public boolean estaMorta() {
        return !viva;
    }

    /**
     * Método matar:
     * 
     * Responsável por modificar o estado da pessoa em questão para morta.
     */
    public void matar() {
        this.viva = false;
    }
}
