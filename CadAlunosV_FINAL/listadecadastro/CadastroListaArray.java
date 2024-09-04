package listadecadastro;
import entidade.*;
import saida.*;
import saida.tabela.*;
import javax.swing.*;
import java.awt.event.*;
import entrada.entradagrafica.*;
import interfacesgraficas.RemoverAlunoGUI;
import java.util.ArrayList;
import java.util.Arrays;
import salvamento.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import salvamento.gerenciarquivos.*;

/**
 * Implementa a lista de cadastro em um ArrayList
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class CadastroListaArray implements ICadastro, Serializable
{
    private ArrayList <Aluno> lista;

    /**
     * CadastroListaArray Construtor
     * Seta a lista construindo uma nova instancia de um ArrayList com o tipo aluno
     */
    public CadastroListaArray(){
        setLista(new <Aluno>ArrayList());
    }

    /**
     * getLista
     * 
     * @return o ArrayList de cadastro
     */
    private ArrayList <Aluno> getLista() {
        return lista;
    }

    /**
     * getQtd
     * 
     * @return o tamanho do ArrayList de cadastro
     */
    public int getQtd() {
        return lista.size();
    }

    /**
     * setLista
     * 
     * @param Seta o ArrayList principal para o ArrayList recebido no parametro
     */
    private void setLista(ArrayList<Aluno>lista) {
        this.lista = lista;
    }

    /**
     * adicionar
     *
     * @param Aluno recebe um objeto do tipo aluno para adicionar ao cadastro
     */
    public void adicionar(Aluno estudante){
        lista.add(estudante);
    }

    /**
     * remover
     *
     * @param ra Recebe um RA do aluno a ser removido do ArrayList de cadastro
     */
    public void remover(String ra) {
        int index = buscarLista(ra);

        if (index!=-1){
            lista.remove(index);
        }
    }

    /**
     * buscar
     * Busca o RA na lista e retorna seu indice se ele existir e se não, retorna -1
     * @param ra O RA a ser buscado na lista
     * @return O indice do RA buscado ou -1 se esse não existir
     */
    public int buscarLista(String ra){
        int index = -1;
        if(estaVazia()==false) {
            for (int i = 0; i<lista.size(); i++) {
                if(ra.equals(lista.get(i).getRa())) {
                    index = i;
                    break; 
                }
            }
        }

        return index;
    }

    /**
     * estaVazia
     *
     * @return Retorna se a lista esta vazia
     */
    public boolean estaVazia(){
        return (lista.isEmpty());
    }

    /**
     * mostrarLista
     *
     * Mostra a lista em uma Tabela JTable
     */
    public void mostrarLista(){
        Aluno[] cadastro = lista.toArray(new Aluno[0]); //Transforma o ArrayList em um array de aluno para facilitar a transposição pela tabela
        SaidaTabela tab = new SaidaTabela();
        tab.TabelaOut(cadastro); //Envia os dados do cadastro e mostra a JTable completa
    }

    /**
     * Método operarCadastro
     *
     * @param op Recebe uma opção do menu grafico
     */
    public void operarCadastro(int op) {
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
                if(getQtd()>0) { //Se a lista nao for vazia
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
                if(getQtd()>0) {   //Se a lista nao estiver vazia, apresenta a Lista em uma JTable
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
        for(int i=0; i<getQtd(); i++) {
            listaRA.add(lista.get(i).getRa());
        }
        return listaRA;
    }

    /**
     * Método salvarCadastro
     *
     * Salva o cadastro em um arquivo Binario
     */
    private void salvarCadastro() {
        ISalvamento arq = new ArquivoBinario();
        GerenciaArquivos file = new GerenciaArquivos();
        String nome = file.salvarArquivo(); //Metodo que cria um arquivo de salvamento com o nome dado pelo usuario
        if(nome!=null)
            arq.gravarArq(lista.toArray(new Aluno[0]), nome);
    }

    /**
     * Método recuperarCadastro
     * 
     * Recupera o cadastro de um arquivo binario 
     */
    private void recuperarCadastro() {
        ISalvamento arq = new ArquivoBinario();
        GerenciaArquivos file = new GerenciaArquivos();
        String nome = file.recolherNomeArq(); //Metodo que recolhe o nome de um arquivo selecionado pelo usuario
        if(nome!=null) {
            Aluno[] alunosSalvamento = (Aluno[]) arq.lerArq(nome);
            ArrayList<Aluno> listaAlunos = new ArrayList<>(Arrays.asList(alunosSalvamento));
            setLista(listaAlunos);
        }
        else
            JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado.");
    }
}
