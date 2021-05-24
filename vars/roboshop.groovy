def call(Map params = [:]) {
  // start Default Arguments
  def args = [
         NEXUS_IP             : '10.1.2.210',
  ]
  args  << params
   
   // End Default + Required Arguments

  pipeline {
    agent {
      label "${args.SLAVE_LABEL}"
    }

  environment {
    COMPONENT       =  "${args.COMPONENT}"
    NEXUS_IP        =  "${args.NEXUS_IP}"
    PROJECT_NAME    =  "${args.PROJECT_NAME}"
    SLAVE_LABEL     =  "${args.SLAVE_LABEL}"
    APP_TYPE        =   "${args.APP_TYPE}"
  }

  stages {

    stage('Prepare Artifacts - NGINX') {
      when {
        environment name: 'APP_TYPE', value: 'NGINX'
        
      } 


      steps {
         sh '''
           cd static
           zip -r ../${COMPONENT}.zip *
        '''     
      }  
    }
    stage('Download Dependencies') {
	    when {
	      environment name: 'APP_TYPE', value: 'NODEJS'
	  	}
	
	    steps {
	      sh '''
		    npm install
		  '''  
		  }
	 }	
      
    stage('Prepare Artifacts - NODE.JS') {    
      when {
	      environment name: 'APP_TYPE', value: 'NODEJS' 
      }
 		
      steps {
	    sh '''
		  zip -r user.zip node_modules server.js
    '''     
      }  
    }

    stage('Upload Artifacts - user') {
      steps {
        sh '''
         curl -f -v -u admin:admin --upload-file user.zip http://10.1.2.210:8081/repository/user/user.zip
        '''
      }
    }

    stage('Upload Artifacts') {
      steps {
        sh '''
         curl -f -v -u admin:admin --upload-file frontend.zip http://${NEXUS_IP}:8081/repository/Frontend/frontend.zip
        '''
      }
    }
   }  
  }
} 