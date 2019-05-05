package com.hgys.iptv.service.impl;

import com.hgys.iptv.service.ActivitiService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @Auther: wangz
 * @Date: 2019/5/4 17:18
 * @Description:
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService ;
    @Autowired
    private TaskService taskService ;
    @Autowired
    private HistoryService historyService;

    /**
     * 通过 inputstream完成部署
     */
    @Override
    public Deployment deployProcessInstance(String resourceName,InputStream is){
       return repositoryService.createDeployment().addInputStream(resourceName,is).deploy();
    }
    /**
     *  通过zip流部署
     *  act_re_deployment  部署信息
     *  act_re_procdef     流程定义的一些信息
     *  act_ge_bytearray   流程定义的bpmn文件及png文件
     */
    @Override
    public Deployment deployProcessInstance (String resource,String processInstanceName){
        InputStream is = Thread.currentThread().getClass().getClassLoader().getResourceAsStream(resource);
        ZipInputStream zipInputStream = new ZipInputStream(is);
        return repositoryService.createDeployment().addZipInputStream(zipInputStream).name(processInstanceName).deploy();
    }
    /**
     * 启动一个流程实例:前提是先已经完成流程定义的部署工作
     * 获取任务表的id，去设置对应的businessKey
     * act_hi_actinst     已完成的活动信息
     * act_hi_identitylink   参与者信息
     * act_hi_procinst   流程实例
     * act_hi_taskinst   任务实例
     * act_ru_execution   执行表
     * act_ru_identitylink   参与者信息
     * act_ru_task  任务

     */
    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey,String businessKey) {
        return runtimeService.startProcessInstanceByKey(processDefinitionKey,businessKey);
    }
    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) {
        return startProcessInstanceByKey(processDefinitionKey,null);
    }

    /**
     * 根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
     */
    @Override
    public Task queryCurrentTask(String processDefinitionKey,String taskAssignee){
        return taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskAssignee(taskAssignee).singleResult();
    }

    @Override
    public void completeTask(String taskId) {
        taskService.complete(taskId);
    }

    /**
     *   处理当前用户的任务
     *   act_hi_actinst
     *   act_hi_identitylink
     *   act_hi_taskinst
     *   act_ru_identitylink
     *   act_ru_task
     */
    @Override
    public void completeTask (String processDefinitionKey,String taskAssignee){
        //查询当前用户的任务
        Task task = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey)
                .taskAssignee(taskAssignee).singleResult();
        // 根据act_ru_task中的task id 完成当前任务
        taskService.claim(task.getId(), taskAssignee);
        taskService.complete(task.getId());
    }

    /**
     * 根据流程实例的id查询该流程历史节点活动信息
     * act_hi_actinst
     * @return
     */
    @Override
    public List<HistoricActivityInstance> queryHistoricActivityInstance(String processInstanceId){
        HistoricActivityInstanceQuery hisInstanceQuery = historyService.createHistoricActivityInstanceQuery();
        hisInstanceQuery.processInstanceId(processInstanceId);//设置流程实例的id
        List<HistoricActivityInstance> list = hisInstanceQuery.orderByHistoricActivityInstanceStartTime().asc().list();//排序根据StartTime
        return list;
    }


    /**
     * 查询流程实例的运行状态ACT_RU_EXECUTION
     */
    @Override
    public List<ProcessInstance> queryProcessInstance(String processDefinitionKey) {
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
//        processInstanceQuery.processInstanceId("37501");
        processInstanceQuery.processDefinitionKey(processDefinitionKey);
//      List<ProcessInstance> a = query.processDefinitionKey(processDefinitionKey).involvedUser(user).listPage(firstrow, rowCount);
        return processInstanceQuery.list();
    }

    /**
     * 查询流程定义信息
     * act_re_procdef
     */
    @Override
    public List<ProcessDefinition> queryProcessDefinition(String processDefinitionKey){
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        processDefinitionQuery.processDefinitionKey(processDefinitionKey);
        List<ProcessDefinition> list = processDefinitionQuery.orderByProcessDefinitionVersion()//根据流程定义的版本号进行排序
                .desc()
                .list();
        //输出流程定义信息
        for(ProcessDefinition processDefinition :list){
            System.out.println("流程定义ID："+processDefinition.getId());
            System.out.println("流程定义名称："+processDefinition.getName());
            System.out.println("流程定义的Key："+processDefinition.getKey());
            System.out.println("流程定义的版本号："+processDefinition.getVersion());
            System.out.println("流程部署的ID:"+processDefinition.getDeploymentId());
            System.out.println("流程部署的ID:"+processDefinition.getResourceName());
        }
        return list;
    }



    /**
     * 删除已经部署的流程定义
     *
     * 影响的表:
     * act_ge_bytearray
     act_re_deployment
     act_re_procdef
     */
    @Override
    public void deleteProcessDefinition (String deploymentId){
        /**
         * 注意事项：
         *     1.当我们正在执行的这一套流程没有完全审批结束的时候，此时如果要删除流程定义信息就会失败
         *     2.如果要强制删除,可以使用repositoryService.deleteDeployment("1",true);
         *     //参数true代表级联删除，此时就会先删除没有完成的流程结点，最后就可以删除流程定义信息  false的值代表不级联
         *
         * @param args
         */
        //3.执行删除流程定义  参数代表流程部署的id
        repositoryService.deleteDeployment(deploymentId,false);
    }





}
