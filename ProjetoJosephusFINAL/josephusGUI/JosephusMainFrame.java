package josephusGUI;
import execuçãojosephus.painelcirculo.*;
import execuçãojosephus.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

/**
 *  A classe JosephusMainFrame realiza a implementação gráfica principal do Problema de Josephus. É um Frame que comporta todos os paineis fundamentais ao programa.
 *  @author
 *  RA: 00333095 NOME Matheus Heimrath Barbosa 
 *  RA: 00331675 NOME Luís Augusto Coelho de Souza
 *  RA: 00332850 NOME João Guilherme Costa Couto 
 *  @version 19/06/2024
 */
public class JosephusMainFrame extends JFrame {
    private JosephusCirculo circuloFrame; //Cria uma variavel da classe JosephusCirculo para iniciar a criação do painel circular
    private JTextField passoTxt; //Cria a caixa de texto para o passo de eliminação
    private JTextField delayTxt; //Cria a caixa de texto para o delay entre eliminações
    private JTextField numPessoasTxt; //Cria a caixa de texto para a quantidade de pessoas na lista circular
    private JTextArea listaDeMortos; //Cria a Area de texto para mostrar a lista de mortos
    private JLabel pessoaEliminadaLabel; //Cria o Label para mostrar a pessoa eliminada na iteração atual
    private JLabel posJosephusLabel; //Cria o Label para mostrar a posição de Josephus, o ultimo sobrevivente na lista circular
    private JosephusGerenciador gerenciador; //Cria uma variavel da classe JosephusGerenciador para gerenciar JosephusCirculo e Josephus
    private Thread execucaoThread; //Cria a thread de execução do problema de Josephus
    private JButton botaoIniciar;

    /**
     * JosephusMainFrame Construtor Cria o Frame principal
     *
     */
    public JosephusMainFrame() { 
        setTitle("Problema de Josephus"); //Define o titulo do frame como Problema de Josephus
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Define operação de finalização da execução do programa como fechamento do Frame
        setLayout(new BorderLayout()); //Define o Layout como um BorderLayout
        setSize(1200, 1000); //Definindo um tamanho inicial
        setResizable(false); //Desativa o redimensionamento do tamanho da janela pelo usuario
        setLocationRelativeTo(null); 

        //Cria o JPanel para as entradas
        JPanel painelDeEntrada = new JPanel(null);
        painelDeEntrada.setPreferredSize(new Dimension(150, 50));

        //Cria o Label e a caixa de texto para a entrada do numero de pessoas
        JLabel labelNumPessoas = new JLabel("Número de pessoas:");
        labelNumPessoas.setBounds(30, 10, 150, 25);
        painelDeEntrada.add(labelNumPessoas);
        numPessoasTxt = new JTextField("");
        numPessoasTxt.setBounds(155, 10, 50, 25);
        painelDeEntrada.add(numPessoasTxt);

        //Cria o Label e a caixa de texto para a entrada do passo de eliminação
        JLabel labelPasso = new JLabel("Passo:");
        labelPasso.setBounds(275, 10, 150, 25);
        painelDeEntrada.add(labelPasso);
        passoTxt = new JTextField("");
        passoTxt.setBounds(320, 10, 50, 25);
        painelDeEntrada.add(passoTxt);

        //Cria o Label e a caixa de texto para a entrada do delay entre eliminações
        JLabel labelDelay = new JLabel("Atraso (ms):");
        labelDelay.setBounds(450, 10, 150, 25);
        painelDeEntrada.add(labelDelay);
        delayTxt = new JTextField("");
        delayTxt.setBounds(530, 10, 50, 25);
        painelDeEntrada.add(delayTxt);

        //Cria o Label para mostrar a pessoas eliminada na iteração atual
        pessoaEliminadaLabel = new JLabel("Pessoa eliminada: ");
        pessoaEliminadaLabel.setBounds(620, 10, 150, 25);
        painelDeEntrada.add(pessoaEliminadaLabel);

        // Cria painel para o círculo (esquerdo)
        circuloFrame = new JosephusCirculo(null);
        JPanel painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.add(circuloFrame, BorderLayout.CENTER); //Monta o painel no  centro do JPanel

        // Cria painel para a lista de mortos (direito)
        JPanel painelDireito = new JPanel(new BorderLayout());
        //Cria a Area de texto para mostrar a lista de mortos
        listaDeMortos = new JTextArea("Eliminados:\n");
        listaDeMortos.setEditable(false);
        listaDeMortos.setLineWrap(true);
        listaDeMortos.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(listaDeMortos);
        scrollPane.setPreferredSize(new Dimension(150, 150)); 
        painelDireito.add(scrollPane, BorderLayout.NORTH); //Posiciona a area de texto no topo do painel direito
        //Cria o Label para mostrar a posição de Josephus no circulo
        posJosephusLabel = new JLabel("");
        posJosephusLabel.setFont(new Font("Arial", Font.BOLD, 18)); //Define a fonte e o tamanho
        posJosephusLabel.setForeground(new Color(0, 0, 255)); //Define a cor azul
        posJosephusLabel.setHorizontalAlignment(SwingConstants.CENTER); //Centraliza o texto
        posJosephusLabel.setBorder(new EmptyBorder(10, 10, 10, 10)); //Adiciona um espaçamento de 10 pixeis entre o texto e as bordas do Painel
        painelDireito.add(posJosephusLabel, BorderLayout.CENTER); //Adiciona o Label no centro do painel

        //Cria um JSplitPane para adicionar dois paineis diferentes separados por uma linha vertical
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo, painelDireito);
        splitPane.setResizeWeight(0.75); //Define a divisão 75/25 do espaço do painel entre o painel esquerdo e direito respectivamente
        splitPane.setEnabled(false); //Desabilita o JSplitPane para evitar interação

