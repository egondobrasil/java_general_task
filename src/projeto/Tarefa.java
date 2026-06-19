package projeto;

public class Tarefa {

	private String titulo;
	private	String description;
	private String	status;

	public Tarefa(String titulo, String description) {
		this.titulo = titulo;
		this.description = description;
		this.status = "Pendente";
	}
	

	public String getTitulo() {return titulo;}	//Retorna o título da tarefa.
	public String getDescription() {return description;}	//Retorna a descrição da tarefa.
	public String getStatus() {return status;}	//Retorna a situação em que se encontra a tarefa.
	public void concluir() {this.status = "Concluir";} //Altera a situação em que se encontra a tarefa para concluída.
	
	@Override
	public String toString() {return titulo + " - " + status;}
}