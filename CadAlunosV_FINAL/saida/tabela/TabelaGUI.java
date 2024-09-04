package saida.tabela;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe responsável por implementar uma Tabela Gráfica que pode ou não ter botões.
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class TabelaGUI extends JFrame
{
    private ArrayList<Object[][]> matrizList;

    /**
     * TabelaGUI Construtor
     *
     * @param dados Recebe uma matriz de dados que contém os dados que a tabela deverá mostrar
     * @param colunas Recebe os nomes das colunas que a tabela terá
     * @param matrizList Opcionalmente recebe uma lista de matrizes de dados que pode ser apresentada no clique de um botão na última coluna 
     * @param comBotao Boolean que indica se a tabela deverá ser implementada com botões ou não
     */
    public TabelaGUI(Object[][] dados, String[] colunas, ArrayList<Object[][]> matrizList, boolean comBotao) {
        //Cria os componentes da tabela
        this.matrizList = matrizList;
        JPanel painelFundo;
        JTable tabela;
        JScrollPane barraRolagem;
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
        tabela = new JTable(dados, colunas);

        //Centraliza o texto da tabela
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        //Define o tamanho de cada coluna de acordo com o tamanho de cada entrada
        TableColumnModel columnModel = tabela.getColumnModel();
        for (int i = 0; i < colunas.length; i++) {
            int largura = 0;
            for (int j = 0; j < dados.length; j++) {
                TableCellRenderer renderer = tabela.getCellRenderer(j, i);
                Component comp = tabela.prepareRenderer(renderer, j, i);
                largura = Math.max(comp.getPreferredSize().width + tabela.getIntercellSpacing().width, largura);
            }
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
            columnModel.getColumn(i).setPreferredWidth(largura);
        }

        //Cria um botao na tabela
        if(comBotao) 
            adicionarBotoes(tabela);

        //adiciona um conteiner com barra de scroll a tabela
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem);

        //Bloqueia a edicao da tabela pelo usuario
        tabela.setDefaultEditor(Object.class, null);
        //Bloqueia a reordenação das colunas da tabela
        tabela.getTableHeader().setReorderingAllowed(false);
        
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Abre a tabela em tela cheia
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Método adicionarBotoes
     *
     * @param tabela Recebe a tabela em que a função deve adicionar os botões
     */
    private void adicionarBotoes(JTable tabela) {
        // Adicione um botão em cada linha da tabela
        TableColumnModel columnModel = tabela.getColumnModel();
        for (int i = 0; i < tabela.getRowCount(); i++) {
            tabela.getColumn("Disciplinas").setCellRenderer(new BotaoGUI()); //Implementa o botão graficamente
            tabela.getColumn("Disciplinas").setCellEditor(new BotaoAcao(new JCheckBox(), matrizList)); //Implementa a ação do botão
        }
    }
}