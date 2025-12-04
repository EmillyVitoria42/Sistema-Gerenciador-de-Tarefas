import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DialogoTarefa extends JDialog {
    private JTextField campoTitulo;
    private JTextArea campoDescricao;
    private JComboBox<String> comboStatus;
    private TelaPrincipal telaPrincipal;
    private Usuario usuario;
    private Tarefa tarefaEditando;

    public DialogoTarefa(TelaPrincipal telaPrincipal, Usuario usuario, Tarefa tarefa) {
        super(telaPrincipal, tarefa == null ? "Nova Tarefa" : "Editar Tarefa", true);
        this.telaPrincipal = telaPrincipal;
        this.usuario = usuario;
        this.tarefaEditando = tarefa;

        setSize(400, 300);
        setLocationRelativeTo(telaPrincipal);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Título:"), gbc);
        campoTitulo = new JTextField(20);
        gbc.gridx = 1;
        add(campoTitulo, gbc);


        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Descrição:"), gbc);
        campoDescricao = new JTextArea(4, 20);
        campoDescricao.setLineWrap(true);
        gbc.gridx = 1;
        add(new JScrollPane(campoDescricao), gbc);


        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Status:"), gbc);
        comboStatus = new JComboBox<>(new String[]{"Pendente", "Em Andamento", "Concluída"});
        gbc.gridx = 1;
        add(comboStatus, gbc);


        if (tarefa != null) {
            campoTitulo.setText(tarefa.getTitulo());
            campoDescricao.setText(tarefa.getDescricao());
            comboStatus.setSelectedItem(tarefa.getStatus());
        }


        JPanel painelBotoes = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> salvar());
        btnCancelar.addActionListener(e -> dispose());

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(painelBotoes, gbc);

        setVisible(true);
    }

    private void salvar() {
        String titulo = campoTitulo.getText().trim();
        String descricao = campoDescricao.getText().trim();
        String status = (String) comboStatus.getSelectedItem();

        try {
            if (titulo.isEmpty()) {
                throw new Exception("Título é obrigatório!");
            }
            if (descricao.isEmpty()) {
                throw new Exception("Descrição é obrigatória!");
            }

            List<Tarefa> tarefas = Arquivo.carregarTarefas();

            if (tarefaEditando == null) {
                // Nova tarefa
                int novoId = tarefas.stream().mapToInt(Tarefa::getId).max().orElse(0) + 1;
                tarefas.add(new Tarefa(novoId, usuario.getEmail(), titulo, descricao, status));
            } else {
                // Editar tarefa
                for (Tarefa t : tarefas) {
                    if (t.getId() == tarefaEditando.getId()) {
                        t.setTitulo(titulo);
                        t.setDescricao(descricao);
                        t.setStatus(status);
                        break;
                    }
                }
            }

            Arquivo.salvarTarefas(tarefas);
            telaPrincipal.atualizarTabela();
            JOptionPane.showMessageDialog(this, "Tarefa salva com sucesso!");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}