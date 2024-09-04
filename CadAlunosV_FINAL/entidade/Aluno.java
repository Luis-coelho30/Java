package entidade;
import java.io.*;

/**
 * Classe que permite construir o perfil de um aluno herando nome (objeto da classe texto), idade e rg da classe Pessoa e inicializando atributos como: 
 * ra, curso, semestre alem de um array da Classe Disciplina para armazenar as disciplinas do aluno
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class Aluno extends Pessoa implements Serializable {
    // Atributos do aluno    
    private String ra;
    private String curso;
    private Disciplina [] disc;
    private int semestre;
    
    /**
     * Aluno Construtor
     *
     * @param nome Nome do aluno
     * @param idade Idade do aluno
     * @param rg RG do aluno
     * @param ra RA do aluno
     * @param curso Curso do aluno
     * @param disc Array de disciplinas do aluno
     * @param semestre Semestre do aluno
     */
    public Aluno(String nome, int idade, String rg, String ra, String curso, Disciplina [] disc, int semestre){
        // Aciona o construtor da classe pai (Pessoa)
        super(nome, idade, rg);
        this.ra = ra;
        this.disc = disc;
        this.curso = curso;
        this.semestre = semestre;
    }
    
    /**
     * Método toString
     *
     * @return As informacoes do aluno: Todas suas disciplinas + nome + idade + RG + RA + curso + semestre em uma string
     */
    public String toString(){
        String disciplinas = "";
        for(int i=0; i<this.disc.length; i++)
            disciplinas += this.disc[i] + "\n";
        String s = "Nome: " + this.nome + " Idade: " + this.idade + " RG: " + this.id + " RA: " + this.ra + " Curso: " + this.curso + " Semestre: " + this.semestre + "\n" + disciplinas;
        return (s);
    }
    
    /**
     * Método getNome
     *
     * @return O nome do aluno para outras operacoes
     */
    public String getNome() {
        return super.nome.getNome();
    }
    
    /**
     * Método getRa
     *
     * @return O RA do aluno para operacoes de Busca
     */
    public String getRa() {
        return this.ra;
    }
    
    public String getRG() {
        return super.id;
    }
    
    public int getIdade() {
        return this.idade;
    }
    
    public int getSemestre() {
        return this.semestre;
    }
    
    public Disciplina[] getDisc() {
        return this.disc;
    }
    
    public String getCurso() {
        return this.curso;
    }
}
