pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
    }


    environment {
        APP_NAME    = 'infra-demo'
        WAR_FILE    = "target/${APP_NAME}.war"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Récupération du code depuis GitHub..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Build du projet Maven..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo "Exécution des tests unitaires..."
                sh 'mvn test'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
                }
            }
        }

        stage('Archive WAR') {
            steps {
                echo "Archivage du WAR..."
                archiveArtifacts artifacts: "${WAR_FILE}", fingerprint: true
            }
        }

        stage('Deploy via Ansible') {
            steps {
                echo "Déploiement sur Liberty Core via Ansible..."
                sh """
                    docker run --rm \
                        -v /var/jenkins_home/workspace/infra-demo:/workspace \
                        -v /ansible:/ansible \
                        -v /liberty-dropins:/liberty-dropins \
                        cytopia/ansible:latest \
                        ansible-playbook /ansible/deploy.yml \
                            -i /ansible/inventory.ini \
                            -e war_src=/workspace/${WAR_FILE} \
                            -e liberty_dropins=/liberty-dropins
                """
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline terminé avec succès — ${APP_NAME}.war disponible"
        }
        failure {
            echo "❌ Pipeline échoué — vérifier les logs ci-dessus"
        }
    }
}
