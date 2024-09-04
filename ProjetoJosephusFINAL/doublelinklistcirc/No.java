 package doublelinklistcirc;
/**
 * No da lista duplamente ligada
 */
public class No{
    /**
     * Atributos
     */
    Object conteudo; // conteudo
    No proximo;  // proximo
    No anterior; // anterior
    long id;                  // id do no         

    /**
     * Construtor
     */
    public No(Object conteudo){
        setConteudo(conteudo);
        setProximo(null);
        setAnterior(null);
        setId(0);
    }
    /**
     * No Construtor
     *
     * @param conteudo dados do No
     * @param id id do no
     */
    public No(Object conteudo, long id){
        setConteudo(conteudo);
        setProximo(null);
        setAnterior(null);
        setId(id);
    }
    /**
     * setters e getters
     */
    public void setConteudo(Object conteudo){
        this.conteudo = conteudo;
    }
    
    public void setProximo(No proximo){
        this.proximo = proximo;
    }
    
    public void setAnterior(No anterior){
        this.anterior = anterior;
    }

    public Object getConteudo(){
        return(this.conteudo);
    }
    
    public No getProximo(){
        return(this.proximo);
    }

    public No getAnterior(){
        return(this.anterior);
    }
    /**
     * Método setId
     *
     * @param id Um parâmetro
     */
    public void setId(long id){
        this.id = id;
    }
    
    /**
     * Método getId
     *
     * @return O valor de retorno
     */
    public long getId(){
        return (this.id);
    }
    
    public String toString(){
        //return "ID: " + getId() + " " + getConteudo().toString(); 
        return(conteudo.toString());
    }
}
