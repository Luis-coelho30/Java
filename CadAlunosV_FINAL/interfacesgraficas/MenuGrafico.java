package interfacesgraficas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Cria uma instancia grafica de um Menu com RadioButtons
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class MenuGrafico extends JFrame {
    private int op;
    /**
     * MenuGrafico Construtor
     *
     * @param titulo O titulo do JFrame
     * @param op1 O texto da opção 1
     * @param op2 O texto da opção 2
     * @param op3 O texto da opção 3
     * @param op4 O texto da opção 4
     * @param op5 O texto da opção 5
     * @param op6 O texto da opção 6
     */
    public MenuGrafico(String titulo, String op1, String op2, String op3, String op4, String op5, String op6) {
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Termina a execução do programa caso o menu seja fechado
        setSize(400, 300);
        setLayout(null); // Usando null layout
        setLocationRelativeTo(null);        
        
        //Cria as instancias dos RadioButtons com textos das opções do construtor
        JRadioButton radioButton1 = new JRadioButton("Opção 1: " + op1);
        radioButton1.setBounds(50, 60, 300, 20);
        JRadioButton radioButton2 = new JRadioButton("Opção 2: " + op2);
        radioButton2.setBounds(50, 90, 300, 20);
        JRadioButton radioButton3 = new JRadioButton("Opção 3: " + op3);
        radioButton3.setBounds(50, 120, 300, 20);
        JRadioButton radioButton4 = new JRadioButton("Opção 4: " + op4);
        radioButton4.setBounds(50, 150, 300, 20);
        JRadioButton radioButton5 = new JRadioButton("Opção 5: " + op5);
        radioButton5.setBounds(50, 180, 300, 20);
        JRadioButton radioButton6 = new JRadioButton("Opção 6: " + op6);
        radioButton6.setBounds(50, 210, 300, 20);
        ButtonGroup botoes = new ButtonGroup();
        botoes.add(radioButton1);
        botoes.add(radioButton2);
        botoes.add(radioButton3);
        botoes.add(radioButton4);
        botoes.add(radioButton5);
        botoes.add(radioButton6);
        
        // Adiciona os radio buttons ao frame
        add(radioButton1);
        add(radioButton2);
        add(radioButton3);
        add(radioButton4);
        add(radioButton5);
        add(radioButton6);
        
        //Configura um itemListener geral para os RadioListeners para recolher a opção selecionada
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) { //Pega o estado do evento
                    //Quando uma opção é selecionada, seta a opção (op) para o valor da opção, limpa a seleção e fecha o menu
                    if (e.getItem() == radioButton1) { 
                        op = 1;
                        botoes.clearSelection();
                        dispose();
                    } else if (e.getItem() == radioButton2) {
                        op = 2;
                        botoes.clearSelection();
                        dispose();
                    } else if (e.getItem() == radioButton3) {
                        op = 3;
                        botoes.clearSelection();
                        dispose();
                    } else if (e.getItem() == radioButton4) {
                        op = 4;
                        botoes.clearSelection();
                        dispose();
                    }
                    else if (e.getItem() == radioButton5){
                        op = 5;
                        botoes.clearSelection();
                        dispose();
                    }
                    else if (e.getItem() == radioButton6){
                        op = 6;
                        botoes.clearSelection();
                        dispose();
                    }
                }
            }
        };
        
        //Adiciona o ItemListener aos radioButtons
        radioButton1.addItemListener(itemListener);
        radioButton2.addItemListener(itemListener);
        radioButton3.addItemListener(itemListener);
        radioButton4.addItemListener(itemListener);
        radioButton5.addItemListener(itemListener);
        radioButton6.addItemListener(itemListener);
        
        //Cria o texto para colocar no JFrame
        JLabel texto = new JLabel("Selecione uma opção:");
        texto.setBounds(50, 20, 300, 20);
        add(texto);
        botoes.clearSelection();
    }
    
    /**
     * Método getOpcao
     *
     * @return O valor da opção selecionada
     */
    public int getOpcao() {
        return this.op;
    }
}
