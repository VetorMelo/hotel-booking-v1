
![Scala](https://img.shields.io/badge/Scala-3.3.5-red)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue)
![MIT License](https://img.shields.io/badge/License-MIT-green)

📌 README.md - Hotel Booking System

# 🏨 Hotel Booking System

Este é um sistema de reservas de hotel desenvolvido em **Scala** e **PostgreSQL**. Ele permite a criação de quartos, gerenciamento de reservas e verificação de disponibilidade por meio de um banco de dados relacional.

---

## 📌 Tecnologias Utilizadas

- **Scala 2.13.6**
- **PostgreSQL**
- **SBT (Scala Build Tool)**
- **JDBC (Java Database Connectivity)**
- **Akka HTTP**

---

## ⚙️ Instalação e Configuração

1️⃣ Pré-requisitos
Antes de começar, você precisa ter instalados:
- **PostgreSQL**: [Baixar aqui](https://www.postgresql.org/download/)
- **SBT**: [Baixar aqui](https://www.scala-sbt.org/download.html)
- **IntelliJ IDEA (opcional, recomendado)**: [Baixar aqui](https://www.jetbrains.com/idea/download/)

---

2️⃣ Clonar o Repositório
bash
git clone https://github.com/seu-usuario/hotel-booking.git
cd hotel-booking

---

3️⃣ Criar o Banco de Dados <br>
Abra o PostgreSQL e execute:<br><br>

CREATE DATABASE hotel_booking;<br>
psql -U postgres -d hotel_booking<br>

CREATE TABLE rooms (<br>
    id SERIAL PRIMARY KEY,<br>
    capacity INT NOT NULL,<br>
    status VARCHAR(20) NOT NULL<br>
);<br><br>

CREATE TABLE reservations (<br>
    id SERIAL PRIMARY KEY,<br>
    room_id INT REFERENCES rooms(id),<br>
    guest_name VARCHAR(100) NOT NULL,<br>
    start_date DATE NOT NULL,<br>
    end_date DATE NOT NULL<br>
);<br><br>

---

4️⃣ Configuração do Projeto <br><br>
Abra o projeto no IntelliJ IDEA ou outro editor de sua preferência. <br>
No terminal do projeto, execute: <br>
sbt update <br><br>

object Database { <br>
  val url = "jdbc:postgresql://localhost:5432/hotel_booking" <br>
  val username = "postgres" <br>
  val password = "sua_senha" <br><br>

  def getConnection(): Connection = { <br>
    DriverManager.getConnection(url, username, password) <br>
  } <br>
} <br><br>

⚠️ **Aviso**: Certifique-se de que o PostgreSQL está rodando antes de executar o projeto.

---

🚀 Como Executar o Projeto <br>
✅ Para compilar e rodar o projeto, execute:
sbt compile
sbt run

Se estiver utilizando IntelliJ IDEA, clique com o botão direito no arquivo Main.scala e selecione Run 'Main'.

📌 Funcionalidades <br><br>
✅ Adicionar quartos ao sistema <br>
✅ Criar reservas para hóspedes <br>
✅ Verificar disponibilidade de quartos <br>

🛠 Possíveis Erros e Soluções <br><br>
❌ "No suitable driver found for jdbc:postgresql" <br>
💡 Solução: Verifique se a dependência do PostgreSQL está instalada corretamente. Rode: <br>
sbt update
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.20"

📜 Licença <br><br>
Este projeto está sob a MIT License - sinta-se livre para contribuir e melhorar! 🚀

---

🏛 Banco de Dados Relacional (PostgreSQL) - Escolha Principal <br><br>
O PostgreSQL, que foi escolhido no projeto, é um banco de dados relacional. Isso significa que os dados são organizados em tabelas estruturadas com chaves primárias e estrangeiras, garantindo integridade e consistência.<br><br>

✅ Justificativas para a Escolha<br>
1️⃣ Relações bem definidas 📊<br><br>

O sistema lida com relacionamentos claros entre entidades, como quartos e reservas.<br>
Exemplo: Cada reserva está associada a um quarto específico (chave estrangeira room_id).<br>
2️⃣ Garantia de Integridade dos Dados 🔐<br><br>

O PostgreSQL permite definir restrições (PRIMARY KEY, FOREIGN KEY, NOT NULL), impedindo registros inconsistentes.<br>
Isso evita, por exemplo, que uma reserva seja feita para um quarto inexistente.<br>
3️⃣ Facilidade de consultas complexas 🔍<br><br>

Usamos SQL para buscar reservas por data, verificar disponibilidade de quartos e gerar relatórios.<br>
Exemplo de consulta:<br>
sql<br>
Copiar<br>
Editar<br>
SELECT * FROM reservations WHERE room_id = 1 AND start_date <= '2025-03-10' AND end_date >= '2025-03-05';<br>
Isso seria muito mais complexo de implementar em um banco NoSQL.<br><br>
4️⃣ Escalabilidade Vertical e Suporte a Transações 🔄<br><br>

O PostgreSQL permite transações ACID, garantindo que uma reserva não seja confirmada se ocorrer um erro no sistema.<br>
📦 Banco de Dados Chave/Valor (Redis, DynamoDB)<br>
Os bancos chave/valor armazenam dados de forma simples, associando um identificador único a um valor.<br><br>

✅ Vantagens:<br><br>

Rápido para consultas simples.<br>
Bom para cache (exemplo: armazenar sessões de usuários).<br>
❌ Por que NÃO usar no projeto?<br><br>

O sistema precisa de relacionamentos estruturados entre quartos e reservas.<br>
Consultas complexas, como verificar disponibilidade de um quarto, seriam difíceis de implementar.<br><br>
📌 Quando usar?<br><br>

Para armazenar sessões de usuários ou cachear dados acessados frequentemente.<br>
📜 Banco de Dados Documental (MongoDB, Firebase Firestore)<br>
Os bancos NoSQL documentais armazenam dados em formato JSON ou BSON, permitindo maior flexibilidade.<br><br>

✅ Vantagens:<br><br>

Estrutura flexível (não precisa de esquemas fixos).<br>
Escalabilidade horizontal eficiente.<br>
❌ Por que NÃO usar no projeto?<br>

O sistema de reservas precisa de relações entre entidades, e bancos documentais não oferecem joins nativos.<br>
A validação de dados é menos rigorosa do que em bancos relacionais.<br><br>
📌 Quando usar?<br><br>

Se o sistema fosse baseado em busca flexível de informações, como um marketplace de hotéis com múltiplos filtros dinâmicos.<br><br>
🎯 Conclusão: Escolha do Banco de Dados<br><br>

Relacional (PostgreSQL)<br>
Estruturado, suporte a SQL, transações ACID<br>
✅ Melhor escolha para um sistema de reservas devido às relações entre entidades<br>

---

Chave/Valor (Redis, DynamoDB)<br>
Alta velocidade, ótimo para cache<br>
❌ Não adequado para relações entre dados<br>

---
Documental (MongoDB, Firestore)<br>
Estrutura flexível, fácil escalabilidade<br>
❌ Não ideal para manter a integridade referencial<br>

---


✅ Escolha final: PostgreSQL<br>
Devido à necessidade de integridade referencial, consultas SQL e transações confiáveis, o PostgreSQL é a melhor escolha para esse sistema de reservas de hotel. 🚀<br><br>

👨‍💻 Autor <br>
Victor Melo<br>
Desenvolvedor FullStack | Backend | DBA










