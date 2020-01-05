package com.wxapp.controller;

import com.wxapp.api.contrats.UploadContratsApi;
import com.wxapp.api.friend.FriendAction;
import com.wxapp.api.friendcycle.FriendCircleApi;
import com.wxapp.api.login.A16Login;
import com.wxapp.api.login.Data62Login;
import com.wxapp.api.msg.SendMsg;
import com.wxapp.api.user.UserOperating;
import com.wxapp.entity.FriendCircle;
import com.wxapp.entity.*;
import com.wxapp.entity.msg.ImageMeg;
import com.wxapp.entity.msg.TextMsg;
import com.wxapp.task.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

//开发业务使用的控制器
@RestController
public class DevController {

    //服务类型注入
    @Autowired
    ExecutorService executorService;

    //Api类型注入，多线程传参数
    @Autowired
    A16Login a16Login;
    @Autowired
    Data62Login data62Login;
    @Autowired
    FriendAction friendAction;
    @Autowired
    SendMsg sendMsg;
    @Autowired
    UserOperating userOperating;
    @Autowired
    FriendCircleApi friendCircleApi;
    @Autowired
    UploadContratsApi uploadContratsApi;

    @PostMapping("/app/Login/A16Login")
    public String a16Login(@RequestBody List<A16User> a16UserList){
        List<Future<String>> futureList = new ArrayList<>();

        System.out.println(a16UserList.size());
        for (A16User a16User : a16UserList) {
            Future<String> submit = executorService.submit(new A16LoginTask(a16Login,a16User));
            futureList.add(submit);
        }

        String returnStr  = getFutureJSON(futureList);
        return returnStr;
    }

    @PostMapping("/app/Login/Data62Login")
    public String data62Login(@RequestBody List<Data62User> data62UserArrayList){
        List<Future<String>> futureList = new ArrayList<>();
        for (Data62User data62User : data62UserArrayList) {
            Future<String> submit = executorService.submit(new Data62LoginTask(data62Login, data62User));
            futureList.add(submit);
        }

        String returnStr  = getFutureJSON(futureList);
        return returnStr;
    }

    //拉取好友列表
    @PostMapping("/app/Friend/GetContractList")
    public String getContractList(@RequestBody List<GetFriendListInfo> getFriendListInfoList){
        List<Future<String>> futureList = new ArrayList<>();
        for (GetFriendListInfo getFriendListInfo : getFriendListInfoList) {
            Future<String> submit = executorService.submit(new GetFriendListTask(friendAction, getFriendListInfo));
            futureList.add(submit);
        }
        String returnStr = getFutureJSON(futureList);
        return returnStr;
    }

    //群发文本消息
    @PostMapping("/app/Message/SendTxtMessage")
    public String sendTxtMessage(@RequestBody List<TextMsg> textMsgList){
        List<Future<String>> futureList = new ArrayList<>();
        for (TextMsg textMsg : textMsgList) {
            Future<String> submit = executorService.submit(new SendTextMsgTask(sendMsg, textMsg));
            futureList.add(submit);
        }
        String returnStr = getFutureJSON(futureList);
        return returnStr;
    }

    //发送图片消息
    @PostMapping("/app/Message/SendImageMessage")
    public String sendImageMessage(@RequestBody List<ImageMeg> imageMegList){
        List<Future<String>> futureList = new ArrayList<>();
        for (ImageMeg imageMeg : imageMegList) {
            Future<String> submit = executorService.submit(new SendImageMsgTask(sendMsg, imageMeg));
            futureList.add(submit);
        }

        String returnStr = getFutureJSON(futureList);
        return returnStr;
    }

    //获取用户的个人信息
    @PostMapping("/app/user/get")
    public String getUserInfo(List<String> wxIds){
        List<Future<String>> futureList = new ArrayList<>();
        for (String wxId : wxIds) {
            Future<String> submit = executorService.submit(new GetUserInfoTask(userOperating, wxId));
            futureList.add(submit);
        }
        String returnStr = getFutureJSON(futureList);
        return returnStr;
    }

    //修改头像
    @PostMapping("/app/user/UploadHeadImage")
    public String uploadHeadImage(List<UploadHeadImage> uploadHeadImageList){
        List<Future<String>> futureList = new ArrayList<>();
        for (UploadHeadImage uploadHeadImage : uploadHeadImageList) {
            Future<String> submit = executorService.submit(new UploadHeadImageTask(uploadHeadImage, userOperating));
            futureList.add(submit);
        }
        String returnStr = getFutureJSON(futureList);
        return returnStr;
    }

    //修改资料
    @PostMapping("/app/user/UpdateProfile")
    public String updateProfile(List<User> userList){
        List<Future<String>> futureList = new ArrayList<>();
        for (User user : userList) {
            Future<String> submit = executorService.submit(new ChangeUserInfoTask(userOperating, user));
            futureList.add(submit);
        }
        String returnStr = getFutureJSON(futureList);
        return returnStr;
    }

    //设置微信号
    @GetMapping("/app/user/SetAlisa")
    public String setAlisa(List<Alisa> alisaList){
        List<Future<String>> futureList = new ArrayList<>();
        for (Alisa alisa : alisaList) {
            Future<String> submit = executorService.submit(new SetAlisaTask(userOperating, alisa));
            futureList.add(submit);
        }
        String returnStr = getFutureJSON(futureList);
        return returnStr;
    }

    //发朋友圈
    @PostMapping("/app/FriendCircle/SendFriendCircle")
    public String sendFriendCircle(List<FriendCircle> friendCircleList){
        List<Future<String>> futureList = new ArrayList<>();
        for (FriendCircle friendCircle : friendCircleList) {
            Future<String> submit = executorService.submit(new SendFriendCircleTask(friendCircleApi, friendCircle));
            futureList.add(submit);
        }
        String returnStr = getFutureJSON(futureList);
        return returnStr;
    }

    //上传通讯录
    @PostMapping("/app/Common/UploadContrats")
    public String uploadContrats(List<Contrat> contratList){
        List<Future<String>> futureList = new ArrayList<>();
        for (Contrat contrat : contratList) {
            Future<String> submit = executorService.submit(new UploadContratsTask(uploadContratsApi, contrat));
            futureList.add(submit);
        }
        String returnStr = getFutureJSON(futureList);
        return returnStr;
    }

    //获取json串，测试功能
    private String getFutureJSON(List<Future<String>> futureList){
        String returnStr = "[";
        for (Future<String> stringFuture : futureList) {
            try {
                returnStr +=stringFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        returnStr = returnStr.substring(0,returnStr.length()-1)+"]";
        return returnStr;
    }

}
