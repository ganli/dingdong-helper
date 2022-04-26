import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 用户信息
 * 通过小程序抓包购物车接口获取headers和body中的数据填入
 */
public class UserConfig {

    private static final Properties p = new Properties();

    static {
        try {
            p.load(UserConfig.class.getResourceAsStream("/UserConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String COOKIE = p.getProperty("cookie");
    public static final String UID = p.getProperty("userId");

    public static final String cityId = p.getProperty("cityId");//默认上海
    //站点id
    public static final String stationId = p.getProperty("stationId");
    //收货地址id
    public static final String addressId = p.getProperty("addressId");


    public static final String apiVer = "9.24.3";
    public static final String buildVer = "2.39.1";
    public static final String appClientId = "3";
    public static final String LONGITUDE = p.getProperty("longitude");
    public static final String LATITUDE = p.getProperty("latitude");


    /**
     * 确认收货地址id和站点id
     * 每天抢之前先允许一下此接口 确认登录信息是否有效 如果失效了重新抓一次包
     */
    public static void main(String[] args) {
        Api.checkUserConfig();
    }

    /**
     * 抓包后参考项目中的image/headers.jpeg 把信息一行一行copy到下面 没有的key不需要复制
     */
    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new LinkedHashMap<>();

        headers.put("ddmc-sdkversion", "2.24.0");
        headers.put("ddmc-city-number", cityId);
        headers.put("ddmc-api-version", apiVer);
        headers.put("ddmc-build-version", buildVer);
        headers.put("ddmc-longitude", LONGITUDE);
        headers.put("ddmc-latitude", LATITUDE);
        headers.put("ddmc-app-client-id", appClientId);
        headers.put("ddmc-uid", UID);
        headers.put("ddmc-channel", "undefined");
        headers.put("ddmc-device-id", "undefined");
        headers.put("ddmc-station-id", stationId);
        headers.put("ddmc-ip", "");
        headers.put("ddmc-os-version", "undefined");

        headers.put("accept", "application/json, text/plain, */*");
        headers.put("accept-encoding", "gzip,compress,br,deflate");
        headers.put("accept-language", "en-US,en;q=0.9");
        headers.put("referer", "https://bcm.m.ddxq.mobi/");
        headers.put("origin", "https://bcm.m.ddxq.mobi");
        headers.put("content-type", "application/x-www-form-urlencoded");

        // ------------  填入以下6项 上面不要动 ------------
        headers.put("cookie", COOKIE);
        headers.put("user-agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 15_4_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148com.bankcomm.maidanba.V2;mapp_saoma;isApplePayUsable;paypinflag;newVCard;digitalcert;WKWebView;UnionPay/1.0 BoComMDB;buildVersion165;mdbTitleBar;");
        return headers;
    }
//
//    public static Map<String, String> getHeaders_old() {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("ddmc-time", String.valueOf(new Date().getTime() / 1000));
//        return headers;
//    }

    /**
     * 抓包后参考项目中的image/body.jpeg 把信息一行一行copy到下面 没有的key不需要复制
     * <p>
     * 这里不能加泛型 有些接口是params  泛型必须要求<String,String> 有些是form表单 泛型要求<String,Object> 无法统一
     */
    public static Map<String, Object> getBody(Map<String, String> headers) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("uid", UID);
        body.put("longitude", LONGITUDE);
        body.put("latitude", LATITUDE);
        body.put("station_id", stationId);
        body.put("city_number", cityId);
        body.put("api_version", apiVer);
        body.put("app_version", buildVer);
        body.put("applet_source", "");
//        body.put("channel", "applet");
        body.put("app_client_id", appClientId);
        body.put("h5_source", "bcm");
        body.put("sharer_uid", "");
//        body.put("time", headers.get("ddmc-time"));
        body.put("s_id", "");
        body.put("openid", "");

        // ------------  填入这2项上面不要动 ------------

//        body.put("device_token", "WFWVc5eQ20p6zYTUhvVTFhH+CmIhEtu8zwngDxrkq6hhyf5uJjfgM54dBwtBeDO85ppMR5b7aYVXqHeJF5ZIqSKm9/cDU2Jt9LQ2FUPCwgjG/w8gZjQgjCg==1487577677129");
        return body;
    }
}
