# SmartParking – Gestão Inteligente de Estacionamento


SmartParking é uma plataforma inteligente para gerenciamento de estacionamentos, integrando sensores IoT, backend em **Java Spring Boot**, banco de dados PostgreSQL, WebSocket para atualizações em tempo real e um frontend simples para testes.


---


## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot 3 (Web, JPA, Security, WebSocket)
- JWT para autenticação
- PostgreSQL / H2
- Docker & Docker Compose
- MQTT (opcional para integração com sensores)
- STOMP + SockJS para tempo real


---


## 🧩 Funcionalidades
- Cadastro e gerenciamento de vagas
- Atualização de status via sensores (OCCUPIED/FREE)
- Reservas de vagas com lock transacional
- Notificações em tempo real para frontend
- API REST organizada


---


## 🛠️ Como Rodar


### ✔️ Rodando localmente
```bash
mvn clean package
java -jar target/smartparking-0.0.1-SNAPSHOT.jar
