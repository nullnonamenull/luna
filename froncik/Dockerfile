# 1. Wybierz bazowy obraz z Node.js do budowania projektu
FROM node:18 as build

# 2. Ustaw katalog roboczy
WORKDIR /app

# 3. Skopiuj pliki projektu do kontenera
COPY package.json package-lock.json ./
RUN npm install
COPY . .

# 4. Zbuduj aplikację React
RUN npm run build

# 5. Wybierz bazowy obraz do serwowania aplikacji
FROM nginx:1.23

# 6. Skopiuj pliki builda do katalogu, z którego Nginx obsługuje pliki
COPY --from=build /app/build /usr/share/nginx/html

# 7. Exponuj port 80
EXPOSE 80

# 8. Start serwera Nginx
CMD ["nginx", "-g", "daemon off;"]