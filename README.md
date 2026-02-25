# LibraryAPI 📚

API REST para gerenciamento de acervo bibliográfico, controle de autores e livros. O projeto foca em boas práticas de arquitetura, segurança com perfis de acesso e auditoria de dados.

## 🚀 Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3.x**
* **Spring Data JPA**
* **Spring Security** (Autenticação Basic & Stateless)
* **PostgreSQL** (Persistência com tipos nativos como ARRAY para Roles)
* **MapStruct** (Mapeamento performático entre DTOs e Entities)
* **Lombok** (Produtividade e redução de Boilerplate)
* **Bean Validation** (Regras de negócio e validação de entrada)

---

## 🔐 Controle de Acesso

A segurança é segmentada por níveis de autoridade, garantindo que cada usuário acesse apenas o que lhe é permitido.

| Recurso | Operação | Gerente (`GERENTE`) | Operador (`OPERADOR`) |
| :--- | :--- | :---: | :---: |
| **Autores** | Cadastro, Atualização e Exclusão | ✅ | ❌ |
| **Autores** | Consultas e Filtros | ✅ | ✅ |
| **Livros** | Cadastro, Atualização e Exclusão | ✅ | ✅ |
| **Livros** | Consultas e Busca Paginada | ✅ | ✅ |



---

## 🛠️ Regras de Negócio e Requisitos

### 1. Cadastro de Autores
* **Campos Obrigatórios**: Nome, Data de Nascimento e Nacionalidade.
* **Unicidade**: Não é permitido cadastrar dois autores com o mesmo Nome, Data de Nascimento e Nacionalidade simultaneamente.
* **Restrição de Exclusão**: Um autor não pode ser removido se possuir livros vinculados ao seu registro.

### 2. Cadastro de Livros
* **Campos Obrigatórios**: ISBN, Título, Data de Publicação e Autor.
* **Filtros de Pesquisa**: Busca paginada por título, gênero, ISBN, nome do autor e ano de publicação.
* **Unicidade**: O ISBN deve ser exclusivo por livro.
* **Validação de Preço**: Para livros publicados a partir de **2020**, o preenchimento do preço torna-se obrigatório.
* **Consistência**: A data de publicação não pode ser superior à data atual.

### 3. Auditoria e Campos Lógicos
Todas as entidades principais contam com rastreabilidade automática:
* **ID**: Identificador único via UUID.
* **Timestamps**: Data de Cadastro e Data da Última Atualização.
* **Usuário**: Registro do login do usuário responsável pela última alteração.

---

## 📋 Como Executar o Projeto

1. **Pré-requisitos**:
   * JDK 21
   * Maven 3.x
   * Instância do PostgreSQL ativa.

2. **Configuração do Banco**:
   Ajuste as propriedades no arquivo `src/main/resources/application.yaml`:
   ```properties
   spring:
    datasource:
      url: jdbc:postgresql://localhost:5432/libraryapi
      username: seu_usuario
      password: sua_senha
      driver-class-name: org.postgresql.Driver

---
**Projeto desenvolvido para fins de estudos de Spring Boot**
