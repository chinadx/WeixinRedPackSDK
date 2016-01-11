package bar.mao.weixin.dto;

public class WechatRedPackResponse {
    
    /*
     * 微信红包的请求返回对象，为了方便构造POST的数据，没有采用JAVA常规的命名方式
     * 
     * 变量名即是发送的参数名
     */

    private String return_code;     // 返回状态码
    private String return_msg;      // 返回信息
    private String sign;            // 签名
    private String result_code;     // 业务结果
    private String err_code;        // 错误代码
    private String err_code_des;    // 错误代码描述

    private String mch_billno;      // 商户订单号
    private String mch_id;          // 商户号
    private String wxappid;         // 公众账号appid
    private String re_openid;       // 用户openid
    private int total_amount;       // 付款金额

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getMch_billno() {
        return mch_billno;
    }

    public void setMch_billno(String mch_billno) {
        this.mch_billno = mch_billno;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getWxappid() {
        return wxappid;
    }

    public void setWxappid(String wxappid) {
        this.wxappid = wxappid;
    }

    public String getRe_openid() {
        return re_openid;
    }

    public void setRe_openid(String re_openid) {
        this.re_openid = re_openid;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

}
