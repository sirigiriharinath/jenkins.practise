def call() {
  pipeline {
  agent any

  stages {

    stage('prepare artifacts') {
      steps {
         sh '''
           cd static
           zip ../frontend.zip *
        '''     
      }  
    }

    stage('Upload Artifacts') {
      steps {
        sh '''
         curl -f -v -u admin:admin --upload-file frontend.zip http://54.204.17.34:8081/repository/Frontend/frontend.zip
        '''
      }
    }
   }  
  }
} 