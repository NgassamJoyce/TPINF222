# 📝 Blog Simple API

 API REST complète pour la gestion d'un blog , développée avec **Spring Boot 3**, **MySQL** et documentée avec **Swagger UI** en ce servant de la plateforme **CleeRoute**.

---

## 🚀 Aperçu

**Blog Simple** est une API RESTful qui permet de créer, lire, mettre à jour et supprimer des articles de blog. Elle inclut également des fonctionnalités avancées comme la recherche par mot-clé, le filtrage par catégorie et par plage de dates.

---

## 🛠️ Technologies utilisées

| Technologie | Version | Rôle |
|---|---|---|
| Java | 17+ | Langage principal |
| Spring Boot | 3.x | Framework backend |
| Spring Data JPA | 3.x | Accès base de données |
| MySQL | 8.x | Base de données |
| Lombok | Latest | Réduction du boilerplate |
| Swagger / OpenAPI | 3.x | Documentation de l'API |
| Maven | 3.x | Gestion des dépendances |

---


## ⚙️ Configuration

### Prérequis

- Java 17 ou supérieur
- MySQL 8.x installé et démarré
- Maven 3.x

### Base de données

Créer la base de données MySQL avant de lancer l'application :

### sql
CREATE DATABASE IF NOT EXISTS Mon_Blog
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;


### application.properties

spring.application.name=Blog_simple
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/Mon_Blog?useSSL=false&serverTimezone=Africa/Douala&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.jdbc.time_zone=Africa/Douala
spring.jpa.properties.hibernate.format_sql=true


 **Note :** Modifiez spring.datasource.username et spring.datasource.password selon votre configuration MySQL locale.



## ▶️ Lancer le projet

# 1. Cloner le dépôt
git clone https://github.com/NgassamJoyce/TPINF222.git
cd blog-simple

# 2. Compiler et lancer
mvn spring-boot:run

L'application démarre sur **http://localhost:8080**



## 📡 Endpoints de l'API

### Base URL : `http://localhost:8080/api/article`

| Méthode | Endpoint | Description |
|---|---|---|
| `GET` | `/api/article` | Récupérer tous les articles |
| `GET` | `/api/article/{id}` | Récupérer un article par ID |
| `POST` | `/api/article` | Créer un nouvel article |
| `PUT` | `/api/article/{id}` | Modifier un article |
| `DELETE` | `/api/article/{id}` | Supprimer un article |
| `GET` | `/api/article/search?keyword=xxx` | Recherche par mot-clé (titre ou catégorie) |
| `GET` | `/api/article/category/{category}` | Filtrer par catégorie |
| `GET` | `/api/article/date?from=...&to=...` | Filtrer par plage de dates |



## 📨 Exemples de requêtes

### Créer un article

*** http
POST /api/article
Content-Type: application/json

{
  "title": "Mon premier article",
  "contain": "Contenu de l'article...",
  "autor": "Joyce",
  "category": "Technologie",
  "tags": "spring,java,api"
}


**Réponse 201 Created :**

*** json
{
  "id": 1,
  "title": "Mon premier article",
  "contain": "Contenu de l'article...",
  "autor": "Joyce",
  "createdAt": "2024-03-24T14:30:00",
  "category": "Technologie",
  "tags": "spring,java,api"
}


### Recherche par mot-clé

*** http
GET /api/article/search?keyword=spring


### Filtrer par date

*** http
GET /api/article/date?from=2024-01-01T00:00:00&to=2024-12-31T23:59:59


---

## 📋 Codes de réponse HTTP

| Code | Signification |
|---|---|
| 200 OK | Requête réussie |
| 201 Created | Article créé avec succès |
| 204 No Content | Article supprimé avec succès |
| 404 Not Found | Article introuvable |
| 409 Conflict | Un article avec ce titre existe déjà |



## 📖 Documentation Swagger

Une fois l'application lancée, la documentation interactive est disponible à :


http://localhost:8080/swagger-ui/index.html




## ✅ Validations

| Champ | Règle |
|---|---|
| title | Obligatoire, entre 5 et 100 caractères |
| contain | Optionnel, max 1000 caractères |
| autor | Optionnel, max 50 caractères |
| category | Obligatoire, max 100 caractères |
| ** tags | Obligatoire |





## 📄 Licence

Ce projet est développé dans le cadre d'un apprentissage personnel avec la plateforme **Cleeroute**.



