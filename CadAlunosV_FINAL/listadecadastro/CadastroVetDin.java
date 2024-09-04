package listadecadastro;
import entidade.*;
import saida.*;
import saida.tabela.*;
import javax.swing.*;
import java.awt.event.*;
import entrada.entradagrafica.*;
import interfacesgraficas.RemoverAlunoGUI;
import java.util.*;
import salvamento.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import salvamento.gerenciarquivos.*;

/**
 * Classe responsavel por realizar o cadastro dos alunos em um vetor Dinamico
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 29/04/2024
 */
public class CadastroVetDin implements ICadastro, Serializable
{
    private Aluno armazenador[];
    private int size=0;

    /**
     * CadastroVetDin Construtor
     * implementa a construção da lista com valor NULL e tamanho 0
     */
    public CadastroVetDin() {
        setArray(null);
        setSize(0);
    }

    /**
     * retorna array de Aluno
     * 
     * @return Retorna o array de alunos 
     */
    private Aluno[] getArray() {
        return armazenador;
    }

    /**
     * Retorna a quntidade
     * 
     * @return int, quantidade de elementos no array
     */
    public int getSize() {
        return size;
    }

    /**
     * @param Seta o array principal como igual ao array recebido
     */
    private void setArray(Aluno[] array) {
        this.armazenador = array;
    }

    /**
     * @param Seta o tamanho do array
     */
    private void setSize(int qtd) {
        this.size = qtd;
    }

    /**
     * Método buscaLista
     * Busca um RA na lista e retorna o indice de sua posição
     * @param String RA que contém o RA procurado
     * @return Retorna o Indice do RA procurado ou -1 para indicar que não foi encontrado
     */
    public int buscarLista(String ra) {
        int i=0;
        int index = -1;
        if(estaVazia()==false) {
            for (i = 0; i<getSize(); i++) {
                if(ra.equals(armazenador[i].getRa())) {
                    index = i;
                    break; 
                }
            }
        }

        return index;
    }

    /**
     * Metodo adicionar
     *
     * @param Aluno, recebe um aluno para adição na lista
     */
    public void adicionar(Aluno novoAluno){
        if(buscarLista(novoAluno.getRa())==-1) {
            if (armazenador == null){ // se for o primeiro elemento          
                setArray(new Aluno[1]);
                armazenador[0] = novoAluno; 
                setSize(getSize()+1);
            }
            else { // outros elementos
                // cria vetor auxiliar com mais um elemento
                Aluno aux[] = new Aluno[armazenador.length+1];

                // copia todos elementos de vet para aux
                copiar(armazenador, aux);

                // insere elemento novo
                aux[aux.length-1] = novoAluno;

                // Transforma vetor auxiliar no atual
                setArray(aux);

                // incrementa contador de elementos    
                setSize(getSize()+1);

            }
        }
    }

    /**
     * Metodo remover
     *
     * @param ra, recebe o RA do aluno a ser removido
     */
    public void remover(String ra) {
        int index = buscarLista(ra);
        if(index!=-1){
            armazenador[index] = null; //Determina o aluno removido como NULL

            if(getSize() > 1){
                // cria vetor auxiliar com mes um elemento
                Aluno aux[] = new Aluno[armazenador.length-1];

                // copia todos elementos de vet para aux
                copiar(armazenador, aux);

                // Transforma vetor auxiliar no atual
                setArray(aux);
                // decrementa contador de elementos    
                setSize(getSize() - 1);
            } else {
                // acabou os elementos
                setArray(null);
                // decrementa contador de elementos    
                setSize(0);

            }

        }
    }

    /**
     * Metodo vazia
     *
     * @return Se a lista esta vazia
     */
    public boolean estaVazia(){
        return getSize()==0;
    }

    /**
     * Metodo copiar
     *
     * @param origem O array original
     * @param destino O array que deve receber uma copia do array original
     */
    private void copiar(Aluno origem[], Aluno destino[]){
        // copia todos
        int i, k = 0;
        for (i = 0; i < origem.length; i++){
            if (origem[i] != null) {
                destino[k] = origem[i];
                k++;
            }
        }       
    }

    /**
     * Método mostrarLista
     * Apresenta a Lista dos alunos cadastrados ao usuário
     */
    public void mostrarLista() {
        SaidaTabela tab = new SaidaTabela(); 
        tab.TabelaOut(getArray()); //Cria a tabela de saida com os dados do array de cadastro
    }

