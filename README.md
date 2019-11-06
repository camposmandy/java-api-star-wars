# Star Wars API (Java + Spring Boot + Postgres<Docker>)

Version: 0.0.1-SNAPSHOT

Author: Amanda Guimarães Campos

## Razão;
  Fornecer uma API que contenha os dados dos planetas para criar um jogo com algumas informações da franquia.

## Pré Requisitos;
  - JDK 8+
  - Postgres
  - Docker
  - Sqlectron (Opcional. Utilizando para visualiza informações no banco de dados)
  - Disponibilizar portas 8080 (Server Port) e 5432 (Database Port)
  
## Docker stepup & commands;
  É necessário verificar se sua máquina possui a imagem do banco de dados Postgres instalada. Para realizar essa
  verificação utilize o comando `docker image ls` no seu terminal. Ele ira listar todas as imagens instaladas.
  
  Caso você não possua a imagem, basta rodar o comando `docker pull postgres` no terminal que ela será baixada em
  sua última versão estável.
  
  Agora basta iniciar o container para se utilizar o banco com o seguinte comando :
  
  `docker run --rm   --name pg-starwars -d -p 5432:5432 postgres`

  (para verificar os containers que já estão inicializados: `docker ps -a`
   para parar um container `docker stop my_container`)


   #### Informações Adicionais
   Para iniciar os comandos psql no terminal execute o comando `docker exec -tiu postgres pg-starwars psql` após 
   a inicialização do container. Abaixo, lista de queries básicas.
  
   |Função              |Comando                |
   |--------------------|-----------------------|
   |Listar todas tabelas| \dt                   |
   |Listar uma tabela   | SELECT * FROM dt_name;|
   
 ## SQLectron
 baixar no site oficial (https://sqlectron.github.io/) e configurar o banco com as seguintes informações possibilitará você de visualizar o banco
 de uma forma mais visual.
  
 ```$xslt
name: pg-starwars
database type: PostgreSQL
server address: localhost, port: 5432
user: postgres
initial database/keyspace: postgres
```

## Run Application (opções)
    1. Abrir o projeto através de uma IDEA e executar a classe principal (StarWarsPlanetsApplication.class)
    2. Executar o comando `mvn clean package` para gerar um jar e executa-lo pelo comando  
    `java -jar star-wars-planets-0.0.1-SNAPSHOT.jar`
    
## Endpoints

A API desenvolvida é composta por 5 métodos, são eles: 
  - Criar
    - Insere um novo planeta na base do Postgres desde que o nome do planeta seja equivalente há um planeta existente na 
    API do Star Wars (https://swapi.co/api/).      
  - Buscar por Id
    - Procura na base do Postgres um planeta com o mesmo id. Caso encontre, retorna todas as informações referentes a ele.
  - Buscar por nome
      - Procura na base do Postgres um planeta com o mesmo nome. Caso encontre, retorna todas as informações referentes a ele.
  - Listar todos
    - Retorna um array com todos os planetas na base do Postgres.
  - Deletar por Id
    - Procura na base do Postgres um planeta com o mesmo Id. Caso encontre, deleta o mesmo da base.


Todos os métodos possuem tratativas de erro, podendo retornar no seguinte modelo:
```
{
    "status": 404,
    "message": "PLANET NOT FOUND",
    "path": "http://localhost:8080/planets/1",
    "timestap": 1566086368721
}
```

#### URL's

`BASE = http://localhost:8080/planets`

|API            |MÉTODO         |URL                   |
|---------------|---------------|----------------------|
|Criar          |POST           |BASE                  |
|Buscar Por Id  |GET            |BASE + /{id}          |
|Buscar Por Nome|GET            |BASE + ?name={nome}   |
|Listar Todos   |GET            |BASE + /list          |
|Deletar Por Id |DELETE         |BASE + /{id}          |


### Exemplos (BODY + RESPONSE)
Também disponível em http://localhost:8080/swagger-ui.html.

##### 1. CRIAR

###### body
```
  {
    "name": "tatooine",
    "climate": "Polar",
    "terrain": "vermelho"
  }
 ```
  
###### response
Os atributos `id` e `movieAppearances` são gerados automáticamente. Se forem enviados na requisição serão substituídos.

  ```
  {
      "id": 1,
      "name": "tatooine",
      "climate": "Polar",
      "terrain": "vermelho",
      "movieAppearances": 5
   }
   ```
   
##### 2. BUSCAR POR ID

###### body
não se aplica.
  
###### response
  ```
  {
      "id": 1,
      "name": "tatooine",
      "climate": "Polar",
      "terrain": "vermelho",
      "movieAppearances": 5
   }
   ```
   
##### 3. BUSCAR POR NOME

###### body
não se aplica.
  
###### response
  ```
  {
      "id": 1,
      "name": "tatooine",
      "climate": "Polar",
      "terrain": "vermelho",
      "movieAppearances": 5
   }
   ```
   
##### 4. LISTAR TODOS

###### body
não se aplica.
  
###### response
```
  [
    {
        "id": 1,
        "name": "tatooine",
        "climate": "Polar",
        "terrain": "vermelho",
        "movieAppearances": 5
    }
  ]
  ```
  
##### 5. DELETAR POR ID

###### body
não se aplica.
  
###### response
não se aplica.
