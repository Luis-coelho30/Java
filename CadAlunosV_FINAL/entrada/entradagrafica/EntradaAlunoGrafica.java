package entrada.entradagrafica;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.util.ArrayList;
import entidade.*;
import listadecadastro.*;

/**
 * Cria uma interface grafica para entrada de um aluno.
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class EntradaAlunoGrafica extends JDialog
{
    private JButton botao, cancelar, addDisc;
    private JLabel labelAvisoRA, labelAvisoIdade, labelAvisoSemestre, labelAvisoRG, labelAvisoCurso, labelAvisoNome, labelAvisoCadastro;
    private Aluno novoAluno;
    private Disciplina[] listaDisc;
    private ArrayList<String> listaRA = new ArrayList<>();

    /**
     * EntradaAlunoGrafica Construtor
     * 
     * @param owner Determina o frame dono do JDialog (implementado com o objetivo de tornar a interface modal, isto é, impedir o uso de outras opções enquanto a interface está aberta)
     * @param titulo Recebe o titulo do JDialog
     * @param listaRA Recebe uma lista dos RAs presentes no cadastro
     */
    public EntradaAlunoGrafica(Frame owner, String titulo, ArrayList listaRA) {
        super(owner, titulo, true);
        this.listaRA =  listaRA;
        setSize(450, 450);
        setLocation(740,350);
        
        Container janela = getContentPane();

        setLayout(null);

        //Define os labels que indicaram a função de cada campo
        JLabel labelNome = new JLabel("Nome: ");
        JLabel labelRa = new JLabel("RA: ");
        JLabel labelRg = new JLabel("RG: ");
        JLabel labelIdade = new JLabel("Idade: ");
        JLabel labelCurso = new JLabel("Curso: ");
        JLabel labelSemestre = new JLabel("Semestre: ");
        
        //Define a localização dos Labels
        labelNome.setBounds(50,40,100,20);
        labelRa.setBounds(50,80,100,20);
        labelRg.setBounds(50,120,100,20);
        labelIdade.setBounds(50,160,100,20);
        labelCurso.setBounds(50,200,100,20);
        labelSemestre.setBounds(50,240,100,20);

        //Define as máscaras
        MaskFormatter mascaraNome = null;
        MaskFormatter mascaraRa = null;
        MaskFormatter mascaraRg = null;
        MaskFormatter mascaraIdade = null;
        MaskFormatter mascaraCurso = null;
        MaskFormatter mascaraSemestre = null;

        try{
            mascaraRa = new MaskFormatter("########"); //Limita a inserção de usuário a apenas numeros de 0-9
            mascaraRg = new MaskFormatter("##.###.###-#"); //Limita a inserção de usuário a apenas numeros de 0-9
            mascaraIdade = new MaskFormatter("##"); //Limita a inserção de usuário a apenas numeros de 0-9
            mascaraSemestre = new MaskFormatter("##"); //Limita a inserção de usuário a apenas numeros de 0-9
            mascaraRa.setPlaceholderCharacter('_'); //Determina um placeholder, um caractere que ocupa os espaços do JFormattedTextField até que o usuário digite algo
            mascaraRg.setPlaceholderCharacter('_'); //Determina um placeholder, um caractere que ocupa os espaços do JFormattedTextField até que o usuário digite algo
            mascaraIdade.setPlaceholderCharacter('0'); //Determina um placeholder, um caractere que ocupa os espaços do JFormattedTextField até que o usuário digite algo
            mascaraSemestre.setPlaceholderCharacter('0'); //Determina um placeholder, um caractere que ocupa os espaços do JFormattedTextField até que o usuário digite algo
        }
        catch(ParseException excp) {
            System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
        }

        //Seta as máscaras nos objetos JFormattedTextField
        JFormattedTextField jFormattedTextNome = new JFormattedTextField(mascaraNome);
        JFormattedTextField jFormattedTextRa = new JFormattedTextField(mascaraRa);
        JFormattedTextField jFormattedTextRg = new JFormattedTextField(mascaraRg);
        JFormattedTextField jFormattedTextIdade = new JFormattedTextField(mascaraIdade);
        JFormattedTextField jFormattedTextCurso = new JFormattedTextField(mascaraCurso);
        JFormattedTextField jFormattedTextSemestre = new JFormattedTextField(mascaraSemestre);
        
        //Define a localização dos jFormattedTextField
        jFormattedTextNome.setBounds(150,40,150,20);
        jFormattedTextRa.setBounds(150,80,70,20);
        jFormattedTextRg.setBounds(150,120,100,20);
        jFormattedTextIdade.setBounds(150,160,20,20);
        jFormattedTextCurso.setBounds(150,200,100,20);
        jFormattedTextSemestre.setBounds(150,240,20,20);

        //Adiciona os rótulos e os campos de textos com máscaras na tela
        janela.add(labelNome);
        janela.add(labelRa);
        janela.add(labelRg);
        janela.add(labelIdade);
        janela.add(labelCurso);
        janela.add(labelSemestre);
        janela.add(jFormattedTextNome);
        janela.add(jFormattedTextRa);
        janela.add(jFormattedTextRg);
        janela.add(jFormattedTextIdade);
        janela.add(jFormattedTextCurso);
        janela.add(jFormattedTextSemestre);
        
        //Cria um Label para mostrar um aviso no campo RA
        labelAvisoRA = new JLabel("");
        labelAvisoRA.setForeground(Color.RED); //Define a cor do texto como vermelha
        labelAvisoRA.setBounds(220, 80, 300, 20); // Posição em frente a caixa de texto RA
        janela.add(labelAvisoRA); //Adiciona o label

        // Adiciona um listener de foco para o campo de texto formatado para verificar quando o usuário termina de editar o campo
        jFormattedTextRa.addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) { //Ao sair do campo de texto
                    String texto = jFormattedTextRa.getText().replaceAll("_", ""); // Remove os espaços reservados
                    if (texto.length() < 8) { //Se o campo estiver incompleto
                        labelAvisoRA.setText("Campo obrigatorio.");
                    } 
                    String ra = jFormattedTextRa.getText();
                    if(raJaExiste(ra)) //Se o RA já existir na lista
                        labelAvisoRA.setText("O RA já existe na lista!");
                }

                public void focusGained(FocusEvent e) { //Ao retornar do campo de texto
                    labelAvisoRA.setText(""); //Apaga o aviso
                }
            });       
        
        //Cria um Label para mostrar um aviso no campo Idade    
        labelAvisoIdade = new JLabel("");
        labelAvisoIdade.setForeground(Color.RED); //Define a cor do texto como vermelha
        labelAvisoIdade.setBounds(175,160,220,20); // Posição em frente a caixa de texto Idade
        janela.add(labelAvisoIdade); //Adiciona o label
        
        // Adiciona um listener de foco para o campo de texto formatado para verificar quando o usuário termina de editar o campo
        jFormattedTextIdade.addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) { //Ao sair do campo de texto
                    int idade = Integer.parseInt(jFormattedTextIdade.getText());
                    if (idade<17 || idade>80) { //Se a idade for invalida
                        labelAvisoIdade.setText("Idade deve estar entre 17 e 80 anos.");
                    }
                }

                public void focusGained(FocusEvent e) { //Ao retornar do campo de texto
                    labelAvisoIdade.setText(""); //Apaga o aviso
                }
            });  
        
        //Cria um Label para mostrar um aviso no campo Semestre
        labelAvisoSemestre = new JLabel("");
        labelAvisoSemestre.setForeground(Color.RED); //Define a cor do texto como vermelha
        labelAvisoSemestre.setBounds(175,240,200,20); // Posição em frente a caixa de texto Semestre
        janela.add(labelAvisoSemestre); //Adiciona o label
        
        // Adiciona um listener de foco para o campo de texto formatado para verificar quando o usuário termina de editar o campo
        jFormattedTextSemestre.addFocusListener(new FocusAdapter() {
                public void focusLost(FocusEvent e) { //Ao sair do campo de texto
                    int semestre = Integer.parseInt(jFormattedTextSemestre.getText());
                    if (semestre<1 || semestre>14) { //Se o semestre for invalido
                        labelAvisoSemestre.setText("Semestre deve estar entre 1 e 14.");
                    }
                }

                public void focusGained(FocusEvent e) { //Ao retornar do campo de texto
                    labelAvisoSemestre.setText(""); //Apaga o aviso
                }
            });  
        
        //Cria um Label para mostrar um aviso no campo RG
        labelAvisoRG = new JLabel("");
        labelAvisoRG.setForeground(Color.RED); //Define a cor do texto como vermelha
        labelAvisoRG.setBounds(250, 120,150,20);  // Posição em frente a caixa de texto RG
        janela.add(labelAvisoRG); //Adiciona o label
        erroEntradaVazia(jFormattedTextRg, labelAvisoRG, 9); //Chama o metodo para enviar mensagem de erro caso a entrada esteja vazia
        
        //Cria um Label para mostrar um aviso no campo Nome
        labelAvisoNome = new JLabel("");
        labelAvisoNome.setForeground(Color.RED); //Define a cor do texto como vermelha
        labelAvisoNome.setBounds(300, 40,150,20); // Posição em frente a caixa de texto Nome
        janela.add(labelAvisoNome); //Adiciona o label
        erroEntradaVazia(jFormattedTextNome, labelAvisoNome, 0); //Chama o metodo para enviar mensagem de erro caso a entrada esteja vazia
        
        //Cria um Label para mostrar um aviso no campo Curso
        labelAvisoCurso = new JLabel("");
        labelAvisoCurso.setForeground(Color.RED); //Define a cor do texto como vermelha
        labelAvisoCurso.setBounds(250, 200,150,20); // Posição em frente a caixa de texto Curso
        janela.add(labelAvisoCurso); //Adiciona o label
        erroEntradaVazia(jFormattedTextCurso, labelAvisoCurso, 0); //Chama o metodo para enviar mensagem de erro caso a entrada esteja vazia  
        
        //Cria o botao de adição de disciplinas
        addDisc = new JButton("Criar lista de Disciplinas");
        addDisc.setBounds(50,280,280,20); //Define a posição do botão
        janela.add(addDisc); //Adiciona o botao
        addDisc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Executa quando o botão é clicado
                    EntradaDiscGUI disc = new EntradaDiscGUI(EntradaAlunoGrafica.this, "Adicione uma disciplina"); //Abre a interface de adição de disciplinas determinando o JDialog Pai como essa classe
                    disc.setVisible(true); 
                    disc.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowOpened(WindowEvent e) {
                                labelAvisoCadastro.setText(""); //Quando a janela de adição é aberta apaga a mensagem que avisava a necessidade de pelo menos uma disciplina cadastrada
                            }

                            @Override
                            public void windowClosed(WindowEvent e) {
                                listaDisc = disc.getDisciplina(); //Quando a janela de adição é fechada ,ou seja, a adição finalizou, recolhe a lista de disciplinas do JDialog filho por um get
                            }
                        });
                }
            });
        
        //Adiciona um botao de cancelar 
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(50,330,100,20); //Define a posicao do botão
        janela.add(cancelar);
        cancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Se o botão for clicado
                    Aluno estudante = null; //Define o estudante como NULL
                    novoAluno = estudante; //Copia o parametro para a variavel private fora do construtor
                    dispose(); //Fecha o JDialog
                }
            });

        //Adiciona um botao de cadastrar 
        botao = new JButton("Cadastrar");
        botao.setBounds(230,330,100,20); //Define a posicao do botão
        janela.add(botao);

        botao.setEnabled(false);  //Desativa o botão por padrão no começo
        //Carrega um ArrayList com os campos de texto
        ArrayList<JFormattedTextField> campos = new ArrayList<>();
        campos.add(jFormattedTextCurso);
        campos.add(jFormattedTextRg);
        campos.add(jFormattedTextNome);
        campos.add(jFormattedTextIdade);
        campos.add(jFormattedTextSemestre);
        campos.add(jFormattedTextRa);
        //Inicia um validador de entrada enviando o ArrayList, o numero de entradas numericas, o numero de entradas especiais e uma lista dos RAs cadastrados
        ValidaEntradaAlunoGraf validador = new ValidaEntradaAlunoGraf(campos, 2, 1, listaRA);
        //Realiza as verificações em cada campo para atualizar o estado do botão
        validador.permitirEntrada(jFormattedTextCurso, botao);
        validador.permitirEntrada(jFormattedTextRg, botao);
        validador.permitirEntrada(jFormattedTextNome, botao);
        validador.permitirEntrada(jFormattedTextIdade, botao);
        validador.permitirEntrada(jFormattedTextSemestre, botao);
        validador.permitirEntrada(jFormattedTextRa, botao);

        //Adiciona label para enviar mensagem de erro ao tentar cadastrar aluno
        labelAvisoCadastro = new JLabel("");
        labelAvisoCadastro.setForeground(Color.RED); //Define a cor do texto para vermelho
        labelAvisoCadastro.setBounds(20,375,400,20); //Define a posição do label
        janela.add(labelAvisoCadastro);
        
        //Define ação do botão cadastro
        botao.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Lógica a ser executada quando o botão é clicado
                    if(listaDisc!=null) { //Se houver disciplina cadastrada
                        int idade = Integer.parseInt(jFormattedTextIdade.getText());
                        int semestre = Integer.parseInt(jFormattedTextSemestre.getText());
                        String curso = jFormattedTextCurso.getText();
                        String rg =jFormattedTextRg.getText();
                        String ra =jFormattedTextRa.getText();
                        String nome = jFormattedTextNome.getText();
                        Disciplina[] inputDisc = listaDisc;
                        Aluno estudante = new Aluno(nome, idade, rg, ra, curso, inputDisc, semestre);
                        novoAluno = estudante;
                        dispose();
                    }

                    else { //Se nao houver, apresenta mensagem e mantém o JDialog aberto
                        //imprime mensagem 
                        labelAvisoCadastro.setText("O aluno deve estar cadastrado em pelo menos uma disciplina");
                    }
                }
            });

    }

    /**
     * Método erroEntradaVazia
     *
     * @param texto Um campo de texto
     * @param label Uma label para modificar o texto
     * @param tamMin O tamanho minimo da entrada
     */
    private void erroEntradaVazia(JFormattedTextField texto, JLabel label, int tamMin) {
        texto.addFocusListener(new FocusAdapter() {
                @Override    
                public void focusLost(FocusEvent e) {
                    if (texto.getText().replace("_", "").replace(".", "").replace("-", "").length() == 0 || texto.getText().replace("_", "").replace(".", "").replace("-", "").length()<tamMin) {
                        label.setText("Campo obrigatorio.");
                    }                
                }

                @Override
                public void focusGained(FocusEvent e) {
                    label.setText("");
                }
            }); 
    }

    /**
     * Método getAluno
     *
     * @return Um objeto do tipo Aluno
     */
    public Aluno getAluno() {
        return this.novoAluno;
    }

    /**
     * Método raJaExiste
     *
     * @param ra Recebe um RA
     * @return True se o RA existe na listaRA e false se não.
     */
    private boolean raJaExiste(String ra) {
        for(String raCadastrado : listaRA) {
            if(ra.equals(raCadastrado))
                return true;
        }
        return false;
    }
}
