package com.xinshuo.treehole.controller;

import com.github.pagehelper.PageInfo;
import com.xinshuo.treehole.entity.Complain;
import com.xinshuo.treehole.service.ComplainService;
import com.xinshuo.treehole.util.Constant;
import com.xinshuo.treehole.util.ResponseJSON;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComplainController {

    @Autowired
    private ComplainService complainService;

    @RequiresAuthentication
    @RequestMapping("/addComplain")
    public ResponseJSON addComplain(@RequestBody Complain complain) {
        complainService.addComplain(complain);
        return new ResponseJSON(Constant.HTTP_CODE_OK, complain, "添加complain成功");
    }


    @RequestMapping("/dealComplain/{isDeleteThing}")
    public ResponseJSON dealComplain(@RequestBody Complain complain,@PathVariable String isDeleteThing) {
        complainService.dealComplain(complain,isDeleteThing);
        return new ResponseJSON(Constant.HTTP_CODE_OK, complain, "处理complain成功");
    }


    @RequestMapping("/deleteComplain/{id}")
    public ResponseJSON deleteComplain(@PathVariable int id) {
        complainService.deleteComplain(id);
        return new ResponseJSON(Constant.HTTP_CODE_OK, id, "删除complain成功");
    }


    @RequestMapping("/getComplainsOfNoDeal")
    public ResponseJSON getComplainsOfNoDeal(int pageNum, int pageSize) {
        PageInfo<Complain> complains = complainService.getComplainsOfNoDeal(pageNum, pageSize);
        return new ResponseJSON(Constant.HTTP_CODE_OK, complains, "获取未处理的complain列表成功");
    }

    @RequestMapping("/getComplainByID/{id}")
    public ResponseJSON getComplainByID(@PathVariable int id) {
       Complain complain = complainService.getComplainByID(id);
        return new ResponseJSON(Constant.HTTP_CODE_OK, complain, "获取complain成功");
    }
}
