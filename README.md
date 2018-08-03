Teste para a vaga de Back-End Developer
==========================

# Dependências
- Todas as dependências já estão definidas no pom.xml
# Servidor
- Apache Tomcat 8

# IDE
- NetBeans

# Framework Utilizado
- Hibernate

# Banco de Dados
- MySQL

Para funcionar o projeto é necessário que tenha um banco de dados criado com o nome xyinc
no MySQL

# Configurando banco de dados no projeto
```
- Vá ao diretoria META-INF
- Abra o arquivo 'context.xml' e altere as propriedades password, username e url.
- Após configurado o projeto pode ser iniciado, o hibernete cuidara da criação das tabelas e colunas do banco de dados
```

# Utilização do projeto
```
- O Projeto deve ser compilado e o deploy feito no tomcat
- Abra o navegador e acesse o endereço http://localhost:8084/yx-inc/ para verificar se o projeto foi implementado
```
# Utilizando os serviços criados
- Todos os testes realizados no ambiente de desenvolvimento foram feitos pela ferramenta Postman
```
- Para listar todos os POI's cadastrados no banco: GET http://localhost:8084/yx-inc/api/poi/list
- Para cadastrar um novo POI: POST http://localhost:8084/yx-inc/api/poi/add
- Para consultar POI's por proximidades: POST http://localhost:8084/yx-inc/api/poi/consulta
```
- Formato das requisições e formato das respostas.
```
- GET http://localhost:8084/yx-inc/api/poi/list
Retorno
[
  {
    "id":1,
    "poi":"Lanchonete",
    "coordenada_x":27,
    "coordenada_y":12
  },
  {
    "id":2,
    "poi":"Posto",
    "coordenada_x":31,
    "coordenada_y":18
  }
 ]

-POST http://localhost:8084/yx-inc/api/poi/add
  Formato do POST enviado
  {   
    "poi":"Posto",
    "coordenada_x":31,
    "coordenada_y":18
  }
  
  Retornos
  200 - Cadastro Realizado com sucesso
  500 - Arquivo fora dos padrões
  406 - Campos com valores inválidos
  
-  POST http://localhost:8084/yx-inc/api/poi/consulta
Requisição
{
  "d_max":10,
  "coordenada_x":10,
  "coordenada_y":20  
}

Retorno

[
    {
        "id": 1,
        "poi": "Lanchonete",
        "coordenada_x": 27,
        "coordenada_y": 12
    },
    {
        "id": 3,
        "poi": "Joalheria",
        "coordenada_x": 15,
        "coordenada_y": 12
    },
    {
        "id": 5,
        "poi": "Pub",
        "coordenada_x": 12,
        "coordenada_y": 8
    },
    {
        "id": 6,
        "poi": "Supermercado",
        "coordenada_x": 23,
        "coordenada_y": 6
    }
]
```

