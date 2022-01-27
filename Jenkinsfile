pipeline {
    agent  {label 'mercy'}
 stages {
  stage('Docker Build and Tag') {
           steps {
              
                sh  'sudo chmod 777 /var/run/docker.sock'
                sh 'docker build -t nginx:latest .' 
                
                  sh 'docker tag nginx sasender/nginx:latest'
                sh 'docker tag nginx sasender/nginx:$BUILD_NUMBER'
               
          }
        }
     
  stage('Publish image to Docker Hub') {
          
            steps {
        withDockerRegistry([ credentialsId: "dockerhub", url: "" ]) {
          sh  'docker push sasender/nginx:latest'
          sh  'docker push sasender/nginx:$BUILD_NUMBER' 
        }
                  
          }
        }
     
      stage('Run Docker container on Jenkins Agent') {
             
            steps {
                sh "docker run -d -p 4030:80 sasender/nginx"
 
            }
        }
 stage('Run Docker container on remote hosts') {
             
            steps {
                sh "docker -H ssh://jenkins@172.31.89.10 run -d -p 4001:80 sasender/nginx"
 
            }
        }
    }
}