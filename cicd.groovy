node('linux')
{
       stage ('Poll') {
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/pkgconfigport.git']]])
        }
  
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/pkgconfigport.git'), string(name: 'PORT_DESCRIPTION', value: 'pkg-config is a computer program that defines and supports a unified interface for querying installed libraries for the purpose of compiling software that depends on them. It allows programmers and installation scripts to work without explicit knowledge of detailed library path information.' ), string(name: 'NODE_LABEL', value: "v2r4")]
  }
}
