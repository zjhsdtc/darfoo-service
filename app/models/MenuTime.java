package models; /**
 * Created by zjh on 15-1-7.
 */

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用来统计一开始进去的大菜单每一个按钮的使用情况统计每一次点击的时间
 */

@Entity
public class MenuTime extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String mac;

    @Constraints.Required
    public String hostip;

    @Constraints.Required
    public int menuid;

    @Constraints.Required
    public Long timestamp = System.currentTimeMillis() / 1000;

    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public Date dueDate = new Date();

    public MenuTime(String mac, String hostip, int menuid) {
        this.mac = mac;
        this.hostip = hostip;
        this.menuid = menuid;
    }
}
