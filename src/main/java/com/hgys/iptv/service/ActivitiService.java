package com.hgys.iptv.service;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;

public interface ActivitiService {

    Deployment deployProcessInstance (String resource, String processInstanceName);

    Deployment deployProcessInstance(String resourceName, InputStream is);

    ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey);

    ProcessInstance startProcessInstanceByKey(String processDefinitionKey);

    Task queryCurrentTask(String processDefinitionKey, String taskAssignee);

    void completeTask (String taskId);
    void completeTask (String processDefinitionKey,String taskAssignee);

    List<HistoricActivityInstance> queryHistoricActivityInstance(String processInstanceId);

    List<ProcessInstance> queryProcessInstance(String processDefinitionKey);

    List<ProcessDefinition> queryProcessDefinition(String processDefinitionKey);

    void deleteProcessDefinition (String deploymentId);
}
