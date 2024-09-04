package execuçãojosephus;
import entidade.*;
import execuçãojosephus.*;
import execuçãojosephus.painelcirculo.*;
import josephusGUI.*;
import java.util.ArrayList;
import doublelinklistcirc.*;
import javax.swing.JPanel;
import javax.swing.*;

/**
 *  A classe JosephusGerenciador é usada para unir as classes Josephus e JosephusCirculo em uma classe gerenciadora para opera-las em conjunto
 *  
 *  @author
 *  RA: 00333095 NOME Matheus Heimrath Barbosa 
 *  RA: 00331675 NOME Luís Augusto Coelho de Souza
 *  RA: 00332850 NOME João Guilherme Costa Couto 
 *  @version 19/06/2024
 */
public class JosephusGerenciador
{
    private Josephus problema; //Cria uma variavel da classe Josephus para alocar as instancias enviadas do frame principal
    private Pessoa josephus; //Cria uma pessoa que vai armazenar Josephus, o ultimo sobrevivente
    private ListaDuplamenteLigadaCircular circulo = new ListaDuplamenteLigadaCircular(); //Cria uma lista circular
    private int passo; //Cria a variavel para armazenar o passo de eliminação
    private int delay; //Cria a variavel para armazenar o delay entre eliminações
    private JosephusCirculo circuloGraf; //Cria uma variavel da classe JosephusCirculo para iniciar a criação do painel circular
    private JosephusMainFrame mainFrame; //Recebe como referência a classe JosephusMainFrame que opera do frame principal do programa para modifica-la

    /**
     * JosephusGerenciador Construtor
     *
     * @param passo O passo de eliminação
     * @param delay o delay entre eliminações
     * @param numPessoas O numero de pessoas no circulo
     * @param circuloGraf O Painel grafico que representa a lista circular
     * @param mainFrame O mainFrame para realizar atualizações no frame principal
     */
    public JosephusGerenciador(int passo, int delay, int numPessoas, JosephusCirculo circuloGraf, JosephusMainFrame mainFrame){
        this.passo = passo;
        this.delay = delay; 
        this.mainFrame = mainFrame; 
        for(int i=numPessoas; i>=1; i--) { //Cria uma lista circular e preenche ela com o numPessoas instancias da classe Pessoa 
            Pessoa pessoa = new Pessoa();
            pessoa.setId(i);
            circulo.inserirInicio(pessoa);
        }
        this.circuloGraf = circuloGraf;
        problema = new Josephus(circuloGraf, delay, mainFrame); //instancia um objeto da classe Josephus para executar o problema com a informações necessárias
    }

    /**
     * Método executarProblema: Executa o problema de Josephus na lista ligada com o atraso apropriado e manda a posição de Josephus ao MainFrame.
     *
     */
    public void executarProblema() {
        this.josephus = problema.sobrevivente(circulo, passo); //inicia a execução para resolver o problema 
        mainFrame.getJosephus(josephus.getId()); //Atualiza o frame com a posição de Josephus
    }

    /**
     * Método getCirculo: Recolhe a lista ligada circular 
     *
     * @return A lista ligada circular
     */
    public ListaDuplamenteLigadaCircular getCirculo() {
        return this.circulo;
    }
}
