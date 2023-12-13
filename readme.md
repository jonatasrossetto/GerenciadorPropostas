# Sistema de emissão e controle de propostas comerciais

Sistema desenvolvido para acompanhar o ciclo de vida de propostas comerciais

## Stack:

### Back-end

- IDE Eclipse >2023-09
- Maven
- Java 17 + Spring Boot 3.1.5
- flyway para migration
- MySql com o Workbench 8.0
- JWT
- Postman
- Docker

### Front-end

- Node.js 20.9.0
- Angular 16.2.9
- Bootstrap
- TypeScript
- Html / Css

### Instruções para gerar os containers do Backend

    Containers separados para a API e o banco de dados

- Criar o arquivo jar do backend
  > #no diretório api <br>
  > mvnw clean package
- baixar a imagem Docker do mysql:
  > docker pull mysql:latest
- criar a imagem Docker para o backend a partir de um dockerfile
  > #no diretório api/target criar o arquivo dockerfile. <br>
  > FROM eclipse-temurin:17-jdk-alpine <br>
  > COPY \*.jar api-0.0.1-SNAPSHOT.jar <br>
  > ENTRYPOINT ["java","-jar","api-0.0.1-SNAPSHOT.jar"] <br>
- criar o container do banco de dados
  - o nome do container será mysqldb
  - a porta exposta será a 3307 para acessar com o DBeaver
  - será criada a network spring-net para a comunicação entre os containers
  - será criado um volume dbvolume para a persistência dos dados
    > docker run -d -p 3307:3306 --net spring-net -v dbvolume:/var/lib/mysql --name mysqldb -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=propostas_api mysql
- criar o container da aplicação api

  - o nome do container será app
  - a porta exposta será a 8080 (no postman utilizar localhost:8080)
    > docker run -d -p 8080:8080 --net spring-net --name app -e DB_SERVER=mysqldb -e DB_PORT=3306 app

- para verificar os containers em execução
  > docker ps
- para verificar os containers existentes
  > docker ps -a
- para remover um container
  > docker rm mysqldb <br>
  > docker rm app
