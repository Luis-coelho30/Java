package execuçãojosephus.painelcirculo;
import entidade.*;
import josephusGUI.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import doublelinklistcirc.*;

/**
 *  A classe JosephusCirculo realiza a criação do Painel que representa a lista circular 
 *  @author
 *  RA: 00333095 NOME Matheus Heimrath Barbosa 
 *  RA: 00331675 NOME Luís Augusto Coelho de Souza
 *  RA: 00332850 NOME João Guilherme Costa Couto 
 *  @version 19/06/2024
 */
public class JosephusCirculo extends JPanel {
    private ListaDuplamenteLigadaCircular grupo; //A lista circular
    private int radius; //Define o raio do círculo maior
    private static final Font FONT = new Font("Arial", Font.BOLD, 12); //Define a fonte padrão do painel inteiro como Arial em negrito tamanho 12

    /**
     * JosephusCirculo Construtor prepara a classe para a construção do circulo 
     *
     * @param grupo Recebe a lista circular para a montagem do painel
     */
    public JosephusCirculo(ListaDuplamenteLigadaCircular grupo) {
        this.grupo = grupo; // Define como padrão a lista circular para a classe toda
        this.radius = 350; // Raio do círculo maior
    }

    /**
     * Método paintComponent: Implementado como parte dos componentes Swing (JComponent) para definir a pintura do painel. Será chamado toda vez que repaint() for usado.
     *
     * @param g Um objeto da classe Graphics, usada para desenhar formas, texto e imagens
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //A chamada desse super é feita para limpar a tela para iniciar o novo desenho
        criarCirculo(g); //A chamada do método criarCirculo realiza o desenho do circulo com as pessoas representadas corretamente na lista circular
    }

    /**
     * Método criarCirculo: Desenha um circulo no painel conforme os itens da lista ligada
     *
     * @param g Um objeto da classe Graphics, usada para desenhar formas, texto e imagens
     */
    private void criarCirculo(Graphics g) {
        if (grupo == null) //Se a lista estiver vazia termina o método (evita erros na criação do mainFrame, que deve criar o espaço do painel vazio)
            return; 
        int centroX = getWidth() / 2; //Pega as coordenadas do horizontais em relação ao circulo maior
        int centroY = getHeight() / 2; //Pega as coordenadas do verticais em relação ao circulo maior
        double curvatura = 2 * Math.PI / grupo.getQtdNos(); //Determina o angulo entre a distancia dos circulos que representam as pessoas

        No atual = grupo.getInicio(); //Pega o primeiro nó da lista
        for (int i = 1; i <= grupo.getQtdNos(); i++) { //Percorre a lista circular
            Pessoa pessoa = (Pessoa) atual.getConteudo(); //Pega o objeto pessoa do nó atual
            if (!pessoa.estaMorta()) { //Se ela não estiver morta, pinta de azul
                g.setColor(Color.BLUE);
            } else { //Senão pinta de vermelho
                g.setColor(Color.RED);
            }
            int x = centroX + (int) (radius * Math.cos(i * curvatura)) - 6; //Define as coordenadas do horizontais dos circulos menores em relação ao circulo maior
            int y = centroY + (int) (radius * Math.sin(i * curvatura)) - 6; //Define as coordenadas do verticais dos circulos menores em relação ao circulo maior
            g.fillOval(x, y, 12, 12); //Usa o fillOval para preecher a posição com um pequeno circulo
            atual = atual.getProximo(); //Move para o proximo nó

            g.setColor(Color.BLACK); //Define a cor para o componente grafico para preto
            g.setFont(FONT); //Define a fonte com a definida estaticamente na classe
            String numero = String.valueOf(i); //Recolhe a posição do circulo colocado
            int stringComprimento = g.getFontMetrics().stringWidth(numero); //Recolhe o comprimento da String para definir as coordenadas
            int stringAltura = g.getFontMetrics().getHeight(); //Recolhe a altura da String para definir as coordenadas

            //Calcula as coordenadas do texto
            int textX = (int) (centroX + (radius + 20) * Math.cos(i * curvatura)) - (stringComprimento / 2); //Calcula as coordenadas horizontais
            int textY = (int) (centroY + (radius + 20) * Math.sin(i * curvatura)) + (stringAltura / 2); //Calcula as coordenadas verticais
            g.drawString(numero, textX, textY); //Desenha o número ao lado do circulo
        }
    }

    /**
     * Método setGrupo: Atualiza a lista circular recebida (para receber as modificações de estado dos elementos)
     *
     * @param grupo A lista circular usada na montagem do painel
     */
    public void setGrupo(ListaDuplamenteLigadaCircular grupo) { 
        this.grupo = grupo; //Atualiza a lista circular
        repaint(); //Redefine o painel e pinta ele novamente de acordo com a lista
    }
}
