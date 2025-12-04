import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {


    public static List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File arquivo = new File("usuarios.txt");
        if (!arquivo.exists()) return usuarios;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Usuario u = Usuario.fromString(linha);
                if (u != null) usuarios.add(u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static void salvarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt"))) {
            for (Usuario u : usuarios) {
                bw.write(u.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Tarefa> carregarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        File arquivo = new File("tarefas.txt");
        if (!arquivo.exists()) return tarefas;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Tarefa t = Tarefa.fromString(linha);
                if (t != null) tarefas.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    public static void salvarTarefas(List<Tarefa> tarefas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("tarefas.txt"))) {
            for (Tarefa t : tarefas) {
                bw.write(t.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}