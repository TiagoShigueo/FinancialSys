# Etapa 1: Build do projeto
FROM node:18 AS builder

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# Etapa 2: Executar a aplicação com next start
FROM node:18-alpine AS runner

WORKDIR /app

COPY --from=builder /app ./

# Só instala dependências de produção
RUN npm ci --only=production

EXPOSE 3000

CMD ["npm", "start"]
