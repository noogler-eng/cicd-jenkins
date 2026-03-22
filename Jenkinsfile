// Jenkinsfile
// Yeh Jenkins pipeline define karta hai
// Groovy DSL (Domain Specific Language) mein likha hai

// pipeline block — poori pipeline yahan hoti hai
pipeline {

    // agent → kahan chalao pipeline
    // any → koi bhi available Jenkins agent
    agent any

    // tools → Jenkins ko batao kaunse tools chahiye
    tools {
        // 'Maven 3.9.5' → Jenkins mein configure kiya hua Maven
        maven 'Maven 3.9.5'
        // 'Java 17' → Jenkins mein configure kiya hua JDK
        jdk 'Java 17'
    }

    // environment → environment variables set karo
    // Poori pipeline mein available rahenge
    environment {
        // Project ka naam — stages mein use karenge
        APP_NAME = 'java-cicd-project'
        // JAR file ka naam
        JAR_FILE = 'target/java-cicd-project-1.0-SNAPSHOT.jar'
        // Build number automatically milta hai Jenkins se
        BUILD_VERSION = "1.0.${BUILD_NUMBER}"
        // BUILD_NUMBER → Jenkins automatically increment karta hai
    }

    // options → pipeline ke options
    options {
        // Agar pipeline 10 minute se zyada lage → fail karo
        timeout(time: 10, unit: 'MINUTES')
        // Last 5 builds ki history rakho
        buildDiscarder(logRotator(numToKeepStr: '5'))
        // Agar ek hi branch pe ek se zyada build chal rahi ho → pehli rok do
        disableConcurrentBuilds()
    }

    // stages → pipeline ke steps (GitHub Actions ke jobs jaisa)
    stages {

        // Stage 1 — Code checkout
        stage('Checkout') {
            steps {
                // git se code lao
                checkout scm
                // scm = Source Code Management — Jenkins ko Git URL pata hai
                // Automatically latest code download ho jaata hai

                // Build info print karo
                echo "=== Build ${BUILD_VERSION} shuru hua ==="
                echo "Branch: ${env.BRANCH_NAME}"
                echo "Commit: ${env.GIT_COMMIT}"
            }
        }

        // Stage 2 — Code compile karo
        stage('Compile') {
            steps {
                echo "=== Java Code Compile Kar Rahe Hain ==="

                // Maven se compile karo
                // -B → batch mode (logs mein download progress mat dikha)
                sh 'mvn -B compile'
                // sh → shell command chalao
                // Maven compile karega → .class files banayega
            }
        }

        // Stage 3 — Tests chalao
        stage('Test') {
            steps {
                echo "=== JUnit Tests Chal Rahe Hain ==="

                // Maven test phase chalao
                sh 'mvn -B test'
                // Sare JUnit tests chalenge
                // Koi test fail → stage fail → pipeline rok do
            }

            // post → stage ke baad kya karo
            post {
                // hamesha — pass ho ya fail
                always {
                    // JUnit test results publish karo Jenkins UI mein
                    junit 'target/surefire-reports/*.xml'
                    // Jenkins automatically graphs banata hai test results ke
                }
            }
        }

        // Stage 4 — Code Quality Check
        stage('Code Quality') {
            steps {
                echo "=== Code Quality Check ==="

                // Maven verify — checkstyle aur other checks
                sh 'mvn -B verify -DskipTests'
                // -DskipTests → tests skip karo (already chal chuke hain)
                // verify → packaging ke pehle sab checks chalao
            }
        }

        // Stage 5 — Package — JAR banao
        stage('Package') {
            steps {
                echo "=== JAR File Ban Rahi Hai ==="

                // Maven package → compile + test + JAR
                sh 'mvn -B clean package -DskipTests'
                // clean → pehle purani build hata do
                // package → naya JAR banao
                // -DskipTests → tests already ho chuke hain

                // JAR file ki info print karo
                sh 'ls -lh target/*.jar'
                // kitna bada hai JAR — dekho
            }
        }

        // Stage 6 — Archive — Build results save karo
        stage('Archive') {
            steps {
                echo "=== Build Results Archive Kar Rahe Hain ==="

                // JAR file Jenkins mein save karo
                // Download kar sako baad mein
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true,
                                 allowEmptyArchive: false
                // artifacts → kaunsi files save karo
                // fingerprint → MD5 hash save karo (verify ke liye)
                // allowEmptyArchive=false → agar file nahi mili → fail karo
            }
        }

        // Stage 7 — Deploy (sirf main branch pe)
        stage('Deploy') {
            // when → condition — kab yeh stage chalao
            when {
                // Sirf jab main branch pe ho
                branch 'main'
                // Agar feature branch pe hai → yeh stage skip karo
            }

            steps {
                echo "=== Production pe Deploy Kar Rahe Hain ==="
                echo "Version: ${BUILD_VERSION}"

                // Real mein yahan deployment script hogi
                // Abhi ke liye simulate karte hain
                sh """
                    echo "JAR file ready hai: ${JAR_FILE}"
                    echo "Deploy version: ${BUILD_VERSION}"
                    java -jar ${JAR_FILE}
                    echo "Deployment complete!"
                """
                // """ """ → multi-line shell script
                // ${VAR} → environment variable use karo Groovy mein
            }
        }

    }

    // post → poori pipeline ke baad kya karo
    post {

        // SUCCESS → pipeline pass hone pe
        success {
            echo "=== Pipeline SUCCESS! Build ${BUILD_VERSION} deploy ho gaya ==="
            // Real mein: Slack notification, email, etc.
        }

        // FAILURE → pipeline fail hone pe
        failure {
            echo "=== Pipeline FAILED! Dekho kya hua ==="
            // Real mein: Team ko email, Slack alert, PagerDuty alert
        }

        // ALWAYS → hamesha — pass ya fail
        always {
            echo "=== Pipeline complete — cleanup kar rahe hain ==="
            // Workspace cleanup karo
            cleanWs()
            // cleanWs() → build ke baad files delete karo — disk space bachao
        }

    }
}


