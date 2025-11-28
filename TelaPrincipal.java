import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class TelaPrincipal extends JFrame {
    private Usuario usuario;
    private List<Tarefa> tarefas;
    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaPrincipal(Usuario usuario) {
        this.usuario = usuario;
        this.tarefas = Arquivo.carregarTarefas();

        setTitle("Gerenciador de Tarefas");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSalvar = new JMenuItem("Salvar");
        JMenuItem itemSair = new JMenuItem("Sair");

        itemSalvar.addActionListener(e -> salvar());
        itemSair.addActionListener(e -> sair());

        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemSair);
        menuBar.add(menuArquivo);
        setJMenuBar(menuBar);


        JPanel painelTopo = new JPanel();
        painelTopo.add(new JLabel("Bem-vindo, " + usuario.getNome() + "!"));
        add(painelTopo, BorderLayout.NORTH);

        String[] colunas = {"ID", "Título", "Descrição", "Status"};
        modelo = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabela = new JTable(modelo);
        carregarTabela();
        add(new JScrollPane(tabela), BorderLayout.CENTER);


        JPanel painelBotoes = new JPanel();
        JButton btnNovo = new JButton("Nova Tarefa");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnAtualizar = new JButton("Atualizar");

        btnNovo.addActionListener(e -> novaTarefa());
        btnEditar.addActionListener(e -> editarTarefa());
        btnExcluir.addActionListener(e -> excluirTarefa());
        btnAtualizar.addActionListener(e -> carregarTabela());

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnAtualizar);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        tarefas = Arquivo.carregarTarefas();
        List<Tarefa> minhasTarefas = tarefas.stream()
                .filter(t -> t.getUsuario().equals(usuario.getEmail()))
                .collect(Collectors.toList());

        for (Tarefa t : minhasTarefas) {
            modelo.addRow(new Object[]{t.getId(), t.getTitulo(), t.getDescricao(), t.getStatus()});
        }
    }

    private void novaTarefa() {
        new DialogoTarefa(this, usuario, null);
    }

    private void editarTarefa() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa!");
            return;
        }


        int id = (Integer) modelo.getValueAt(linha, 0);
        Tarefa tarefa = tarefas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);

        if (tarefa != null) {
            new DialogoTarefa(this, usuario, tarefa);
        }
    }

    private void excluirTarefa() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa!");
            return;
        }

        int confirma = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir esta tarefa?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {

            int id = (Integer) modelo.getValueAt(linha, 0);
            tarefas.removeIf(t -> t.getId() == id);
            Arquivo.salvarTarefas(tarefas);
            carregarTabela();
            JOptionPane.showMessageDialog(this, "Tarefa excluída!");
        }
    }

    private void salvar() {
        Arquivo.salvarTarefas(tarefas);
        JOptionPane.showMessageDialog(this, "Dados salvos!");
    }

    private void sair() {
        int confirma = JOptionPane.showConfirmDialog(this,
                "Deseja sair?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            dispose();
            new TelaLogin();
        }
    }

    public void atualizarTabela() {
        carregarTabela();
    }
}