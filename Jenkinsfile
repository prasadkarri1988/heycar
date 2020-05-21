pipeline {
    agent any
    
    stages {
        stage("Git clone") {
            steps {
                //git clone  
                deleteDir()
                echo 'Clone the latest code from the code-base'
                sh 'git clone https://github.com/prasadkarri1988/heycar.git'       
            }
            
        }
        stage("Testcases") {
            steps {
                //Execute testcases 
                echo 'Execute test cases'
                dir("heycar"){
                    sh 'mvn clean test' 
                }               
            }
            
        }
        stage("Maven Build") {
            steps {
                echo 'Execute Maven Build'
                dir("heycar"){
                    sh 'mvn clean package'
                }
            }
            
        }
        stage("Docker Build") {            
            steps {
                echo 'Execute Docker Build'
                dir("heycar"){
                    sh "docker build -t heycar:\"${env.BUILD_NUMBER}\" . "
                    docker push prasadkarri1988/heycar:version1
                    echo "Check the Docker image"
                    sh 'docker images'
                }
            }
            
        }
        stage("Deployment") {
            steps {
                dir("heycar"){
                    sh "docker run -d -p 80:8080 heycar:\"${env.BUILD_NUMBER}\""
                }
            }            
        }
      
    }//end stages
}