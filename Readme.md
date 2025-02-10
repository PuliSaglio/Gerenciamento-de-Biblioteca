
# Biblioteca API

## Propósito do Projeto
Este projeto consiste em uma API desenvolvida para gerenciar uma biblioteca. A aplicação permite realizar operações como cadastrar, buscar, alterar e excluir livros e usuários, além de gerenciar empréstimos e devoluções de livros.

O objetivo é fornecer um sistema robusto e documentado para facilitar o gerenciamento de bibliotecas, com ênfase em boas práticas de desenvolvimento e uso de tecnologias modernas.

## Participantes
- Ignacio Saglio Rossini

## Link da Gravação do Vídeo
[Assista ao vídeo de apresentação do projeto](https://example.com)

## Organização do Projeto
O projeto foi estruturado seguindo os princípios de arquitetura em camadas (Controller, Service, Repository). Cada camada desempenha uma função específica:

1. **Controller**: Lida com as requisições HTTP e interage com o Service.
2. **Service**: Contém a lógica de negócio e validações.
3. **Repository**: Gerencia os dados, utilizando estruturas de dados em memória.

### Diagrama de Classes
```plaintext
+----------------+       +------------------+       +------------------+
| Livro          |       | Usuario          |       | Emprestimo       |
|----------------|       |------------------|       |------------------|
| titulo         |       | nome             |       | idTransacao      |
| autor          |       | email            |       | dataEmprestimo   |
| isbn           |       | id               |       | dataDevolucao    |
| disponivel     |       |                  |       |                  |
+----------------+       +------------------+       +------------------+
     |                          |                        |
     |                          |                        |
     v                          v                        v
+----------------+       +------------------+       +----------------------+
| LivroRepository|       | UsuarioRepository|       | EmprestimoRepository|
+----------------+       +------------------+       +----------------------+
     ^                          ^                        ^
     |                          |                        |
     |                          |                        |
+----------------+       +------------------+       +------------------+
| LivroService   |       | UsuarioService   |       | EmprestimoService |
+----------------+       +------------------+       +------------------+
     ^                          ^                        ^
     |                          |                        |
+----------------+       +------------------+       +----------------------+
| LivroController|       | UsuarioController|       | EmprestimoController|
+----------------+       +------------------+       +----------------------+
```

## Instruções de Como Realizar o Build

1. Certifique-se de ter o [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior instalado.
2. Instale o [Maven](https://maven.apache.org/install.html).
3. Clone o repositório do projeto:
   ```bash
   git clone https://github.com/usuario/projeto-biblioteca.git
   ```
4. Navegue até o diretório do projeto:
   ```bash
   cd projeto-biblioteca
   ```
5. Execute o seguinte comando para compilar o projeto:
   ```bash
   mvn clean install
   ```

## Instruções de Como Executar

1. Após realizar o build, execute o projeto utilizando o Maven:
   ```bash
   mvn spring-boot:run
   ```
2. A API estará disponível em `http://localhost:8080`.
3. Acesse a documentação do Swagger em:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

## Endpoints Principais
- **Livros**:
    - `POST /livros`: Cadastrar um novo livro.
    - `GET /livros/{isbn}`: Buscar um livro pelo ISBN.
    - `PUT /livros/{isbn}`: Atualizar informações de um livro.
    - `DELETE /livros/{isbn}`: Excluir um livro.

- **Usuários**:
    - `POST /usuarios`: Cadastrar um novo usuário.
    - `GET /usuarios/{id}`: Buscar um usuário pelo ID.
    - `PUT /usuarios/{id}`: Atualizar informações de um usuário.
    - `DELETE /usuarios/{id}`: Excluir um usuário.

- **Empréstimos**:
    - `POST /emprestimos`: Registrar um novo empréstimo.
    - `PUT /emprestimos/{idTransacao}`: Registrar a devolução de um livro.


