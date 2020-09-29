
node{

    
    stage('Git checkout'){
        git credentialsId: '3bd3ecc8-f093-41d3-9e51-06e8cb752351', url: 'https://github.com/avishekdevops/Web_app_bootcamp.git'
    }
    
    stage('Build & Test'){
        def mavenHome = tool name: 'maven-3', type: 'maven'
        def mavenCMD = "${mavenHome}/bin/mvn"
       
        sh "${mavenCMD} clean package"
    }
    
    stage('Build Docker Image'){
        def dockerHome = tool name: 'mydocker', type: 'dockerTool'
        def dockerCMD = "${dockerHome}/bin/docker"
        sh "echo ${dockerHome}"
       
       sh "${dockerCMD} build -t amodak/bootcamp_webapp:1.7 ."
    }
    
    stage('Push Docker Image to DockerHub'){
        withCredentials([string(credentialsId: 'docpasswd', variable: 'dockerPWD')]){
        sh "docker login -u amodak -p ${dockerPWD}"
        sh 'docker push amodak/bootcamp_webapp:1.7'
    }
    }
    stage('Provision EC2 Instance'){
        sh 'pip list|grep boto'
       ansiblePlaybook becomeUser: 'ubuntu', credentialsId: 'ec2-login', playbook: '/home/ubuntu/git_sprintboot/dynamic_web_docker_bootcamp/playbook.yml', sudoUser: 'ubuntu'
       sleep(60)
    }
    
    
        
    stage('Installation of Docker in new provisioned EC2 Instance'){
        def cliCommand = 'aws ec2 describe-instances --query "Reservations[*].Instances[*].PublicIpAddress[]" --region us-east-2'
        def output = sh script: "${cliCommand}" , returnStdout:true
        def ipList = output.split('"')
        ipAddress=ipList[1]
        println ipAddress
 
        sh "echo ${ipAddress}"
        //def dockerCMD = 'docker run -p 7777:8080 -d amodak/bootcamp_webapp:1.7'
	    sshagent(['devOps-key']) {
	    def update = 'sudo apt-get update' 
	    def dockerInstall = 'sudo apt install docker.io -y'
        def dockerVersion = 'docker --version'
        def dockerStart = 'sudo systemctl start docker'
        def dockerrun = 'sudo docker run -p 9050:8080 -d amodak/bootcamp_webapp:1.7'

        sh "ssh -o StrictHostKeyChecking=no ubuntu@${ipAddress} ${update}"
        sh "ssh -o StrictHostKeyChecking=no ubuntu@${ipAddress} ${dockerInstall}"
        sh "ssh -o StrictHostKeyChecking=no ubuntu@${ipAddress} ${dockerVersion}"
        sh "ssh -o StrictHostKeyChecking=no ubuntu@${ipAddress} ${dockerStart}"
        sh "ssh -o StrictHostKeyChecking=no ubuntu@${ipAddress} ${dockerrun}"
        
        }
    }
	
  
}
