import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCadastro extends JFrame {
    private JTextField campoNome, campoEmail;
    private JPasswordField campoSenha, campoConfirmar;
    private TelaLogin telaLogin;

    public TelaCadastro(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;

        setTitle("Cadastrar Usuário");
        setSize(350, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(telaLogin);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);
        campoNome = new JTextField(15);
        gbc.gridx = 1;
        add(campoNome, gbc);


        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);
        campoEmail = new JTextField(15);
        gbc.gridx = 1;
        add(campoEmail, gbc);


        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Senha:"), gbc);
        campoSenha = new JPasswordField(15);
        gbc.gridx = 1;
        add(campoSenha, gbc);


        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Confirmar:"), gbc);
        campoConfirmar = new JPasswordField(15);
        gbc.gridx = 1;
        add(campoConfirmar, gbc);


        JPanel painelBotoes = new JPanel();
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnVoltar = new JButton("Voltar");

        btnCadastrar.addActionListener(e -> cadastrar());
        btnVoltar.addActionListener(e -> dispose());

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnVoltar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(painelBotoes, gbc);

        setVisible(true);
    }

    private void cadastrar() {
        String nome = campoNome.getText().trim();
        String email = campoEmail.getText().trim();
        String senha = new String(campoSenha.getPassword());
        String confirmar = new String(campoConfirmar.getPassword());

        try {
            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                throw new Exception("Preencha todos os campos!");
            }
            if (nome.length() < 3) {
                throw new Exception("Nome deve ter pelo menos 3 caracteres!");
            }
            if (!email.contains("@")) {
                throw new Exception("Email inválido!");
            }
            if (senha.length() < 4) {
                throw new Exception("Senha deve ter pelo menos 4 caracteres!");
            }
            if (!senha.equals(confirmar)) {
                throw new Exception("As senhas não coincidem!");
            }

            List<Usuario> usuarios = Arquivo.carregarUsuarios();
            for (Usuario u : usuarios) {
                if (u.getEmail().equals(email)) {
                    throw new Exception("Email já cadastrado!");
                }
            }

            usuarios.add(new Usuario(nome, email, senha));
            Arquivo.salvarUsuarios(usuarios);

            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
            telaLogin.recarregarUsuarios();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}