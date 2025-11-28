import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaLogin extends JFrame {
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private List<Usuario> usuarios;

    public TelaLogin() {
        usuarios = Arquivo.carregarUsuarios();

        setTitle("Login - Gerenciador de Tarefas");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JLabel titulo = new JLabel("Gerenciador de Tarefas");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titulo, gbc);


        gbc.gridwidth = 1; gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);
        campoEmail = new JTextField(15);
        gbc.gridx = 1;
        add(campoEmail, gbc);


        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Senha:"), gbc);
        campoSenha = new JPasswordField(15);
        gbc.gridx = 1;
        add(campoSenha, gbc);


        JPanel painelBotoes = new JPanel();
        JButton btnLogin = new JButton("Login");
        JButton btnCadastrar = new JButton("Cadastrar");

        btnLogin.addActionListener(e -> login());
        btnCadastrar.addActionListener(e -> new TelaCadastro(this));

        painelBotoes.add(btnLogin);
        painelBotoes.add(btnCadastrar);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(painelBotoes, gbc);

        setVisible(true);
    }

    private void login() {
        String email = campoEmail.getText().trim();
        String senha = new String(campoSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        for (Usuario u : usuarios) {
            // CORRIGIDO: Usando checkSenha() em vez de getSenha()
            if (u.getEmail().equals(email) && u.checkSenha(senha)) {
                dispose();
                new TelaPrincipal(u);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Email ou senha incorretos!");
    }

    public void recarregarUsuarios() {
        usuarios = Arquivo.carregarUsuarios();
    }
}