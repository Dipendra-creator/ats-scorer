package com.student.ats_scorer.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Ats {
  @PostConstruct
  public void Preprocess() {
    System.out.println("Preprocessing ATS data...");
  }

  private Map<String, Map<String, Map<String, List<String>>>> ats;

  public Ats() {
    ats = new HashMap<>();
    setDefaultAts();
  }

  private void setDefaultAts() {
    ats.put("Programming Languages", Map.of(
        "Languages", Map.of(
            "keywords", List.of(
                "Python", "Java", "C", "C++", "C#", "JavaScript", "TypeScript", "Ruby", "Swift", "Kotlin",
                "Go", "Rust", "PHP", "Perl", "Haskell", "Scala", "R", "MATLAB", "Lua", "Dart", "Elixir",
                "Erlang", "Objective-C", "Visual Basic", "F#", "Clojure", "Groovy", "Assembly", "Fortran",
                "COBOL"
            )
        )
    ));

    ats.put("Web Development", Map.of(
        "Frontend", Map.of(
            "keywords", List.of(
                "HTML", "CSS", "JavaScript", "TypeScript", "React", "Angular", "Vue.js", "Svelte",
                "Bootstrap", "Tailwind CSS", "Material UI", "Redux", "Next.js", "Gatsby", "jQuery",
                "LESS", "SCSS", "WebAssembly"
            )
        ),
        "Backend", Map.of(
            "keywords", List.of(
                "Node.js", "Express", "Django", "Flask", "Ruby on Rails", "Laravel", "ASP.NET", "Spring Boot",
                "Phoenix", "Koa", "Symfony", "Hapi", "Meteor", "FastAPI", "GraphQL", "RESTful API", "Socket.io"
            )
        ),
        "Full Stack & Misc", Map.of(
            "keywords", List.of(
                "MEAN", "MERN", "LAMP", "JAMstack", "Serverless", "Progressive Web App", "Web Components"
            )
        )
    ));

    ats.put("Data Science & Analytics", Map.of(
        "Programming & Scripting", Map.of(
            "keywords", List.of(
                "Python", "R", "Julia", "SQL", "SAS", "SPSS"
            )
        ),
        "Libraries & Frameworks", Map.of(
            "keywords", List.of(
                "Pandas", "NumPy", "SciPy", "Scikit-Learn", "Matplotlib", "Seaborn", "Plotly", "TensorFlow",
                "PyTorch", "Keras", "NLTK", "SpaCy", "Statsmodels", "XGBoost", "LightGBM", "CatBoost"
            )
        ),
        "Tools & Platforms", Map.of(
            "keywords", List.of(
                "Jupyter", "RStudio", "Tableau", "Power BI", "Excel", "Google Data Studio", "RapidMiner", "Apache Superset"
            )
        ),
        "Big Data", Map.of(
            "keywords", List.of(
                "Hadoop", "Spark", "Kafka", "Hive", "Pig", "Flink", "Cassandra", "MongoDB", "Elasticsearch",
                "Presto", "Impala"
            )
        )
    ));

    ats.put("Machine Learning & AI", Map.of(
        "Frameworks", Map.of(
            "keywords", List.of(
                "TensorFlow", "PyTorch", "Keras", "Scikit-Learn", "MXNet", "Caffe", "Theano", "CNTK", "Chainer"
            )
        ),
        "Techniques & Algorithms", Map.of(
            "keywords", List.of(
                "Supervised Learning", "Unsupervised Learning", "Reinforcement Learning", "Deep Learning",
                "Neural Networks", "Convolutional Neural Networks", "Recurrent Neural Networks", "Decision Trees",
                "Random Forest", "Gradient Boosting", "Support Vector Machines", "Clustering", "Dimensionality Reduction",
                "Ensemble Learning"
            )
        ),
        "Applications & Use Cases", Map.of(
            "keywords", List.of(
                "Computer Vision", "Natural Language Processing", "Speech Recognition", "Recommendation Systems",
                "Time Series Analysis", "Anomaly Detection", "Predictive Analytics", "Robotics", "Autonomous Vehicles"
            )
        )
    ));

    ats.put("Database Technologies", Map.of(
        "Relational Databases", Map.of(
            "keywords", List.of(
                "MySQL", "PostgreSQL", "Oracle", "SQL Server", "SQLite", "MariaDB", "DB2"
            )
        ),
        "NoSQL Databases", Map.of(
            "keywords", List.of(
                "MongoDB", "Redis", "Cassandra", "CouchDB", "Neo4j", "DynamoDB", "HBase", "Couchbase", "Firebase"
            )
        ),
        "Data Warehousing", Map.of(
            "keywords", List.of(
                "Snowflake", "BigQuery", "Amazon Redshift", "Teradata", "SAP HANA", "Vertica", "Exadata"
            )
        ),
        "ORM & Data Access", Map.of(
            "keywords", List.of(
                "SQLAlchemy", "Hibernate", "Entity Framework", "ActiveRecord", "Doctrine", "Dapper"
            )
        )
    ));

    ats.put("Cloud Computing & DevOps", Map.of(
        "Cloud Providers", Map.of(
            "keywords", List.of(
                "AWS", "Azure", "Google Cloud", "IBM Cloud", "Oracle Cloud", "DigitalOcean", "Heroku", "Alibaba Cloud"
            )
        ),
        "Containers & Orchestration", Map.of(
            "keywords", List.of(
                "Docker", "Kubernetes", "OpenShift", "Containerization", "LXC", "rkt"
            )
        ),
        "Infrastructure & Automation", Map.of(
            "keywords", List.of(
                "Terraform", "Ansible", "Chef", "Puppet", "CloudFormation", "Jenkins", "GitLab CI/CD", "Travis CI",
                "CircleCI", "Bamboo", "SaltStack"
            )
        ),
        "Serverless & Functions", Map.of(
            "keywords", List.of(
                "AWS Lambda", "Azure Functions", "Google Cloud Functions", "FaaS", "Serverless Framework", "OpenFaaS"
            )
        )
    ));

    ats.put("Software Engineering & Methodologies", Map.of(
        "Development Processes", Map.of(
            "keywords", List.of(
                "Agile", "Scrum", "Kanban", "Waterfall", "XP", "Test Driven Development", "Behavior Driven Development",
                "Continuous Integration", "Continuous Delivery", "CI/CD"
            )
        ),
        "Tools & Collaboration", Map.of(
            "keywords", List.of(
                "Git", "SVN", "JIRA", "Confluence", "Trello", "Asana", "Slack", "Microsoft Teams", "Visual Studio Code",
                "Eclipse", "IntelliJ IDEA", "Atom", "Sublime Text"
            )
        ),
        "Design & Architecture", Map.of(
            "keywords", List.of(
                "UML", "Design Patterns", "SOLID", "DRY", "KISS", "YAGNI", "Microservices", "Monolithic",
                "Service-Oriented Architecture", "REST", "GraphQL", "gRPC", "Event-Driven Architecture"
            )
        )
    ));

    ats.put("Cybersecurity", Map.of(
        "Fundamentals", Map.of(
            "keywords", List.of(
                "Cybersecurity", "Information Security", "Encryption", "Authentication", "Authorization", "Firewall",
                "Antivirus", "Malware"
            )
        ),
        "Testing & Assessment", Map.of(
            "keywords", List.of(
                "Penetration Testing", "Vulnerability Assessment", "Security Audits", "Risk Management",
                "Incident Response", "SOC", "Zero Trust", "Multi-factor Authentication"
            )
        ),
        "Tools & Technologies", Map.of(
            "keywords", List.of(
                "Wireshark", "Metasploit", "Nmap", "Burp Suite", "Kali Linux", "Snort", "OSSEC", "Splunk",
                "OpenVAS", "John the Ripper"
            )
        ),
        "Standards & Compliance", Map.of(
            "keywords", List.of(
                "ISO 27001", "NIST", "GDPR", "HIPAA", "PCI-DSS", "CIS Controls", "SOC 2", "FISMA"
            )
        )
    ));

    ats.put("Mobile Development", Map.of(
        "Platforms", Map.of(
            "keywords", List.of(
                "Android", "iOS", "Windows Mobile", "BlackBerry", "Tizen", "HarmonyOS"
            )
        ),
        "Frameworks", Map.of(
            "keywords", List.of(
                "React Native", "Flutter", "Xamarin", "Ionic", "Cordova", "SwiftUI", "Jetpack Compose",
                "NativeScript"
            )
        ),
        "Languages", Map.of(
            "keywords", List.of(
                "Java", "Kotlin", "Swift", "Objective-C", "Dart", "C#", "JavaScript"
            )
        )
    ));

    ats.put("Operating Systems & Networking", Map.of(
        "Operating Systems", Map.of(
            "keywords", List.of(
                "Linux", "Windows", "macOS", "Unix", "BSD", "Solaris", "Android OS", "iOS", "Fedora", "Red Hat", "Debian", "Ubuntu"
            )
        ),
        "Networking Fundamentals", Map.of(
            "keywords", List.of(
                "TCP/IP", "UDP", "DNS", "DHCP", "HTTP", "HTTPS", "FTP", "SSH", "VPN", "LAN", "WAN",
                "SDN", "VLAN", "Subnetting", "NAT", "Proxy"
            )
        ),
        "Protocols & Tools", Map.of(
            "keywords", List.of(
                "IPv4", "IPv6", "BGP", "OSPF", "RIP", "MPLS", "SNMP", "Wireshark", "NetFlow"
            )
        )
    ));

    ats.put("Emerging Technologies", Map.of(
        "Blockchain & Cryptocurrency", Map.of(
            "keywords", List.of(
                "Blockchain", "Bitcoin", "Ethereum", "Smart Contracts", "Hyperledger", "DeFi", "NFT", "Solidity", "DApp"
            )
        ),
        "Internet of Things (IoT)", Map.of(
            "keywords", List.of(
                "IoT", "Internet of Things", "MQTT", "Zigbee", "LoRaWAN", "Smart Home", "Edge Computing",
                "Wearables", "Sensor Networks"
            )
        ),
        "Quantum Computing", Map.of(
            "keywords", List.of(
                "Quantum Computing", "Qubit", "Superposition", "Quantum Algorithm", "Quantum Supremacy",
                "D-Wave", "IBM Q", "Quantum Entanglement", "Quantum Simulator"
            )
        ),
        "VR, AR & XR", Map.of(
            "keywords", List.of(
                "Virtual Reality", "Augmented Reality", "Mixed Reality", "Extended Reality", "Unity", "Unreal Engine",
                "Oculus", "HoloLens", "Vuforia", "ARKit", "ARCore"
            )
        ),
        "5G & Edge", Map.of(
            "keywords", List.of(
                "5G", "Edge Computing", "Fog Computing", "IoT Edge", "Latency", "Network Slicing", "C-RAN"
            )
        )
    ));
  }

  public Map<String, Map<String, Map<String, List<String>>>> getAts() {
    return ats;
  }

  @PreDestroy
  public void Cleanup() {
    System.out.println("Cleaning up ATS data...");
  }
}
