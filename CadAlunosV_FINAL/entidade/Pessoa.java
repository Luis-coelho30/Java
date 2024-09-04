package entidade;
import java.io.*;


/**
 * Classe que permite armazenar as caracteristicas de uma pessoa.
 * Permite recolher o nome, nome invertido, qtd de palavras no nome, nome bibliografico (metodos da classe NomePessoa), idade e rg
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class Pessoa implements Serializable{
    NomePessoa nome;
    int idade;
    String id;
    
    Pessoa(String nome, int idade, String rg){
        this.nome = new NomePessoa(nome);
        this.idade = idade;
        this.id = rg;
    }    
}