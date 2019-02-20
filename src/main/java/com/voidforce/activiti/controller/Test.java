package com.voidforce.activiti.controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voidforce.activiti.service.SimpleRuntimeService;

@RestController
public class Test {

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private SimpleRuntimeService simpleRuntimeService;
	
	@GetMapping(value = "/") 
	public void startProcess(){
		//return "Hello Spring Boot!"; 
		Deployment deployment = repositoryService
	            .createDeployment()  // 创建一个Deployment
	            .name("test流程")          // 给流程起的一个名字
	            .addClasspathResource("processes/simpleTest.bpmn")    // bpmn的路径  你这里没有png图片 ，所以 就用这一个
	            .deploy();
	        
	     System.out.println("流程部署ID：" +deployment.getId());
	     System.out.println("流程部署时间：" +deployment.getDeploymentTime());
	     System.out.println("流程部署名称：" +deployment.getName());
		
	     simpleRuntimeService.startProcess("aaa", "simpleTest:1:4", null);
	} 
}
