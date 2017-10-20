package com.foxcod.jxls.runcode;

import lombok.Cleanup;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvRowToCol {

    public static void main(String[] args) throws IOException {
        List<Map<String, Object>> sampleData = new ArrayList<>();

        Map<String, Object> sampleMap1 = new HashMap<>();
        sampleMap1.put("companyName", "회사1");
        sampleMap1.put("data1", "회사1 데이터1");
        sampleMap1.put("data2", "회사1 데이터2");
        sampleMap1.put("data3", "회사1 데이터3");

        Map<String, Object> sampleMap2 = new HashMap<>();
        sampleMap2.put("companyName", "회사2");
        sampleMap2.put("data1", "회사2 데이터1");
        sampleMap2.put("data2", "회사2 데이터2");
        sampleMap2.put("data3", "회사2 데이터3");

        sampleData.add(sampleMap1);
        sampleData.add(sampleMap2);

        downloadExcel(sampleData,"list",  "C:\\eTest\\admin_siteinv_site_report.xls");
    }

    public static void downloadExcel(List<?> objectList, String objectName, String tempRealPath) throws IOException {
        Context context = new Context();
        context.putVar(objectName, objectList);

        //합계필드값에 데이터를 필요한경우
        if(objectList.isEmpty() == false) {
            Map<String, Object> map = (Map)objectList.get(0);
            context.putVar("sumData", map);
        }

        context.putVar(objectName, objectList);

        @Cleanup
        InputStream is = new BufferedInputStream(new FileInputStream(tempRealPath));

        @Cleanup
        FileOutputStream os = new FileOutputStream("C:\\eTest\\test1.xls");
        JxlsHelper.getInstance().processTemplate(is, os, context);
    }
}

