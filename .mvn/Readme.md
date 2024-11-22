Sistema de Gerenciamento de Biblioteca Online. Este sistema permitirá gerenciar livros, usuários e empréstimos de livros, e contará com funcionalidades básicas, como cadastro, consulta, e relatórios.
1. Descrição do Projeto

O projeto será uma API back-end para gerenciar uma biblioteca. Ele será baseado em conceitos de orientação a objetos, utilizando boas práticas, e contará com testes automatizados com JUnit.
2. Requisitos
   Funcionalidades:

   Cadastro de livros
   Título, autor, ISBN, categoria.
   Cadastro de usuários
   Nome, ID, email.
   Gerenciamento de empréstimos
   Registrar empréstimos e devoluções.
   Relatórios
   Listar livros disponíveis.
   Listar usuários com empréstimos atrasados.
   Filtros e consultas
   Consultar livros por categoria ou autor.

Ferramentas/Tecnologias:

    JUnit: Para testes unitários e integração.
    Maven: Para gerenciamento de dependências e build.
    Stream API: Para manipulação de listas e coleções.
    Collections Framework: Para armazenar e organizar dados.
    Orientação a Objetos: Para modelar o sistema.

3. Estrutura do Projeto
   Pacotes:

   com.biblioteca.model: Classes do modelo.
   com.biblioteca.service: Regras de negócio.
   com.biblioteca.repository: Simulação de banco de dados com coleções.
   com.biblioteca.controller: Ponto de entrada das funcionalidades.
   com.biblioteca.utils: Utilitários.
   com.biblioteca.test: Testes unitários e de integração.