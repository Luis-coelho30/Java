package entrada;
/**
 * Classe responsavel por identificar erros nas entradas do programa
 * 
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class Utils
{            
    /**
     * Método isNumericFloat
     *
     * @param str Um parâmetro
     * @return O valor de retorno
     */
    public static boolean isNumericFloat(String str) { //Metodo responsavel por identificar irregularidades com um numero float
        if (str == null || str.length() == 0) {
            return false;
        }
        boolean foundDecimal = false;
        for (char c : str.toCharArray()) { // percorre a string
            if (!Character.isDigit(c)) { // verifica se eh digito
                if (c == '.' && !foundDecimal) { // verifica se existe um unico ponto
                    foundDecimal = true;
                } else {
                    return false;
                }
            }
        }            
        return true;
    }
    
    /**
     * Método isNumericInt
     *
     * @param str Um parâmetro
     * @return O valor de retorno
     */
    public static boolean isNumericInt(String str) { //Metodo responsavel por identificar irregularidades com um numero inteiro
        if (str == null || str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) { // percorre a string
            if (!Character.isDigit(c)) { // verifica se eh digito
                return false; //Se nao for indica falso pois nao eh inteiro
            }
        }            
        return true;
    }
    
    /**
     * Método isRA
     *
     * @param str Um parâmetro
     * @return O valor de retorno
     */
    public static boolean isRA(String str) { //Metodo responsavel por identificar irregularidades com o RA
        if (str.length() != 8) { //RA deve ter 8 digitos
            return false;
        }
        for (char c : str.toCharArray()) { // percorre a string
            if (!Character.isDigit(c)) { // verifica se eh digito
                return false; //Se nao for devolve falso pois nao ha digito no RA
            }
        }       
        return true;
    }
    
    /**
     * Método isIdade
     *
     * @param idade Um parâmetro
     * @return O valor de retorno
     */
    public static boolean isIdade(int idade) { //Metodo responsavel por identificar irregularidades com a Idade
        if(idade<17 || idade>90) //Se a idade for menor que 17 ou maior que 90 devolve falso
            return false;
            
        return true;
    }
    
    /**
     * Método isSemestre
     *
     * @param semestre Um parâmetro
     * @return O valor de retorno
     */
    public static boolean isSemestre(int semestre) { //Metodo responsavel por identificar irregularidades com o semestre
        if(semestre<1 || semestre>14) //Se o semestre for menor que zero ou maior que 14 devolve falso
            return false;
    
        return true;
    }
    
    /**
     * Método isRG
     *
     * @param str Um parâmetro
     * @return O valor de retorno
     */
    public static boolean isRG(String str) { //Metodo responsavel por identificar irregularidades com o RG
        if (str == null || (str.length() < 9 || str.length() > 15)) { //RG deve ter pelo menos 9 digitos
            return false;
        }
        int ponto=0;
        int traco=0;
        for (char c : str.toCharArray()) { // percorre a string
            if (!Character.isDigit(c)) { // verifica se eh digito
                if (c == '.') { // verifica se existe um ponto
                    ponto++; //Adiciona 1 ao contador de ponto
                } 
                else if(c == '-') { //verifica se existe um traco
                    traco++; //Adiciona 1 ao contador de traco
                }
                
                else { //Se o caractere encontrado nao for um ponto ou traco devolve falso
                    return false;
                }
            }
        }       
        return (ponto == 2 && traco == 1) || (ponto==0 && traco == 0); //Retorna True se houver 2 ou 0 pontos e 1 ou 0 tracos
    }
    
    /**
     * Método isMedia
     *
     * @param media Um parâmetro
     * @return O valor de retorno
     */
    public static boolean isMedia(float media) { //Metodo responsavel por identificar irregularidades com a media
        if(media<0 || media>10) //Se for menor que 0 ou maior que 10 retorna falso
            return false;
            
        return true;
    }
}

