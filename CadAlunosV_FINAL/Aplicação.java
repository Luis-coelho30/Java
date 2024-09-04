import entidade.*;
import entrada.*;
import interfacesgraficas.MenuGrafico;
import listadecadastro.*;
import entrada.entradagrafica.*;
import javax.swing.JOptionPane;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe Aplicacao permite que o usuario faça operações na Lista de cadastro
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class Aplicação
{
    private static int op;
    public static void main(String args[]) {
        ICadastro cad = new CadastroVetDin();
        //ICadastro cad = new CadastroListaArray();
        MenuGrafico menu = new MenuGrafico("Menu principal", "Cadastrar aluno na lista", "Remover aluno da lista", "Mostrar lista","Salvar Cadastro", "Carregar Cadastro", "Sair");

        menu.setVisible(true);
        menu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {  
                    op = menu.getOpcao();
                    cad.operarCadastro(op);
                    if(op != 6) {
                        menu.setVisible(true); // Mostra novamente o menu
                    }
                    else {
                        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente fechar o programa?\nTodo progresso não salvo será perdido.", "Sair", JOptionPane.YES_NO_OPTION); //Termina a execução do programa
                        if(resposta==JOptionPane.NO_OPTION)
                            menu.setVisible(true);
                    }
                }
            });
    }
}
