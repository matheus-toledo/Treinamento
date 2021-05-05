## Client
AngularJS

## Server
Java 8
Jersey -> Rest API
- GET
- POST
- PUT
- DELETE
- PATCH
- OPTIONS  

Tomcat 9
https://tomcat.apache.org/download-90.cgi

Maven - Gradle
Jersey

------------------------------------------------
Links para leitura
Boas praticas de uma API Rest
https://docs.microsoft.com/pt-br/azure/architecture/best-practices/api-design

Clean Code Javascript
https://github.com/felipe-augusto/clean-code-javascript

Style Guide AngularJS - John Papa
https://github.com/johnpapa/angular-styleguide/blob/master/a1/i18n/pt-BR.md

Learning GIT
https://learngitbranching.js.org/?locale=pt_BR

- controllers
- models/entidades
- services
- repository

-------------------------------------------------

1 - Fazer chamada rest do Front para o Back e exibir o Hello World do Backend no frontend.

2 - Criar AlunoController no backend ("/alunos")
- GET

Entidade Aluno.java
String nome;
String email;
Integer idade;

List<Aluno> alunos = new ArrayList();

3 - Backend retornar em JSON
- Para isso, adicionar lib para trabalhar com json
- org.json
```json
{
  "nome": "",
  "email": "",
  "idade": ""
}
```
- exibir eles no HTML

3 - Criar os outros endpoints do aluno
- POST
- PUT
- DELETE

4 - Criar um formulário no frontend para salvar um usuário
- Adiciona o bootstrap https://getbootstrap.com/docs/4.6/getting-started/introduction/
Campos: 
Nome
Email
Idade
  
Botão pra dar submit nesse formulário e enviar essas informações para o backend

4 - Enviar esse usuário para o backend e salvar ele na memória

----------------------------------------------------------

5 - Adicionar id na entidade de Aluno
6 - Buscar aluno um Aluno por id 
@GET
@Path("/{id}")

alunos?nome=Joao&idade=40
alunos?id=1
alunos/1

7 - Listar todos os alunos em uma tabela
8 - deletar aluno pela listagem da tabela
9 - editar aluno pela listagem da tabela (vai para nova tela de edição)
- busca o aluno pelo id e preenche os campos 
10 - botão adicionar que redireciona para a tela de inclusão de alunos
  
11 - Tudo isso usando as rotas do AngularJS
Pode fazer com a lib ui-router ou nativamente com o AngularJS

---------------------------------------------
[x] - Validar a linha da tabela usando o ng-form

[x] - Depois de incluir, envia para a tela de editar Aluno

[] - Editar o aluno na tela de edição (controller de editar aluno)
Realizando a mesma validação da tela de editar
Adicionar botão "Voltar" para a tela de listagem ao lado do botão de salvar

[] - Adicionar a camada de serviço
aluno.service.js
Vai ter os métodos que faz a request para o backend
- incluir aluno
- editar aluno
- listar aluno
- deletar aluno
- consultar aluno

[x] - Criar entidade Usuario 
- id
- nome
- data de nascimento

Mesmas telas de Aluno (listar, editar e incluir)

--- Estudar JPA e Hibernate
--- Git - Git Learning

[] - Criar entidade de Escola
- id
- Nome - 255 caracteres
- diretor = usuario
- descricao = 300 caracteres
- ativo

[] - Criar entidade de Matéria
- id
- Nome
- descrição = 2500 caracteres
- Professor = usuário

[] - Criar entidade de Curso
- id
- nome
- sigla = somente letras de no máximo 5 letras
- coordenador = usuario
- escola (Escola)
- lista de matérias (Set<Materia>) 
  - estudar collections em java
- descrição = máximo 2500 caracteres# Treinamento_Escola

[ ] - Criar entidade de Turma
- id
- nome
- matricula (Sigla do curso - 0000)
- curso (Curso)
- alunos (List<Aluno>)
  
[ ] - Alterar entidade de Aluno
- Mesmos atributos
- Matricula (Sigla da Turma - 0)
- Turma (Turma)