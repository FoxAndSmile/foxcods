package sometime.ParsingExcelFile;

import lombok.Cleanup;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParseExceling {
    private static final String sampleExcelPath = "D:\\hycho\\project\\foxcode\\sometime\\src\\main\\java\\sometime\\ParsingExcelFile\\sample1.xlsx";

    public static void main(String[] args) throws IOException {
        System.out.println(getStatResult(sampleExcelPath));
    }

    private static ResponseEntity<?> getStatResult(String filePath) throws IOException {
        try {

            DecimalFormat df = new DecimalFormat("#");
            @Cleanup
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            int rowIdx = 0;
            String dspKey = "";
            List<StatResult> resultList = new ArrayList<>();

            while (iterator.hasNext()) {
                int cellIdx = 0;
                Row curRow = iterator.next();
                Iterator<Cell> cellIterator = curRow.iterator();
                StatResult statResult = new StatResult();
                statResult.setDspKey(dspKey);

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

                    // dsp key 구하기
                    if(rowIdx == 0 && cellIdx == 0) {
                        if(currentCell.getCellTypeEnum() != CellType.STRING) {
                            System.out.println("ERROR");
                        }

                        dspKey = currentCell.getStringCellValue();
                        break;
                    }

                    if(rowIdx > 1) { // Title 넘기기
                        switch (cellIdx) {
                            case 0:
                                statResult.setDatetime(String.valueOf(df.format(currentCell.getNumericCellValue())));
                                break;
                            case 1:
                                statResult.setBidRequests(currentCell.getNumericCellValue());
                                break;
                            case 2:
                                statResult.setBidResponses(currentCell.getNumericCellValue());
                                break;
                            case 3:
                                statResult.setWinNotices(currentCell.getNumericCellValue());
                                break;
                            case 4:
                                statResult.setClicks(currentCell.getNumericCellValue());
                                break;
                            case 5:
                                statResult.setResultPrice(currentCell.getNumericCellValue());
                                break;
                        }
                    }
                    cellIdx++;
                }
                if(rowIdx > 1) {
                    resultList.add(statResult);
                }
                rowIdx++;
            }

            return ResponseResult.result(ResponseResult.RESULT_SUCCESS_CD,
                    ResponseResult.RESULT_SUCCESS_MSG,
                    resultList);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseResult.result(ResponseResult.RESULT_FAILUE_CD,
                    ResponseResult.RESULT_FAILUE_MSG,
                    null);
        }
    }
}
