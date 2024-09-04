package entrada;
import entidade.*;
import javax.swing.JOptionPane;
/**
 * Classe EntradaAlunoGUI recebe os dados do aluno por JOptionPane e retorna um objeto Aluno
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class EntradaAlunoGui implements IEntradaAluno
{
    String nome, idadeStr=null, rg=null, ra=null, curso, mediaStr=null, semestreStr=null, numDiscStr=null;
    int semestre=0, idade=0, numDisc;
    boolean Erro = false;
    String erroMsg;

    /**
     * Método registrar
     * Recebe os dados de um aluno por meio de JOptionPane
     * @return Retorna um objeto da classe Aluno
     */
    public Aluno registrar() {
        nome = JOptionPane.showInputDialog("Forneça um nome: "); //Recebe o nome do aluno

        do { //Inicia Loop para evitar erros
            try {
                if(Erro) { //Se houver erros aponta mensagem com explicacao
                    erroMsg = "Idade invalida.\nSua entrada: " + idade + "\nA idade deve ser um numero inteiro no intervalo de 17 a 90 anos.";
                    JOptionPane.showMessageDialog(null, erroMsg, "Erro na entrada", JOptionPane.ERROR_MESSAGE);
                    Erro = false;
                }
                idadeStr = JOptionPane.showInputDialog("Forneça a idade: "); //Recebe a idade do aluno
                if(Utils.isNumericInt(idadeStr) == false) { //Se a idade nao for inteiro aponta erro e retorna ao comeco para nova entrada
                    Erro = true; //Flag indica erro
                    continue;
                }
                idade = Integer.parseInt(idadeStr);
                if(!Utils.isIdade(idade)) //Se houver erro de intervalo na idade indica pela flag
                    Erro = true; 
            }catch (NullPointerException e) { //Se o inteiro estiver fora do intervalo 
                JOptionPane.showConfirmDialog(null,"Deseja cancelar a inserção?", "Selecione: ", JOptionPane.YES_NO_OPTION);
                Erro = true;
            }
        }while(Erro);

        do { //Inicia Loop para evitar erros
            if(Erro) {//Se houver erros aponta mensagem com explicacao
                erroMsg = "RG invalido.\nSua entrada: " + rg + "\nO RG deve ser uma sequencia de caracteres de tamanho minimo 9 e contendo apenas 2 pontos e 1 traco no maximo (nao obrigatorio)";
                JOptionPane.showMessageDialog(null, erroMsg, "Erro na entrada", JOptionPane.ERROR_MESSAGE);
                Erro = false;
            }
            rg = JOptionPane.showInputDialog("Forneça o rg: "); //Recebe o rg do aluno
            if(!Utils.isRG(rg)) //Se houver erro especifico no RG indica pela flag
                Erro = true;
        }while(Erro);

        curso = JOptionPane.showInputDialog("Forneça o seu curso: ");

        do { //Inicia Loop para evitar erros
            if(Erro) { //Se houver erros aponta mensagem com explicacao
                erroMsg = "Semestre invalido.\nSua entrada: " + semestreStr + "\nO Semestre deve ser um numero inteiro entre 1 e 14";
                JOptionPane.showMessageDialog(null, erroMsg, "Erro na entrada", JOptionPane.ERROR_MESSAGE);
                Erro = false;
            }
            semestreStr = JOptionPane.showInputDialog("Forneça o semestre: "); //Recebe o semestre do aluno
            if(Utils.isNumericInt(semestreStr) == false) { //Se semestre nao for um inteiro
                Erro = true;
                continue;
            }            
            semestre = Integer.parseInt(semestreStr);
            if(!Utils.isSemestre(semestre)) //Se houver erro de intervalo no semestre indica pela flag
                Erro = true;
        }while(Erro);

        do {
            if(Erro) {//Se houver erros aponta mensagem com explicacao
                erroMsg = "Numero de Disciplinas invalido.\nSua entrada: " + numDiscStr + "\nUm aluno pode participar de no máximo 10 e no minimo uma disciplina(s) por semestre";
                JOptionPane.showMessageDialog(null, erroMsg, "Erro na entrada", JOptionPane.ERROR_MESSAGE);
                Erro = false;
            }
            numDiscStr = JOptionPane.showInputDialog("Forneca a quantidade de disciplinas em que o aluno esta inscrito: "); //Recebe o rg do aluno
            if(Utils.isNumericInt(numDiscStr)==false) {
                Erro = true;
                continue;
            }
            numDisc = Integer.parseInt(numDiscStr);
            if(numDisc>10 || numDisc<1) //Se houver erro de intervalo no  indica pela flag
                Erro = true;
        }while(Erro);

        Disciplina [] inputDisc; //Cria o vetor de entrada das Disciplinas com a classe Disciplina
        inputDisc = new Disciplina[numDisc]; //Aloca espaco para numDisc disciplinas
        String codDisc, nomeDisc; //inicializa as variaveis que irao armazenar o codigo e o nome da disciplina

        for(int i = 0; i < numDisc; i++) { //Loop for para recolher as disciplinas
            codDisc = JOptionPane.showInputDialog("Forneça o Codigo de uma Disciplina : "); //Recebe o codigo da disciplina
            nomeDisc = JOptionPane.showInputDialog("Forneça o nome de uma Disciplina : "); //Recebe o nome da disciplina
            float media=-1;
            do { //Inicia Loop para evitar erros
                if(Erro) { //Se houver erros aponta mensagem com explicacao
                    erroMsg = "Media invalida.\nSua entrada: " + mediaStr + "\nA media deve ser um numero no intervalo de 0 a 10.";
                    JOptionPane.showMessageDialog(null, erroMsg, "Erro na entrada", JOptionPane.ERROR_MESSAGE);
                    Erro = false;
                }
                mediaStr = JOptionPane.showInputDialog("Forneça sua media: "); //Recebe a media
                if(Utils.isNumericFloat(mediaStr) == false) { //Se a media nao for um numero float valido
                    Erro = true;
                    continue;
                }
                media = Float.parseFloat(mediaStr);
                if(!Utils.isMedia(media)) //Se a media estiver fora do intervalo 
                    Erro = true;
            }while(Erro);
            inputDisc[i] = new Disciplina(codDisc, nomeDisc, media); //Preenche uma posicao do vetor 
        }

        Aluno estudante = new Aluno(nome, idade, rg, ra, curso, inputDisc, semestre);

        return estudante;
    }
    /*
    public String setRA(){
        String ra=null;
        do { //Inicia Loop para evitar erros

            if(Erro) { //Se houver erros aponta mensagem com explicacao
                erroMsg = "RA invalido.\nSua entrada: " + ra + "\nO RA deve ser uma sequencia de 8 digitos";
                JOptionPane.showMessageDialog(null, erroMsg, "Erro na entrada", JOptionPane.ERROR_MESSAGE);
                Erro = false;
            }
            ra = JOptionPane.showInputDialog("Forneça o ra: "); //Recebe o RA do aluno
            if(!Utils.isRA(ra)) //Se houver erro especifico no RA indica pela flag
                Erro = true;

        }while(Erro);

        this.ra = ra;
        return ra;
    }*/
}