        //Adiciona painel de entrada (topo) e JSplitPane (centro)
        setLayout(new BorderLayout());
        add(painelDeEntrada, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        //Cria o botão para iniciar o problema de josephus
        botaoIniciar = new JButton("Iniciar");
        botaoIniciar.setBounds(780, 10, 100, 25);
        painelDeEntrada.add(botaoIniciar);
        //Define a ação do botão
        botaoIniciar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    iniciarJosephus(); //Inicia a execução do problema de Josephus
                }
            });
        //Cria o botão de reiniciar para reiniciar a execução e começar o problema com outras entradas
        JButton botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.setBounds(930, 10, 100, 25);
        painelDeEntrada.add(botaoReiniciar);
        //Define a ação do botão
        botaoReiniciar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reiniciarJosephus(); //Reinicia a execução do programa
                }
            });
        //Cria o botão de sair para finalizar a execução do programa  
        JButton botaoSair = new JButton("Sair");
        botaoSair.setBounds(1080, 10, 100, 25);
        painelDeEntrada.add(botaoSair);
        //Define a ação do botão
        botaoSair.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Fecha a janela
                    System.exit(0); //finaliza a execução
                }
            });

        setVisible(true); //Torna o Frame visivel
    }

    /**
     * Método iniciarJosephus: Inicia a execução do problema de Josephus
     *
     */
    private void iniciarJosephus() {
        int numPessoas=0;
        int passo=0;
        int delay=0;
        try {
            // Converter os textos para inteiros
            numPessoas = Integer.parseInt(numPessoasTxt.getText());
            passo = Integer.parseInt(passoTxt.getText());
            delay = Integer.parseInt(delayTxt.getText());

            //Verifica as condições para bloquear entradas invalidas
            if (numPessoas < 2 || numPessoas > 120) {
                throw new NumberFormatException("O número de pessoas deve estar entre 2 e 120.");
            }
            if (passo <= 1 || passo > numPessoas) {
                throw new NumberFormatException("O passo deve ser maior que 1 e menor ou igual ao número de pessoas.");
            }

            //Se tudo estiver válido continua com a criação do gerenciador e execução
            //Desativa as caixas de texto enquanto o problema é executado
            numPessoasTxt.setEnabled(false); 
            passoTxt.setEnabled(false);
            delayTxt.setEnabled(false);
            botaoIniciar.setEnabled(false);
            gerenciador = new JosephusGerenciador(passo, delay, numPessoas, circuloFrame, this); //Cria uma instancia do gerenciador para executar o Problema 
            circuloFrame.setGrupo(gerenciador.getCirculo()); //Envia a lista circular para que o painel seja preenchido pelo circulo correspondente

            //Inicia a execução do problema de Josephus em uma thread separada
            execucaoThread = new Thread(() -> { 
                        gerenciador.executarProblema(); //Inicia a execução do problema 
                }); 
            execucaoThread.start(); //Inicia a thread

        } catch (NumberFormatException ex) { //Caso haja execeção, envia mensagem de erro pelo JOptionPane
            if (ex.getMessage().startsWith("For input string")) {
                JOptionPane.showMessageDialog(this, "Por favor, insira apenas números inteiros válidos.");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos.\nDetalhes: " + ex.getMessage());
            }
        }

    }

    /**
     * Método reiniciarJosephus: reinicia a execução do problema de Josephus
     *
     */
    private void reiniciarJosephus() {
        if (execucaoThread != null && execucaoThread.isAlive()) {
            execucaoThread.interrupt(); // Interrompe a execução atual
        }
        //Libera as caixas de texto
        numPessoasTxt.setEnabled(true);
        passoTxt.setEnabled(true);
        delayTxt.setEnabled(true);
        botaoIniciar.setEnabled(true);
        numPessoasTxt.setText("");
        passoTxt.setText("");
        delayTxt.setText("");
        //Reinicia as Label de posição de josephus e da pessoa eliminada na iteração atual  
        posJosephusLabel.setText("");
        listaDeMortos.setText("Eliminados:\n");
        pessoaEliminadaLabel.setText("Pessoa eliminada: ");
        //Limpa o círculo
        circuloFrame.setGrupo(null); 
        circuloFrame.repaint();
    }

    /**
     * Método atualizarPessoaEliminada: Atualiza o label de pessoa eliminada
     *
     * @param pessoa Recebe a nova pessoa eliminada para atualizar o label
     */
    public void atualizarPessoaEliminada(int pessoa) {
        pessoaEliminadaLabel.setText("Pessoa eliminada: " + pessoa);
    }

    /**
     * Método atualizarListaDeMortos: Atualiza a lista de mortos
     *
     * @param mortos Recebe a nova lista de mortos para atualizar o label
     */
    public void atualizarListaDeMortos(String mortos) {
        listaDeMortos.setText("Eliminados:\n" + mortos);
    }

    /**
     * Método getJosephus
     *
     * @param pos Recebe a posição de Josephus no problema (ultimo sobrevivente)
     */
    public void getJosephus(int pos){
        posJosephusLabel.setText("Josephus estava na posição " + pos + "!");
    }
}
