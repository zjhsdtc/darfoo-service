import com.avaje.ebean.Ebean;
import models.Menu;
import models.MenuTime;
import models.Tab;
import models.TabTime;
import org.junit.Test;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

/**
 * Created by zjh on 15-1-7.
 */
public class StatisticTest {
    @Test
    public void insertMenu(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                int menuid = 3;
                Menu menu = new Menu(macaddress, hostip, menuid, 0L);
                menu.save();
            }
        });
    }

    @Test
    public void insertMenuTime(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                int menuid = 3;
                MenuTime menuTime = new MenuTime(macaddress, hostip, menuid);
                menuTime.save();
            }
        });
    }

    @Test
    public void insertTab(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                int tabid = 3;
                Tab tab = new Tab(macaddress, hostip, tabid, 0L);
                tab.save();
            }
        });
    }

    @Test
    public void insertTabTime(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String macaddress = "00:ad:05:01:a6:85";
                String hostip = "fe80::2ad:5ff:fe01:a685%wlan0";
                int tabid = 3;
                TabTime tabTime = new TabTime(macaddress, hostip, tabid);
                tabTime.save();
            }
        });
    }
}