    /**
     * Método operarCadastro
     *
     * @param op Recebe a opção do menu
     */
    public void operarCadastro(int op) {
        String ra;
        switch(op) {    
            case 1: 
                JFrame frame = new JFrame(); //Cria o frame para o JDialog de entrada
                EntradaAlunoGrafica registra = new EntradaAlunoGrafica(frame, "Cadastro", criarListaRA()); //Cria a entrada grafica de um aluno e envia a lista de RAs cadastrados para evitar entradas duplicadas
                registra.setModal(true); //Seta a entrada como modal para impedir que o usuário use outras partes do programa
                registra.setVisible(true);
                registra.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            //Quando a entrada grafica for fechada, recolhe o aluno por meio de um getter
                            Aluno novoAluno = registra.getAluno();
                            if(novoAluno!=null) { //Se o usuário não tiver cancelado a entrada
                                adicionar(novoAluno); 
                            }

                        }
                    });
                while (registra.isVisible()) {  //Enquanto a interface esta aberta mantem a compilação pausada para esperar o cadastro pelo usuário 
                    try { 
                        Thread.sleep(100);
                    } catch (InterruptedException E) {
                        E.printStackTrace();
                    }
                } 
                break;

            case 2: 
                if(getSize()>0) { //Se a lista nao for vazia
                    JFrame frameR = new JFrame(); //Cria o frame para o JDialog de entrada
                    RemoverAlunoGUI remover = new RemoverAlunoGUI(frameR, "Remover aluno da lista", criarListaRA()); //Cria a remoção grafica de um aluno e envia a lista de RAs para eliminar apenas RAs validos
                    remover.setModal(true); //Seta a entrada como modal para impedir que o usuário use outras partes do programa
                    remover.setVisible(true);
                    remover.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                //Quando a entrada grafica for fechada, recolhe o RA de um aluno por meio de um getter 
                                String ra = remover.getRA();
                                if(ra!=null) { //Se o usuário não tiver cancelado a entrada
                                    remover(ra);
                                }
                            }
                        });
                    while (remover.isVisible()) { //Enquanto a interface esta aberta mantem a compilação pausada para esperar a remoção pelo usuário 
                        try { 
                            Thread.sleep(100);
                        } catch (InterruptedException E) {
                            E.printStackTrace();
                        }
                    }
                }
                else //Se a lista estiver vazia apresenta mensagem para o usuario
                    JOptionPane.showMessageDialog(null, "A lista de alunos esta vazia.");
                break;

            case 3: 
                if(getSize()>0) {   //Se a lista nao estiver vazia, apresenta a Lista em uma JTable
                    mostrarLista();
                }
                else //Se a lista estiver vazia apresenta mensagem para o usuario
                    JOptionPane.showMessageDialog(null, "A lista de alunos esta vazia.");
                break;

            case 4: 
                if(estaVazia()==false)
                    salvarCadastro(); //Salva o cadastro em um Arquivo Binário
                else
                    JOptionPane.showMessageDialog(null, "Não há nada a ser salvo.");
                break;

            case 5:
                recuperarCadastro(); //Recupera o cadastro de um Arquivo Binário e seta a lista de cadastro para esse recolhido.
                break;

            case 6:
                break;
        }
    }

    /**
     * Método criarListaRA
     *
     * @return Uma lista de RAs dos alunos cadastrados
     */
    public ArrayList criarListaRA() {
        ArrayList<String> listaRA = new ArrayList<>();
        for(int i=0; i<getSize(); i++) {
            listaRA.add(armazenador[i].getRa());
        }
        return listaRA;
    }

    /**
     * Método salvarCadastro
     *
     *  Salva o cadastro em um arquivo Binario
     */
    private void salvarCadastro() {
        ISalvamento arq = new ArquivoBinario();
        GerenciaArquivos file = new GerenciaArquivos();
        String nome = file.salvarArquivo(); //Metodo que cria um arquivo de salvamento com o nome dado pelo usuario
        if(nome!=null)
            arq.gravarArq((Aluno[]) armazenador, nome);
    }

    /**
     * Método recuperarCadastro
     *
     *  Recupera o cadastro de um arquivo binario 
     */
    private void recuperarCadastro() {
        ISalvamento arq = new ArquivoBinario();
        GerenciaArquivos file = new GerenciaArquivos();
        String nome = file.recolherNomeArq(); //Metodo que recolhe o nome de um arquivo selecionado pelo usuario
        if(nome!=null) {
            setArray((Aluno[])arq.lerArq(nome));
            setSize(armazenador.length);
        }
        else
            JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado.");
    }
}