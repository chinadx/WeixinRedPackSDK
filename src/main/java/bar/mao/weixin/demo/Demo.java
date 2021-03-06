package bar.mao.weixin.demo;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import bar.mao.weixin.config.WeixinConfig;
import bar.mao.weixin.dto.WechatRedPackRequest;
import bar.mao.weixin.dto.WechatRedPackResponse;
import bar.mao.weixin.util.HttpUtil;
import bar.mao.weixin.util.WechatUtils;

public class Demo {
	

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        /*
         * 构建请求参数
         */
        WechatRedPackRequest request = new WechatRedPackRequest();
        request.setNonce_str(UUID.randomUUID().toString().replace("-", ""));
        request.setMch_id(WeixinConfig.MCH_ID);

        // 商户订单号（每个订单号必须唯一） 组成： mch_id+yyyymmdd+10位一天内不能重复的数字
        String mchBillno = WeixinConfig.MCH_ID + sdf.format(new Date()) + "0" + System.currentTimeMillis() % 1000000000;
        request.setMch_billno(mchBillno);
        
        request.setWxappid(WeixinConfig.APP_ID);
        request.setNick_name("我是一个测试");
        request.setSend_name("人人猎手");
        request.setRe_openid("oQEsruBvocq_YBCl7X4yyfIK5GEU"); // 接收者的微信openId;

        int money = 1 * 150; // 以分为单位
        request.setTotal_amount(money);
        request.setMin_value(money);
        request.setMax_value(money);
        request.setTotal_num(1);
        request.setWishing("感谢您参加人人猎手的xx推广活动，这是您的辛苦所得。祝您新年快乐！");
        request.setClient_ip("1.1.1.1");
        request.setAct_name("布丁APP全员推广活动");
        request.setRemark("推广越多所得越多，区域排名前三以及全国排名前三还有更加丰厚的奖励哦。");
        String sign = "";
        try {
            sign = WechatUtils.buildRequestSign(request, WeixinConfig.KEY);
        } catch (Exception e) {
            throw e;
        }
        request.setSign(sign);

        /*
         * 发送请求并解析返回的xml
         */
        FileInputStream instream = new FileInputStream(new File(WeixinConfig.KEY_PATH));

        String resp = HttpUtil.wechatPost(WeixinConfig.API_URL,
                WechatUtils.convertObjectToXml(request, WechatRedPackRequest.class), instream);
        System.out.println("红包调用成功，返回数据为 resp = " + resp);
        WechatRedPackResponse response = WechatUtils.convertXmlToResponse(resp);

        if (!response.getReturn_code().equals("SUCCESS")) {
            System.out.println(response.getReturn_msg());
            System.exit(-1);;
        }
        if (!response.getResult_code().equals("SUCCESS")) {
            System.out.println(response.getErr_code() + response.getErr_code_des());
            System.exit(-1);;
        }
        System.out.println(response.getResult_code());
    }

}
