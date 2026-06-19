package projeto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



//Classe responsável pela tela principal do sistema.
//Esatamos herdando o JFrame, ou seja, teremos uma janela gráfica.
public class TelaTarefa extends JFrame{
	
	//Lista interna que armazena todas as taredas cadastradas pelo usuário:
	private ArrayList<Tarefa> tarefas = new ArrayList<>();
	
// Nesta parte não há uma ordem específica, então as linhas podem ser em qualquer ordem:
	private DefaultListModel<Tarefa> modeloLista; // Modelo responsável por armazenar os dados exibidos na lista visual.
	private JTextArea campoDescription; // Campo onde o usuário digitará a descrição da tarefa.
	private JTextField campoTitulo;	// Campo onde o usuário digitará o título da tarefa.
	
	private JList<Tarefa> listaTarefas; // Lista visual que exibe as tarefas cadastradas na tela.
	
	//Construtor da tela:
	public TelaTarefa(){
		setTitle("Gerenciador de tarefas"); // Define o título da janela.
		setSize(850,550); // Define o tamanho da janela (largura X altura).
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Encerra o programa ao clicar no botão de fechar da interface.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setResizable(false); // Impede que o usuário redimensione a janela.
		criarInterface(); // Chama o método responsável por montar a interface gráfica.
		setVisible(true); // Deixa a janela visível.
	}
	
	// Método responsável por criar e organizar todos os componentes visuais:
	private void criarInterface() {
		JPanel painelPrincipal = new JPanel(new BorderLayout(20,20)); // Painel principal da tela.
		painelPrincipal.setBorder(BorderFactory.createEmptyBorder(25,25,25,25)); // Define o espaçamento interno do painel principal.
		painelPrincipal.setBackground(new Color(236,240,243)); // Define a cor de fundo da aplicação.
		
		//Título principal da aplicação:
		JLabel titulo = new JLabel("Gerenciador de Tarefas");
		titulo.setFont(new Font("Segoe UI",Font.BOLD,30));
		titulo.setForeground(new Color(44,62,80));
		
		//Subtituçlo principal da aplicação:
		JLabel subtitulo = new JLabel("Organize suas tarefas de forma simples e" + "prática");
		subtitulo.setFont(new Font("Segoe UI",Font.PLAIN,15));
		subtitulo.setForeground(new Color(100,116,139));
		
		//Painel superior para agrupar título e subtítulo:
		JPanel painelTopo = new JPanel(new GridLayout(2,1));
		painelTopo.setBackground(new Color(236,240,243));
		painelTopo.add(titulo);
		painelTopo.add(subtitulo);
		
		//Adiciona o painel superior no topo da tela:
		painelPrincipal.add(painelTopo, BorderLayout.NORTH);
		JPanel cardFormulario = criarCard();
		cardFormulario.setLayout(new BorderLayout(10,10));
	}
	
	//Retorne aqui:
	private JPanel criarCard() {return;}
	
	
}
