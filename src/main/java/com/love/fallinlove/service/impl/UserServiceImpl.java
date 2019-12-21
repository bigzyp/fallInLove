package com.love.fallinlove.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.love.enums.CodeMessageEnum;
import com.love.enums.LoveRuntimeException;
import com.love.fallinlove.dao.UserDao;
import com.love.fallinlove.dao.UserPairDao;
import com.love.fallinlove.domain.User;
import com.love.fallinlove.domain.UserPair;
import com.love.fallinlove.dto.UserDTO;
import com.love.fallinlove.dto.WxUserInfo;
import com.love.fallinlove.service.UserService;
import com.love.fallinlove.vo.LoversVO;
import com.love.utils.HttpUtil;
import com.love.utils.LogUtil;
import com.love.utils.RandomUtil;
import com.love.utils.StringUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lixin
 * @Description:
 * @Date: 2019/11/9 9:52
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserPairDao userPairDao;

    private static final String APPID = "wx52d179af061637c9";

    private static final String APPSECRET = "e7ea2df3c7097db2fa776554ec6d604e";

    /**
     * @param info
     * @param request
     * @param response
     * @Description: 微信小程序登录
     * @params: [info, request, response]
     * @Return: com.love.fallinlove.domain.User
     * @Author: lixin
     * @Date: 2019/11/9 10:38
     * @Modified:
     */
    @Override
    public User loginAppWechat(WxUserInfo info, HttpServletRequest request, HttpServletResponse response) {
        LogUtil.info("loginAppWechat params WxUserInfo=" + info.toString());

        // 解析 encryptedData
        info = analysisUserInfo(info, request);

        if (ObjectUtils.isEmpty(info.getUnionid())) {
            //openid
            //获取access_token
            String accessTokenUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + APPSECRET + "&js_code=" + info.getCode() + "&grant_type=authorization_code";
            String result = HttpUtil.get(accessTokenUrl);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String unionid = jsonObject.getString("unionid");
            if (!ObjectUtils.isEmpty(unionid)) {
                info.setUnionid(unionid);
            } else {
                LogUtil.info("INFO:: jscode2session unionid is null, get openid jsonObject=" + jsonObject);
                String openid = jsonObject.getString("openid");
                info.setUnionid(openid);
            }
            LogUtil.info("INFO:: loginAppWechat WxUserInfo={"
                    + " code=" + info.getCode()
                    + " sex=" + info.getSex()
                    + " nickname=" + info.getNickname()
                    + "}");
            if (ObjectUtils.isEmpty(info.getUnionid())) {
                LogUtil.error("ERROR:: jscode2session  jsonObject=" + jsonObject);
                throw new LoveRuntimeException(CodeMessageEnum.SERVICE_ERROR.getCode(), " jscode2session error!");
            }
        }

        User user = userDao.findUserByWechat(info.getUnionid());
        if (ObjectUtils.isEmpty(user)) {
            String password = RandomUtil.getLowerString(8);
            LogUtil.info("wxAppLogin  begin!!! code=" + info.getCode() + ", sex=" + info.getSex() + "， nickname=" + info.getNickname());
            if (ObjectUtils.isEmpty(info.getSex())) {
                info.setSex("0");
            }
            if (!StringUtil.isNumeric(info.getSex())) {
                LogUtil.error("ERROR:: loginAppWechat map=" + info);
                throw new LoveRuntimeException(CodeMessageEnum.SERVICE_ERROR.getCode(), " jscode2session error， sex error!");
            }
            String loginName = RandomUtil.getLowerString(8);
            if (ObjectUtils.isEmpty(info.getNickname())) {
                info.setNickname("love" + RandomUtil.number(8));
            }
            if (ObjectUtils.isEmpty(info.getHeadPortrait())) {
                info.setHeadPortrait("");
            }
            String sex = info.getSex();
            String nickname = info.getNickname();
            String head = info.getHeadPortrait();
            user = new User();
            user.setLoginName(loginName);
            user.setPassword(password);
            user.setNickname( nickname);
            user.setSex(Integer.valueOf(sex));
            user.setHeadPortrait(head);
            user.setMobilePhone("");
            user.setWechat(info.getUnionid());
            user.setState(1);
            user.setGmtCreate(new Date());
            user.setGmtModified(new Date());
            user.setGmtDelete(new Date());
            userDao.insert(user);
        }
        if (ObjectUtils.isEmpty(user.getSex())) {
            user.setSex(0);
        }
        user.setOpenid(info.getOpenid());

        return user;
    }


    /**
     * @param info
     * @param request
     * @param response
     * @Description: 微信小程序登录
     * @params: [info, request, response]
     * @Return: com.love.fallinlove.domain.User
     * @Author: lixin
     * @Date: 2019/11/22 16:33
     * @Modified:
     */
    @Override
    public LoversVO wxAppLogin(WxUserInfo info, HttpServletRequest request, HttpServletResponse response) {
        User user = loginAppWechat(info, request, response);
        User joinUser = userDao.selectJoinUserByUserId(user.getUserId());
        LoversVO loversVO = new LoversVO();
        loversVO.setUser(user);
        loversVO.setJoinUser(joinUser);
        return loversVO;
    }

    /**
     * @param userDTO
     * @Description: 编辑用户信息
     * @params: [userDTO]
     * @Return: void
     * @Author: lixin
     * @Date: 2019/11/23 14:12
     * @Modified:
     */
    @Override
    public void updateUserInfo(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setGmtModified(new Date());
        user.setNickname(userDTO.getNickname());
        user.setHeadPortrait(userDTO.getHeadPortrait());
        user.setAddress(userDTO.getAddress());
        userDao.updateByPrimaryKey(user);
    }

    /**
     * @param user
     * @Description: 账号登录
     * @params: [user]
     * @Return: com.love.fallinlove.vo.LoversVO
     * @Author: lixin
     * @Date: 2019/12/21 10:19
     * @Modified:
     */
    @Override
    public LoversVO loginIn(User user) {
        User loginUser = userDao.selectUserByLogin(user);
        if (loginUser == null) {
            throw new LoveRuntimeException(CodeMessageEnum.LOGIN_ERROR);
        }
        User joinUser = userDao.selectJoinUserByUserId(loginUser.getUserId());
        LoversVO loversVO = new LoversVO();
        loversVO.setUser(loginUser);
        loversVO.setJoinUser(joinUser);
        return loversVO;
    }

    /**
     * @param info
     * @param request
     * @param response
     * @Description: 微信小程序推荐
     * @params: [info, request, response]
     * @Return: com.love.fallinlove.domain.User
     * @Author: lixin
     * @Date: 2019/11/22 14:45
     * @Modified:
     */
    @Override
    public LoversVO wxAppReferee(WxUserInfo info, HttpServletRequest request, HttpServletResponse response) {
        User user = loginAppWechat(info, request, response);
        if (info.getRefereeId() != null && info.getRefereeId() != 0) {
            //userId 自己  refereeId是邀请人
            int count = userPairDao.selectCountByUserId(user.getUserId(), info.getRefereeId());
            if (count == 0) {
                UserPair userPair = new UserPair();
                userPair.setUserId(info.getRefereeId());
                userPair.setUserJoinId(user.getUserId());
                userPair.setGmtCreate(new Date());
                userPair.setState(1);
                userPairDao.insert(userPair);
            }
        }
        User joinUser = userDao.selectByPrimaryKey(info.getRefereeId());
        LoversVO loversVO = new LoversVO();
        loversVO.setUser(user);
        loversVO.setJoinUser(joinUser);
        return loversVO;
    }


    private WxUserInfo analysisUserInfo(WxUserInfo info, HttpServletRequest request) {
        if (!ObjectUtils.isEmpty(info.getInfoData())) {
            System.out.println("infoData" + info.getInfoData());
            JSONObject dataObj = JSONObject.parseObject(info.getInfoData());
            String code = (String) dataObj.get("code");
            String encryptedData = (String) dataObj.get("encryptedData");
            String iv = (String) dataObj.get("iv");
            info.setCode(code);
            String sessionkey = getSessionKey(info, request);
            JSONObject userInfo = this.getUserInfo(encryptedData, sessionkey, iv);
            LogUtil.info("analysisUserInfo userInfo=" + userInfo);
            info.setUnionid(userInfo.getString("unionId"));
            info.setOpenid(userInfo.getString("openId"));
            info.setHeadPortrait(userInfo.getString("avatarUrl"));
            info.setNickname(userInfo.getString("nickName"));
            info.setSex("1".equals(userInfo.getString("gender")) ? "1" : "0");

            LogUtil.info("analysisUserInfo WxUserInfo=" + info.toString());
        }
        return info;
    }

    /**
     * @Description: 微信小程序获得SessionKey
     * @params: [info, request]
     * @Return: java.lang.String
     * @Author: sulijuan
     * @Date: 2018/12/3 10:32
     * @Modified:
     */
    private String getSessionKey(WxUserInfo info, HttpServletRequest request) {

        try {
            Map<String, String> jsonObject = getSessionByCode(info.getCode(), request);
            LogUtil.info("getSessionKey jsonObject=" + jsonObject);
            return jsonObject.get("sessionKey");
        } catch (LoveRuntimeException e) {
            LogUtil.error(e, "getSessionKey LoveRuntimeException error!");
        } catch (Exception e) {
            LogUtil.error(e, "getSessionKey Exception error=");
        }
        return "";
    }

    /**
     * @Description: 微信小程序获得SessionKey、openid
     * @params: [info, request]
     * @Return: java.lang.String
     * @Author: sulijuan
     * @Date: 2018/12/14 10:32
     * @Modified:
     */
    private Map<String, String> getSessionByCode(String code, HttpServletRequest request) {

        String accessTokenUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + APPSECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String result = HttpUtil.get(accessTokenUrl);
        JSONObject jsonObject = JSONObject.parseObject(result);
        LogUtil.info("getSessionByCode jsonObject=" + jsonObject);
        String sessionKey = jsonObject.getString("session_key");
        Map<String, String> map = new HashMap<>();
        if (ObjectUtils.isEmpty(sessionKey)) {
            LogUtil.error("ERROR:: getSessionByCode jsonObject=" + jsonObject);
            throw new LoveRuntimeException(jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
        }
        map.put("sessionKey", sessionKey);
        map.put("openid", jsonObject.getString("openid"));
        return map;
    }

    /**
     * 获取信息
     */
    public JSONObject getUserInfo(String encryptedData, String sessionkey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionkey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            LogUtil.error(e, "wxApp NoSuchAlgorithmException getUserInfo error");
        } catch (NoSuchPaddingException e) {
            LogUtil.error(e, "wxApp NoSuchPaddingException getUserInfo error");
        } catch (InvalidParameterSpecException e) {
            LogUtil.error(e, "wxApp InvalidParameterSpecException getUserInfo error");
        } catch (IllegalBlockSizeException e) {
            LogUtil.error(e, "wxApp IllegalBlockSizeException getUserInfo error");
        } catch (BadPaddingException e) {
            LogUtil.error(e, "wxApp BadPaddingException getUserInfo error");
        } catch (UnsupportedEncodingException e) {
            LogUtil.error(e, "wxApp UnsupportedEncodingException getUserInfo error");
        } catch (InvalidKeyException e) {
            LogUtil.error(e, "wxApp InvalidKeyException getUserInfo error");
        } catch (InvalidAlgorithmParameterException e) {
            LogUtil.error(e, "wxApp InvalidAlgorithmParameterException getUserInfo error");
        } catch (NoSuchProviderException e) {
            LogUtil.error(e, "wxApp NoSuchProviderException getUserInfo error");
        }
        return null;
    }

}
