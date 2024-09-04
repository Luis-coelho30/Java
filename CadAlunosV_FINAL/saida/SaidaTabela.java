package saida;
import entidade.*;
import saida.tabela.*;
import listadecadastro.*;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;

/**
 * Traduz os dados recebidos do cadastro para portar eles para uma tabela
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class SaidaTabela
{
    /**
     * Método TabelaOut
     *
     * @param lista O cadastro de alunos
     */
    public void TabelaOut(Aluno[] lista) {
        ArrayList<Object[][]> matrizDisc = new ArrayList<>(); //Cria um arrayList de matrizes para armazenar as Disciplinas de cada aluno (1 matriz por aluno)
        int numAlunos = lista.length; //Recebe o numero de alunos na lista de cadastro
        int numAtributos = 7; // Nome, idade, rg, ra, curso, semestre, nome do botao
        int numInfoDisc = 3; //Codigo, nome, media
        Object[][] dadosAluno = new Object[numAlunos][numAtributos]; //Cria uma matriz do tipo object para receber os dados do aluno
        // Preenchendo a matriz com as informações de cada aluno
        for (int i = 0; i < numAlunos; i++) { //Para cada aluno
            Aluno aluno = lista[i]; 
            dadosAluno[i][0] = aluno.getNome(); //Preenche a primeira coluna com o nome do aluno
            dadosAluno[i][1] = aluno.getIdade(); //Preenche a segunda coluna com a idade do aluno
            dadosAluno[i][2] = aluno.getRG(); //Preenche a terceira coluna com o RG do aluno
            dadosAluno[i][3] = aluno.getRa(); //Preenche a quarta coluna com o RA do aluno
            dadosAluno[i][4] = aluno.getCurso(); //Preenche a quinta coluna com o curso do aluno
            dadosAluno[i][5] = aluno.getSemestre(); //Preenche a sexta coluna com o Semestre do aluno
            dadosAluno[i][6] = "Ver disciplinas de " + aluno.getNome(); //Preenche a ultima coluna com o nome dos botões.

            Disciplina[] disc = aluno.getDisc(); //Recebe as disciplinas de um aluno
            int qtdDisc = disc.length; //Recebe a quantidade das disciplinas do aluno
            Object[][] dadosDisc = new Object[qtdDisc][3]; //Cria uma matriz de dados com a quantidade de disciplinas do aluno
            for(int j = 0; j < qtdDisc; j++) { //Preenche a Matriz com as disciplinas
                dadosDisc[j][0] = disc[j].getcodDisc(); //Preenche a primeira coluna com o Codigo da Disciplina
                dadosDisc[j][1] = disc[j].getnomeDisc(); //Preenche a primeira coluna com o nome da Disciplina
                dadosDisc[j][2] = disc[j].getMedia(); //Preenche a primeira coluna com a media da disciplina
            }
            matrizDisc.add(dadosDisc); //Adiciona a matriz de Disciplinas ao arrayList de Disciplinas
        }

        String[] colunas = {"Nome", "Idade", "RG", "RA", "Curso", "Semestre", "Disciplinas"}; //Define o nome das colunas da matriz 

        TabelaGUI tabela = new TabelaGUI(dadosAluno, colunas, matrizDisc, true); //Cria uma instancia da tabela com todos os dados do aluno e com botões com ações individuais para mostrar as disciplinas dos alunos
        tabela.setVisible(true);
    }
}
