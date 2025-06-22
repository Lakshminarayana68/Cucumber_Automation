pipeline {
    agent any

    tools {
        maven 'Maven_Home'
        jdk 'Java_Home'
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Choose browser')
        string(name: 'TAGS', defaultValue: '@signup', description: 'Cucumber tags to run')
    }

    environment {
        REPORT_DIR = "target/Reports"  // Updated to match report archive location
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        timestamps()
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "Cloning repository..."
                checkout scm
            }
        }

        stage('Build Project') {
            steps {
                echo "Building project using Maven..."
                bat 'mvn clean compile'
            }
        }

        stage('Execute Tests') {
            steps {
                echo "Running tests on ${params.BROWSER} with tags ${params.TAGS}"
                bat "mvn test -Dbrowser=${params.BROWSER} -Dcucumber.filter.tags=${params.TAGS}"
            }
        }

        stage('Retry on Failure') {
            when {
                expression { currentBuild.result == 'FAILURE' }
            }
            steps {
                echo "Retrying failed test run once..."
                retry(1) {
                    bat "mvn test -Dbrowser=${params.BROWSER} -Dcucumber.filter.tags=${params.TAGS}"
                }
            }
        }

        stage('Archive Test Reports') {
            steps {
                echo "Archiving ExtentReports and test results..."
                archiveArtifacts artifacts: "${env.REPORT_DIR}/ExtentReport.html", allowEmptyArchive: true
                junit 'target\\surefire-reports\\*.xml'
            }
        }

        stage('Send Email Notification') {
            steps {
                echo "Sending email with attached test report..."
                emailext(
                    subject: "Jenkins Build: ${currentBuild.currentResult} - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """
                        <p>Hello Team,</p>
                        <p><b>Build Status:</b> ${currentBuild.currentResult}</p>
                        <p><b>Project:</b> ${env.JOB_NAME}</p>
                        <p><b>Build URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                        <p><b>Browser:</b> ${params.BROWSER}</p>
                        <p><b>Tags:</b> ${params.TAGS}</p>
                        <p><b>Extent Report:</b> <a href="${env.BUILD_URL}artifact/${env.REPORT_DIR}/ExtentReport.html">Click to View</a></p>
                        <p>Regards,<br>Jenkins</p>
                    """,
                    mimeType: 'text/html',
                    attachmentsPattern: "${env.REPORT_DIR}/ExtentReport.html",
                    to: 'adityasr2278@gmail.com'
                )
            }
        }
    }

    post {
        always {
            echo "Cleaning workspace..."
            cleanWs()
        }
        success {
            echo "Build completed successfully."
        }
        failure {
            echo "Build failed. Please check logs and reports."
        }
    }
}
