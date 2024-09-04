package doublelinklistcirc;
import java.util.Random;
public class ListaDuplamenteLigadaCircular implements IListaDuplamenteLigadaCircular {
    private No inicio; // ref para primeiro elemento
    private No fim;    // ref para ultimo elemento

    int qtdNos; // Quantidade de nos
    private Random r = new Random(System.currentTimeMillis());

    // -------------------------------------------------------------
    public ListaDuplamenteLigadaCircular() {   
        setInicio(null);
        setFim(null);
        setQtdNos(0);
    }
    // Setters e Getters
    /**
     * Method getInicio
     *
     * @return endereco do primeiro no
     */
    public No getInicio() {
        return inicio;
    }

    public void setInicio(No inicio) {
        this.inicio = inicio;
    }

    /**
     * Method getFim
     *
     * @return endereco do ultimo no
     */
    public No getFim() {
        return fim;
    }

    public void setFim(No fim) {
        this.fim = fim;
    }

    /**
     * Metodo getQtdNos
     *
     * @return qtidade de nos
     */
    public int getQtdNos(){
        return this.qtdNos;
    }

    /**
     * Metodo setQtdNos
     *
     * @param qtdNos atualiza qtde de nos
     */
    private void setQtdNos(int qtdNos){
        this.qtdNos = qtdNos;
    }

    // -------------------------------------------------------------
    public boolean estaVazia() {
        return getInicio() == null; 
    }

    // -------------------------------------------------------------
    public void inserirInicio(Object elem) {

        No novoNo = new No(elem, r.nextLong());   // make new link

        if( estaVazia() ) { 
            setFim(novoNo);
        } else {
            getInicio().setAnterior(novoNo); // 1
        }
        novoNo.setProximo(getInicio()); // 2
        setInicio(novoNo); // 3
        getFim().setProximo(getInicio()); // 4
        getInicio().setAnterior(getFim()); //5
        // Decrementa qtidade de nos
        setQtdNos(getQtdNos() + 1);

    }

    // -------------------------------------------------------------
    public void inserirFim(Object elem)    {
        No novoNo = new No(elem, r.nextLong());  

        if( estaVazia() ) {
            setInicio(novoNo);
        } else {
            getFim().setProximo(novoNo); //1
            novoNo.setAnterior(getFim()); //2
        }
        setFim(novoNo); //3
        getFim().setProximo(getInicio()); // 4
        getInicio().setAnterior(getFim()); //5
        // Incrementa quantidade de nos
        setQtdNos(getQtdNos() + 1);

    }

    // -------------------------------------------------------------
    public No removerInicio() {
        No temp = null;
        if(getInicio() != null && getFim() != null) {
            temp = getInicio();

            if (getInicio() == getFim()) {
                setInicio(null);
                setFim(null);
            } else {
                getFim().setProximo(getInicio().getProximo());
                getInicio().getProximo().setAnterior(getFim()); 
                setInicio(getInicio().getProximo());
            }
            temp.setAnterior(null);
            temp.setProximo(null);
        }
        // Decrementa qtidade de nos
        setQtdNos(getQtdNos() - 1);
        return temp;
    }

    // -------------------------------------------------------------
    public No removerFim() {
        No temp = null;
        if(getFim() != null && getInicio() != null) {
            temp = getFim();

            if(getFim() == getInicio()) {
                setFim(null);
                setInicio(null); 
            }
            else {
                getInicio().setAnterior(getFim().getAnterior());
                setFim(getFim().getAnterior());
                getFim().setProximo(getInicio());
            }
            temp.setAnterior(null);
            temp.setProximo(null);
        }
        // Decrementa qtidade de nos
        setQtdNos(getQtdNos() - 1);
        return temp;
    }

    // -------------------------------------------------------------
    public boolean inserirApos(long key, Object elem) {
        No noAtual = getInicio(); 

        while((Long)noAtual.getId() != key) {
            if(noAtual == getFim()) {
                return false; 
            }
            noAtual = noAtual.getProximo(); 
        }
        No novoNo = new No(elem, r.nextLong());

        if(noAtual==getFim()) {
            novoNo.setProximo(getInicio()); 
            setFim(novoNo); 
        }
        else {
            novoNo.setProximo(noAtual.getProximo());
            noAtual.getProximo().setAnterior(novoNo);
        }
        novoNo.setAnterior(noAtual);
        noAtual.setProximo(novoNo); 
        // Incrementa quantidade de nos
        setQtdNos(getQtdNos() + 1);
        return true; 
    }

    // -------------------------------------------------------------
    public No remover(long chave) {
        No noAtual = null;
        if(getInicio() != null) {
            noAtual = getInicio();

            while((Long)noAtual.getId() != chave) {
                if(noAtual == getFim()) {
                    return null;                 
                }
                noAtual = noAtual.getProximo(); 
            }

            if(noAtual == getInicio()) {
                noAtual = removerInicio();
            } else if (noAtual == getFim()) {
                noAtual = removerFim();
            }
            else {
                noAtual.getAnterior().setProximo(noAtual.getProximo());
                noAtual.getProximo().setAnterior(noAtual.getAnterior());
                // Decrementa qtidade de nos
                setQtdNos(getQtdNos() - 1);
            }
            noAtual.setAnterior(null);
            noAtual.setProximo(null);

        }
        return noAtual;
    }

    // -------------------------------------------------------------
    public String toString() {
        String s = "[ ";
        No noAtual = getInicio();  // inicia do inicio
        if(noAtual != null) {
            do {   
                s = s + noAtual.toString() + " ";  
                noAtual = noAtual.getProximo();   
            } while(noAtual != getInicio());
        }
        s = s + "]";
        return s;
    }

    // -------------------------------------------------------------
    public String toStringDoFim() {
        String s = "[ ";
        No noAtual = getFim();  // inicia no fim

        if(noAtual != null) {
            do {
                s = s + noAtual.toString() + " "; 
                noAtual = noAtual.getAnterior(); 
            }while(noAtual != getFim());
        }
        s = s + "]";
        return s;
    }

    public void limparLista() {

        No temp = getInicio();

        while (temp != getFim()) {
            removerFim();
        }

        setInicio(null);
        setFim(null);

    }
}  
