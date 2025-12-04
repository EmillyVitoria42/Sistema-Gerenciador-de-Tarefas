public class Tarefa {
    private int id;
    private String usuario;
    private String titulo;
    private String descricao;
    private String status;

    public Tarefa(int id, String usuario, String titulo, String descricao, String status) {
        this.id = id;
        this.usuario = usuario;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return id + ";" + usuario + ";" + titulo + ";" + descricao + ";" + status;
    }

    public static Tarefa fromString(String linha) {
        String[] partes = linha.split(";");
        if (partes.length == 5) {
            try {

                return new Tarefa(
                        Integer.parseInt(partes[0]),
                        partes[1],
                        partes[2],
                        partes[3],
                        partes[4]
                );
            } catch (NumberFormatException e) {

                System.err.println("Erro de formato de ID da Tarefa. Linha ignorada.");
                return null;
            }
        }
        return null;
    }
}