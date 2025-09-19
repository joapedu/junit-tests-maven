# Java TDD Lab 🧪☕

A hands-on project that applies **Test-Driven Development (TDD)** using **Java**, **JUnit 5**, and **Maven**.  
It demonstrates good practices in unit testing, object-oriented design, and modular architecture.

---

## 🔍 Project Overview

This project simulates an academic grading system with a focus on **unit testing** and **test-first development**.

The goal is not to build a complete system, but to use a simple domain to explore:
- How to write clean and modular Java classes
- How to structure and run unit tests with JUnit
- How to manage dependencies and compilation with Maven

---

## ⚙️ Technologies Used

- Java 8+
- Maven 3.6+
- JUnit 5
- IntelliJ IDEA / Eclipse (optional)
- Git

---

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/joapedu/java-tdd-lab.git
cd java-tdd-lab
```

### Run the tests with Maven
```bash
mvn test
```
> You should see output similar to:

``[INFO] Running br.ufrn.imd.MatriculaTest``

``[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0``

---

### ✅ Example Concepts Demonstrated

- ✅ Test-Driven Development (Red → Green → Refactor)

- ✅ Unit testing with JUnit

- ✅ Usage of BigDecimal for precision

- ✅ Business rule validation

- ✅ Parameterized tests

- ✅ Clear domain-driven design

### 📌 Example Use Case

The system simulates a grading evaluation where a student's status (e.g., Approved, Failed, Recovery) is calculated based on their grades. The logic is fully validated via unit tests.

### 🆘 Contributing

See [CONTRIBUTING](./CONTRIBUTING).

### 🤓 Author

Developed as part of a programming lab focused on software testing and clean Java architecture.