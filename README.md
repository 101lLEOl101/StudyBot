# StudyBot — система тестирования через Telegram

[![Telegram Bot](https://img.shields.io/badge/Telegram-Bot-blue.svg)](https://t.me/YourBotName)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1+-green.svg)](https://spring.io)
[![Vue.js](https://img.shields.io/badge/Vue.js-3.3+-brightgreen.svg)](https://vuejs.org)

**StudyBot** — это образовательная платформа, где преподаватели создают тесты на веб-сайте, а студенты проходят их в Telegram-боте.

---

## 🔍 Основные возможности

### 👨‍🏫 **Для преподавателей**:
- Создание/редактирование/удаление тестов
- Управление группами студентов
- Настройка параметров тестирования

### 👨‍🎓 **Для студентов**:
- Прохождение тестов прямо в Telegram
- Просмотр результатов и статистики
- История пройденных тестов

---

## 🛠 Технологический стек

### **Backend (API)**:
- Java 17 + Spring Boot 3.1
- Spring Security + JWT аутентификация
- Hibernate + MySQL
- Swagger для документации API

### **Frontend (Веб-интерфейс)**:
- Vue.js 3.3
- Mantine UI компоненты
- Axios для работы с API

### **Telegram Bot**:
- Python + aiogram 3.x
- Асинхронные запросы к API
- Интуитивный интерфейс в чате

---

## 🚀 Установка и запуск

### **Требования**:
- JDK 17, MySQL 8+, Node.js 16+, Python 3.10+

### **Backend**:
```bash
git clone https://github.com/your/repo.git
cd backend
./mvnw spring-boot:run
