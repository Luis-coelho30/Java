package saida.tabela;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Classe responsavel por atribuir ação aos botões da Tabela
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class BotaoAcao extends DefaultCellEditor { 
    protected JButton button;
    private String label;
    private ArrayList<Object[][]> dados;
    private int row;

    /**
     * BotaoAcao Construtor
     *
     * @param checkBox recebe um JCheckBox para adicionar ação a um botao
     * @param dados recebe os dados que cada botão deve apresentar
     */
    public BotaoAcao(JCheckBox checkBox, ArrayList<Object[][]> dados) {
        super(checkBox);
        this.dados = dados;
        button = new JButton(); //Cria o botão
        button.setOpaque(true); //Define o botão para que fique opaco, isto é, não mostre os componentes abaixo de sua camada
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Exibir uma nova tabela com os dados da matriz associada à linha
                String[] colunas = {"Nome da Disciplina", "Codigo da Disciplina", "Media do aluno"};
                TabelaGUI tab = new TabelaGUI(dados.get(row), colunas, null, false); //Cria uma instancia da TabelaGUI com uma matriz de dados correspondente a linha da tabela no ArrayList e sem botões
                tab.setVisible(true);
            }
        });
    }

    /**
     * Método getTableCellEditorComponent
     *
     * Metodo da interface TableCellEditor que permite o retorno do componente que será usado na edição da tabela
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        this.row = row; // Armazenar o número da linha atual
        button.setText(label);
        return button;
    }

    /**
     * Método getCellEditorValue
     *
     * @return Garante que o botão tenha o nome dado depois da renderização da tabela
     */
    public Object getCellEditorValue() {
        return label;
    }
}