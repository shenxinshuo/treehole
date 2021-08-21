package com.xinshuo.treehole.controller;

import com.xinshuo.treehole.entity.Notice;
import com.xinshuo.treehole.service.NoticeService;
import com.xinshuo.treehole.util.Constant;
import com.xinshuo.treehole.util.ResponseJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //获取公告
    @RequestMapping("/getNotice")
    public ResponseJSON getNotice() {
        String notice = noticeService.getNotice();
        return new ResponseJSON(Constant.HTTP_CODE_OK, notice, "获取公告成功");
    }

    //修改公告
    @RequestMapping("/updateNotice")
    public ResponseJSON updateNotice(@RequestBody Notice notice) {
        noticeService.updateNotice(notice);
        return new ResponseJSON(Constant.HTTP_CODE_OK, notice, "修改公告成功");
    }
}
