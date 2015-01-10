import com.avaje.ebean.Ebean;
import models.auth.User;
import org.junit.Test;
import utils.CryptUtils;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

/**
 * Created by zjh on 15-1-10.
 */
public class UserTest {
    @Test
    public void insertUser(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String username = "cleantha";
                String password = CryptUtils.base64EncodeStr("pppppppp");
                User user = new User(username, password);
                user.save();
            }
        });
    }

    @Test
    public void isExistsUser(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String username = "cleantha333";
                String password = CryptUtils.base64EncodeStr("pppppppp");
                User user = Ebean.find(User.class).where().eq("username", username).eq("password", password).findUnique();

                if (user != null){
                    System.out.println("用户已经存在");
                }else{
                    System.out.println("用户不存在");
                }
            }
        });
    }
}
