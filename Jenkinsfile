pipeline {
    agent any
    
    stages {
        stage("Git clone") {
            steps {
                //git clone  
                deleteDir()
                echo 'Clone the latest code from the code-base'
                'git clone https://github.com/prasadkarri1988/heycar.git'       
            }
            
        }
        stage("Testcases") {
            steps {
                //Execute testcases 
                echo 'Execute test cases'
                dir("heycar"){
                     'mvn clean test' 
                }               
            }
            
        }
        stage("Maven Build") {
            steps {
                echo 'Execute Maven Build'
                dir("heycar"){
                     'mvn clean package'
                }
            }
            
        }
       
      
    }
}
         
