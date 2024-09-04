package interfacesgraficas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.text.MaskFormatter;
import java.text.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;

/**
 * Implementa uma interface grafica para a remoção de um aluno
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class RemoverAlunoGUI extends JDialog
{
    private ArrayList<String> listaRA = new ArrayList<>(); 
    private String ra=null;

    /**
     * RemoverAlunoGUI Construtor
     *
     * @param owner O JFrame pai para o JDialog
     * @param titulo O titulo do JDialog
     * @param listaRA Uma lista dos RAs cadastrados
     */
    public RemoverAlunoGUI(JFrame owner, String titulo, ArrayList<String> listaRA) {
        super(owner, titulo, true);
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 200);
        setLayout(null); // Usando null layout
        setLocationRelativeTo(null);
        this.listaRA = listaRA;

        //Cria o label para indicar o que o usuario tem que digitar no campo de texto
        JLabel labelRa = new JLabel("Insira o RA do Aluno: ");
        labelRa.setBounds(50,50,200,20);
        MaskFormatter mascaraRa = null;
        //Cria o label para avisar erros na entrada 
        JLabel labelAvisoRA = new JLabel("");
        labelAvisoRA.setBounds(242,50,200,20);
        labelAvisoRA.setForeground(Color.RED);

        try{
            mascaraRa = new MaskFormatter("########"); //Usa um MaskFormatter que limita as entradas em numeros 0-9
        }
        catch(ParseException excp) {
            System.err.println("Erro na formatação: " + excp.getMessage());
            System.exit(-1);
        }

        //Cria um campo de texto com a mascara
        JFormattedTextField jFormattedTextRa = new JFormattedTextField(mascaraRa);
        jFormattedTextRa.setBounds(170,50,70,20);

        //Adiciona o label de texto, o label de aviso e o campo de texto
        add(labelRa);
        add(labelAvisoRA);
        add(jFormattedTextRa);

        //Cria o botão de cancelar
        JButton cancelar = new JButton("Cancelar");
        cancelar.setBounds(70,100,100,30);
        add(cancelar);
        cancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent E) {
                    //Se o botão for apertado
                    ra = null; //Seta o ra para null
                    dispose(); //Fecha a entrada grafica
                }
            });

        //Cria o botão de remover
        JButton remover = new JButton("Remover");
        remover.setBounds(240,100,100,30);
        remover.setEnabled(false); //Desativa o botão por padrao
        add(remover);
        remover.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent E) {
                    ra=jFormattedTextRa.getText(); //Seta o ra para a entrada do usuario
                    dispose(); //Fecha a entrada grafica
                } 
            });

        //Adiciona um DocumentListener ao campo de texto para escutar as mudanças e liberar a entrada caso ela esteja correta
        jFormattedTextRa.getDocument().addDocumentListener(new DocumentListener() {
                //Implementa os metodos da interface DocumentListener
                @Override
                public void insertUpdate(DocumentEvent e) {
                    permitirRemocao();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    permitirRemocao();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    permitirRemocao();
                }

                private void permitirRemocao() { //Metodo que libera ou bloqueia o botão de entrada 
                    String textoRA = jFormattedTextRa.getText().replace(" ", ""); //Recebe o que está escrito no campo de texto
                    if (textoRA.isEmpty() || textoRA.length() < 8) { //Se o campo estiver incompleto
                        labelAvisoRA.setText("Campo obrigatório.");
                        remover.setEnabled(false); //Desativa o botão
                    } else if (!raJaExiste(textoRA)) { //Se o RA nao existir na lista
                        labelAvisoRA.setText("O RA não existe na lista!");
                        remover.setEnabled(false); //Desativa o botão
                    } else { //Se estiver correta
                        labelAvisoRA.setText("");
                        remover.setEnabled(true); //Ativa o botão
                    }
                }
            });      
    }

    /**
     * Método raJaExiste
     *
     * @param ra Um RA para buscar na lista
     * @return Se o RA esta ou nao na lista
     */
    private boolean raJaExiste(String ra) {
        for(String raCadastrado : listaRA) {
            if(ra.equals(raCadastrado))
                return true;
        }
        return false;
    }

    /**
     * Método getRA
     *
     * @return O RA a ser removido
     */
    public String getRA() {
        return this.ra;
    }
}
