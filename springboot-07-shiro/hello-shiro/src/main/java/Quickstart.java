import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quickstart {
//    使用日志输出
    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);
    public static void main(String[] args) {
//        通过工厂读取配置文件，创建实例
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
//        单例，全局唯一的对象，可以不用配置
        SecurityUtils.setSecurityManager(securityManager);

//        通过SecurityUtils获取当前的用户对象subject
        Subject currentUser = SecurityUtils.getSubject();
//        通过当前用户拿到session，存值并且取值
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("Subject存在session的值为[" + value + "]");
        }

//        测试当前的用户是否被认证
        if (!currentUser.isAuthenticated()) {
//            没有被认证，创建一个令牌，该用户是在ini文件里存在的
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
//            开启该令牌的记住我功能
            token.setRememberMe(true);
            try {
//                执行了登录操作
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                log.info("该用户名不正确 " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                log.info("用户" + token.getPrincipal() + " 不正确");
            } catch (LockedAccountException lae) {
                log.info("该账户的用户名" + token.getPrincipal() + "被锁定了" +
                        "请联系您的管理员解锁");
            }
            // ... 在这里捕获更多异常 (也许定制的特定于您的应用程序？
            catch (AuthenticationException ae) {
                //意外情况？错误？
            }
        }

        //打印其识别主体（在本例中为用户名）
        log.info("用户 [" + currentUser.getPrincipal() + "] 成功登陆了");

        //测试角色,是否有这个role
        if (currentUser.hasRole("schwartz")) {
            log.info("May the Schwartz be with you!");
        } else {
            log.info("Hello, mere mortal.");
        }

        //测试输入权限（非实例级）
//        粗粒度
        if (currentUser.isPermitted("lightsaber:wield")) {
            log.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        //测试是否拥有更高的权限
//        细粒度
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }

        //登出
        currentUser.logout();

        System.exit(0);
    }
}
