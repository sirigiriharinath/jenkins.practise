folder('ci-pipelines') {
  displayname ('Projecr A')
  description('folder for project A')
}
pipelinejob('frontend') {
  configure { flowdefintion ->
    flowdefintion << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmflowDefinition',plugin:'workflow-cps'){
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