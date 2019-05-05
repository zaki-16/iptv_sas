package com.hgys.iptv.activiti;

import com.hgys.iptv.service.impl.ActivitiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * @Auther: wangz
 * @Date: 2019/4/29 11:20
 * @Description:
 */
@Controller
@RequestMapping("/act")
public class ActivitiController {
    @Autowired
    ActivitiServiceImpl activitiService;

    @GetMapping("/uploadWorkFlow")
    public String fileupload(@RequestParam MultipartFile file, HttpServletRequest request){
        try{
            if(file !=null && !file.isEmpty()){
                String filename=file.getOriginalFilename();
                InputStream is=file.getInputStream();
                activitiService.deployProcessInstance(filename,is);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "index";
    }
    @GetMapping("/deploy")
    @ResponseBody
    public String deploy(String resource,String processInstanceName){
       activitiService.deployProcessInstance("diagram/holidayBpmnZip.zip","请假流程实例");
//        activitiService.deployProcessInstance(resource,processInstanceName);
        return "OK";
    }

    //使用leaveapply表的主键作为businesskey,连接业务数据和流程数据
    @GetMapping("/startProcess")
    @ResponseBody
    public String startProcessInstanceByKey(String processDefinitionKey, String businessKey){
        activitiService.startProcessInstanceByKey(processDefinitionKey,businessKey);
        return "Ok";
    }
    @GetMapping("/taskCompleter/{taskId}")
    public String complete(@PathVariable("taskId") String taskId){
        activitiService.completeTask(taskId);
        return "Ok";
    }


}
