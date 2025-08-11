📝 Desafio Técnico: API Gerenciamento de Clientes
-
---
## Objetivo
Esta é uma API REST desenvolvida com Spring Boot para gerenciamento de clientes por usuário. O acesso aos recursos é protegido por um mecanismo de autenticação via filters, garantindo que apenas usuários autenticados possam interagir com os endpoints. O projeto utiliza Java 21, possui tratamento de exceções personalizadas, geração de logs estruturados e está preparado para fácil integração com front‑ends ou outros serviços.

---

Principais Endpoints 
---


``POST``  


Cadastro de clientes com integração da [API ViaCep](https://viacep.com.br/ws/01310-930/json "ViaCep") 


``GET`` 

Retorna uma lista de todos os clientes cadastrado pelo usuário;


``PUT /id`` 

Atualiza as informações do cliente;


``GET /id`` 

Retorna um cliente específico;


``GET /categoriaId`` 

Retorna uma lista de clientes cadastrados em uma categoria específica;

---

# ✨ Funcionalidades
- ✅ **Autenticação de usuários**
- ✅ **Criação, listagem, atualização e exclusão de clientes**
- ✅ **Autorização por usuário (cada um gerencia apenas seus clientes)**
- ✅ **Filtros personalizados para autenticação**
- ✅ **Tratamento de exceções customizado (com mensagens amigáveis no padrão JSON)**
- ✅ **Código pronto para extensões futuras (ex.: auditoria, multi‑tenant, etc.)**

---
## 🚀 Tecnologias utilizadas

- ☕ **Java 21**
- 🌱 **Spring Boot (Spring Web, Spring Boot DevTools, Spring Data JPA)**
- 🗄️ **Banco de dados relacional (PostgreSQL)**
- 🔑 **JWT para autenticação**
- 🧪 **JUnit 5 e Mockito para testes unitários**
- 📦 **Maven para gerenciamento de dependências**
- 💻 **Postman para testes manuais** 
- ⚙️ **Utilização do Docker (Dockerfile) para rodar a aplicação** 