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

        stage('Copy WAR to Liberty') {
            steps {
                echo "Copie du WAR vers Liberty dropins..."
                sh "cp ${WAR_FILE} /liberty-apps/infra-demo.war"
                echo "WAR déposé dans /liberty-apps/"
            }
        }

        stage('Deploy via Ansible') {
            steps {
                echo "Déploiement sur Liberty Core via Ansible..."
                // Cette étape sera complétée lors de la mise en place d'Ansible
                echo "WAR prêt : ${WAR_FILE} — en attente playbook Ansible"
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
