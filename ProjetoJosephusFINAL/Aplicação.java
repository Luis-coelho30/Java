import javax.swing.*;
import josephusGUI.*;
/**
 *  A classe Aplicação executa a abertura do frame principal.
 * 
 *  RA: 00333095 NOME Matheus Heimrath Barbosa 
 *  RA: 00331675 NOME Luís Augusto Coelho de Souza
 *  RA: 00332850 NOME João Guilherme Costa Couto 
 *  @version 19/06/2024
 */
public class Aplicação
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JosephusMainFrame());
    }
}