folder('CI-Pipelines') {
  displayName('CI Pipelines')
  description('CI Pipelines')
}

def component = ["frontend","users","login","todo"];

def count=(component.size()-1)
for (i in 0..count) {
  def j=component[i]
  pipelineJob("CI-Pipelines/${j}-ci") {
    configure { flowdefinition ->
      flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
          'userRemoteConfigs' {
            'hudson.plugins.git.UserRemoteConfig' {
              'url'('https://github.com/sasender/frontend-1.git/'+j+'.git')
              'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
            }
          }
          'branches' {
            'hudson.plugins.git.BranchSpec' {
              'name'('*/tags/*')
            }
          }
        }
        'scriptPath'('Jenkinsfile')
        'lightweight'(true)
      }
    }
  }
}

  pipelineJob("CI-Pipelines/login-ci") {
    configure { flowdefinition ->
      flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
          'userRemoteConfigs' {
            'hudson.plugins.git.UserRemoteConfig' {
              'url'('https://github.com/sasender/login.git')
            }
          }
          'branches' {
            'hudson.plugins.git.BranchSpec' {
              'name'('*/main')
            }
          }
        }
        'scriptPath'('Jenkinsfile')
        'lightweight'(true)
      }
    }
  }

  pipelineJob("CI-Pipelines/users-ci") {
    configure { flowdefinition ->
      flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
          'userRemoteConfigs' {
            'hudson.plugins.git.UserRemoteConfig' {
              'url'('https://github.com/sasender/users.git')
              'refspec'('\'+refs/tags/*\':\'refs/remotes/origin/tags/*\'')
            }
          }
          'branches' {
            'hudson.plugins.git.BranchSpec' {
              'name'('*/tags/*')
                          }
          }
        }
        'scriptPath'('Jenkinsfile')
        'lightweight'(true)
      }
    }
  }  

  pipelineJob("CI-Pipelines/todo-ci") {
    configure { flowdefinition ->
      flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
          'userRemoteConfigs' {
            'hudson.plugins.git.UserRemoteConfig' {
              'url'('https://github.com/sasender/todo.git')
            }
          }
          'branches' {
            'hudson.plugins.git.BranchSpec' {
              'name'('*/main')
            }
          }
        }
        'scriptPath'('Jenkinsfile')
        'lightweight'(true)
      }
    }
  }



