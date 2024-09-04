package execuçãojosephus;
import entidade.*;
import execuçãojosephus.painelcirculo.*;
import josephusGUI.*;
import doublelinklistcirc.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * A classe Josephus é responsável pela execução da lógica principal do Problema de Josephus, modificando os elementos da Lista e deixando apenas um vivo que é Josephus. 
 * 
 *  RA: 00333095 NOME Matheus Heimrath Barbosa 
 *  RA: 00331675 NOME Luís Augusto Coelho de Souza
 *  RA: 00332850 NOME João Guilherme Costa Couto 
 *  @version 19/06/2024
 */
public class Josephus
{
    private JosephusCirculo painel; //Recebe como referência a classe JosephusCirculo, que gera o circulo que representa a lista de pessoas
    private ArrayList<Pessoa> listaMortos; //Lista de pessoas que já foram eliminadas 
    private int delay; //Delay que deverá ser acionado a cada morte na lista 
    private JosephusMainFrame mainFrame; //Recebe como referência a classe JosephusMainFrame que opera do frame principal do programa

    /**
     * Josephus Construtor prepara a classe Josephus para receber informações sobre o painel circular, o mainframe (para atualizações em tempo real no programa) e para receber o tempo de atraso necessário entre mortes 
     *
     * @param painel O painel que contém o circulo que representa a lista circular 
     * @param delay O delay que deverá ser acionado a cada morte na lista 
     * @param mainFrame O frame principal que cuida do frame principal do programa
     */
    public Josephus(JosephusCirculo painel, int delay, JosephusMainFrame mainFrame) {
        this.painel = painel;
        this.listaMortos = new ArrayList<>(); 
        this.delay = delay;
        this.mainFrame = mainFrame;
    }

    /**
     * Método sobrevivente: Realiza as operações na lista circular definindo elementos como mortos até que o processo recursivo de eliminação de Josephus termine
     *
     * @param lista Recebe a lista onde deverá fazer as operações
     * @param k Recebe o passo com que deve operar as eliminações
     * @return A posição de Josephus, ou seja, do último sobrevivente
     */
    public Pessoa sobrevivente(ListaDuplamenteLigadaCircular lista, int k) {
        if (lista.estaVazia()) { //Verifica se a lista está vazia (para evitar erros durante a criação do frame principal)
            return null;
        }
        int vivos = lista.getQtdNos(); //A quantidade de nós na lista inicialmente indica a quantidade de vivos no programa
        No atual = lista.getInicio(); //Pega o primeiro nó da lista
        while (vivos > 1) { //Enquanto ainda houver mais de uma pessoa viva, executa a função de eliminação
            for (int i = 1; i < k; i++) { //Contador para percorrer o passo de eliminação
                do { //Loop interno para travar o contador para contar apenas quando se percorreu por uma pessoa viva
                    atual = atual.getProximo(); 
                } while (((Pessoa) atual.getConteudo()).estaMorta());
            }

            No proximo = atual.getProximo(); // armazena proxima posição
            Pessoa pessoa = (Pessoa) proximo.getConteudo(); //Recolhe a pessoa da proxima posição
            while (pessoa.estaMorta()) { // Se a proxima pessoa estiver morta, avança na lista até encontrar uma viva
                proximo = proximo.getProximo();
                pessoa = (Pessoa) proximo.getConteudo();
            }
            Pessoa pessoaSelecionada = (Pessoa) atual.getConteudo(); //Pessoa selecionada recebe a pessoa que será eliminada nesta iteração.
            listaMortos.add(pessoaSelecionada); //Adiciona a pessoa selecionada na lista de mortos
            pessoaSelecionada.matar(); //Mata a pessoa selecionada (modifica seu estado para morta)
            vivos--; //Diminui o contador de vivos
            atual = proximo; // Move para o proximo nó vivo
            SwingUtilities.invokeLater(() -> painel.repaint()); //Realiza a atualização do painel circular (Pinta o circulo eliminado como vermelho)
            mainFrame.atualizarPessoaEliminada(pessoaSelecionada.getId()); //Atualiza o Label de pessoa eliminada com a pessoa morta nesta iteração
            mainFrame.atualizarListaDeMortos(getMortos()); //Atualiza o JTextArea do mainframe com a nova lista de mortos (agora composta pela pessoa selecionada + anteriores.

            try { //Adiciona delay à thread de para simular atraso ao passo de eliminação
                Thread.sleep(delay);
            } catch (InterruptedException e) { //Caso haja uma execeção de interrupção de thread (o botão reiniciar foi pressionado) o programa interrompe a thread interna de delay de execução e termina o método
                Thread.currentThread().interrupt(); //Termina a thread
                break; //Finaliza o método
            }
        }

        return (Pessoa) atual.getConteudo(); //A pessoa sobrevivente
    }

    /**
     * Método getMortos: Usa o conteúdo da lista de mortos e o transforma em String
     *
     * @return Uma String com uma lista de mortos
     */
    public String getMortos() {
        StringBuilder mortos = new StringBuilder(); //Inicia o StringBuilder para construir a string da lista de mortos
        for(int i=0; i<listaMortos.size(); i++){
            if (i > 0) { 
                mortos.append(", ");
            }
            mortos.append(listaMortos.get(i).getId());
        }   

        return mortos.toString(); //Retorna a lista de mortos como string
    }
}

