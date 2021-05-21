def call() {
  pipeline {
  agent any

  stages {

    stage('prepare artifacts') {
      steps {
         sh '''
           cd static
           zip ../frontend.zip "
        ...     
      }  
    }

    stage('Upload Artifacts') {
      steps {
        sh '''
         curl -v -u admin:admin --upload-file frontend.zip http://10.1.2.210:8081/nexus/repository/Frontend/frontend.zip
        '''
      }
    }
   }  
  }
} 