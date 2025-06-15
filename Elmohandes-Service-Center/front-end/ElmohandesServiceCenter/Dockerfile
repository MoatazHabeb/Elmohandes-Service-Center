FROM node:16
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 7700
CMD ["npm", "start", "--", "--host", "0.0.0.0", "--poll", "1000"]
