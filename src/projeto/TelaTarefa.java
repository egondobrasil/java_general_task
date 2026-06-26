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
		
		//Título do formulário:
		JLabel tituloFormulario = new JLabel("Nova Tarefa");
		tituloFormulario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tituloFormulario.setForeground(new Color(44,62,80));
		
		//Painel para agrupar os campos dos formulários:
		JPanel campos = new JPanel(new GridLayout(4,1,8,8));
		campos.setBackground(Color.white);
		
		//Label do campo de título:
		JLabel labelTitulo = new JLabel("Título");
		labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		//Campo para digitar o título:
		campoTitulo = new JTextField();
		campoTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		//Borda personalizada do campo de título:
		campoTitulo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(203,213,225)),
				BorderFactory.createEmptyBorder(8,10,8,10)));
		
		//Label do campo de descrição:
		JLabel labelDescription = new JLabel("Descrição");
		labelDescription.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		//Campo de texto para digitar a descrição da tarefa:
		campoDescription = new JTextArea();
		campoDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		campoDescription.setLineWrap(true);
		campoDescription.setWrapStyleWord(true);
		campoDescription.setBorder(BorderFactory.createEmptyBorder(8,10,8,10));
		
		//Scroll para o campo de descrição:
		JScrollPane scrollDescription = new JScrollPane(campoDescription);
		scrollDescription.setBorder(BorderFactory.createLineBorder(new Color(203,213,225)));
		
		campos.add(labelTitulo);
		campos.add(campoTitulo);
		campos.add(labelDescription);
		campos.add(scrollDescription);
		

		JButton botaoAdicionar = criarBotao("Adicionar tarefa", new Color(37,99,235)); //Botão para adicionar uma nova tarefa.
		
		//Adicionar titulo, campos e botão ao card de formulário:
		cardFormulario.add(tituloFormulario, BorderLayout.NORTH);
		cardFormulario.add(campos, BorderLayout.CENTER);
		cardFormulario.add(botaoAdicionar, BorderLayout.SOUTH);
		
		painelPrincipal.add(cardFormulario, BorderLayout.WEST); //Adiciona o card do  formulário a esquerda da tela.
		
		//Cria o card na lista de tarefas:
		JPanel cardLista = criarCard();
		cardLista.setLayout(new BorderLayout(10,10));
		
		//Título da área de tarefas cadastradas:
		JLabel tituloLista = new JLabel("Tarefas cadastradas");
		tituloLista.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tituloLista.setForeground(new Color(44,62,80));
		
		modeloLista = new DefaultListModel<>();//Modelo que guarda os dados que serão exibidos na JList.
		listaTarefas = new JList<>(modeloLista);//Lista visual que exibirá as tarefas.
		listaTarefas.setCellRenderer(new TarefaCardRenderer());//Define um renderer personalizado para exibir cada tarefa com um card.
		listaTarefas.setFixedCellHeight(75);//Define uma altura fixa para cada tarefa exibida.
		listaTarefas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//Permite selecionar apenas uma tarefa por vez.
		listaTarefas.setBackground(Color.WHITE);//Define a cor de fundo da lista.
		
		//Scroll para a lista de tarefas cadastradas:
		JScrollPane scrollLista = new JScrollPane(listaTarefas);
		scrollLista.setBorder(BorderFactory.createLineBorder(new Color(226,232,240)));
		
		//Painel inferior, onde ficam os botões de ação na lista de tarefas:
		JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		painelBotoes.setBackground(Color.WHITE);
		
		//Botões para concluir e remover uma tarefa cadastrada:
		JButton botaoConcluir = criarBotao("Concluir", new Color(22,163,74));
		JButton botaoRemover = criarBotao("Remover", new Color(220,38,38));
		
		//Adiciona os botões ao painel inferior:
		painelBotoes.add(botaoConcluir);
		painelBotoes.add(botaoRemover);
		
		//Adiciona título, lista e botões à sessão da lista:
		cardLista.add(tituloLista, BorderLayout.NORTH);
		cardLista.add(scrollLista, BorderLayout.CENTER);
		cardLista.add(painelBotoes, BorderLayout.SOUTH);
		
		painelPrincipal.add(cardLista, BorderLayout.CENTER); //Adiciona o carda da lista ao centro da tela.
		add(painelPrincipal); //Adiciona o painel principal à nossa janela.
		
		//Eventos para os botões de adicionar, concluir e remover uma tarefa:
		botaoAdicionar.addActionListener(e -> adicionarTarefa());
		botaoConcluir.addActionListener(e -> concluirTarefa());
		botaoRemover.addActionListener(e -> removerTarefa());
		
		}
	
	//Método responsável por criar um painel com aparência de card:
	private JPanel criarCard() {
		JPanel painel = new JPanel();
		painel.setBackground(Color.WHITE);
		painel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(226,232,240)),BorderFactory.createEmptyBorder(20,20,20,20)));
		painel.setPreferredSize(new Dimension(33,400));
		return painel;}
	
	//Método responsável por criar os botões padronizados:
	private JButton criarBotao(String texto, Color cor) {
		 //Cria o botão no texto recebido na criação de nossa interface:
		JButton botao = new JButton(texto);
		botao.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fonte do botão.
		botao.setBackground(cor); //Cor de fundo.
		botao.setFocusPainted(false); //Remove a borda de foco.
		botao.setCursor(new Cursor(Cursor.HAND_CURSOR)); //Altera o cursor ao passar o mouse sobre o botão.
		botao.setBorder(BorderFactory.createEmptyBorder(10,18,10,18)); //Define espaçamento interno do botão (também chamamos de padding).
		return botao;
	}
	
	//Método responsável por adicionar uma nova tarefa:
	private void adicionarTarefa() {
		//Captura o título e descrição digitado pelo usuário:
		String titulo = campoTitulo.getText().trim();
		String description= campoDescription.getText().trim();
		//Verifica se os campois estão vazios:
		if (titulo.isEmpty()|| description.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha todos os campos.");return;}
		
		//CRia um novo objeto Tarefa:
		Tarefa tarefa = new Tarefa(titulo, description);
		tarefas.add(tarefa); //Adiciona a tarefav à lista interna.
		modeloLista.addElement(tarefa); //Adiciona a tarefa.
		
		//Limpa os campos de título e descrição:
		campoTitulo.setText("");
		campoDescription.setText("");
		}
	private void concluirTarefa() {
		//Pega a tarefa selecionada na lista visual:
		Tarefa tarefaSelecionada = listaTarefas.getSelectedValue();
		//Verifica se o usuário selecionou uma tarefa:
		if(tarefaSelecionada == null) {JOptionPane.showMessageDialog(this, "Selecione uma tarefa");return;}
		tarefaSelecionada.concluir(); //Altera o status da tarefa.
		listaTarefas.repaint(); //Atualiza visualmente a lista.
		
	}
	
	//Método responsável por remover uma tarefa:
	private void removerTarefa() {
		//Pega a tarefa selecionada na lista:
		Tarefa tarefaSelecionada = listaTarefas.getSelectedValue();
		//Verifica se o usuário selecionou uma tarefa:
		if (tarefaSelecionada == null) {JOptionPane.showMessageDialog(this, "Selecione uma tarefa.");return;}
		tarefas.remove(tarefaSelecionada); //Remove da lista linterna.
		modeloLista.removeElement(tarefaSelecionada); //Remove da lista visual. 
		
	}
	//Classe interna para fazer a renderização do card de tarefa:
	private class TarefaCardRenderer extends JPanel implements ListCellRenderer<Tarefa> {
		private JLabel labelTitulo; //Exibe o título.
		private JLabel labelDescription; //Exibe a descrição.
		private JLabel labelStatus; //Exibe o status.
		
		//COnstrutor do renderer:
		public TarefaCardRenderer() {setLayout(new BorderLayout(8,5));
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 8, 0, Color.WHITE),BorderFactory.createEmptyBorder(10,12,10,12)));
		
		//Cria e estiliza a label de titulo:
		labelTitulo = new JLabel();
		labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		labelTitulo.setForeground(new Color(30,41,59));
		
		//Cria e estiliza a label de descrição:
		labelDescription = new JLabel();
		labelDescription.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		labelDescription.setForeground(new Color(100,116,139));
		
		//Estiliza o Status:
		labelStatus = new JLabel();
		labelStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		//Organiza o título e a descrição em 2 linhas e 1 coluna:
		JPanel painelTexto = new JPanel(new GridLayout(2,1));
		painelTexto.setOpaque(false); //Deixa o painel transparente.
		painelTexto.add(labelTitulo); //Adiciona o título.
		painelTexto.add(labelDescription); //Adiciona a descrição.
		
		//Adiciona o painel no centro do Card:
		add(painelTexto, BorderLayout.CENTER);
		add(labelStatus, BorderLayout.EAST); //Coloca o Status à direita do card.
		
		}
		 // Método para recriar a listagem

        @Override
        public Component getListCellRendererComponent(
                JList<? extends Tarefa> list,
                Tarefa tarefa,
                int index,
                boolean isSelected,
                boolean cellHasFocus
        ) {

            // Define o título que será exibido no card
            labelTitulo.setText(tarefa.getTitulo());

            // Define a descrição que será exibida no card
            labelDescription.setText(tarefa.getDescription());

            // Define o status que será exibido no card
            labelStatus.setText(tarefa.getStatus());

            // Se a tarefa estiver concluída, o status fica verde
            if (tarefa.getStatus().equals("Concluída")) {
                labelStatus.setForeground(new Color(22, 163, 74));
            } else {
                // Caso contrário, o status fica laranja
                labelStatus.setForeground(new Color(234, 88, 12));
            }

            // Se o card estiver selecionado, muda a cor do fundo
            if (isSelected) {
                setBackground(new Color(219, 234, 254));
            } else {
                // Caso contrário, usa um fundo claro
                setBackground(new Color(248, 250, 252));
            }

            // Retorna o próprio painel personalizado para ser exibido na lista
            return this;
        }
    }
}