# Sistema-Gerenciador-de-Tarefas
Sistema desktop desenvolvido em Java com interface gráfica Swing para gerenciamento de tarefas pessoais. O sistema permite que múltiplos usuários cadastrem-se e gerenciem suas tarefas de forma individual, com persistência de dados em arquivos locais.


Propósito Principal
Fornecer uma ferramenta simples e eficiente para organização pessoal através do gerenciamento de tarefas, permitindo:
s
Criação, leitura, atualização e exclusão (CRUD) de tarefas
Categorização por prioridade e status
Filtros e buscas
Histórico completo de operações
Exportação de relatórios

* Funcionalidades
Requisitos Obrigatórios Implementados

Sistema de Autenticação

Tela de login com validação de credenciais
Cadastro de novos usuários
Armazenamento seguro em arquivo usuarios.txt


Tela Inicial (Home)

Menu principal com JMenuBar
Visualização de tarefas em JTable
Navegação clara e intuitiva


Operações CRUD Completas

Create: Criar novas tarefas através de formulário
Read: Listar todas as tarefas do usuário
Update: Editar tarefas existentes
Delete: Excluir tarefas com confirmação


Persistência de Dados

Salvamento automático após cada operação
Opção de salvamento manual através do menu
Carregamento automático ao iniciar
Arquivos: usuarios.txt e tarefas.txt


Tratamento de Exceções

Try-catch em todas as operações críticas
Validações completas nos formulários
Mensagens de erro amigáveis ao usuário
Log de erros no histórico



Requisitos Opcionais Implementados

Histórico de Alterações *

Todas as operações registradas em historico.log
Timestamp de cada ação
Visualização através do menu Relatórios
Rastreamento de login/logout


Funcionalidades Extras

Filtros por status e prioridade
Exportação de relatórios em TXT
Estatísticas de tarefas
Interface moderna e responsiva
