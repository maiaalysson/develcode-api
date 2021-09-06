# develcode-api
Development test DevelCode-API

Teste MVP - DevelCode company

Banco de dados utilizado:
   
     Banco: MySQL
     Versão: 8.0.25 - MySQL Community Server - GPL
     Port:3306
     Engine: InnoDB
     Configuração: develcode-api/src/main/resources/application.properties
     OBS: Modificar Nome, Password e URL caso não esteja utilizando como localhost na porta 3306,
          para conexão com banco de dados. 
   
Uso da API no Postman:

     Adicionar Usuário
      POST
        localhost:8080/users
        Headers: Key: Accept | Value: application/json
        Body: Raw : JSON { 
               "nome": "Tester",
               "Data de Nascimento": "01/04/1998"
              }
      
     Listar Usuários
      GET
        localhost:8080/users
        Headers: Key: Accept | Value: application/json
      
     Buscar Usuário
      GET
        localhost:8080/users/id
        Exemplo: localhost:8080/users/1
        Headers: Key: Accept | Value: application/json
      
     Atualizacao "completa" de Usuário
      PUT
        localhost:8080/users/id
        Exemplo: localhost:8080/users/1
        Headers: Key: Accept | Value: application/json
        Body: Raw : JSON { 
               "nome": "Alysson Gabriel",
               "Data de Nascimento": "00/00/0000"
              }
    
     Editar de forma parcial um Usuário
      PATCH
        localhost:8080/users/id
        Exemplo: localhost:8080/users/1
        Headers: Key: Accept | Value: application/json
        Body: Raw : JSON { "Data de Nascimento": "01/04/1998" }
        
     Excluir Usuário
      DELETE
        localhost:8080/users/id
        Exemplo: localhost:8080/users/1
        Headers: Key: Accept | Value: application/json
    
     Adicionar Foto de Usuário
      POST
        localhost:8080/users/{ID}/foto
        Exemplo: localhost:8080/users/1/foto
        Headers: Key: Accept | Value: multipart/form-data
        Body: Form-data :
              Key: foto | value: imagem.png ou jpeg.
           
     Atualizar Foto de Usuário
      PUT
        localhost:8080/users/{ID}/foto
        Exemplo: localhost:8080/users/1/foto
        Headers: Key: Accept | Value: multipart/form-data
        Body: Form-data :
              Key: foto | value: imagem.png ou jpeg.
            
     Buscar Foto de Usuário
      GET
        localhost:8080/users/{ID}/foto
        Exemplo: localhost:8080/users/1/foto
        Headers: Key: Accept | Value: application/json || image/jpeg || image/png
       
     Excluir Foto de Usuário
      DELETE
        localhost:8080/users/{ID}/foto
        Exemplo: localhost:8080/users/1/foto
        Headers: Key: Accept | Value: application/json      
      
Software utilizado para testar os end-points : Postman e Navegador.

Testes unitários e documentação(Swagger) não desenvolvidos.
