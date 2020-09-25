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
	
	stage('Pull docker image in '){
	    def dockerCMD = 'docker run -p 9096:8080 -d amodak/bootcamp_webapp:1.7'
	    sshagent(['AWS']) {
        sh "ssh -o StrictHostKeyChecking=no ubuntu@13.235.95.115 ${dockerCMD}"
    }
        
}   
}
