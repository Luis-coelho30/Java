package saida.tabela;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * Classe por implementar os botões graficamente
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class BotaoGUI extends JButton implements TableCellRenderer {
    /**
     * BotaoGUI Construtor
     *  
     * Torna o botão em opaco para impedir a visua
     */
    public BotaoGUI() {
        setOpaque(true);
    }

    /**
     * Método getTableCellRendererComponent
     *
     * Garante a renderização correta do botão
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString()); //Value é recebido da implementação do TableCellEditor como o nome do botão 
        return this;
    }
}
