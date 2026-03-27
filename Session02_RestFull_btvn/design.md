# Backend Technical Design Document

## 1. Giới thiệu (Introduction)

**Dự án:** Task Management System

**Mô tả:**  
Backend API cho hệ thống quản lý công việc (Task) và người dùng (User).  
Mỗi công việc (Task) sẽ được gán cho một người dùng (User).

**Công nghệ chính:**
- Java 17+
- Spring Boot 3.x
- Spring Web
- MySQL / PostgreSQL
- Lombok

---

## 2. Kiến trúc Hệ thống (System Architecture)

Áp dụng **Layered Architecture**:

- **Controller Layer (@RestController)**  
  Xử lý request, trả response, validate input.

- **Service Layer (@Service)**  
  Xử lý business logic.

- **Repository Layer (@Repository)**  
  Tương tác dữ liệu (DB hoặc in-memory).

- **Entity Layer (@Entity / Model)**  
  Định nghĩa đối tượng dữ liệu.

---

## 3. Thiết kế Cơ sở dữ liệu (Database Design)

**Loại DB:** Relational Database (MySQL/PostgreSQL)

### Bảng `users`
| Field    | Type   | Description       |
|----------|--------|-------------------|
| id       | int    | Primary Key       |
| username | String | Tên người dùng    |
| email    | String | Email             |
| role     | String | ROLE_USER / ADMIN |

### Bảng `tasks`
| Field       | Type   | Description         |
|-------------|--------|---------------------|
| id          | int    | Primary Key         |
| title       | String | Tiêu đề công việc   |
| description | String | Mô tả               |
| priority    | String | low / medium / high |
| assignedTo  | int    | FK -> users.id      |

**Relationship:**
- 1 User → N Task
## 4. Thiết kế API (RESTful API Design)

### 🔹 Base URL

/api/v1

---

## USER APIs

### 1. Lấy toàn bộ user
GET /api/v1/users

### 2. Tạo user mới
POST /api/v1/users


**Request Body**
{
  "username": "cuong",
  "email": "cuong@gmail.com",
  "role": "USER"
}


### 3. Cập nhật role user
PUT /api/v1/users/{id}
### 4. Xóa user
DELETE /api/v1/users/{id}

### TASK APIs

### 1. Lấy toàn bộ task
GET /api/v1/tasks

### 2. Tạo task mới
POST /api/v1/tasks

**Request Body**
{
  "title": "Fix bug",
  "description": "Fix login bug",
  "priority": "high",
  "assignedTo": 1
}
### 3. Cập nhật trạng thái / priority task
PUT /api/v1/tasks/{id}

### 4. Xóa task
DELETE /api/v1/tasks/{id}


### 5. Luồng hoạt động (Workflow)
  - Client gửi HTTP request
  - Controller nhận request
  - Validate dữ liệu đầu vào
  - Service xử lý logic nghiệp vụ
  - Repository truy xuất dữ liệu
  - Trả về JSON response
### 6. Cấu trúc thư mục (Project Structure)
   src/main/java/re/edu
   ├── controller/
   │   ├── UserController.java
   │   └── TaskController.java
   ├── service/
   │   ├── UserService.java
   │   └── TaskService.java
   ├── repository/
   │   ├── UserRepository.java
   │   └── TaskRepository.java
   ├── model/
   │   ├── User.java
   │   └── Task.java
### 7.HTTP Status Code sử dụng
  - 200	OK
  - 201	Created
  - 204	No Content
  - 400	Bad Request
  - 404	Not Found