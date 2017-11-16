package sometime.MinuteWriteFile1;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class FileWriteScheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
    private static final String writeFilePath = "D:\\hycho\\tools\\nginx-1.13.6\\html\\data";

    @Scheduled(cron="0 * * * * *")
    public void writeFile() throws FileNotFoundException {
        log.info("The time is now {}", dateFormat.format(new Date()));

        List<Map<String, Object>> list = new ArrayList<>();

        getMinusSecondBySize(19).forEach(s -> {
            list.add(getNewData(s, "mobon"));
            list.add(getNewData(s, "mobon2"));
            list.add(getNewData(s, "mobon3"));
        });

        Gson gson = new Gson();
        String json = gson.toJson(list);

        Calendar cal = new GregorianCalendar(Locale.KOREA);
        cal.setTime(new Date()) ;
        cal.add(Calendar.MINUTE, -1);

        PrintWriter pw = new PrintWriter(writeFilePath + "\\" + "AD-exchange-01_" + dateFormat.format(cal.getTime()) + ".traffic");
        pw.println(json);
        pw.close();
    }

    public Map<String, Object> getNewData(String d, String com) {
        Map<String, Object> t = new HashMap<>();
        t.put("datetime", d);
        t.put("bidRequests", (int) (Math.random() * 100));
        t.put("bidResponses", (int) (Math.random() * 100));
        t.put("winNotices", (int) (Math.random() * 100));
        t.put("clicks", (int) (Math.random() * 100));
        t.put("avgBidPrice", (int) (Math.random() * 100));
        t.put("avgWinPrice", (int) (Math.random() * 100));

        if(com.equals("mobon")) {
            t.put("dspInfoId", "12");
        } else if(com.equals("mobon2")) {
            t.put("dspInfoId", "13");
        } else if(com.equals("mobon3")) {
            t.put("dspInfoId", "14");
        }

        return t;
    }


    public static List<String> getMinusSecondBySize(int s) {
        List<String> r = new ArrayList<>();
        Calendar cal = new GregorianCalendar(Locale.KOREA);
        cal.setTime(new Date()) ;
        cal.add(Calendar.MINUTE, -1);

        for(int i = 0; i <= s; i++) {
            SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss");
            r.add(fm.format(cal.getTime()));
            cal.add(Calendar.SECOND, 3);
        }

        return r;
    }
}
