package entrada.entradagrafica;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import entidade.*;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Cria uma interface para a adição de disciplinas.
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class EntradaDiscGUI extends JDialog
{
    private JButton armazenar, cancelar, finalizar;
    private JTable table;
    private JLabel labelAvisoCod, labelAvisoNome, labelAvisoMedia;
    private Disciplina[] listaDisc;

    /**
     * EntradaDiscGUI Construtor
     *
     * @param owner Recebe o JDialog pai deste JDialog
     * @param titulo Recebe um titulo para este JDialog
     */
    EntradaDiscGUI(JDialog owner, String titulo) {
        super(owner, titulo);
        setSize(525, 450);
        setLocation(740,350);

        Container janela = getContentPane();

        setLayout(null);

        //Define os rótulos que indicam o que preencher em cada campo de texto
        JLabel labelCod = new JLabel("Codigo da Disciplina: ");
        JLabel labelNome = new JLabel("Nome da Disciplina: ");
        JLabel labelMedia = new JLabel("Media do aluno: ");
        //Posiciona os labels
        labelCod.setBounds(50,40,150,20);
        labelNome.setBounds(50,80,150,20);
        labelMedia.setBounds(50,120,100,20);

        //Define as máscaras
        MaskFormatter mascaraCod = null;
        MaskFormatter mascaraNome = null;
        MaskFormatter mascaraMedia = null;

        try{
            mascaraMedia = new MaskFormatter("##.#"); //Limita a entrada do usuario em apenas numeros de 0-9
            mascaraMedia.setPlaceholderCharacter('0');
        }
        catch(ParseException excp) {
            System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
        }

        //Seta as máscaras nos objetos JFormattedTextField
        JFormattedTextField jFormattedTextCod = new JFormattedTextField(mascaraCod);
        JFormattedTextField jFormattedTextNome = new JFormattedTextField(mascaraNome);
        JFormattedTextField jFormattedTextMedia = new JFormattedTextField(mascaraMedia);
        //Posiciona os campos de textos
        jFormattedTextCod.setBounds(180,40,70,20);
        jFormattedTextNome.setBounds(180,80,200,20);
        jFormattedTextMedia.setBounds(180,120,41,20);

        //Adiciona os rótulos e os campos de textos com máscaras na tela
        janela.add(labelCod);
        janela.add(labelNome);
        janela.add(labelMedia);
        janela.add(jFormattedTextCod);
        janela.add(jFormattedTextNome);
        janela.add(jFormattedTextMedia);

        //Cria uma tabela no modelo JTable
        table = new JTable();
        table.setBounds(50, 200, 450, 200); //Determina o tamanho da tabela
        JScrollPane scrollPane = new JScrollPane(table); //Cria a um campo expandivel com barra de deslizamento para englobar a tabela
        scrollPane.setBounds(20, 150, 450, 150); //Posiciona o campo
        janela.add(scrollPane); //Adiciona o campo
        
        //Cria um label para enviar mensagem de erro caso a entrada esteja incorreta
        labelAvisoMedia = new JLabel("");
        labelAvisoMedia.setForeground(Color.RED); //Define a cor do texto para vermelho
        labelAvisoMedia.setBounds(225,120,200,20); //Posiciona o label em frente a caixa de texto Media
        janela.add(labelAvisoMedia);
        
        //Adiciona listener de foco para o campo de texto de media
        jFormattedTextMedia.addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) { //Se sair do campo de texto
                    float media = 0; 
                    String textoFloat = jFormattedTextMedia.getText();
                    try { 
                        media = Float.parseFloat(textoFloat);
                    } catch(Exception E) {
                    }
                    if (media<0 || media>10) { //Se a media no campo estiver no intervalo errado
                        labelAvisoMedia.setText("A media deve estar entre 0 e 10.");
                    }
                }

                public void focusGained(FocusEvent e) { //Se retornar ao campo de texto
                    labelAvisoMedia.setText(""); //Apaga mensagem de erro
                }
            });
        
        //Cria um label para enviar mensagem de erro caso a entrada esteja incorreta
        labelAvisoCod = new JLabel("");
        labelAvisoCod.setForeground(Color.RED);//Define a cor do texto para vermelho
        labelAvisoCod.setBounds(255,40,200,20);  //Posiciona o label em frente a caixa de texto codDisc
        janela.add(labelAvisoCod);
        erroEntradaVazia(jFormattedTextCod, labelAvisoCod); //chama metodo para impedir entrada vazia

        //Cria um label para enviar mensagem de erro caso a entrada esteja incorreta
        labelAvisoNome = new JLabel("");
        labelAvisoNome.setForeground(Color.RED); //Define a cor do texto para vermelho
        labelAvisoNome.setBounds(385,80,200,20); //Posiciona o label em frente a caixa de texto Nome
        janela.add(labelAvisoNome);
        erroEntradaVazia(jFormattedTextNome, labelAvisoNome); //chama metodo para impedir entrada vazia
        
        //Cria uma lista de Disciplinas
        listaDisc = new Disciplina[0];
        
        //adiciona o botão de cancelar
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(90,320,100,20); //Posiciona o botão
        janela.add(cancelar);
        cancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Se o botão for clicado
                    Disciplina[] disc = null;
                    listaDisc = disc; //Define a lista de Disciplinas como NULL
                    dispose();
                    //Fecha a janela
                }
            });
        
        //adiciona o botão de armazenar
        armazenar = new JButton("Armazenar");
        armazenar.setBounds(200,320,100,20); //Posiciona o botão
        janela.add(armazenar);
        
        //preenche um ArrayList com os campos da interface grafica
        ArrayList<JFormattedTextField> campos = new ArrayList<>();
        campos.add(jFormattedTextCod);
        campos.add(jFormattedTextNome);
        campos.add(jFormattedTextMedia);

        //Desativa o botão por padrao
        armazenar.setEnabled(false);
        //Cria um validador para atualizar o estado do botão se todas as entradas forem validas
        ValidaEntradaDisc validador = new ValidaEntradaDisc(campos);        
        validador.permitirEntrada(jFormattedTextCod, armazenar);
        validador.permitirEntrada(jFormattedTextNome, armazenar);
        validador.permitirEntrada(jFormattedTextMedia, armazenar);

        //Adiciona ação ao botão armazenar 
        armazenar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Se o botão for clicado
                    String codDisc = jFormattedTextCod.getText();
                    String nomeDisc = jFormattedTextNome.getText();
                    float media = Float.parseFloat(jFormattedTextMedia.getText());

                    Disciplina disc = new Disciplina(codDisc, nomeDisc, media); //Cria um objeto do tipo Disciplina

                    Disciplina[] aux = new Disciplina[listaDisc.length+1]; //Cria um array com uma posição extra
                    System.arraycopy(listaDisc, 0, aux, 0, listaDisc.length); //Copia o array antigo para o auxiliar

                    aux[aux.length-1] = disc; //Coloca o objeto novo na ultima posição 

                    listaDisc = aux; //Array principal recebe o auxiliar

                    updateTable(); //Atualiza a tabela com as disciplinas
                    //Limpa os texto dos campos de texto
                    jFormattedTextCod.setText(""); 
                    jFormattedTextNome.setText("");
                    jFormattedTextMedia.setText("00.0");
                    //Libera o botão para finalizar a lista de disciplinas
                    finalizar.setEnabled(true);
                }
            });
        
        //Adiciona um botao para finalizar a lista de disciplinas
        finalizar = new JButton("Finalizar lista");
        finalizar.setBounds(120,350,150,20); //Posiciona o botão
        janela.add(finalizar);
        //Adiciona ação ao botão finalizar 
        finalizar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(listaDisc.length>0) {
                        dispose(); // Fecha a janela se a lista não estiver vazia
                    }
                }
            });
        finalizar.setEnabled(false); //Desativa o botão pois este só pode funcionar se a lista de Disciplinas tiver ao menos um elemento
    }

    private void updateTable() { //Atualiza a tabela de disciplinas
        String[] nomesColunas = {"Código", "Nome", "Média"}; //Define o nome das colunas
        DefaultTableModel model = new DefaultTableModel(nomesColunas, 0); //Cria a tabela com as colunas definidas

        for (Disciplina disc : listaDisc) { //Percorre a lista de disciplinas e preenche cada linha com uma disciplina
            Object[] dadosLinha = {disc.getcodDisc(), disc.getnomeDisc(), disc.getMedia()};
            model.addRow(dadosLinha);
        }

        table.setModel(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //instancia uma classe que renderiza as celulas da tabela
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); //Alinha horizontalmente o conteudo de cada celula
        table.setDefaultRenderer(Object.class, centerRenderer); //Centraliza o texto de qualquer conteudo do tipo Object.class da tabela

        // Ajusta a largura das colunas de acordo com o conteúdo
        for (int coluna = 0; coluna < table.getColumnCount(); coluna++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(coluna);
            int preferredWidth = 0;

            // Encontra a largura preferida para a coluna com base na entrada mais longa
            for (int linha = 0; linha < table.getRowCount(); linha++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(linha, coluna); //Recebe o renderizador de cada celula na tabela
                Component component = table.prepareRenderer(cellRenderer, linha, coluna); //Recebe o component que foi configurado com o conteudo de cada celula (assim pode-se calcular o tamanho que ela devera ter)
                preferredWidth = Math.max(component.getPreferredSize().width + 1, preferredWidth); //Calcula o tamanho para cada coluna de acordo com a necessidade
            }

            // Define a largura preferida para a coluna
            tableColumn.setPreferredWidth(preferredWidth);
        }

        table.setDefaultEditor(Object.class, null); //Desativa a edição de celulas da tabela pelo usuário

        table.getTableHeader().setReorderingAllowed(false); //Desativa a reordenação das colunas na tabela pelo usuário
    }

    /**
     * Método erroEntradaVazia
     * @param texto Um campo de texto
     * @param label Uma label para modificar o texto
     */
    private void erroEntradaVazia(JFormattedTextField texto, JLabel label) {
        texto.addFocusListener(new FocusAdapter() {
                @Override    
                public void focusLost(FocusEvent e) { //Quando sair da caixa de texto
                    if (texto.getText().length() == 0) { //Se estiver vazia
                        label.setText("Campo obrigatorio."); 
                    }                
                }

                @Override
                public void focusGained(FocusEvent e) { //Quando retornar a caixa de texto
                    label.setText(""); //Limpa o aviso
                }
            }); 
    }

    /**
     * Método getDisciplina
     *
     * @return Retorna a lista de Disciplinas assim que o cadastro delas foi concluido
     */
    public Disciplina[] getDisciplina()
    {
        return this.listaDisc;
    }
}
