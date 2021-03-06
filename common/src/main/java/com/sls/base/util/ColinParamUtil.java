package com.sls.base.util;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author SLS
 * @data 2019/3/4 9:24
 * <p>
 * 参数工具类
 **/
public class ColinParamUtil {

    /**
     * 多参数是否为空检查
     *
     * @param params 多参数
     * @return boolean true 为其中含有空的参数
     */
    public static boolean paramCheck(List<Object> params) {
        List<Object> paramNullList = params.stream().filter(StringUtils::isEmpty).collect(Collectors.toList());
        return paramNullList.size() > 0;
    }

    /**
     * 获取MD5 加密
     */
    public static String md5(String body) {
        StringBuilder buf = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(body.getBytes());
            byte[] b = md.digest();
            int i;
            for (byte b1 : b) {
                i = b1;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return buf.toString();
    }

    /**
     * 对Map 进行key 的升序排列
     *
     * @param map
     * @return
     */
    public static String sortMap(Map<String, String> map) {
        List<Map.Entry<String, String>> infoIds = new ArrayList<>(map.entrySet());
        infoIds.sort(Comparator.comparing(Map.Entry::getKey));
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String> infoId : infoIds) {
            String id = infoId.toString();
            params.append(id).append("&");
        }
        return params.toString();
    }

}
