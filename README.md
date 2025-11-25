# Full-Stack Vacation Booking System - Spring Boot Backend

A production-ready RESTful API built with Spring Boot for managing vacation bookings, demonstrating enterprise-level Java backend development.

## 🚀 Live Demo
- **Backend API**: `http://localhost:8080/api`
- **Frontend**: Angular SPA (not included per policy)

## 🎯 Project Overview

This project is a comprehensive vacation booking system backend that handles:
- Customer management with geographic divisions
- Vacation package listings with associated excursions
- Shopping cart functionality
- Order processing with transaction management
- RESTful API endpoints for frontend integration

**Built for**: Educational Purpose Only 
**Grade**: ✅ Competent (Passed all rubric requirements)

---

## 🛠️ Tech Stack

### Core Technologies
- **Java 21** - Latest LTS version
- **Spring Boot 4.0.0** - Enterprise application framework
- **Spring Data JPA** - Data persistence with Hibernate
- **Spring Data REST** - Automatic REST endpoint generation
- **MySQL 8.0** - Relational database
- **Maven** - Dependency management
- **Lombok** - Boilerplate reduction

### Key Features Implemented
- ✅ RESTful API design
- ✅ JPA/Hibernate ORM with complex relationships
- ✅ Transaction management
- ✅ Input validation
- ✅ CORS configuration for frontend integration
- ✅ Dependency injection
- ✅ Repository pattern
- ✅ Service layer architecture
- ✅ DTO pattern for data transfer

---

## 📁 Project Architecture
```
com.example.backend/
├── config/              # Configuration classes
│   ├── RestDataConfig   # REST API & CORS configuration
│   └── DataLoader       # Sample data initialization
├── controllers/         # REST endpoints
│   └── CheckoutController
├── services/           # Business logic layer
│   ├── CheckoutService (Interface)
│   ├── CheckoutServiceImpl
│   ├── Purchase (DTO)
│   └── PurchaseResponse (DTO)
├── dao/                # Data access layer
│   ├── CartRepository
│   ├── CustomerRepository
│   ├── VacationRepository
│   └── ... (7 repositories total)
└── entities/           # JPA entities
    ├── Customer
    ├── Cart
    ├── CartItem
    ├── Vacation
    ├── Excursion
    ├── Division
    ├── Country
    └── StatusType (Enum)
```

---

## 🗄️ Database Schema

### Entity Relationships
- **Customer** → **Division** (Many-to-One)
- **Division** → **Country** (Many-to-One)
- **Customer** → **Cart** (One-to-Many)
- **Cart** → **CartItem** (One-to-Many)
- **CartItem** → **Vacation** (Many-to-One)
- **CartItem** ↔ **Excursion** (Many-to-Many)
- **Vacation** → **Excursion** (One-to-Many)

### Key Design Decisions
- Used `BigDecimal` for monetary values (precision)
- Implemented `LAZY` fetching for performance optimization
- Applied `CascadeType.ALL` for related entities
- Enum for order status (`pending`, `ordered`, `canceled`)

---

## 🔧 Setup & Installation

### Prerequisites
```bash
Java 21 or higher
MySQL 8.0 or higher
Maven 3.6+
```

### 1. Clone Repository
```bash
git clone https://github.com/Hardik86/Angular-Spring_Backend.git
cd Angular-Spring_Backend
```

### 2. Database Setup
```bash
mysql -u root -p
source database-script.sql
```

This creates:
- Database: `full-stack-ecommerce`
- User: `ecommerceapp` / `ecommerceapp`
- Tables with sample data

### 3. Configure Application
Edit `src/main/resources/application.properties` if needed:
```properties
spring.datasource.url=?
spring.datasource.username=?
spring.datasource.password=?
```

### 4. Build & Run
```bash
mvn clean install
mvn spring-boot:run
```

Application starts at: `http://localhost:8080`

---

## 📡 API Endpoints

### Auto-Generated Endpoints (Spring Data REST)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/vacations` | List all vacation packages |
| GET | `/api/vacations/{id}` | Get vacation by ID |
| GET | `/api/excursions` | List all excursions |
| GET | `/api/customers` | List all customers |
| GET | `/api/divisions` | List all geographic divisions |
| GET | `/api/countries` | List all countries |

### Custom Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/checkout/purchase` | Place vacation order |

