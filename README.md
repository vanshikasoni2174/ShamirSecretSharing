# 🔐 Shamir's Secret Sharing (Simplified) - Java Implementation

This Java project demonstrates a simplified version of **Shamir’s Secret Sharing**, a cryptographic method used to split a secret into multiple parts (shares) and later reconstruct the original secret using polynomial interpolation.

---

## 📁 Project Structure

ShamirSecretSharing/
├── src/
│ └── main/
│ └── java/
│ └── SecretFinder.java
├── testcase1.json
├── testcase2.json
├── pom.xml
└── README.md

yaml
Copy
Edit

---

## 🚀 Features

- ✅ Parses JSON test cases containing base-encoded shares
- ✅ Performs Lagrange polynomial interpolation to reconstruct the secret
- ✅ Supports any base format (e.g., base-10, base-16)
- ✅ Easy to extend for additional test cases
- ✅ Built using Maven for dependency management

---

## 📦 Dependencies

This project uses the [Jackson](https://github.com/FasterXML/jackson) library for JSON parsing.

<dependency> <groupId>com.fasterxml.jackson.core</groupId> <artifactId>jackson-databind</artifactId> <version>2.16.0</version> </dependency> ```
🧪 Test Case Format (testcase1.json, testcase2.json)
Each file should contain:

json
Copy
Edit
{
  "1": { "base": "10", "value": "12345" },
  "2": { "base": "10", "value": "23456" },
  "3": { "base": "10", "value": "34567" }
}
Keys represent the share index, and the values contain:

base: base of the encoded number

value: the share value in the given base

🛠 How to Run
💻 Requirements
Java 17 or later

Maven

IntelliJ IDEA or any Java IDE

✅ Steps
Clone the repository:

bash
Copy
Edit
git clone https://github.com/yourusername/ShamirSecretSharing.git
cd ShamirSecretSharing
Open the project in IntelliJ IDEA and let Maven resolve dependencies.

Click "Load Maven Changes" if prompted.

Place your test JSON files (testcase1.json, testcase2.json) in the root directory.

Run the program by updating the filename in main():

java
Copy
Edit
String filename = "testcase1.json";
Build & Run:

bash
Copy
Edit
mvn compile
mvn exec:java -Dexec.mainClass=SecretFinder
📤 Output Example
css
Copy
Edit
🔍 Reading key: 1, node: {"base":"10","value":"12345"}
🔍 Reading key: 2, node: {"base":"10","value":"23456"}
🔍 Reading key: 3, node: {"base":"10","value":"34567"}
✅ Reconstructed secret: 1234
📚 Reference
Shamir’s Secret Sharing Scheme (Wikipedia)

Polynomial Interpolation using Lagrange Formula

👩‍💻 Author
Vanshika Soni
B.Tech CSE | Data Science | GATE CSE 2026 Aspirant
LinkedIn | GitHub

