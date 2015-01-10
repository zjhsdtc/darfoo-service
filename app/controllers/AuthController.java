package controllers;

import com.avaje.ebean.Ebean;
import models.auth.Bind;
import models.auth.User;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.CryptUtils;

import javax.persistence.PersistenceException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjh on 15-1-10.
 */
public class AuthController extends Controller {

    /**
     * 检查指定的mac地址是否已经绑定了账户
     * @param mac
     * @return
     */
    public static Result isMacBind(String mac){
        Map<String, Object> result = new HashMap<String, Object>();
        Bind bind = Ebean.find(Bind.class).where().eq("mac", mac).findUnique();

        if (bind != null){
            System.out.println("mac地址已经绑定账号了");
            result.put("status", "error");
            return ok(Json.toJson(result));
        }else{
            System.out.println("mac地址还没有绑定账号");
            result.put("status", "ok");
            return ok(Json.toJson(result));
        }
    }

    public static Result bindMac(){
        DynamicForm form = Form.form().bindFromRequest();
        String mac = form.get("mac");
        String username = form.get("username");
        String password = form.get("password");

        Map<String, Object> result = new HashMap<String, Object>();
        User user = Ebean.find(User.class).where().eq("username", username).eq("password", password).findUnique();
        if (user != null){
            System.out.println("用户已经存在");
            result.put("status", "500");
            return ok(Json.toJson(result));
        }else{
            System.out.println("用户不存在");
            try {
                User newUser = new User(username, password);
                newUser.save();
                long userid = newUser.getId();
                Bind bind = new Bind(userid, mac);
                bind.save();
                long bindid = bind.getId();
                if (userid > 0 && bindid > 0){
                    result.put("status", "200");
                    return ok(Json.toJson(result));
                }else{
                    result.put("status", "501");
                    return ok(Json.toJson(result));
                }
            }catch (PersistenceException e){
                System.out.println("duplicate username or mac insert failed");
                result.put("status", "503");
                return ok(Json.toJson(result));

            }
        }
    }
}