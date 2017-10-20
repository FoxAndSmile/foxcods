package com.foxcod.jxls.utils;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Excel 유틸
 * @author 조호영 (hycho@enliple)
 * @since 2017.08.03
 * @version 0.1
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일       수정자    수정내용
 *  ----------  ------   ---------------------------
 *  2017.08.03  조호영    최초 작성
 *
  * </pre>
 */

@Slf4j
public class ExcelUtils {

    /**
     * 인자를 사용해서 Excel을 만들고 Download 해주는 기능 
     * 헤더값이 필요한경우 objectList에서 첫번재리스트를 가져와서 sumData에 사용
     * @param response : Download를 위한 HttpServletResponse
     * @param objectList : Excel의 데이터를 담당할 리스트 객체 ${objectName.속성}
     * @param objectName : Excel에서 사용할 이름
     * @param fileName : 다운로드 받을 Excel 파일 이름
     * @param tempRealPath : Excel의 template 실제 경로 (C:\exceltemplate\template.xls, /template/excel)
     * @throws IOException
     */
    public static void downloadExcel(HttpServletResponse response, List<?> objectList, String objectName, String fileName, String tempRealPath) throws IOException {
        Context context = new Context();
        context.putVar(objectName, objectList);
        
        //합계필드값에 데이터를 필요한경우
        if(objectList.isEmpty() == false){
        	Map<String, Object> map = (Map)objectList.get(0);
        	context.putVar("sumData", map);	
        }

        context.putVar(objectName, objectList);

        @Cleanup
        InputStream is = new BufferedInputStream(new FileInputStream(tempRealPath));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xls\"");

        @Cleanup
        OutputStream os = response.getOutputStream();
        JxlsHelper.getInstance().processTemplate(is, os, context);
    }

    public static void downloadTotalExcel(HttpServletResponse response, List<?> objectList, String objectName, List<?> totalList, String fileName, String tempRealPath) throws IOException {
        Context context = new Context();
        context.putVar(objectName, objectList);

        //합계필드값에 데이터를 필요한경우
        if(objectList.isEmpty() == false){
            Map<String, Object> map = (Map)objectList.get(0);
            context.putVar("sumData", map);
        }

        context.putVar(objectName, objectList);
        context.putVar("total", totalList);

        @Cleanup
        InputStream is = new BufferedInputStream(new FileInputStream(tempRealPath));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xls\"");

        @Cleanup
        OutputStream os = response.getOutputStream();
        JxlsHelper.getInstance().processTemplate(is, os, context);
    }
}
