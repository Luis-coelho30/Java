package entrada.entradagrafica;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import javax.swing.JButton;
import entidade.*;
import listadecadastro.*;
//Completa
/**
 * Valida as entradas recebidas na Entrada Grafica de um aluno.
 * Se forem validas, ativa o botão que permite o cadastro, senão mantém ele desativado
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class ValidaEntradaAlunoGraf
{
    private ArrayList<JFormattedTextField> campos = new ArrayList<>(); 
    private int entradasNumericas;
    private int entradasEspeciais;
    private ArrayList<String> listaRA = new ArrayList<>();

    /**
     * ValidaEntradaAlunoGraf Construtor
     *
     * @param Recebe um ArrayList dos campos de JFormattedTextField presentes na interface grafica (necessario para garantir que todas as entradas sejam validas ao mesmo tempo)
     * @param numEntradasNumericas recebe o numero de entradas numericas
     * @param entradasEspeciais recebe o numero de entradas com condições especiais
     * @param listaRA recebe uma lista dos RAs presentes no cadastro
     */
    ValidaEntradaAlunoGraf(ArrayList<JFormattedTextField> campos, int numEntradasNumericas, int entradasEspeciais, ArrayList listaRA) {
        this.campos = campos;
        this.listaRA = listaRA;
        entradasNumericas = numEntradasNumericas;
        this.entradasEspeciais = entradasEspeciais;
    }

    /**
     * Método permitirEntrada
     *
     * @param campo Recebe um campo para verificar o seu estado durante o preenchimento
     * @param botao Recebe o botao a ser ativado/desativado
     */
    public void permitirEntrada(JFormattedTextField campo, JButton botao) {
        //Adiciona um DocumentListener ao campo: Responsavel por escutar as mudanças no campo
        campo.getDocument().addDocumentListener(new DocumentListener() {
                //Implementa os metodos da interface DocumentListener atualizando o estado do botao a cada mudança 
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
                    boolean entradaEspecialCorreta = true;
                    for(int i=0; i<campos.size()-entradasNumericas-entradasEspeciais; i++) { //Verifica se as entradas regulares estao preenchidas.
                        String texto = campos.get(i).getText().replace("_", "").replace(".", "").replace("-", ""); // Remove os espaços reservados
                        if (texto.isEmpty()) {
                            formatacaoValidaTxt = false;
                            break;
                        }
                    }
                    for(int j=campos.size()-entradasNumericas-entradasEspeciais; j<campos.size()-entradasEspeciais; j++) { //Verifica se as entradas numericas estao preenchidas e dentro dos intervalos aceitos
                        String textoInt = campos.get(j).getText(); // Remove os espaços reservados
                        if (textoInt.isEmpty()) {
                            formatacaoValidaTxt = false;
                            break;
                        }
                        int numero = Integer.parseInt(textoInt);
                        //Idade
                        if(j==3) {
                            if(numero<17 || numero>80) {
                                intervaloNumerico = false;
                                break;
                            }
                        }
                        //Semestre
                        if(j==4) {
                            if(numero<1 || numero>14) {
                                intervaloNumerico = false;
                                break;
                            }
                        }
                    }
                    for(int m=campos.size()-entradasEspeciais; m<campos.size(); m++) { //Verifica a(s) entrada(s) que necessitam de condições complexas
                        String textoRA = campos.get(m).getText().replace("_", "").replace(".", "").replace("-", "");
                        if (textoRA.isEmpty()) {
                            formatacaoValidaTxt = false;
                            break;
                        }
                        if(raJaExiste(textoRA)) {
                            entradaEspecialCorreta = false;
                            break;
                        }
                    }
                    //Ativa o botão se todos os campos estão preenchidos corretamente
                    botao.setEnabled(formatacaoValidaTxt && intervaloNumerico && entradaEspecialCorreta);
                }
            });
    }

    /**
     * Método raJaExiste
     *
     * @param Recebe um RA
     * @return True se o RA existe na listaRA do construtor
     */
    private boolean raJaExiste(String ra) {
        for(String raCadastrado : listaRA) {
            if(ra.equals(raCadastrado))
                return true;
        }
        return false;
    }
}
