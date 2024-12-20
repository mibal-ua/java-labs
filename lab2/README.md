### Laboratory Work №3: XML and JSON Serializer by annotation processing

---

#### **Author**

Mykhailo Balakhon | IM-22

#### **Project Description**

This project is an implementation of **Variant 1** from Laboratory Work №3. The goal is to create a serializer for annotated Java objects into **XML** and **JSON** formats. The implementation supports mapping Java object fields to their representations in the serialized output. The project demonstrates the use of **annotations** and **reflection** in Java to achieve dynamic serialization.

---

#### **Features**

- **XML Serialization**: Convert Java objects to XML format with custom field mappings.
- **JSON Serialization**: Convert Java objects to JSON format with custom field mappings.
- **Annotations**: Custom annotations to define serialization rules.
- **Reflection**: Dynamically process annotations and map object fields to serialized formats.
- **Performance Comparison**: Demonstrates execution time differences between reflection-based and non-reflection-based approaches.

---

#### **Project Structure**

- **src/main/java**
    - Contains all source files, including:
        - Custom annotations.
        - Serializer implementation for XML and JSON.
        - Demonstration classes.
- **src/test/java**
    - Unit tests for the serializer and annotation processing.

---

#### **Setup Instructions**

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd <repository-directory>/lab3
   ```

2. **Build the Project**  
   Ensure you have **Maven** installed. Run the following command to build the project:
   ```bash
   mvn clean install
   ```

3. **Run the Program**  
   Use the following command to execute the program:
   ```bash
   java -jar target/lab2-xml-json-serializer-<version>.jar
   ```

4. **Testing**  
   To run tests, execute:
   ```bash
   mvn test
   ```

---

#### **Usage**

1. Annotate Java classes with custom serialization annotations.
2. Use the provided serializer to convert objects to XML or JSON.
3. Pass the serialized output to appropriate consumers or save it to files.

---

#### **Benchmark Results**

To compare the performance of reflection-based and plain serialization, a benchmark was conducted using **JMH**. The results are as follows:

| Benchmark                      | Mode | Count | Average Time (ms/op) |
|--------------------------------|------|-------|----------------------|
| Reflection-based Serialization | avgt | 5     | 0.001 ± 0.001        |
| Processing-based Serialization | avgt | 5     | ≈ 10⁻⁴               |

**Observations:**

- Reflection-based serialization is slightly slower due to the overhead of dynamic field access and annotation processing.
- Plain serialization is faster but lacks the flexibility and dynamic capabilities of reflection-based approaches.

---

#### **References**

- [XML](https://uk.wikipedia.org/wiki/XML)
- [JSON](https://uk.wikipedia.org/wiki/JSON)
- [Java Code Style Guide (Google)](https://google.github.io/styleguide/javaguide.html)
- [Java Annotations Documentation](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)

---

#### **Notes**

- The project adheres to **Google Java Code Style**.
- All custom classes are documented with **Javadoc**.
