package entrada;
import entidade.*;
import java.util.Scanner;
/**
 * Classe EntradaAlunoConsole recebe os dados de um Aluno por meio do console e os retorna como um objeto Aluno
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class EntradaAlunoConsole implements IEntradaAluno
{
    String nome, rg=null, ra=null, curso;
    int semestre=0, idade=0, numDisc;
    boolean Erro = false;
    String erroMsg;
    Scanner scan = new Scanner(System.in);
    /**
     * Método registrar
     * Recebe os dados de um aluno por meio do Console
     * @return Retorna um objeto da classe Aluno
     */
    public Aluno registrar() {
        System.out.println("Forneça um nome: "); //Recebe o nome do aluno
        nome = scan.nextLine();

        do {
            Erro = false; //Sentinela que detecta erros
            System.out.println("Forneça a idade: ");
            try { //Tenta receber um inteiro idade
                idade = Integer.parseInt(scan.nextLine());
                if (idade < 17 || idade > 90) //Se o inteiro estiver fora do intervalo, envia exceção de IllegalArgumentException
                    throw new IllegalArgumentException("A idade enviada não pertence ao intervalo aceito (idade deve ser um número inteiro no intervalo de 18 a 90 anos)");
            } catch (NumberFormatException e) { //Se nao for um numero
                System.out.println("A idade enviada não corresponde a um número inteiro.");
                Erro = true;
            } catch (IllegalArgumentException e) { //Se o inteiro estiver fora do intervalo 
                System.out.println(e.getMessage());
                Erro = true;
            }
        } while (Erro);

        do { //Inicia Loop para evitar erros
            Erro=false; //Sentinela que detecta erros
            System.out.println("Forneça o rg: "); //Recebe o rg do aluno
            rg = scan.nextLine();
            if(Utils.isRG(rg)==false) {
                Erro=true;
                System.out.println("Formato de RG invalido. O RG deve ter pelo menos 9 digitos e sua entrada pode consistir de pontos e um traco ou ser apenas os numeros");
            }
        }while(Erro);
        
        System.out.println("Forneça o seu curso: ");
        curso = scan.nextLine();

        do { //Inicia Loop para evitar erros
            Erro=false; //Sentinela que detecta erros
            System.out.println("Forneça o semestre: "); //Recebe o semestre do aluno
            try{
                semestre = Integer.parseInt(scan.nextLine());
                if(semestre<1 || semestre>14) //Se o inteiro estiver fora do intervalo, envia exceção de IllegalArgumentException
                    throw new IllegalArgumentException("O semestre enviado nao pertence ao intervalo aceito. Semestre deve estar entre 1 e 14");
            } catch(NumberFormatException e) { //Se nao for um numero
                System.out.println("O semestre enviado nao corresponde a um numero inteiro.");
                Erro=true;
            } catch (IllegalArgumentException e) { //Se o inteiro estiver fora do intervalo 
                System.out.println(e.getMessage());
                Erro = true;
            }
        }while(Erro);

        do {
            Erro=false; //Sentinela que detecta erros
            System.out.println("Forneca a quantidade de disciplinas em que o aluno esta inscrito: "); //Recebe o rg do aluno
            try{
                numDisc = Integer.parseInt(scan.nextLine());
                if(numDisc<1 || numDisc>10) //Se o inteiro estiver fora do intervalo, envia exceção de IllegalArgumentException
                    throw new IllegalArgumentException("Numero de disciplinas invalido, aluno deve estar inscrito em no minimo 1 e no maximo 10 disciplinas por semestre");
            }catch(NumberFormatException e) { //Se nao for um numero
                System.out.println("O numero de disciplinas enviado nao corresponde a um numero inteiro.");
                Erro=true;
            } catch (IllegalArgumentException e) { //Se o inteiro estiver fora do intervalo 
                System.out.println(e.getMessage());
                Erro = true;
            }
        }while(Erro);

        Disciplina [] inputDisc; //Cria o vetor de entrada das Disciplinas com a classe Disciplina
        inputDisc = new Disciplina[numDisc]; //Aloca espaco para numDisc disciplinas
        String codDisc, nomeDisc; //inicializa as variaveis que irao armazenar o codigo e o nome da disciplina

        for(int i = 0; i < numDisc; i++) { //Loop for para recolher as disciplinas
            System.out.println("Forneça o Codigo de uma Disciplina : "); //Recebe o codigo da disciplina
            codDisc = scan.nextLine();
            System.out.println("Forneça o nome de uma Disciplina : "); //Recebe o nome da disciplina
            nomeDisc = scan.nextLine();
            float media=-1;
            do { //Inicia Loop para evitar erros
                Erro=false; //Sentinela que detecta erros
                System.out.println("Forneça sua media: "); //Recebe a media
                try{
                    media = Float.parseFloat(scan.nextLine());
                    if(media<0 || media>10) //Se o inteiro estiver fora do intervalo, envia exceção de IllegalArgumentException
                        throw new IllegalArgumentException("A media enviada nao pertence ao intervalo aceito (media deve ser um numero inteiro no intervalo de 0 a 10");
                }catch(NumberFormatException e) { //Se nao for um numero
                    System.out.println("A media enviada nao corresponde a um numero.");
                    Erro=true;
                } catch (IllegalArgumentException e) { //Se o inteiro estiver fora do intervalo 
                    System.out.println(e.getMessage());
                    Erro = true;
                }
            }while(Erro);
            inputDisc[i] = new Disciplina(codDisc, nomeDisc, media); //Preenche uma posicao do vetor 
        }

        //Cria um objeto estudante com os dados recebidos
        Aluno estudante = new Aluno(nome, idade, rg, ra, curso, inputDisc, semestre);

        return estudante;
    }
    /*
    public String setRA() {
        String ra;
        do { //Inicia Loop para evitar erros
            Erro=false; //Sentinela que detecta erros
            System.out.println("Forneça o ra: "); //Recebe o rg do aluno
            ra = scan.nextLine();
            if(Utils.isRA(ra)==false) {
                Erro=true;
                System.out.println("Formato de RA invalido. O RA eh uma sequencia de 8 digitos.");
            }
        }while(Erro);
        
        this.ra=ra;
        return ra;
    }*/
}