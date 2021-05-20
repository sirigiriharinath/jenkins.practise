pipelinejob('frontend') {
  configure { flowdefintion ->
    flowdefintion << delegate.'defination'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmflowDefination',plugin:'workflow-cps'){
      'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git'){
        'userRemoteConfigs' {
          'hudson.plugins.git.UserRemoteconfig' {
              'url'('https://github.com/sasender/frontend.git')
          
          }  
        } 
        'branches' {
           'hudson.plugins.git.Branchspec' {
              'name'('*/main')
           } 
        }
      }
      'scriptpath'('jenkins.practise')
      'lightweight' (true)  
    }
  }  
}