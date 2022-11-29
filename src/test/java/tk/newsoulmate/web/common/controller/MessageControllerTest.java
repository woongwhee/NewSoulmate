package tk.newsoulmate.web.common.controller;

import org.junit.jupiter.api.Test;
import tk.newsoulmate.domain.vo.ExtMessage;
import tk.newsoulmate.web.common.APIKeys;

import static org.junit.jupiter.api.Assertions.*;

class MessageControllerTest {

    @Test
    void sendMessage() {
        new APIKeys();
        MessageController mc=new MessageController();
        ExtMessage msg=new ExtMessage();
        msg.setTelNum("01072788562");
        msg.setMessageContent("입양신청이 성공적으로 접수되었습니다.\n신청하신 2022-11-31 에 방문해주시면 됩니다.");
        System.out.println(mc.sendMessage(msg));;

    }
}