package entidade;
import java.io.*;


/**
 * Classe que permite manipular um nome usando os metodos da classe Texto.
 * Permite recolher o nome, nome invertido, qtd de palavras no nome, nome bibliografico
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class NomePessoa implements Serializable{
    // Atributos
    private Texto nome;

    // Construtores
    public NomePessoa(String nome){
        setNome(nome);
    }

    /**
     * @return o nome recebido
     */
    public String getNome() {
        return this.nome.getTxt();
    }

    /**
     * variavel local nome recebe o nome como objeto da classe texto
     */
    protected void setNome(String nome) {
        this.nome = new Texto(nome);
    }

    /**
     * Retorna quantidade de palavras do nome
     * 
     */
    public int getQtdePalavras(){
        return this.nome.getQtdePalavras();
    }
    
    /**
     * Retorna nome invertido
     * 
     */
    public String getNomeInvertido(){
        return this.nome.inverterTexto();
    }

    /**
     * Retorna nome bibliografico
     * 
     */
    public String getNomeBiblio(){

        // Separa as palavras
        String vts[] = this.nome.getTxt().split(" ");
        int qtd = vts.length;

        String sBib = vts[qtd-1] + ", "; // ultimo nome + a virgula
        // Monta o texto
        for (int i=0; i < (qtd-1); i++){
            String pal = vts[i].toLowerCase(); // pega palavra
            if(!verificaStr(pal)){ // Se nao for preposicao concatena
                sBib = sBib + vts[i].toUpperCase().charAt(0) + ". ";
            }
        }
        return sBib;
    }

    /**
     * Verifica se string eh uma String a ser retirada
     * @param s string a ser verificada
     * @return true eh preposicao false nao eh preposicao
     */
    private boolean verificaStr(String s){
        // Vetor de strings a serem retiradas
        final String sRet[]={"da", "de", "do", "di", "das", "dos", "e",};

        for (int i = 0; i < sRet.length; i++){
            if(sRet[i].equals(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna os atributos como string
     */
    public String toString(){
        return this.nome.toString();
    }

}