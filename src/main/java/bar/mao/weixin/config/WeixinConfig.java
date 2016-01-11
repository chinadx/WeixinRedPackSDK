package bar.mao.weixin.config;

public class WeixinConfig {
    
    /*
     * 微信支付分配的商户号
     */
    public static final String MCH_ID = "1303633701";
    
    /*
     * 商户appid
     */
    public static final String APP_ID = "wx5d46390167c7603b";
    
    /*
     * API密钥
     */
    public static final String KEY = "8A09EE36-92BB-7587-E7F7-4F2F1ACECA04";
    
    /*
     * 密钥文件的存放路径
     */
    public static final String KEY_PATH = "/tmp/apiclient_cert.p12";
    
    /*
     * 微信红包的API地址
     */
    public static final String API_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
    
}