---

## 🧪 Testing

### Using Postman

**1. Get All Vacations**
```
GET http://localhost:8080/api/vacations
```

**2. Place Order**
```
POST http://localhost:8080/api/checkout/purchase
Content-Type: application/json

{
  "cart": {
    "package_price": 1500.00,
    "party_size": 2,
    "status": "pending",
    "customer": { "id": 6 }
  },
  "cartItems": [
    {
      "vacation": { "id": 1 },
      "excursions": [
        { "id": 1 },
        { "id": 2 }
      ]
    }
  ]
}
```

**Expected Response:**
```json
{
  "orderTrackingNumber": "a1b2c3d4-e5f6-7890-abcd-ef1234567890"
}
```

### Database Verification
```sql
USE `full-stack-ecommerce`;

-- Verify order was saved
SELECT * FROM carts 
WHERE status = 'ordered' 
ORDER BY create_date DESC 
LIMIT 1;

-- Verify cart items
SELECT * FROM cart_items 
ORDER BY create_date DESC 
LIMIT 1;
```

---

## 💡 Key Implementation Highlights

### 1. Transaction Management
```java
@Transactional
public PurchaseResponse placeOrder(Purchase purchase) {
    // All operations succeed or all fail (ACID compliance)
}
```

### 2. Validation
```java
@NotBlank(message = "First name is required")
private String firstName;
```

### 3. Dependency Injection
```java
public CheckoutServiceImpl(CartRepository cartRepository, 
                           CustomerRepository customerRepository) {
    // Spring automatically injects dependencies
}
```

### 4. CORS Configuration
```java
@CrossOrigin(origins = "http://localhost:4200")
```
Allows Angular frontend to communicate with backend.

### 5. UUID Generation
```java
String orderTrackingNumber = UUID.randomUUID().toString();
```
Unique, non-sequential order tracking numbers.

---

## 🎓 What I Learned

### Technical Skills
- Building RESTful APIs with Spring Boot
- JPA/Hibernate entity relationships (One-to-Many, Many-to-Many)
- Transaction management and data consistency
- Input validation and error handling
- CORS configuration for cross-origin requests
- Maven dependency management
- MySQL database design and optimization

### Software Engineering Principles
- **SOLID Principles**: Single Responsibility, Dependency Inversion
- **Design Patterns**: Repository, Service, DTO
- **Layered Architecture**: Separation of concerns
- **Clean Code**: Readable, maintainable, testable

### Problem-Solving
- Resolved customer ID mismatch between frontend and backend
- Implemented proper entity fetch strategies for performance
- Handled cascade operations correctly to maintain data integrity

---

## 📈 Future Enhancements

- [ ] JWT authentication & authorization
- [ ] Unit testing with JUnit & Mockito
- [ ] Integration tests with TestContainers
- [ ] Redis caching for improved performance
- [ ] Pagination for large datasets
- [ ] Advanced search/filtering
- [ ] Email confirmation on order placement
- [ ] Payment gateway integration (Stripe)
- [ ] Dockerization
- [ ] CI/CD pipeline with GitHub Actions
- [ ] Deployment to AWS/Azure

---

## 📝 Assignment Requirements Met

✅ **A**: Created Spring Boot project with all 4 required dependencies  
✅ **B**: GitLab repository with commits after each task  
✅ **C**: Constructed 4 packages (controllers, entities, dao, services)  
✅ **D**: Entity classes matching UML diagram with enum  
✅ **E**: Repository interfaces with JpaRepository and CORS  
✅ **F**: Services package with all 4 required components  
✅ **G**: Validation for Angular frontend inputs  
✅ **H**: REST controller with POST mapping  
✅ **I**: 5 sample customers added programmatically  
✅ **J**: Successfully tested with Angular frontend  
✅ **K**: Professional communication throughout

---

## 👨‍💻 Author

**Hardik Hariyani**

- GitHub: https://github.com/Hardik86/Angular-Spring_Backend
- LinkedIn: https://www.linkedin.com/in/hardik-hariyani/
- Email: hardikhariyani86@gmail.com

---

## 📄 License

This project was created as coursework for a University. 

---

## 🙏 Acknowledgments

- Spring Boot Documentation
- Baeldung Tutorials

---



*Built with ❤️ using Spring Boot*
