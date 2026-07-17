# Pantry Buddy - Smart Kitchen Inventory Management System

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-6DB33F?style=flat&logo=springboot&logoColor=white)
![React](https://img.shields.io/badge/React-19-61DAFB?style=flat&logo=react&logoColor=black)
![Vite](https://img.shields.io/badge/Vite-8-646CFF?style=flat&logo=vite&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat&logo=mysql&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-yellow)
[![Live Demo](https://img.shields.io/badge/Live_Demo-Vercel-000000?style=flat&logo=vercel&logoColor=white)](https://pantrybuddy-ni8s4lvab-silvesterdev.vercel.app)

A full-stack web application that helps households manage their kitchen pantry, track food expiry dates, and reduce food waste.

## Live Demo

**Frontend:** [https://pantrybuddy-ni8s4lvab-silvesterdev.vercel.app](https://pantrybuddy-ni8s4lvab-silvesterdev.vercel.app)

**Backend API:** [https://pantrybuddy-production.up.railway.app/api](https://pantrybuddy-production.up.railway.app/api)

---

## Problem Statement

Many households struggle with:
- Buying duplicate grocery items
- Forgetting available ingredients
- Food expiring unnoticed
- Difficulty tracking pantry inventory
- Wasting money due to expired food

**Pantry Buddy** solves these problems by digitizing the pantry and providing smart inventory management.

---

## Features

| Feature | Description |
|---------|-------------|
| **Pantry Management** | Add, edit, delete, and search pantry items |
| **Expiry Tracking** | Track expiry dates with visual alerts |
| **Low Stock Alerts** | Automatic alerts when quantity falls below minimum |
| **OCR Scanner** | Upload grocery bills/receipts to auto-detect items using Tesseract OCR |
| **Analytics Dashboard** | Charts showing category distribution, expiry timeline, low stock |
| **Recipe Suggestions** | Get recipe ideas based on available ingredients |
| **Category Organization** | Organize by Vegetables, Fruits, Dairy, Beverages, etc. |

---

## Tech Stack

### Backend
| Technology | Purpose |
|------------|---------|
| Java 17 | Core programming language |
| Spring Boot 4.0.5 | Application framework |
| Spring MVC | REST API development |
| Spring Data JPA | Database ORM |
| Hibernate | Entity management |
| Tess4J 5.11.0 | OCR for receipt scanning |
| Lombok | Boilerplate code reduction |
| Maven | Build tool |

### Frontend
| Technology | Purpose |
|------------|---------|
| React 19 | UI library |
| Vite 8 | Build tool & dev server |
| React Router DOM 7 | Client-side routing |
| Axios | HTTP client |
| Recharts / Chart.js | Data visualization |
| CSS3 | Styling |

### Database
| Technology | Purpose |
|------------|---------|
| MySQL 8.0 | Relational database |

---

## Project Structure

```
pantrybuddy/
├── backend/                          # Spring Boot backend
│   └── src/main/java/com/example/pantrybuddy/
│       ├── PantrybuddyApplication.java
│       ├── controller/
│       │   ├── ItemController.java           # CRUD for items
│       │   ├── DashboardController.java      # Dashboard stats
│       │   ├── AuthController.java           # Authentication
│       │   ├── RecipeController.java         # Recipe suggestions
│       │   ├── ReceiptUploadController.java  # OCR upload
│       │   └── ImageUploadController.java    # Image handling
│       ├── model/
│       │   ├── FoodItem.java                 # Pantry item entity
│       │   └── User.java                     # User entity
│       ├── repository/
│       │   ├── ItemRepository.java
│       │   └── UserRepository.java
│       ├── service/
│       │   ├── ExpiryService.java
│       │   ├── UserService.java
│       │   └── ai/OCRService.java            # Tesseract OCR
│       └── dto/
│           └── DashboardResponse.java
│
├── frontend/                         # React frontend
│   └── src/
│       ├── main.jsx                         # Entry point
│       ├── App.jsx                          # Routes & layout
│       ├── components/
│       │   ├── Sidebar.jsx                  # Navigation sidebar
│       │   └── CategoryChart.jsx            # Pie chart component
│       ├── pages/
│       │   ├── Dashboard.jsx                # Main dashboard
│       │   ├── PantryItems.jsx              # Item list view
│       │   ├── AddItem.jsx                  # Add new item form
│       │   ├── ExpiringSoon.jsx             # Expiry alerts
│       │   ├── LowStock.jsx                 # Low stock view
│       │   ├── UploadReceipt.jsx            # OCR upload
│       │   └── Recipes.jsx                  # Recipe suggestions
│       ├── services/
│       │   └── api.js                       # Axios config
│       └── Styles/
│           ├── Dashboard.css
│           └── Navbar.css
│
└── README.md
```

---

## REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/items` | Get all pantry items |
| GET | `/api/items/{id}` | Get item by ID |
| POST | `/api/items` | Add new item |
| PUT | `/api/items/{id}` | Update an item |
| DELETE | `/api/items/{id}` | Delete an item |
| PATCH | `/api/items/{id}/quantity` | Update item quantity |
| GET | `/api/items/expiring-soon` | Items expiring within 4 days |
| GET | `/api/items/low-stock` | Low stock items |
| POST | `/api/ocr/upload` | Upload receipt for OCR |
| GET | `/api/recipes` | Get recipe suggestions |

---

## Prerequisites

- **Java 17+**
- **Node.js 18+** and npm
- **MySQL 8.0**
- **Maven 3.6+**
- **Tesseract OCR** (for receipt scanning feature)

---

## Installation

### 1. Clone the repository

```bash
git clone https://github.com/Silvester20041/pantrybuddy.git
cd pantrybuddy
```

### 2. Database Setup

```sql
CREATE DATABASE pantry_db;
```

### 3. Backend Setup

```bash
cd backend

# Update database credentials in src/main/resources/application.properties
# spring.datasource.username=your_username
# spring.datasource.password=your_password

# Run the application
./mvnw spring-boot:run
```

Backend runs at `http://localhost:8082`

### 4. Frontend Setup

```bash
cd frontend

# Install dependencies
npm install

# Start development server
npm run dev
```

Frontend runs at `http://localhost:5173`

---

## Database Schema

### items table

| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT (PK) | Auto-generated primary key |
| name | VARCHAR | Item name |
| category | VARCHAR | Item category |
| quantity | INT | Current quantity |
| expiry_date | DATE | Expiry date |
| notified | BOOLEAN | Expiry notification flag |

---

## Screenshots

> Add screenshots of Dashboard, Pantry Items, OCR Scanner, and Analytics here

---

## Future Enhancements

- [ ] JWT Authentication & Role-Based Access Control
- [ ] Email notifications for expiring items
- [ ] Barcode / QR code scanner
- [ ] AI-powered recipe generator
- [ ] Nutrition analysis
- [ ] Docker deployment
- [ ] Microservices architecture
- [ ] Mobile application (React Native)
- [ ] Family shared pantry
- [ ] Shopping list generator
- [ ] Dark mode

---

## Author

**Silvester** - [GitHub](https://github.com/Silvester20041)
