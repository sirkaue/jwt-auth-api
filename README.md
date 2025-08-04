# 🔐 JWT Auth API - Stateless Authentication com Spring Security

Uma API demonstrativa com **autenticação stateless baseada em JWT**, utilizando **Spring Security**.  
Ideal para quem busca entender como proteger endpoints de forma moderna, simples e segura em aplicações Java.

---

## 🎯 Objetivo

Esta API foi construída com o propósito de demonstrar, de forma clara e funcional:

- Como implementar **autenticação stateless** usando **JWT**
- Como estruturar endpoints protegidos com **Spring Security**
- Como utilizar um **refresh token** para renovar o access token
- Como criar um fluxo completo de login, registro e autenticação em Java

---

## 🔒 Como Funciona a Autenticação

- O usuário se autentica via `/api/auth/login` e recebe um **Access Token** e um **Refresh Token**
- O **Access Token** é usado no header `Authorization` para acessar endpoints protegidos
- Quando o access token expira, o usuário pode obter um novo via `/api/auth/refresh-token`
- A API não mantém estado do usuário no servidor (stateless) — todo controle é feito via tokens

> ⚠️ Nenhum `HttpSession` é criado. A segurança é totalmente baseada em tokens válidos e assinados.

---

## 🔐 Principais Endpoints

| Método | Rota                        | Acesso     | Descrição                                         |
|--------|-----------------------------|------------|--------------------------------------------------|
| POST   | `/api/auth/register`        | Público    | Registra um novo usuário                         |
| POST   | `/api/auth/login`           | Público    | Autentica usuário e retorna tokens               |
| POST   | `/api/auth/refresh-token`   | Público    | Gera novo access token a partir do refresh token |
| GET    | `/api/users/me`             | Protegido  | Retorna os dados do usuário autenticado          |

> Envie o token no header:  
> `Authorization: Bearer <access_token>`

---

## 🧾 Exemplo de JWT (payload)

```json
{
  "iss": "jwt-auth-api",
  "sub": "1",
  "aud": "jwt-auth-client",
  "name": "Kauê Dev",
  "email": "kaue@example.com",
  "iat": 1754345443,
  "exp": 1754346343
}
```

---

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot**
- **Spring Security (JWT Resource Server)**
- **Spring Web**
- **JPA / Hibernate**
- **Lombok**
- **H2 Database (memória)**

---

## 📦 Como Executar

```bash
git clone https://github.com/sirkaue/jwt-auth-api.git
cd jwt-auth-api
./mvnw spring-boot:run
```

## 🐳 Como Executar com Docker


1. Navegue até a pasta raiz do projeto:

```sh
cd jwt-auth-api/
```

2. Construa a imagem Docker:

```sh
docker build -t auth-service:dev .
```

3. Execute o container:

```sh
docker run -d --name jwt-auth-api -p 8080:8080 auth-service:dev
```

Após executar os comandos acima, a API estará disponível em `http://localhost:8080`.
