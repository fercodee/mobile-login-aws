# Atividade: Tema 1 - Login por telefone

## Descrição
Servidor Spring Boot em Kotlin responsável por prover uma API REST para autenticação de dispositivos móveis através de número de telefone e UUID, gerando códigos OTP. A persistência de dados utiliza H2. O projeto será configurado para deploy gratuito na AWS.

## Aluno
- **Nome:** Fernando Cordeiro de Freitas

## Link do Vídeo
- **Vídeo de Demonstração:** https://youtu.be/QxKnHrb14OU

## Endpoints Principais
- `POST /users/login`: Solicita login via telefone e uuid. Retorna 202 com envio de SMS ou 200 caso já validado.
- `POST /users/confirm`: Confirma o código OTP enviado via SMS.
- `PUT /users/{id}`: Atualiza os dados cadastrais (nome, descrição) do usuário.

## Rodando o Projeto
Para iniciar o servidor localmente, utilize o comando abaixo ou qualquer IDE da sua preferência:
```bash
./gradlew bootRun
```
O console do H2 estará disponível em `http://localhost:8080/h2-console`.
