package tk.newsoulmate.web.common.controller;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import tk.newsoulmate.domain.vo.ExtMessage;
import tk.newsoulmate.web.common.APIKeys;

public class MessageController {

    private DefaultMessageService messageService;

    public MessageController() {
        this.messageService = NurigoApp.INSTANCE.initialize(APIKeys.SolapiKey, APIKeys.SolapiSecretKey, "https://api.solapi.com");
    }

    public String sendMessage(ExtMessage msg){
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom(APIKeys.telNum);
        message.setTo(msg.getTelNum());
        message.setText(msg.getMessageContent());
        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        String result=response.getStatusCode();
        return result;
    }


}
