package bar.mao.weixin.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;

import bar.mao.weixin.dto.WechatRedPackRequest;
import bar.mao.weixin.dto.WechatRedPackResponse;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WechatUtils {

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串，排除空值
     * 
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        System.out.println(params.toString());
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    /**
     * 生成签名结果
     * 
     * @param sPara 要签名的字典
     * @return 签名结果字符串
     * @throws UnsupportedEncodingException
     */
    public static String buildRequestSign(Map<String, String> sPara, String key) throws UnsupportedEncodingException {
        String prestr = createLinkString(sPara) + "&key=" + key; // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        return DigestUtils.md5Hex(prestr).toUpperCase();
    }

    public static String buildRequestSign(WechatRedPackRequest request, String key) throws Exception {
        try {
            Map<String, String> sPara = BeanUtils.describe(request);
            cleanMap(sPara);
            return buildRequestSign(sPara, key);
        } catch (Exception e) {
            throw e;
        }
    }

    /*
     * 清除Map里的空值，以及由 BeanUtils.describe 产生的class键
     */
    public static void cleanMap(Map<String, String> map) {
        List<String> keys = new ArrayList<>();
        for (Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() == null || entry.getValue().equals("")) {
                keys.add(entry.getKey());
            }
        }

        for (String key : keys) {
            map.remove(key);
        }

        if (map.keySet().contains("class")) {
            map.remove("class");
        }
    }

    /*
     * xml和对象的互相转换
     */
    public static <T> String convertObjectToXml(Object obj, Class<T> type) {
        XStream xstream = new XStream(new XppDriver(new XmlFriendlyNameCoder("__", "_")));
        xstream.alias("xml", type);
        String xml = xstream.toXML(obj);
        return xml;
    }

    public static WechatRedPackResponse convertXmlToResponse(String xml) {
        XStream xstream = new XStream();
        xstream.alias("xml", WechatRedPackResponse.class);
        WechatRedPackResponse response = (WechatRedPackResponse) xstream.fromXML(xml);
        return response;
    }
}
