pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
               echo 'Compiling the java source code'
                bat 'mvn compile'
                }
             }

        stage('Test') {
            steps {
                echo 'Running the compiled java code.'
                bat 'mvn test'
                }
             }

        stage('Reports') {
            steps {

                echo  'JUnit reporting the test results'
                junit(
                      allowEmptyResults: true,
                      testResults: '**/target/surefire-reports/*.xml'
                     )


                }
             }
           }
        }