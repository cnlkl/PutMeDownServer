package com.clover.service.impl;

import com.clover.service.SMSCodeService;
import com.clover.utils.HttpConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lkl on 2017/3/21.
 */
@Service
public class MobSMSCodeServiceImpl implements SMSCodeService{

    private static final Logger logger =
            LoggerFactory.getLogger(MobSMSCodeServiceImpl.class);

    private static final String APP_KEY = "19219815dbf5a";

    private static final String ADDRESS =
            "https://webapi.sms.mob.com/sms/verify";

    @Override
    public boolean verifyCode(String phone, String code, String... args) {

        Map<String, String> params = new HashMap<>();
        params.put("appkey", APP_KEY);
        params.put("phone", phone);
        params.put("code", code);
        params.put("zone", args[0]);

        String response =
                HttpConnectionUtil.postWithoutCertificate(params, ADDRESS);

        return "{\"status\":200}".equals(response);
    }
}
