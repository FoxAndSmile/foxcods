package sometime.AggregateFileData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AggregateFile2 {

    private static final String trafficFileDirPath = "D:\\temp";

    public static void main(String[] args) {
        File fileDir = new File(AggregateFile2.trafficFileDirPath);
        File[] fileList = fileDir.listFiles();

        Long startdate = 201711151030L;
        Long enddate = 201711151050L;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<StatResult>>() {}.getType();

        List<StatResult> result = new ArrayList<>();

        Arrays.stream(fileList).filter(f -> {
            return filterFileByDate(startdate, enddate, f);
        }).map(f -> {
            return convFromJson(gson, listType, f);
        }).forEach(f -> {
            f.forEach(g -> {
                result.add(g);
            });
        });

        List<StatResult> sortedResult = result.stream().sorted((o1, o2) -> {
            return sortDatetime(o1, o2);
        }).collect(Collectors.toList());
    }

    private static int sortDatetime(StatResult o1, StatResult o2) {
        if(Long.parseLong(o1.getDatetime()) > Long.parseLong(o2.getDatetime())) {
            return 1;
        } else {
            return -1;
        }
    }

    private static List<StatResult> convFromJson(Gson gson, Type listType, File f) {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(f));
            return (List<StatResult>) gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean filterFileByDate(Long startdate, Long enddate, File f) {
        String s = f.getName();
        Long datetime = Long.parseLong(s.substring(s.indexOf("_") + 1, s.indexOf(".")));

        if (datetime > startdate && datetime < enddate) {
            return true;
        }
        return false;
    }
}

