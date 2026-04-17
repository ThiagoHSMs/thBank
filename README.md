ThBank - Wallet Management System 💰
O ThBank é uma aplicação Full-Stack desenvolvida para a gestão de finanças pessoais. O sistema permite a visualização consolidada de saldos, monitorização de limite de cartão de crédito e histórico detalhado de transações financeiras.

🚀 Tecnologias Utilizadas
Backend
Java 17 com Spring Boot 3

Spring Data JPA: Persistência de dados e consultas customizadas.

Spring Security: Configuração de segurança e CORS.

PostgreSQL: Banco de dados relacional.

Lombok: Redução de código boilerplate.

Jackson: Serialização/deserialização de objetos JSON.

Frontend
Angular 19+: Framework para construção da interface SPA.

RxJS: Programação reativa para gestão de estados e chamadas assíncronas.

TypeScript: Tipagem estática para maior segurança no desenvolvimento.

CSS3: Design responsivo com foco em UX financeira.

🛠️ Desafios Técnicos Resolvidos
Durante o desenvolvimento, foram aplicadas soluções para problemas comuns em sistemas empresariais:

Gestão de Proxies Hibernate: Resolução de erros de definição de tipo (ByteBuddyInterceptor) na serialização de entidades Lazy-Loading.

Consolidação de Dados (DTOs): Criação de um DashboardService capaz de calcular saldos, despesas mensais e agrupamentos por categoria em tempo de execução.

Integridade de Dados: Implementação de filtros em Java Streams para evitar chaves nulas na geração de mapas de gastos por categoria.

SSR Compatibility: Adaptação do frontend Angular para lidar com Server-Side Rendering (SSR), tratando acessos ao localStorage e outros objetos do navegador.

📸 Funcionalidades
✅ Resumo Financeiro: Visualização instantânea do saldo total em conta.

✅ Gestão de Crédito: Monitorização de gastos em tempo real com barra de progresso em relação ao limite total.

✅ Extrato Inteligente: Histórico de transações com diferenciação visual entre receitas (+) e despesas (-) utilizando feedback visual de cores.

✅ Categorização: Agrupamento automático de transações por tipo (Alimentação, Lazer, Geral, etc.).

🔧 Como Executar o Projeto
Pré-requisitos
JDK 17+

Node.js & Angular CLI

PostgreSQL

1. Backend (Spring Boot)
Clone o repositório.

Configure o application.properties com as suas credenciais do PostgreSQL.

Execute ./mvnw spring-boot:run.

2. Frontend (Angular)
Navegue até a pasta wallet-frontend.

Instale as dependências: npm install.

Inicie o servidor: ng serve.

Aceda a http://localhost:4200.

👤 Autor
Thiago Henrique Santos Morais

LinkedIn: https://www.linkedin.com/in/thiago-henrique-6799b9163/

