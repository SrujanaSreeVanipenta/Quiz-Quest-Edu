package com.example.quizquest;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushQuestions extends AppCompatActivity {

    private FirebaseFirestore db;
    private static final String TAG = "PushQuestions";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FirebaseFirestore.getInstance();
        List<Map<String, Object>> questions = new ArrayList<>();

        // ==============================================================
        // 🔹 PROGRAMMING (50 questions)
        // ==============================================================
        questions.add(createQ("Programming", "What is C?", "Programming Language", "Coffee", "Operating System", "None", "option1", "Easy"));
        questions.add(createQ("Programming", "Which company developed Java?", "Microsoft", "Sun Microsystems", "Apple", "IBM", "option2", "Easy"));
        questions.add(createQ("Programming", "What is the size of int in Java?", "2 bytes", "4 bytes", "8 bytes", "Depends on OS", "option2", "Medium"));
        questions.add(createQ("Programming", "Which keyword is used to inherit a class in Java?", "this", "super", "extends", "implements", "option3", "Easy"));
        questions.add(createQ("Programming", "Which of these is not a Java keyword?", "class", "public", "void", "include", "option4", "Medium"));
        questions.add(createQ("Programming", "Which method starts execution in Java?", "start()", "main()", "run()", "execute()", "option2", "Easy"));
        questions.add(createQ("Programming", "Which operator is used for bitwise AND?", "&", "|", "^", "~", "option1", "Medium"));
        questions.add(createQ("Programming", "What is polymorphism?", "One form", "Many forms", "Overloading", "Inheritance", "option2", "Medium"));
        questions.add(createQ("Programming", "Which of the following handles exceptions?", "if-else", "try-catch", "switch", "loop", "option2", "Easy"));
        questions.add(createQ("Programming", "OOP stands for?", "Object Oriented Programming", "Old Oriented Programming", "Online Object Program", "None", "option1", "Easy"));
        questions.add(createQ("Programming", "Which symbol starts a comment in Java?", "//", "/*", "#", "--", "option1", "Easy"));
        questions.add(createQ("Programming", "Which function allocates memory dynamically in C?", "alloc()", "malloc()", "new()", "create()", "option2", "Medium"));
        questions.add(createQ("Programming", "Which keyword prevents inheritance?", "sealed", "private", "final", "protected", "option3", "Medium"));
        questions.add(createQ("Programming", "JVM stands for?", "Java Virtual Machine", "Java Visual Machine", "Just Virtual Memory", "Java Verified Memory", "option1", "Easy"));
        questions.add(createQ("Programming", "Which of these stops a loop?", "stop", "exit", "break", "end", "option3", "Easy"));
        questions.add(createQ("Programming", "Which function prints output in C?", "print()", "printf()", "println()", "display()", "option2", "Easy"));
        questions.add(createQ("Programming", "Which structure follows FIFO?", "Stack", "Queue", "Tree", "Graph", "option2", "Medium"));
        questions.add(createQ("Programming", "Fastest average sorting algorithm?", "Bubble", "Merge", "Insertion", "Selection", "option2", "Hard"));
        questions.add(createQ("Programming", "Not an OOP concept?", "Encapsulation", "Abstraction", "Polymorphism", "Compilation", "option4", "Medium"));
        questions.add(createQ("Programming", "Function to free memory in C?", "delete()", "release()", "free()", "dispose()", "option3", "Easy"));
        questions.add(createQ("Programming", "Data type for text?", "int", "char", "String", "boolean", "option3", "Easy"));
        questions.add(createQ("Programming", "Which data structure uses LIFO?", "Stack", "Queue", "List", "Array", "option1", "Easy"));
        questions.add(createQ("Programming", "Default access modifier in Java?", "public", "protected", "private", "package-private", "option4", "Medium"));
        questions.add(createQ("Programming", "What is recursion?", "Looping function", "Function calling itself", "Nested function", "Repetition", "option2", "Medium"));
        questions.add(createQ("Programming", "Which keyword defines a constant in Java?", "const", "final", "static", "constant", "option2", "Easy"));

        questions.add(createQ("Programming", "Which loop guarantees at least one execution?", "for", "while", "do-while", "enhanced-for", "option3", "Easy"));
        questions.add(createQ("Programming", "Which of these is NOT a primitive type in Java?", "int", "boolean", "String", "char", "option3", "Easy"));
        questions.add(createQ("Programming", "Which collection in Java does NOT allow duplicates?", "List", "Set", "ArrayList", "Vector", "option2", "Medium"));
        questions.add(createQ("Programming", "Which keyword is used to create an object in Java?", "alloc", "malloc", "new", "create", "option3", "Easy"));
        questions.add(createQ("Programming", "What is method overloading?", "Same name, different params", "Same name, same params", "Different name, same params", "Different classes", "option1", "Medium"));
        questions.add(createQ("Programming", "Which of these is a reference data type in Java?", "int", "float", "char", "String", "option4", "Easy"));
        questions.add(createQ("Programming", "Which keyword is used to refer current object in Java?", "self", "this", "current", "me", "option2", "Easy"));
        questions.add(createQ("Programming", "Which header file is needed for printf in C?", "stdlib.h", "stdio.h", "conio.h", "string.h", "option2", "Easy"));
        questions.add(createQ("Programming", "Which of these is used for logical AND in Java?", "&", "&&", "|", "!", "option2", "Easy"));
        questions.add(createQ("Programming", "Which keyword is used for inheritance in C++?", "inherit", "extends", ":", "implements", "option3", "Medium"));
        questions.add(createQ("Programming", "Which access modifier allows access only within the same class?", "public", "private", "protected", "default", "option2", "Easy"));
        questions.add(createQ("Programming", "Which method is called when an object is destroyed in C++?", "destroy()", "delete()", "destructor", "~ClassName()", "option4", "Medium"));
        questions.add(createQ("Programming", "Which data structure is used for function call management?", "Queue", "Stack", "List", "Graph", "option2", "Medium"));
        questions.add(createQ("Programming", "Which keyword is used to avoid overriding a method in Java?", "private", "final", "sealed", "static", "option2", "Medium"));
        questions.add(createQ("Programming", "What does IDE stand for?", "Integrated Development Environment", "Internal Debug Environment", "Integrated Data Engine", "Input Development Engine", "option1", "Easy"));
        questions.add(createQ("Programming", "Which of the following is NOT a valid identifier?", "name", "2name", "name2", "name", "option2", "Easy"));
        questions.add(createQ("Programming", "Which memory is used for local variables?", "Heap", "Stack", "Static", "Register", "option2", "Medium"));
        questions.add(createQ("Programming", "Which type of error is found at compile time?", "Logical", "Runtime", "Syntax", "Semantic", "option3", "Easy"));
        questions.add(createQ("Programming", "Which keyword is used to declare an abstract method?", "virtual", "abstract", "final", "static", "option2", "Medium"));
        questions.add(createQ("Programming", "Which is not a looping statement in C?", "for", "while", "do", "repeat", "option4", "Medium"));
        questions.add(createQ("Programming", "Which operator is used to access members of a structure in C?", ".", "->", "::", ":", "option1", "Medium"));
        questions.add(createQ("Programming", "Which file extension is used for Java source files?", ".class", ".java", ".jar", ".jvm", "option2", "Easy"));
        questions.add(createQ("Programming", "Which concept allows using the same function name with different arguments?", "Overloading", "Overriding", "Hiding", "Shadowing", "option1", "Medium"));
        questions.add(createQ("Programming", "In C, which specifier is used to print an integer?", "%c", "%d", "%f", "%s", "option2", "Easy"));
        questions.add(createQ("Programming", "Which of these is used for single-line comments in C++?", "//", "/*", "#", "--", "option1", "Easy"));

        // ==============================================================
        // 🔹 DBMS (50 questions)
        // ==============================================================
        questions.add(createQ("DBMS", "What does DBMS stand for?", "Database Management System", "Data Block Main System", "Database Model Setup", "Data Base Main System", "option1", "Easy"));
        questions.add(createQ("DBMS", "Which SQL command removes a table?", "DELETE", "DROP", "TRUNCATE", "REMOVE", "option2", "Medium"));
        questions.add(createQ("DBMS", "Eliminates transitive dependency?", "1NF", "2NF", "3NF", "BCNF", "option3", "Medium"));
        questions.add(createQ("DBMS", "Primary key cannot be?", "NULL", "Unique", "Repeated", "All of these", "option1", "Easy"));
        questions.add(createQ("DBMS", "Full form of SQL?", "Structured Query Language", "Simple Query Language", "Sequential Query List", "Standard Query Library", "option1", "Easy"));
        questions.add(createQ("DBMS", "Which join shows all matches?", "INNER", "LEFT", "RIGHT", "FULL OUTER", "option4", "Medium"));
        questions.add(createQ("DBMS", "NoSQL example?", "MySQL", "MongoDB", "Oracle", "PostgreSQL", "option2", "Easy"));
        questions.add(createQ("DBMS", "What is a transaction?", "SQL statement", "Unit of work", "Stored procedure", "Trigger", "option2", "Medium"));
        questions.add(createQ("DBMS", "ACID stands for?", "Atomicity, Consistency, Isolation, Durability", "Accuracy, Control, Integrity, Data", "Access, Consistency, Integrity, Data", "All", "option1", "Hard"));
        questions.add(createQ("DBMS", "Foreign key is used to?", "Identify row", "Refer another table", "Encrypt data", "Index table", "option2", "Medium"));
        questions.add(createQ("DBMS", "Candidate key is?", "Subset of super key", "Primary key", "Foreign key", "Composite key", "option1", "Medium"));
        questions.add(createQ("DBMS", "Normalization aims to?", "Reduce redundancy", "Add redundancy", "Increase size", "None", "option1", "Easy"));
        questions.add(createQ("DBMS", "Which ensures data security?", "Normalization", "Authorization", "Primary key", "Trigger", "option2", "Medium"));
        questions.add(createQ("DBMS", "View in SQL is?", "Virtual table", "Permanent table", "Temporary table", "Base table", "option1", "Easy"));
        questions.add(createQ("DBMS", "Which normal form removes partial dependency?", "1NF", "2NF", "3NF", "BCNF", "option2", "Medium"));
        questions.add(createQ("DBMS", "What is denormalization?", "Adding redundancy", "Removing redundancy", "Normalizing table", "Splitting data", "option1", "Medium"));
        questions.add(createQ("DBMS", "Which command changes data?", "UPDATE", "SELECT", "CREATE", "ALTER", "option1", "Easy"));
        questions.add(createQ("DBMS", "Trigger runs when?", "On table event", "Manually", "Before query", "On backup", "option1", "Medium"));
        questions.add(createQ("DBMS", "Which stores metadata?", "Data dictionary", "Schema", "Table", "Index", "option1", "Medium"));
        questions.add(createQ("DBMS", "What is BCNF?", "Boyce Codd Normal Form", "Base Code Normal Form", "Best Case Normal Format", "Binary Code Normal Form", "option1", "Hard"));
        questions.add(createQ("DBMS", "Transaction failure handled by?", "Rollback", "Commit", "Select", "Update", "option1", "Medium"));
        questions.add(createQ("DBMS", "Default SQL sorting?", "ASC", "DESC", "Random", "None", "option1", "Easy"));
        questions.add(createQ("DBMS", "Data Integrity ensures?", "Accuracy and consistency", "Speed", "Security", "Duplication", "option1", "Medium"));
        questions.add(createQ("DBMS", "Which index improves search?", "B-Tree", "Linear", "Binary", "None", "option1", "Medium"));
        questions.add(createQ("DBMS", "Entity Relationship Model designed by?", "Codd", "Chen", "Turing", "Shannon", "option2", "Medium"));

        questions.add(createQ("DBMS", "Which SQL command removes all rows but keeps structure?", "DELETE", "DROP", "TRUNCATE", "REMOVE", "option3", "Medium"));
        questions.add(createQ("DBMS", "Which key uniquely identifies a record?", "Foreign key", "Super key", "Primary key", "Composite key", "option3", "Easy"));
        questions.add(createQ("DBMS", "Relation in DBMS is represented as?", "Row", "Column", "Table", "File", "option3", "Easy"));
        questions.add(createQ("DBMS", "Which of the following is a DDL command?", "SELECT", "INSERT", "UPDATE", "CREATE", "option4", "Medium"));
        questions.add(createQ("DBMS", "Which of the following is a DML command?", "ALTER", "DROP", "DELETE", "GRANT", "option3", "Medium"));
        questions.add(createQ("DBMS", "Which command is used to give privileges?", "GRANT", "REVOKE", "ALLOW", "PERMIT", "option1", "Medium"));
        questions.add(createQ("DBMS", "What is a super key?", "Any key that identifies a row", "Only primary key", "Only foreign key", "Composite key", "option1", "Medium"));
        questions.add(createQ("DBMS", "Redundancy means?", "Duplicate data", "Missing data", "Corrupted data", "Encrypted data", "option1", "Easy"));
        questions.add(createQ("DBMS", "Which language is used to query the database?", "DDL", "DCL", "DML", "HTML", "option3", "Easy"));
        questions.add(createQ("DBMS", "What does JOIN in SQL do?", "Inserts data", "Combines rows from tables", "Deletes rows", "Updates rows", "option2", "Medium"));
        questions.add(createQ("DBMS", "Cross join returns?", "Matched rows", "Unmatched rows", "Cartesian product", "Intersection", "option3", "Hard"));
        questions.add(createQ("DBMS", "Which normal form deals with multivalued dependencies?", "1NF", "2NF", "3NF", "4NF", "option4", "Hard"));
        questions.add(createQ("DBMS", "Which constraint ensures values in a column are unique?", "PRIMARY KEY", "FOREIGN KEY", "UNIQUE", "CHECK", "option3", "Medium"));
        questions.add(createQ("DBMS", "Which clause filters groups?", "WHERE", "GROUP BY", "HAVING", "ORDER BY", "option3", "Medium"));
        questions.add(createQ("DBMS", "Which key can accept NULL values?", "Primary key", "Foreign key", "Unique key", "Composite primary key", "option2", "Medium"));
        questions.add(createQ("DBMS", "What is a schema?", "User data", "Structure of database", "Backup of database", "Index file", "option2", "Easy"));
        questions.add(createQ("DBMS", "Which SQL function counts rows?", "COUNT()", "SUM()", "AVG()", "MAX()", "option1", "Easy"));
        questions.add(createQ("DBMS", "Which SQL clause sorts result?", "GROUP BY", "ORDER BY", "SORT BY", "RANGE BY", "option2", "Easy"));
        questions.add(createQ("DBMS", "What is a composite key?", "Multiple foreign keys", "Combination of attributes forming key", "Key from different tables", "Encrypted key", "option2", "Medium"));
        questions.add(createQ("DBMS", "Which command renames a table?", "ALTER TABLE RENAME", "RENAME TABLE", "MODIFY TABLE", "CHANGE TABLE", "option1", "Medium"));
        questions.add(createQ("DBMS", "Clustered index means?", "Index on secondary key", "Index that defines table order", "Index on foreign key", "None", "option2", "Hard"));
        questions.add(createQ("DBMS", "Which is NOT a logical operator in SQL?", "AND", "OR", "NOT", "SUM", "option4", "Easy"));
        questions.add(createQ("DBMS", "COMMIT does what?", "Undo changes", "Save changes permanently", "Lock table", "Close connection", "option2", "Easy"));
        questions.add(createQ("DBMS", "Which join returns all rows when there is match in right table or not?", "INNER JOIN", "LEFT JOIN", "RIGHT JOIN", "FULL JOIN", "option3", "Medium"));

        // ==============================================================
        // 🔹 OPERATING SYSTEMS (50 questions)
        // ==============================================================
        questions.add(createQ("Operating Systems", "Which OS is open source?", "Windows", "Linux", "MacOS", "Unix", "option2", "Easy"));
        questions.add(createQ("Operating Systems", "What is a process?", "Program in memory", "Thread", "Function", "Variable", "option1", "Easy"));
        questions.add(createQ("Operating Systems", "Preemptive scheduling algorithm?", "FCFS", "SJF Non-preemptive", "Round Robin", "None", "option3", "Medium"));
        questions.add(createQ("Operating Systems", "Virtual memory means?", "RAM + Disk", "ROM", "Cache", "Register", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Not a type of OS?", "Batch", "Time-Sharing", "Multitasking", "Python", "option4", "Easy"));
        questions.add(createQ("Operating Systems", "Deadlock occurs due to?", "Mutual exclusion", "Hold and wait", "No preemption", "All", "option4", "Hard"));
        questions.add(createQ("Operating Systems", "Semaphore is used for?", "Synchronization", "Scheduling", "Memory", "I/O", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Command to view processes in Linux?", "ps", "ls", "pwd", "cd", "option1", "Easy"));
        questions.add(createQ("Operating Systems", "Context switch means?", "Switching processes", "Switching CPU", "Switching threads", "None", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Paging belongs to?", "Memory management", "I/O", "Scheduling", "Security", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Thrashing occurs due to?", "High paging", "Low memory", "CPU overload", "None", "option1", "Hard"));
        questions.add(createQ("Operating Systems", "What is kernel?", "Core of OS", "Software", "Hardware", "API", "option1", "Easy"));
        questions.add(createQ("Operating Systems", "Which manages CPU?", "Scheduler", "Loader", "Dispatcher", "Compiler", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Critical section problem solved by?", "Synchronization", "Interrupts", "Deadlock", "Paging", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Which OS is developed by Apple?", "Linux", "MacOS", "Windows", "Android", "option2", "Easy"));
        questions.add(createQ("Operating Systems", "System call provides?", "Interface to kernel", "User commands", "API library", "All", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Round robin is used in?", "Batch", "Interactive", "Real-time", "Embedded", "option2", "Medium"));
        questions.add(createQ("Operating Systems", "Fragmentation occurs in?", "Memory allocation", "I/O", "CPU", "Storage", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Spooling stands for?", "Simultaneous peripheral operation online", "Special pooling", "Simple parallel output", "None", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Zombie process is?", "Terminated but in process table", "Active", "Blocked", "New", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Shell is?", "User interface", "Kernel", "Driver", "Compiler", "option1", "Easy"));
        questions.add(createQ("Operating Systems", "Which OS used in mobile?", "Windows", "Android", "Unix", "Linux", "option2", "Easy"));
        questions.add(createQ("Operating Systems", "FIFO is used in?", "Paging", "Queue", "CPU scheduling", "Disk", "option3", "Medium"));
        questions.add(createQ("Operating Systems", "In multitasking OS?", "Many tasks execute", "One task only", "No tasks", "Limited tasks", "option1", "Easy"));
        questions.add(createQ("Operating Systems", "System program example?", "Compiler", "Linker", "Loader", "All", "option4", "Medium"));

        questions.add(createQ("Operating Systems", "Which scheduling algorithm selects process with smallest CPU burst time?", "FCFS", "SJF", "Round Robin", "Priority", "option2", "Medium"));
        questions.add(createQ("Operating Systems", "Which is NOT a valid process state?", "New", "Running", "Terminated", "Blocked-Completed", "option4", "Medium"));
        questions.add(createQ("Operating Systems", "Time quantum is related to which scheduling?", "FCFS", "SJF", "Round Robin", "Priority", "option3", "Medium"));
        questions.add(createQ("Operating Systems", "Which of these is used for mutual exclusion?", "Semaphore", "Paging", "Swapping", "Polling", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "What is starvation?", "Process never gets CPU", "Process stuck in I/O", "Process killed", "Process finished early", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Which algorithm can cause starvation?", "Round Robin", "FCFS", "Priority Scheduling", "Lottery Scheduling", "option3", "Medium"));
        questions.add(createQ("Operating Systems", "Which memory is fastest?", "Cache", "RAM", "Disk", "Flash", "option1", "Easy"));
        questions.add(createQ("Operating Systems", "Swapping is related to?", "CPU scheduling", "Memory management", "File management", "I/O management", "option2", "Medium"));
        questions.add(createQ("Operating Systems", "Which table keeps track of processes?", "Page table", "Process table", "Hash table", "Routing table", "option2", "Easy"));
        questions.add(createQ("Operating Systems", "What does MMU stand for?", "Main Memory Unit", "Memory Management Unit", "Multi Memory Unit", "Managed Memory Unit", "option2", "Medium"));
        questions.add(createQ("Operating Systems", "Which disk scheduling algorithm gives best throughput?", "FCFS", "SSTF", "SCAN", "C-SCAN", "option2", "Medium"));
        questions.add(createQ("Operating Systems", "Which command is used to change directory in Linux?", "ls", "cd", "pwd", "mv", "option2", "Easy"));
        questions.add(createQ("Operating Systems", "Page fault occurs when?", "Page found in memory", "Page not in memory", "Page is dirty", "Page is locked", "option2", "Medium"));
        questions.add(createQ("Operating Systems", "Which of the following is NOT a deadlock condition?", "Mutual exclusion", "Hold and wait", "Preemption", "Circular wait", "option3", "Hard"));
        questions.add(createQ("Operating Systems", "Which technique solves critical section problem?", "Busy waiting only", "Peterson's algorithm", "Deadlock", "Swapping", "option2", "Hard"));
        questions.add(createQ("Operating Systems", "Which OS is commonly used in servers?", "DOS", "Windows XP", "Linux", "Android", "option3", "Easy"));
        questions.add(createQ("Operating Systems", "What is the main role of scheduler?", "Manage memory", "Select next process", "Handle I/O", "Encrypt files", "option2", "Medium"));
        questions.add(createQ("Operating Systems", "Which is an example of real-time OS?", "Windows 10", "Linux Ubuntu", "VxWorks", "Android", "option3", "Hard"));
        questions.add(createQ("Operating Systems", "Which operation is NOT performed by OS?", "Memory management", "Process management", "Compilation", "File management", "option3", "Easy"));
        questions.add(createQ("Operating Systems", "Which of the following is NOT a type of kernel?", "Monolithic", "Microkernel", "Hybrid", "Distributed kernel", "option4", "Medium"));
        questions.add(createQ("Operating Systems", "Which type of fragmentation occurs in paging?", "Internal", "External", "Both", "None", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Which scheduling algorithm is suitable for time sharing?", "Round Robin", "SJF", "FCFS", "Priority", "option1", "Medium"));
        questions.add(createQ("Operating Systems", "Which mechanism allows processes to communicate?", "Paging", "Segmentation", "IPC", "DMA", "option3", "Medium"));
        questions.add(createQ("Operating Systems", "Context of a process is stored in?", "Stack", "PCB", "Cache", "Heap", "option2", "Medium"));

        // ==============================================================
        // 🔹 WEB DEVELOPMENT (50 questions)
        // ==============================================================
        questions.add(createQ("Web Development", "HTML stands for?", "HyperText Markup Language", "Hyperlink Text Markup Language", "HighText Markup Language", "HyperTool Markup Language", "option1", "Easy"));
        questions.add(createQ("Web Development", "CSS stands for?", "Cascading Style Sheets", "Colorful Style Sheets", "Computer Style Sheets", "Creative Style Sheets", "option1", "Easy"));
        questions.add(createQ("Web Development", "JavaScript is used for?", "Styling", "Structure", "Behavior", "Database", "option3", "Easy"));
        questions.add(createQ("Web Development", "Which tag creates a hyperlink?", "<a>", "<link>", "<href>", "<url>", "option1", "Easy"));
        questions.add(createQ("Web Development", "Bootstrap is a?", "CSS Framework", "JS Library", "Database", "Programming Language", "option1", "Easy"));
        questions.add(createQ("Web Development", "DOM stands for?", "Document Object Model", "Data Object Model", "Database Object Model", "Design Object Model", "option1", "Medium"));
        questions.add(createQ("Web Development", "Which is client-side scripting?", "PHP", "JavaScript", "Python", "Java", "option2", "Easy"));
        questions.add(createQ("Web Development", "Which attribute specifies an image source?", "src", "href", "link", "alt", "option1", "Easy"));
        questions.add(createQ("Web Development", "Semantic HTML means?", "Tags with meaning", "Invisible tags", "CSS tags", "JS tags", "option1", "Medium"));
        questions.add(createQ("Web Development", "HTTP stands for?", "HyperText Transfer Protocol", "Hyperlink Text Transfer Protocol", "HyperTool Transfer Protocol", "None", "option1", "Easy"));
        questions.add(createQ("Web Development", "Which HTTP method is idempotent?", "GET", "POST", "PUT", "DELETE", "option1", "Medium"));
        questions.add(createQ("Web Development", "Which CSS property changes text color?", "font-color", "text-color", "color", "font", "option3", "Easy"));
        questions.add(createQ("Web Development", "Inline CSS uses which attribute?", "style", "link", "css", "src", "option1", "Easy"));
        questions.add(createQ("Web Development", "What is responsive design?", "Fits all screen sizes", "Fast loading", "Dark mode", "Animation", "option1", "Medium"));
        questions.add(createQ("Web Development", "Which HTML tag is used for lists?", "<ul>", "<p>", "<div>", "<span>", "option1", "Easy"));
        questions.add(createQ("Web Development", "Which symbol starts comments in CSS?", "/* */", "//", "#", "<!-- -->", "option1", "Medium"));
        questions.add(createQ("Web Development", "Which framework uses JSX?", "React", "Angular", "Vue", "Django", "option1", "Medium"));
        questions.add(createQ("Web Development", "Which tag defines a table row?", "<tr>", "<td>", "<th>", "<table>", "option1", "Easy"));
        questions.add(createQ("Web Development", "Which company developed React?", "Facebook", "Google", "Microsoft", "Amazon", "option1", "Medium"));
        questions.add(createQ("Web Development", "Which is a CSS preprocessor?", "Sass", "NodeJS", "React", "JSON", "option1", "Medium"));
        questions.add(createQ("Web Development", "JavaScript data type for arrays?", "object", "array", "list", "collection", "option1", "Medium"));
        questions.add(createQ("Web Development", "Event to trigger function on button click?", "onclick", "onhover", "onsubmit", "onpress", "option1", "Easy"));
        questions.add(createQ("Web Development", "Which CSS property controls outer spacing around an element?", "margin", "border", "padding", "spacing", "option1", "Medium"));
        questions.add(createQ("Web Development", "Which tag defines an input field?", "<input>", "<enter>", "<field>", "<textbox>", "option1", "Easy"));
        questions.add(createQ("Web Development", "AJAX stands for?", "Asynchronous JavaScript and XML", "Automatic Java Application XML", "Active JS and XHTML", "None", "option1", "Medium"));

        questions.add(createQ("Web Development", "Which HTML tag is used for the largest heading?", "<h6>", "<h1>", "<head>", "<title>", "option2", "Easy"));
        questions.add(createQ("Web Development", "Which attribute opens a link in a new tab?", "href", "target=\"_blank\"", "rel", "new", "option2", "Medium"));
        questions.add(createQ("Web Development", "Which tag is used to display an image?", "<image>", "<img>", "<pic>", "<media>", "option2", "Easy"));
        questions.add(createQ("Web Development", "Which CSS property sets the background color?", "bgcolor", "background", "background-color", "color", "option3", "Easy"));
        questions.add(createQ("Web Development", "Which property controls the size of text?", "font-size", "text-size", "size", "font-style", "option1", "Easy"));
        questions.add(createQ("Web Development", "Which symbol is used for ID selector in CSS?", ".", "#", "*", "&", "option2", "Easy"));
        questions.add(createQ("Web Development", "Which symbol is used for class selector in CSS?", ".", "#", "@", "&", "option1", "Easy"));
        questions.add(createQ("Web Development", "Which display value places elements in a new line?", "inline", "block", "inline-block", "flex", "option2", "Medium"));
        questions.add(createQ("Web Development", "Which property sets inner spacing in an element?", "margin", "padding", "border", "gap", "option2", "Easy"));
        questions.add(createQ("Web Development", "Which CSS position value keeps element fixed on screen?", "absolute", "relative", "fixed", "static", "option3", "Medium"));
        questions.add(createQ("Web Development", "Which tag is used for a form?", "<form>", "<input>", "<fieldset>", "<submit>", "option1", "Easy"));
        questions.add(createQ("Web Development", "Which input type is used for email?", "text", "mail", "email", "address", "option3", "Easy"));
        questions.add(createQ("Web Development", "Which tag defines a line break?", "<br>", "<lb>", "<break>", "<newline>", "option1", "Easy"));
        questions.add(createQ("Web Development", "Which of these is a JavaScript framework?", "React", "Laravel", "Django", "Flask", "option1", "Medium"));
        questions.add(createQ("Web Development", "Which HTTP status code means 'Not Found'?", "200", "301", "404", "500", "option3", "Easy"));
        questions.add(createQ("Web Development", "Which tag is used for bold text?", "<strong>", "<b>", "<bold>", "<em>", "option2", "Easy"));
        questions.add(createQ("Web Development", "Which tag is used for italic text?", "<italic>", "<i>", "<it>", "<em>", "option2", "Easy"));
        questions.add(createQ("Web Development", "Which attribute is used for alternate text in image?", "title", "alt", "src", "name", "option2", "Easy"));
        questions.add(createQ("Web Development", "Which CSS property adds a shadow to a box?", "box-shadow", "shadow", "text-shadow", "outline", "option1", "Medium"));
        questions.add(createQ("Web Development", "Which HTML5 element is used for navigation links?", "<navigate>", "<nav>", "<menu>", "<links>", "option2", "Medium"));
        questions.add(createQ("Web Development", "Which HTML5 element is used for footer?", "<bottom>", "<footer>", "<foot>", "<end>", "option2", "Easy"));
        questions.add(createQ("Web Development", "Which protocol is secure version of HTTP?", "HTTP", "HTTPS", "FTP", "SSH", "option2", "Easy"));
        questions.add(createQ("Web Development", "Which tag is used to embed JavaScript?", "<js>", "<script>", "<javascript>", "<code>", "option2", "Easy"));
        questions.add(createQ("Web Development", "Which method is used to select element by ID in JS?", "getElementById()", "querySelectorAll()", "getElementsByClassName()", "findById()", "option1", "Medium"));
        questions.add(createQ("Web Development", "JSON stands for?", "Java Source Object Notation", "JavaScript Object Notation", "Java Standard Object Notation", "None", "option2", "Medium"));

        // ==============================================================
        // 🔹 IMPORTANT TOPICS (50 questions)
        // ==============================================================
        questions.add(createQ("Important Topics", "Which sorting algorithm is stable?", "QuickSort", "MergeSort", "HeapSort", "SelectionSort", "option2", "Medium"));
        questions.add(createQ("Important Topics", "What is time complexity of binary search?", "O(n)", "O(log n)", "O(n log n)", "O(1)", "option2", "Easy"));
        questions.add(createQ("Important Topics", "Which data structure uses recursion?", "Stack", "Queue", "Tree", "Array", "option1", "Medium"));
        questions.add(createQ("Important Topics", "Which OS concept handles multiple processes?", "Multitasking", "Paging", "Scheduling", "None", "option1", "Easy"));
        questions.add(createQ("Important Topics", "SQL command to filter results?", "WHERE", "SELECT", "GROUP BY", "HAVING", "option1", "Easy"));
        questions.add(createQ("Important Topics", "Which data structure is used in BFS?", "Stack", "Queue", "Tree", "Heap", "option2", "Medium"));
        questions.add(createQ("Important Topics", "DFS uses which structure?", "Stack", "Queue", "Heap", "Graph", "option1", "Medium"));
        questions.add(createQ("Important Topics", "Which network model has 7 layers?", "OSI", "TCP/IP", "Hybrid", "None", "option1", "Easy"));
        questions.add(createQ("Important Topics", "Which layer handles routing?", "Network", "Transport", "Session", "Physical", "option1", "Easy"));
        questions.add(createQ("Important Topics", "What is encapsulation?", "Wrapping data", "Hiding details", "Polymorphism", "Inheritance", "option1", "Easy"));
        questions.add(createQ("Important Topics", "Normalization is used to?", "Remove redundancy", "Increase redundancy", "Add redundancy", "None", "option1", "Medium"));
        questions.add(createQ("Important Topics", "In Java, garbage collection manages?", "Memory", "CPU", "Threads", "Disk", "option1", "Easy"));
        questions.add(createQ("Important Topics", "DNS stands for?", "Domain Name System", "Data Network Server", "Domain Node Service", "None", "option1", "Easy"));
        questions.add(createQ("Important Topics", "Which protocol sends email?", "SMTP", "HTTP", "FTP", "POP", "option1", "Easy"));
        questions.add(createQ("Important Topics", "Which structure helps implement recursion?", "Stack", "Queue", "Array", "Graph", "option1", "Medium"));
        questions.add(createQ("Important Topics", "OSI full form?", "Open Systems Interconnection", "Online Server Interface", "Operating System Integration", "Open Signal Interface", "option1", "Easy"));
        questions.add(createQ("Important Topics", "What is binary tree?", "Each node ≤ 2 children", "Each node ≤ 3 children", "Linear list", "Cyclic graph", "option1", "Easy"));
        questions.add(createQ("Important Topics", "What is a deadlock?", "Process waiting forever", "Process completed", "Ready queue", "Cache miss", "option1", "Medium"));
        questions.add(createQ("Important Topics", "Which data structure uses LIFO?", "Stack", "Queue", "Tree", "Graph", "option1", "Easy"));
        questions.add(createQ("Important Topics", "OSI transport layer uses?", "TCP", "IP", "HTTP", "DNS", "option1", "Medium"));
        questions.add(createQ("Important Topics", "Compiler converts?", "Source to object", "Object to binary", "Code to data", "None", "option1", "Easy"));
        questions.add(createQ("Important Topics", "Which algorithm finds shortest path?", "Dijkstra", "Kruskal", "Prim", "Merge", "option1", "Medium"));
        questions.add(createQ("Important Topics", "Heap is used for?", "Priority queue", "Stack", "Sorting only", "Memory allocation", "option1", "Medium"));
        questions.add(createQ("Important Topics", "Which sorting is not comparison-based?", "Counting Sort", "MergeSort", "QuickSort", "HeapSort", "option1", "Hard"));
        questions.add(createQ("Important Topics", "What is Big-O notation?", "Time complexity", "Algorithm", "Compiler", "None", "option1", "Medium"));

        questions.add(createQ("Important Topics", "Which traversal of BST gives sorted order?", "Preorder", "Inorder", "Postorder", "Level order", "option2", "Medium"));
        questions.add(createQ("Important Topics", "Space complexity of array insertion at end?", "O(1)", "O(n)", "O(log n)", "O(n log n)", "option1", "Easy"));
        questions.add(createQ("Important Topics", "Which hashing technique handles collision by list?", "Open addressing", "Chaining", "Probing", "Rehashing", "option2", "Medium"));
        questions.add(createQ("Important Topics", "Which algorithm is used for minimum spanning tree?", "Dijkstra", "Kruskal", "Bellman-Ford", "Floyd", "option2", "Medium"));
        questions.add(createQ("Important Topics", "Postfix of (A+B)C?", "AB+C", "A+BC*", "ABC+", "AB+C", "option1", "Medium"));
        questions.add(createQ("Important Topics", "Which traversal uses queue?", "DFS", "BFS", "Preorder", "Inorder", "option2", "Easy"));
        questions.add(createQ("Important Topics", "Which protocol is used to transfer files?", "SMTP", "HTTP", "FTP", "DNS", "option3", "Easy"));
        questions.add(createQ("Important Topics", "Which layer in OSI is responsible for encryption?", "Physical", "Data Link", "Presentation", "Application", "option3", "Medium"));
        questions.add(createQ("Important Topics", "Best case time complexity of QuickSort?", "O(n)", "O(n log n)", "O(n^2)", "O(log n)", "option2", "Medium"));
        questions.add(createQ("Important Topics", "Which data structure is used in undo operation?", "Queue", "Stack", "Heap", "Graph", "option2", "Easy"));
        questions.add(createQ("Important Topics", "Which algorithm uses divide and conquer?", "QuickSort", "Dijkstra", "Prim", "Kruskal", "option1", "Medium"));
        questions.add(createQ("Important Topics", "What is latency?", "Processing speed", "Delay in response", "Bandwidth", "Throughput", "option2", "Medium"));
        questions.add(createQ("Important Topics", "Which protocol resolves IP to MAC?", "ARP", "RARP", "DHCP", "ICMP", "option1", "Medium"));
        questions.add(createQ("Important Topics", "Which address is used to identify network uniquely?", "IP address", "MAC address", "Port", "URL", "option1", "Easy"));
        questions.add(createQ("Important Topics", "In binary search tree, left child is?", "Greater than root", "Less than root", "Equal to root", "Unrelated", "option2", "Easy"));
        questions.add(createQ("Important Topics", "Which searching algorithm works on sorted data?", "Linear search", "Binary search", "Hashing", "DFS", "option2", "Easy"));
        questions.add(createQ("Important Topics", "Which algorithm detects negative weight cycles?", "Dijkstra", "Bellman-Ford", "Prim", "Kruskal", "option2", "Hard"));
        questions.add(createQ("Important Topics", "Which network device connects multiple networks?", "Hub", "Switch", "Router", "Repeater", "option3", "Medium"));
        questions.add(createQ("Important Topics", "What is throughput?", "Number of processes completed per unit time", "Delay in system", "Error rate", "Memory usage", "option1", "Medium"));
        questions.add(createQ("Important Topics", "Which memory is volatile?", "ROM", "Hard disk", "RAM", "CD-ROM", "option3", "Easy"));
        questions.add(createQ("Important Topics", "Which protocol assigns IP automatically?", "DNS", "DHCP", "ARP", "FTP", "option2", "Medium"));
        questions.add(createQ("Important Topics", "Stack overflow occurs when?", "Stack pointer out of range", "Queue empty", "Heap full", "Disk full", "option1", "Medium"));
        questions.add(createQ("Important Topics", "Which is not a type of linked list?", "Singly", "Doubly", "Circular", "Parallel", "option4", "Medium"));
        questions.add(createQ("Important Topics", "Time complexity of linear search?", "O(1)", "O(n)", "O(log n)", "O(n^2)", "option2", "Easy"));

        // ==============================================================
        // 🔹 GENERAL KNOWLEDGE (30 questions)
        // ==============================================================
        questions.add(createQ("General Knowledge", "Capital of India?", "New Delhi", "Mumbai", "Kolkata", "Chennai", "option1", "Easy"));
        questions.add(createQ("General Knowledge", "Currency of Japan?", "Yen", "Won", "Rupee", "Dollar", "option1", "Easy"));
        questions.add(createQ("General Knowledge", "Taj Mahal is located in?", "Delhi", "Agra", "Jaipur", "Mumbai", "option2", "Easy"));
        questions.add(createQ("General Knowledge", "Who is known as the Father of the Nation (India)?", "Subhas Chandra Bose", "Mahatma Gandhi", "Jawaharlal Nehru", "Bhagat Singh", "option2", "Easy"));
        questions.add(createQ("General Knowledge", "Largest continent in the world?", "Africa", "Europe", "Asia", "Australia", "option3", "Easy"));
        questions.add(createQ("General Knowledge", "Which ocean is the largest?", "Indian Ocean", "Pacific Ocean", "Atlantic Ocean", "Arctic Ocean", "option2", "Easy"));
        questions.add(createQ("General Knowledge", "The national animal of India is?", "Lion", "Elephant", "Tiger", "Peacock", "option3", "Easy"));
        questions.add(createQ("General Knowledge", "The national flower of India?", "Rose", "Lotus", "Jasmine", "Sunflower", "option2", "Easy"));
        questions.add(createQ("General Knowledge", "Which planet is known as the Red Planet?", "Venus", "Mars", "Jupiter", "Saturn", "option2", "Easy"));
        questions.add(createQ("General Knowledge", "Which is the smallest prime number?", "0", "1", "2", "3", "option3", "Easy"));
        questions.add(createQ("General Knowledge", "Who invented the telephone?", "Alexander Graham Bell", "Thomas Edison", "Newton", "Einstein", "option1", "Easy"));
        questions.add(createQ("General Knowledge", "Gateway of India is in?", "Mumbai", "Kolkata", "Chennai", "Delhi", "option1", "Easy"));
        questions.add(createQ("General Knowledge", "Which country is called the Land of Rising Sun?", "India", "Japan", "China", "Korea", "option2", "Easy"));
        questions.add(createQ("General Knowledge", "Largest democracy in the world?", "USA", "India", "UK", "Russia", "option2", "Easy"));
        questions.add(createQ("General Knowledge", "Who wrote 'Jana Gana Mana'?", "Rabindranath Tagore", "Bankim Chandra", "Sarojini Naidu", "Tagore & Naidu", "option1", "Easy"));
        questions.add(createQ("General Knowledge", "Which of these is a Union Territory?", "Bihar", "Ladakh", "Gujarat", "Punjab", "option2", "Easy"));
        questions.add(createQ("General Knowledge", "World Environment Day is celebrated on?", "5 June", "5 July", "15 August", "1 May", "option1", "Easy"));
        questions.add(createQ("General Knowledge", "Which country hosted the 2016 Summer Olympics?", "China", "Brazil", "UK", "Russia", "option2", "Medium"));
        questions.add(createQ("General Knowledge", "Who discovered gravity?", "Einstein", "Newton", "Galileo", "Kepler", "option2", "Easy"));
        questions.add(createQ("General Knowledge", "Which gas do plants absorb during photosynthesis?", "Oxygen", "Carbon dioxide", "Nitrogen", "Hydrogen", "option2", "Easy"));
        questions.add(createQ("General Knowledge", "Which is the longest river in the world?", "Ganga", "Amazon", "Nile", "Yangtze", "option3", "Medium"));
        questions.add(createQ("General Knowledge", "Which Indian city is called Silicon Valley of India?", "Hyderabad", "Pune", "Bengaluru", "Chennai", "option3", "Medium"));
        questions.add(createQ("General Knowledge", "Which day is celebrated as Teachers' Day in India?", "5 September", "14 November", "26 January", "15 August", "option1", "Easy"));
        questions.add(createQ("General Knowledge", "First woman Prime Minister of India?", "Indira Gandhi", "Sarojini Naidu", "Pratibha Patil", "Sonia Gandhi", "option1", "Medium"));
        questions.add(createQ("General Knowledge", "Which Indian state has the longest coastline?", "Gujarat", "Tamil Nadu", "Andhra Pradesh", "Kerala", "option1", "Medium"));
        questions.add(createQ("General Knowledge", "Which festival is called Festival of Lights?", "Holi", "Diwali", "Eid", "Christmas", "option2", "Easy"));
        questions.add(createQ("General Knowledge", "Largest desert in the world?", "Thar", "Sahara", "Gobi", "Kalahari", "option2", "Medium"));
        questions.add(createQ("General Knowledge", "Which is the highest civilian award in India?", "Padma Shri", "Padma Bhushan", "Padma Vibhushan", "Bharat Ratna", "option4", "Medium"));
        questions.add(createQ("General Knowledge", "Where is India’s Space Research Organisation (ISRO) headquarter?", "New Delhi", "Chennai", "Bengaluru", "Hyderabad", "option3", "Medium"));

        // ==============================================================
        // 🔹 HEALTH CARE (30 questions)
        // ==============================================================
        questions.add(createQ("Health Care", "Normal body temperature in Celsius?", "36.5–37.5°C", "30–31°C", "40–41°C", "25–26°C", "option1", "Easy"));
        questions.add(createQ("Health Care", "Which vitamin is produced in the skin by sunlight?", "Vitamin A", "Vitamin B12", "Vitamin C", "Vitamin D", "option4", "Easy"));
        questions.add(createQ("Health Care", "Which organ pumps blood in the body?", "Lungs", "Kidney", "Heart", "Liver", "option3", "Easy"));
        questions.add(createQ("Health Care", "Good source of Vitamin C?", "Rice", "Orange", "Potato", "Bread", "option2", "Easy"));
        questions.add(createQ("Health Care", "What does BMI stand for?", "Body Mass Index", "Basic Muscle Intake", "Blood Measure Index", "Body Measure Indicator", "option1", "Easy"));
        questions.add(createQ("Health Care", "Which mineral is important for strong bones?", "Iron", "Calcium", "Sodium", "Iodine", "option2", "Easy"));
        questions.add(createQ("Health Care", "Disease caused by mosquito bite?", "Tuberculosis", "Malaria", "Diabetes", "Asthma", "option2", "Easy"));
        questions.add(createQ("Health Care", "Which organ is affected by hepatitis?", "Heart", "Liver", "Lungs", "Kidney", "option2", "Medium"));
        questions.add(createQ("Health Care", "Which blood group is universal donor?", "A", "B", "AB", "O", "option4", "Medium"));
        questions.add(createQ("Health Care", "Which disease is caused by deficiency of insulin?", "Hypertension", "Diabetes Mellitus", "Anemia", "Goitre", "option2", "Medium"));
        questions.add(createQ("Health Care", "How many glasses of water are generally recommended per day?", "1–2", "2–3", "6–8", "12–14", "option3", "Easy"));
        questions.add(createQ("Health Care", "Which vitamin is important for good eyesight?", "Vitamin A", "Vitamin C", "Vitamin D", "Vitamin K", "option1", "Easy"));
        questions.add(createQ("Health Care", "High blood pressure is also called?", "Hypotension", "Hypertension", "Hyperglycemia", "Hypoglycemia", "option2", "Medium"));
        questions.add(createQ("Health Care", "Which nutrient is the main source of energy?", "Proteins", "Carbohydrates", "Vitamins", "Minerals", "option2", "Easy"));
        questions.add(createQ("Health Care", "Which organ filters blood to form urine?", "Heart", "Liver", "Kidney", "Pancreas", "option3", "Easy"));
        questions.add(createQ("Health Care", "RBC stands for?", "Red Blood Cells", "Rapid Blood Count", "Rich Blood Cells", "Ready Blood Components", "option1", "Easy"));
        questions.add(createQ("Health Care", "Disease caused by deficiency of iodine?", "Scurvy", "Rickets", "Goitre", "Night Blindness", "option3", "Medium"));
        questions.add(createQ("Health Care", "Which vaccine was widely used against COVID-19 in India?", "Polio", "Covishield/Covaxin", "BCG", "MMR", "option2", "Easy"));
        questions.add(createQ("Health Care", "Which habit is a major risk factor for lung cancer?", "Reading", "Walking", "Smoking", "Drinking water", "option3", "Easy"));
        questions.add(createQ("Health Care", "Which exercise is best for heart health?", "Sleeping", "Brisk walking/jogging", "Watching TV", "Sitting", "option2", "Easy"));
        questions.add(createQ("Health Care", "Which test is used to measure blood sugar level?", "ECG", "MRI", "CT Scan", "Blood glucose test", "option4", "Medium"));
        questions.add(createQ("Health Care", "Which organ helps in breathing?", "Stomach", "Lungs", "Kidneys", "Pancreas", "option2", "Easy"));
        questions.add(createQ("Health Care", "Which vitamin deficiency causes rickets?", "Vitamin A", "Vitamin D", "Vitamin C", "Vitamin K", "option2", "Medium"));
        questions.add(createQ("Health Care", "What should you do first in case of minor cut?", "Ignore it", "Wash with clean water", "Tie very tight rope", "Pour strong acid", "option2", "Easy"));
        questions.add(createQ("Health Care", "Which organ detoxifies chemicals and drugs in the body?", "Heart", "Liver", "Lungs", "Brain", "option2", "Medium"));
        questions.add(createQ("Health Care", "Which of these is a lifestyle disease?", "Measles", "Chickenpox", "Diabetes Type 2", "Typhoid", "option3", "Medium"));
        questions.add(createQ("Health Care", "Which measure indicates obesity?", "High height", "High BMI", "Low pulse", "Low BP", "option2", "Medium"));
        questions.add(createQ("Health Care", "How many chambers are there in human heart?", "2", "3", "4", "5", "option3", "Easy"));
        questions.add(createQ("Health Care", "Which blood component helps in clotting?", "RBC", "WBC", "Platelets", "Plasma", "option3", "Medium"));
        questions.add(createQ("Health Care", "Which disease is prevented by ORS (Oral Rehydration Solution)?", "Dehydration due to diarrhea", "Asthma", "Cancer", "Arthritis", "option1", "Medium"));

        // ==============================================================
        // 🔹 APTITUDE (30 questions)
        // ==============================================================
        questions.add(createQ("Aptitude", "What is 15% of 200?", "25", "30", "35", "40", "option2", "Easy"));
        questions.add(createQ("Aptitude", "If a shirt costs ₹500 and discount is 20%, selling price is?", "₹400", "₹450", "₹480", "₹520", "option1", "Easy"));
        questions.add(createQ("Aptitude", "Simplify: 8 × 7 ÷ 4", "12", "13", "14", "15", "option3", "Easy"));
        questions.add(createQ("Aptitude", "What is the next number in 2, 4, 8, 16, ?", "20", "24", "30", "32", "option4", "Easy"));
        questions.add(createQ("Aptitude", "If 3x = 27, what is x?", "3", "6", "9", "12", "option2", "Easy"));
        questions.add(createQ("Aptitude", "A train covers 120 km in 3 hours. Speed?", "20 km/h", "30 km/h", "40 km/h", "60 km/h", "option3", "Easy"));
        questions.add(createQ("Aptitude", "The ratio 2:3 is equivalent to?", "4:7", "6:9", "8:10", "10:14", "option2", "Easy"));
        questions.add(createQ("Aptitude", "What is 3/4 of 80?", "40", "50", "60", "30", "option3", "Easy"));
        questions.add(createQ("Aptitude", "If SP = ₹900, Profit = 20%, Cost Price?", "₹650", "₹700", "₹750", "₹800", "option3", "Medium"));
        questions.add(createQ("Aptitude", "Average of 10, 20, 30, 40?", "20", "25", "30", "35", "option2", "Easy"));
        questions.add(createQ("Aptitude", "Find the missing number: 5, 10, 20, 40, ?", "60", "70", "80", "90", "option3", "Easy"));
        questions.add(createQ("Aptitude", "A and B together can do a work in 10 days. A alone does it in 25 days. B alone will do it in?", "15 days", "16.67 days", "20 days", "30 days", "option2", "Medium"));
        questions.add(createQ("Aptitude", "Simple interest on ₹1000 at 10% per annum for 2 years?", "₹100", "₹150", "₹200", "₹250", "option3", "Easy"));
        questions.add(createQ("Aptitude", "If perimeter of a square is 40 cm, its side is?", "5 cm", "8 cm", "10 cm", "12 cm", "option2", "Easy"));
        questions.add(createQ("Aptitude", "0.75 expressed as fraction is?", "3/4", "1/3", "2/5", "4/5", "option1", "Easy"));
        questions.add(createQ("Aptitude", "Two numbers are in ratio 3:5 and sum is 40. Bigger number?", "15", "20", "25", "30", "option3", "Medium"));
        questions.add(createQ("Aptitude", "If x – 5 = 9, then x is?", "3", "9", "14", "15", "option3", "Easy"));
        questions.add(createQ("Aptitude", "The next odd number after 99 is?", "100", "101", "102", "103", "option2", "Easy"));
        questions.add(createQ("Aptitude", "Cube of 5 is?", "15", "25", "100", "125", "option4", "Easy"));
        questions.add(createQ("Aptitude", "A person walks 4 km east and then 3 km north. Distance from start?", "3 km", "4 km", "5 km", "7 km", "option3", "Medium"));
        questions.add(createQ("Aptitude", "40% of a number is 32. Number is?", "60", "70", "80", "90", "option3", "Medium"));
        questions.add(createQ("Aptitude", "If CP = ₹500 and loss is 10%, SP is?", "₹450", "₹400", "₹550", "₹600", "option1", "Easy"));
        questions.add(createQ("Aptitude", "What is the LCM of 6 and 8?", "12", "18", "24", "48", "option3", "Easy"));
        questions.add(createQ("Aptitude", "Square root of 196?", "12", "13", "14", "15", "option3", "Easy"));
        questions.add(createQ("Aptitude", "If 5 pens cost ₹75, cost of 8 pens?", "₹100", "₹110", "₹115", "₹120", "option4", "Medium"));
        questions.add(createQ("Aptitude", "A clock shows 3:15. Angle between hour and minute hand?", "0°", "7.5°", "15°", "30°", "option2", "Hard"));
        questions.add(createQ("Aptitude", "The sum of interior angles of a triangle?", "90°", "120°", "180°", "270°", "option3", "Easy"));
        questions.add(createQ("Aptitude", "If 12 men finish work in 8 days, how many men for same work in 6 days (same rate)?", "10", "14", "16", "18", "option2", "Medium"));
        questions.add(createQ("Aptitude", "What is 7/8 in percentage?", "75%", "80%", "85%", "90%", "option2", "Medium"));
        questions.add(createQ("Aptitude", "If a=2, b=3 then value of a² + b²?", "9", "10", "11", "13", "option2", "Easy"));

        // ==============================================================
        // 🔹 PUSH TO FIRESTORE
        // ==============================================================
        for (Map<String, Object> q : questions) {
            db.collection("quizQuestions")
                    .add(q)
                    .addOnSuccessListener(ref -> Log.d(TAG, "✅ Added: " + ref.getId()))
                    .addOnFailureListener(e -> Log.e(TAG, "❌ Failed: ", e));
        }

        Log.d(TAG, "🎯 All questions push triggered! (Total: " + questions.size() + ")");
    }

    // 🔹 Helper
    private Map<String, Object> createQ(String topic, String question,
                                        String option1, String option2, String option3, String option4,
                                        String answer, String difficulty) {

        Map<String, Object> q = new HashMap<>();
        q.put("topic", topic);
        q.put("question", question);
        q.put("option1", option1);
        q.put("option2", option2);
        q.put("option3", option3);
        q.put("option4", option4);
        q.put("answer", answer);       // "option1"..."option4"
        q.put("difficulty", difficulty);
        return q;
    }
}