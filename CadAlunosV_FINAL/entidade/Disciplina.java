package entidade;
import java.io.*;

/**
 * Classe que armazena o nome de uma disciplina, seu codigo e a media do aluno nela.
 * 
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class Disciplina implements Serializable
{
    String codDisc;
    Texto nomeDisc;
    float media;
    
    /**
     * Disciplina Construtor
     *
     * @param codDisc Recebe o codigo da disciplina
     * @param nomeDisc Recebe o nome da disciplina
     * @param media Recebe a media do aluno na disciplina
     */
    public Disciplina(String codDisc, String nomeDisc, float media) {
        this.codDisc = codDisc;
        this.nomeDisc = new Texto(nomeDisc);
        this.media = media;
    }
    
    //retorna dados da classe
    /**
     * Método toString
     *
     * @return A string com os dados do aluno em uma disciplina
     */
    public String toString(){
        String s = "(" + this.codDisc + ")" +
                   " " + this.nomeDisc +
                   "  Media: " + this.media;
        return (s);
    }
    
    public String getcodDisc() {
        return this.codDisc;
    }
    
    public Texto getnomeDisc() {
        return this.nomeDisc;
    }
    
    public float getMedia() {
        return this.media;
    }
}