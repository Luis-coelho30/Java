package salvamento;
import java.io.*;
import entidade.*;

/**
 * Implementa o salvamento de um objeto em um arquivo binario
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */

public class ArquivoBinario implements ISalvamento{
    /**
     * Method gravarObj grava um objeto em arquivo
     *
     * @param objeto objeto a ser gravado
     * @param nomeArq nome do arquivo
     */
    public void gravarArq(Object objeto, String nomeArq){
        ObjectOutputStream output = null;
        try {
            File file = new File(nomeArq);
            output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(objeto);  // escreve o objeto no arquivo
        } catch(Exception e){
            System.out.println(e.toString());
        } finally {
            try {
                output.close();
            } catch(Exception ex) {
                // Nao faz nada!
            }
        }
    }

    /**
     * Method lerObj
     *
     * @param nomeArq nome do arquivo a ser lido
     * @return Object o objeto lido
     */
    public Object lerArq(String nomeArq){
        Object objeto = null;
        ObjectInputStream input = null;
        try {
            File file = new File(nomeArq);
            input = new ObjectInputStream(new FileInputStream(file));
            objeto = (Object)input.readObject();  // le o objeto do arquivo
        }
        catch(Exception e){
            System.out.println(e.toString());
        } finally {
            try {
                input.close();
            } catch(Exception ex) {
                // Nao faz nada!
            }
        }
        return objeto;
    }
}

