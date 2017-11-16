package sometime.AggregateFileData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.Type;

public class AggregateFile {

    private static final String trafficFileDirPath = "D:\\temp";

    public static void main(String[] args) {
        File fileDir = new File(AggregateFile.trafficFileDirPath);
        File[] fileList = fileDir.listFiles();

        Long startdate = 201711151030L;
        Long enddate = 201711151050L;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<StatResult>>() {}.getType();

        List<StatResult> result = new ArrayList<>();

        Arrays.stream(fileList).filter(f -> {
            String s = f.getName();
            Long datetime = Long.parseLong(s.substring(s.indexOf("_") + 1, s.indexOf(".")));

            if (datetime > startdate && datetime < enddate) {
                return true;
            }

            return false;
        }).map(f -> {
            JsonReader reader = null;
            try {
                reader = new JsonReader(new FileReader(f));
                return (List<StatResult>) gson.fromJson(reader, listType);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }).forEach(f -> {
            f.forEach(g -> {
                result.add(g);
            });
        });

        List<StatResult> sortedResult = result.stream().sorted((o1, o2) -> {
            if(Long.parseLong(o1.getDatetime()) > Long.parseLong(o2.getDatetime())) {
                return 1;
            } else {
                return -1;
            }
        }).collect(Collectors.toList());
    }

}

