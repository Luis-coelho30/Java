package salvamento.gerenciarquivos;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe responsavel por armazenar metodos que gerenciam os arquivos no programa
 * @author Luis Augusto Coelho de Souza RA00331675
 * @author João Guilherme Costa Couto RA00332850
 * @author Matheus Heimrath Barbosa RA00333095
 * @version 21/05/2024
 */
public class GerenciaArquivos
{
    /**
     * Método salvarArquivo
     *
     * @return O nome de um arquivo criado para salvamento
     */
    public String salvarArquivo() {
        String nome = null;
        //Cria um FileChooser, uma interface grafica que permite a selecao ou criacao de um arquivo 
        JFileChooser fileChooser = new JFileChooser();
        //Define o diretorio em que o FileChooser sera aberto
        fileChooser.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
        //Define filtro para arquivos de salvamento (.dat)
        FileNameExtensionFilter saveFilter = new FileNameExtensionFilter("Arquivos de salvamento (.dat)", "dat");
        fileChooser.addChoosableFileFilter(saveFilter);
        //Define filtro padrão como filtro de salvamento
        fileChooser.setFileFilter(saveFilter);
        //Desabilitar a opção de "todos os arquivos" para bloquear a criacao de outros tipos de arquivos
        fileChooser.setAcceptAllFileFilterUsed(false);
        // Mostrar o seletor de arquivos e aguarda a criacao pelo usuário
        int result = fileChooser.showSaveDialog(null);
        //Pega o arquivo criado
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //Verifica se o arquivo tem a extensão .dat, senão adiciona
            if (!selectedFile.getName().toLowerCase().endsWith(".dat")) {
                selectedFile = new File(selectedFile.getParentFile(), selectedFile.getName() + ".dat");
            }
            try {
                // Cria o arquivo no sistema de arquivos
                if (selectedFile.createNewFile()) {
                    nome = selectedFile.getAbsolutePath(); //nome recebe o nome do arquivo criado para devolver como retorno
                    JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso em: " + nome);
                } else {
                    JOptionPane.showMessageDialog(null, "Arquivo nao pode ser criado."); //Se falhar em criar o arquivo
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro na criacao do arquivo: " + e.getMessage()); //Se falhar em criar o arquivo
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum arquivo criado.");
        }

        return nome;
    }
    
    /**
     * Método recolherNomeArq
     *
     * @return O nome de um arquivo de salvamento selecionado
     */
    public String recolherNomeArq() {
        String nome=null;
        //Cria um FileChooser, uma interface grafica que permite a selecao ou criacao de um arquivo 
        JFileChooser fileChooser = new JFileChooser();
        //Define o diretorio em que o FileChooser sera aberto
        fileChooser.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
        //Define filtro para arquivos de salvamento (.dat)
        FileNameExtensionFilter saveFilter = new FileNameExtensionFilter("Arquivos de salvamento (.dat)", "dat");
        fileChooser.addChoosableFileFilter(saveFilter);
        // Define filtro padrao como o filtro de salvamento
        fileChooser.setFileFilter(saveFilter);
        // Impede que o usuario busque por outros tipos de arquivo
        fileChooser.setAcceptAllFileFilterUsed(false);
        //mostra o FileChooser e recupera um arquivo
        int result = fileChooser.showOpenDialog(null);
        //Quando o usuario seleciona o arquivo
        if (result == JFileChooser.APPROVE_OPTION) {
            // Recebe o arquivo
            java.io.File selectedFile = fileChooser.getSelectedFile();
            // Nome recebe o nome do arquivo com .dat
            nome = selectedFile.getName();
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado.");
        }

        return nome;
    }
}
