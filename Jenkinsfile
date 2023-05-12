pipeline {
    agent any
    stages {
        stage('Build test code') {
            steps {
                bat 'mvn clean install -DskipTests' // Build tests
            }
        }
        stage('Run selenium grid') {
            steps {
                bat 'docker compose up -d' // Run Docker Selenium
            }
        }
        stage('Execute test') {
            steps {
                bat 'mvn test' // Run tests
                bat 'docker compose down' // Shut down Docker Selenium
            }
        }
    }
    post {
        always {
            script { // Generate Allure report
                allure([
                        includeProperties: false,
                        jdk              : '',
                        properties       : [],
                        reportBuildPolicy: 'ALWAYS',
                        results          : [[path: 'target/allure-results']]
                ])
            }
        }
    }
}