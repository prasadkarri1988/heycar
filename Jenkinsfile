pipeline {
    agent any
    
    stages {
        stage("Git clone") {
            steps {
                //git clone  
                deleteDir()
                echo 'Clone the latest code from the code-base'
               bat 'git clone https://github.com/prasadkarri1988/heycar.git'    
            }
            
        }
       
      
    }
}
         
