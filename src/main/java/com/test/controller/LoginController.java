package com.test.controller;


import com.test.dao.StaffMapper;
import com.test.entity.CompanyAccountManagement;
import com.test.entity.Staff;
import com.test.service.CAMService;
import com.test.util.MD5Tools;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private StringRedisTemplate redisTemplate = new StringRedisTemplate();
    @Autowired
    private JavaMailSender sender;
    @Resource
    private StaffMapper staffMapper;//用于工号查询
    @Resource
    private CAMService camService;
    @Value("${spring.from}")
    private String from;

    /**
     * 进入欢迎页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/hello")
    public String Welecome(Model model) {
        model.addAttribute("thif", 200);
        return "login";
    }

    /**
     * 进入注册页面
     *
     * @return
     */
    @RequestMapping("/GoCAMAdd")
    public String GoCAMAdd() {
        return "GoLogin";
    }

    /**
     * 进入修改页面！
     *
     * @param model
     * @return
     */
    @RequestMapping("/GoCamUpdate")
    public String GoCamUpdate(Model model) {
        model.addAttribute("msg", "修改密码");
        return "GoUpLogin";
    }

    /**
     * 进入忘记密码页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/GoCamForget")
    public String GoCamForget(Model model) {
        model.addAttribute("msg", "忘记密码");
        return "GoUpLogin";
    }

    /**
     * 退出
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:CamHello";
    }

    /**
     * 判断验证码是否正确
     *
     * @param camAccount
     * @param camCode
     * @return
     */
    @RequestMapping("/chackCamCode")
    @ResponseBody
    public String chackCamCode(String camAccount, String camCode) {
        String code = redisTemplate.opsForValue().get(camAccount+1);
        if (code==null||code.equals("")){
            return "null";
        }
        if (camCode.equals(code)) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * 根据工号查询
     *
     * @param sJobId
     * @return
     */
    @RequestMapping("/CamCheckJobId")
    @ResponseBody
    public String CamCheckJobId(String sJobId) {
        List<Staff> byJobID = staffMapper.getByJobID(sJobId);
        List<CompanyAccountManagement> camByID = new ArrayList<>();
        for (Staff staff : byJobID) {
            camByID = camService.getCAMByID(staff.getSId());
        }
        if (!(byJobID.size() > 0) || camByID.size() > 0) {
            return "false";
        } else {
            return "true";
        }
    }

    /**
     * 根据账号查询
     *
     * @param camAccount
     * @return
     */
    @RequestMapping("/CamCheckAccount")
    @ResponseBody
    public String CamCheckAccount(String camAccount) {
        String login = camService.getLogin(camAccount);
        if (login==null) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * 发送验证码并向redis存储验证码
     *
     * @param camAccount
     * @return
     */
    @RequestMapping("/SendEmail")
    public @ResponseBody
    String SendEmail(String camAccount) {
        String code = String.valueOf((int) (Math.random() * 9000 + 1000));
        redisTemplate.opsForValue().set(camAccount+1, code, 5, TimeUnit.MINUTES);
        MimeMessage mimeMessage = sender.createMimeMessage();
        String checkEmail = camService.getCheckEmail(camAccount);
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(checkEmail);
            helper.setSubject("验证码");
            helper.setText("这是你的验证码"+code+"\n不要泄露给别人哦！",true);
            sender.send(mimeMessage);
            return "true";
        }catch (Exception e){
            return "false";
        }
    }

    /**
     * 登录
     *
     * @param request
     * @param model
     * @param companyAccountManagement
     * @param session
     * @param code
     * @return
     */
    @RequestMapping("/CamEnter")
    public Object getLogin(HttpServletRequest request, Model model, CompanyAccountManagement companyAccountManagement, HttpSession session, String code) {
        try {
            //通过网页获取的用户账号查询MySQL数据库得到该用户的密码，放到CheckPassword
            String CheckPassword = camService.getLogin(companyAccountManagement.getCamAccount());
            //当通过用户名查询不到密码，即密码为空的时候
            if (CheckPassword == null) {
                model.addAttribute("msg", "用户不存在");
                return "login";
            } else {
                Object obj = redisTemplate.opsForValue().get(companyAccountManagement.getCamAccount());

                if (!CheckPassword.equals(MD5Tools.Md5Data(companyAccountManagement.getCamPassword()))) {
                    if (obj == null) {
                        redisTemplate.opsForValue().set(companyAccountManagement.getCamAccount(), "1", 5, TimeUnit.MINUTES);
                    } else {
                        int num = Integer.parseInt(String.valueOf(obj));
                        if (num > 2) {
                            model.addAttribute("thif", 400);
                            Object sessionCode = String.valueOf(request.getSession().getAttribute("captcha"));
                            if (!sessionCode.equals(String.valueOf(code).trim().toLowerCase())) {
                                model.addAttribute("msg", "验证码错误");
                                return "login";
                            }
                        } else {
                            redisTemplate.boundValueOps(companyAccountManagement.getCamAccount()).increment(1);
                        }
                    }
                } else if (CheckPassword.equals(MD5Tools.Md5Data(companyAccountManagement.getCamPassword()))) {
                    Object sessionCode = String.valueOf(request.getSession().getAttribute("captcha"));
                    if (!sessionCode.equals(String.valueOf(code).trim().toLowerCase())) {
                        model.addAttribute("msg", "验证码错误");
                        model.addAttribute("thif", 400);
                        return "login";
                    }
                    session.setAttribute("companyAccountManagement", companyAccountManagement);
                    model.addAttribute("msg", companyAccountManagement.getCamAccount());
                    return "login";
                }
                model.addAttribute("msg", "密码错误");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("msg", "登录异常" + e);
            return "login";
        }
    }

    /**
     * 修改
     *
     * @param camAccount
     * @param camPassword
     * @param model
     * @return
     */
    @RequestMapping("/CamUpdate")
    public String CamUpdate(String camAccount, String camPassword, Model model) {
        try {
            int update = camService.StaUpdateByCamAccount(camAccount, MD5Tools.Md5Data(camPassword));
            if (update > 0) {
                model.addAttribute("msg", "修改成功");
                return "login";
            }
            model.addAttribute("msg", "修改出现错误");
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "修改出现异常" + e);
            return "login";
        }

    }

    /**
     * 注册账号
     *
     * @param companyAccountManagement
     * @param sJobId
     * @return
     */
    @RequestMapping("/CAMAdd")
    public String CAMAdd(CompanyAccountManagement companyAccountManagement, String sJobId, Model model) {
        try {
            List<Staff> byJobID = staffMapper.getByJobID(sJobId);
            for (Staff staff : byJobID) {
                companyAccountManagement.setCamPassword(MD5Tools.Md5Data(companyAccountManagement.getCamPassword()));
                companyAccountManagement.setCamStaffId(staff.getSId());
                companyAccountManagement.setCamRoot(0);
            }
            int check = camService.LogAdd(companyAccountManagement);
            if (check > 0) {
                model.addAttribute("msg", "注册成功");
                return "login";
            }
            model.addAttribute("msg", "注册失败");
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "注册出现异常" + e);
            return "login";
        }
    }

    /**
     * 验证码
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        captcha.setLen(3);  // 几位数运算，默认是两位
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        String result = captcha.text(); // 获取运算的结果：5
        // 验证码存入session
        request.getSession().setAttribute("captcha", result);
        // 输出图片流
        captcha.out(response.getOutputStream()); // 输出验证码
    }


}
