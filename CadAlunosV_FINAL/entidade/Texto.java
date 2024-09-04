package entidade;
import java.io.*;

/**
 * Armazena um texto e permite inverter ou limpar os espacos excedentes e recolher o tamanho do texto
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class Texto implements Serializable{
    // Atributo da classe ou varáveis de instancia
    private String txt;

    /** 
     * Construtor: armazena o texto e limpa os espaços excedentes
     * 
     * @param txt String com o texto que será armazenado
     */
    Texto(String txt){
        setTxt(txt);
        limpaEspacosExcedentes();
    }

    // setters e getters
    private void setTxt(String t){
        this.txt = t;
    }

    public String getTxt(){
        return this.txt;
    }

    /**
     * Retorna um texto invertido
     * @return txtInvertido String contendo o texto invertido
     */
    public String inverterTexto(){
        String txtInvertido = "";
        if(!(this.txt == null || this.txt.equals("") )){
            for (int i=this.txt.length()-1; i >= 0; i--){
                txtInvertido = txtInvertido + this.txt.charAt(i);
            }
        } else {
            return null;
        }
        return txtInvertido;
    }

    /**
     * Retorna quantidade de palavras do nome
     * @return qtd numero de palavras
     */
    public int getQtdePalavras(){
        return (getTxt().split(" ").length);
    }

    /**
     * Retira espacos excedentes de uma string
     * @return s string sem espacos excedentes
     */
    private void limpaEspacosExcedentes(){
        // Elimina espacos do inicio e fim da string
        setTxt(this.txt.trim());

        // Elimina espacos excedentes do meio da string
        String s = "";
        for (int i=0; i < this.txt.length(); i++){
            s = s + this.txt.charAt(i); // pega o caractere da posicao
            if(this.txt.charAt(i) == ' '){
                while(this.txt.charAt(i+1) == ' '){ // avanca se tiver mais espacos
                    i++;
                }
            }
        }
        setTxt(s);
    }

    /**
     * Retorna os atributos como string
     */
    public String toString(){
        return(getTxt());
    }
}