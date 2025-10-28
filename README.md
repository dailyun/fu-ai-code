# Fu AI Code

## 项目简介
Fu AI Code 是一个围绕 AI 辅助生成应用代码的全栈项目，后端基于 Spring Boot、MyBatis-Flex 与 LangChain4j 构建，支持多种代码生成模式（HTML、单 / 多文件、Vue 项目）并提供流式输出能力，方便在前端实时呈现生成结果。【F:src/main/java/com/dali/fuaicode/ai/AiCodeGeneratorService.java†L12-L53】

系统内置应用管理、部署与代码下载、会话历史等能力，并结合鉴权、限流、会话态管理等机制，保障多用户场景下的安全性与可用性。【F:src/main/java/com/dali/fuaicode/controller/AppController.java†L320-L434】用户模块提供注册、登录、登出以及管理员专属的增删改查接口，便于进行多角色协作。【F:src/main/java/com/dali/fuaicode/controller/UserController.java†L43-L165】

## 功能亮点
- **AI 代码生成流水线**：通过 LangChain4j 封装多种系统 Prompt，支持一次生成完整项目或按需流式输出片段，便于实时预览与编辑。【F:src/main/java/com/dali/fuaicode/ai/AiCodeGeneratorService.java†L12-L53】
- **应用全生命周期管理**：内置应用分页、详情、部署和代码打包下载接口，并对敏感操作进行登录校验与权限控制。【F:src/main/java/com/dali/fuaicode/controller/AppController.java†L320-L434】
- **实时对话与限流保护**：`/app/chat/gen/code` 端点以 Server-Sent Events 推送生成内容，并对同一用户设定速率限制，防止滥用。【F:src/main/java/com/dali/fuaicode/controller/AppController.java†L352-L381】
- **多渠道素材生成**：集成 Pexels 图片搜索与阿里云 DashScope Logo 生成工具，辅助构建更完整的应用素材。【F:src/main/java/com/dali/fuaicode/langgraph4j/tools/ImageSearchTool.java†L23-L55】【F:src/main/java/com/dali/fuaicode/langgraph4j/tools/LogoGeneratorTool.java†L23-L60】

## 技术栈
- **后端**：Spring Boot 3、Spring MVC、LangChain4j、MyBatis-Flex、Redis Session、Caffeine、Redisson、Knife4j、Selenium 等。【F:pom.xml†L32-L168】
- **前端**：Vue 3、Vite、Pinia、Vue Router、Ant Design Vue、TypeScript、ESLint / Prettier。【F:fufu-ai-code-frontend/package.json†L6-L44】
- **数据库**：MySQL 作为主数据存储，提供完整建表脚本，Redis 用于 Session 与缓存管理。【F:create_table.sql†L2-L67】【F:src/main/resources/application.yml†L1-L52】

## 环境要求
在本地运行项目前，请确保安装以下依赖：
- JDK 21 及以上（项目 `java.version` 为 21）。【F:pom.xml†L29-L31】
- Maven 或使用仓库自带的 `mvnw` 脚本。
- Node.js 18+ 与 npm，用于启动前端 Vite 应用。【F:fufu-ai-code-frontend/package.json†L6-L44】
- MySQL 8+，并创建 `fufu_ai_code` 库后执行建表脚本。【F:create_table.sql†L2-L67】
- Redis（默认连接 `localhost:6379`，数据库索引 0）。【F:src/main/resources/application.yml†L17-L24】

## 快速开始
1. **配置数据库与缓存**
   - 创建数据库并执行根目录下的 `create_table.sql`。【F:create_table.sql†L2-L67】
   - 按需修改 `src/main/resources/application.yml` 中的 MySQL 与 Redis 连接信息。【F:src/main/resources/application.yml†L1-L32】

2. **配置 AI 与外部服务凭据（可选但推荐）**
   - 在环境变量或额外的配置文件中设置 OpenAI 兼容接口的 `langchain4j.open-ai.*` 参数，用于标准对话与推理模型。【F:src/main/java/com/dali/fuaicode/config/StreamingChatModelConfig.java†L16-L41】【F:src/main/java/com/dali/fuaicode/config/RoutingAiModelConfig.java†L12-L45】【F:src/main/java/com/dali/fuaicode/config/ReasoningStreamingChatModelConfig.java†L16-L49】
   - 配置 `pexels.api-key` 以启用图片搜索工具。【F:src/main/java/com/dali/fuaicode/langgraph4j/tools/ImageSearchTool.java†L25-L55】
   - 配置 `dashscope.api-key` 与 `dashscope.image-model`，开启 Logo 生成功能。【F:src/main/java/com/dali/fuaicode/langgraph4j/tools/LogoGeneratorTool.java†L23-L56】

3. **启动后端服务**
   ```bash
   cd fu-ai-code
   ./mvnw spring-boot:run
   ```
   默认服务监听 `http://localhost:48888/api`，会话信息存储在 Redis 中。【F:src/main/resources/application.yml†L17-L32】

4. **启动前端界面**
   ```bash
   cd fufu-ai-code-frontend
   npm install
   npm run dev
   ```
   前端将通过 Vite 启动开发服务器，可在 `.env` 或代码中调整后端接口地址。【F:fufu-ai-code-frontend/package.json†L6-L14】

5. **可选：运行后端测试**
   ```bash
   cd fu-ai-code
   ./mvnw test
   ```

## 常用命令速查
- `./mvnw spring-boot:run`：启动后端。
- `./mvnw test`：执行后端测试。
- `npm run dev` / `npm run build` / `npm run preview`：前端开发、构建与预览流程。【F:fufu-ai-code-frontend/package.json†L6-L14】
- `npm run lint` / `npm run format`：前端代码质量与格式化工具。【F:fufu-ai-code-frontend/package.json†L6-L14】

## 访问接口与监控
- 业务接口统一挂载在 `/api` 前缀下，示例：`GET /api/app/chat/gen/code` 使用 SSE 推送生成内容。【F:src/main/resources/application.yml†L25-L32】【F:src/main/java/com/dali/fuaicode/controller/AppController.java†L352-L381】
- 集成 SpringDoc + Knife4j，可在本地运行后访问 `http://localhost:48888/api/doc.html` 查看接口文档（需根据实际部署调整）。【F:src/main/resources/application.yml†L34-L43】
- 暴露了健康检查与 Prometheus 指标端点，可对接运维监控平台。【F:src/main/resources/application.yml†L44-L51】

