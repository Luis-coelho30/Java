package entrada.entradagrafica;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.JButton;
/**
 * Valida as entradas de Disciplinas na Entrada grafica de Disciplinas
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class ValidaEntradaDisc
{
    private ArrayList<JFormattedTextField> campos = new ArrayList<>(); 

    /**
     * ValidaEntradaDisc Construtor
     *
     * @param campos recebe os campos de texto da interface grafica
     */
    ValidaEntradaDisc(ArrayList<JFormattedTextField> campos) {
        this.campos = campos;
    }

    /**
     * Método permitirEntrada
     *
     * @param campo Recebe um campo de texto
     * @param botao Recebe um botão para alterar seu estado
     */
    public void permitirEntrada(JFormattedTextField campo, JButton botao) {
        //Adiciona um DocumentListener a cada campo para escutar qualquer mudança feita pelo usuário
        campo.getDocument().addDocumentListener(new DocumentListener() {
                //Implementa os metodos da interface
                @Override
                public void insertUpdate(DocumentEvent e) { 
                    atualizarEstadoDoBotao();
                }

                @Override
                public void removeUpdate(DocumentEvent e) { 
                    atualizarEstadoDoBotao();
                }

                @Override
                public void changedUpdate(DocumentEvent e) { 
                    atualizarEstadoDoBotao();
                }

                private void atualizarEstadoDoBotao() { 
                    // Verifica se todos os campos estão preenchidos antes de ativar o botão
                    boolean formatacaoValidaTxt = true;
                    boolean intervaloNumerico = true;
                    
                    //Procura se os dois campos de texto estão preenchidos (Codigo da Disciplina e Nome)
                    String texto = campos.get(0).getText(); 
                    if (texto.isEmpty()) { 
                        formatacaoValidaTxt = false;
                    }
                    else {
                        texto = campos.get(1).getText();
                        if (texto.isEmpty()) {
                            formatacaoValidaTxt = false;
                        }
                    }
                    
                    //Procura se o campo de texto da media esta em um intervalo numerico correto
                    String textoFloat = campos.get(2).getText();
                    float numero = 0;
                    try {
                        numero = Float.parseFloat(textoFloat);
                    } catch(Exception e) {
                    }

                    if(numero<0 || numero>10) {
                        intervaloNumerico = false;
                    }
                    
                    //Se todas as condições estiverem satisfeitas libera o botão
                    botao.setEnabled(formatacaoValidaTxt && intervaloNumerico);
                }
            });
    }
}
