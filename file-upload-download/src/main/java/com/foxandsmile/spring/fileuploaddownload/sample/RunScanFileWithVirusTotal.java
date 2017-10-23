package com.foxandsmile.spring.fileuploaddownload.sample;

import com.google.gson.internal.LinkedTreeMap;
import com.kanishka.virustotal.dto.FileScanReport;
import com.kanishka.virustotal.dto.ScanInfo;
import com.kanishka.virustotal.dto.VirusScanInfo;
import com.kanishka.virustotal.exception.APIKeyNotFoundException;
import com.kanishka.virustotal.exception.UnauthorizedAccessException;
import com.kanishka.virustotalv2.VirusTotalConfig;
import com.kanishka.virustotalv2.VirustotalPublicV2;
import com.kanishka.virustotalv2.VirustotalPublicV2Impl;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * Virus total을 사용해서 해당 파일이 문제 있는지 검증, 현재 API는 OPEN API로써 큰 파일이나 많은 요청은 제한이 되고 있습니다.
 * Virus Total은 예상외로 괜챃다. 해당 파일이나 URL등이 실제 여러 바이러스 스캐너(알약, v3 등등)에서 감지하고 그것을 알려주는 API를 제공도 해준다.
 * 아래 코드는 VirusTotla을 사용해서 해당 파일들이 문제가 있는지 확인해주는 코드이다.
 */
public class RunScanFileWithVirusTotal {

    private static final String badFilePath = "D:\\hycho\\project\\virustotal\\testfile\\eicar.com.txt";
    private static final String goodFilePath = "D:\\hycho\\project\\virustotal\\testfile\\Ecma-262.pdf";
    private static final String goodImgPath = "D:\\hycho\\project\\virustotal\\testfile\\Chrysanthemum.jpg";

    private static final String apiKey = "f9b252b9ee921519e8147ed3d4c5b67d92ea2b55bdf94d6fa3554fdb8681a081";

    public static void main(String[] args) {
        scanFile(goodImgPath);
    }

    public static void scanFile(String FilePath) {
        try {
            VirusTotalConfig.getConfigInstance().setVirusTotalAPIKey(apiKey);
            VirustotalPublicV2 virusTotalRef = new VirustotalPublicV2Impl();

            ScanInfo scanInformation = virusTotalRef.scanFile(new File(FilePath));

            System.out.println("___SCAN INFORMATION___");
            System.out.println("MD5 :\t" + scanInformation.getMd5());
            System.out.println("Perma Link :\t" + scanInformation.getPermalink());
            System.out.println("Resource :\t" + scanInformation.getResource());
            System.out.println("Scan Date :\t" + scanInformation.getScanDate());
            System.out.println("Scan Id :\t" + scanInformation.getScanId());
            System.out.println("SHA1 :\t" + scanInformation.getSha1());
            System.out.println("SHA256 :\t" + scanInformation.getSha256());
            System.out.println("Verbose Msg :\t" + scanInformation.getVerboseMessage());
            System.out.println("Response Code :\t" + scanInformation.getResponseCode());
            System.out.println("----------------------------------------------------");
            getFileScanReport(scanInformation.getResource());
            System.out.println("done.");
        } catch (APIKeyNotFoundException ex) {
            System.err.println("API Key not found! " + ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Unsupported Encoding Format!" + ex.getMessage());
        } catch (UnauthorizedAccessException ex) {
            System.err.println("Invalid API Key " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Something Bad Happened! " + ex.getMessage());
        }
    }

    public static void getFileScanReport(String resourceId) {
        try {
            VirusTotalConfig.getConfigInstance().setVirusTotalAPIKey(apiKey);
            VirustotalPublicV2 virusTotalRef = new VirustotalPublicV2Impl();

            String resource = resourceId;
            FileScanReport report = virusTotalRef.getScanReport(resource);

            System.out.println("MD5 :\t" + report.getMd5());
            System.out.println("Perma link :\t" + report.getPermalink());
            System.out.println("Resourve :\t" + report.getResource());
            System.out.println("Scan Date :\t" + report.getScanDate());
            System.out.println("Scan Id :\t" + report.getScanId());
            System.out.println("SHA1 :\t" + report.getSha1());
            System.out.println("SHA256 :\t" + report.getSha256());
            System.out.println("Verbose Msg :\t" + report.getVerboseMessage());
            System.out.println("Response Code :\t" + report.getResponseCode());
            System.out.println("Positives :\t" + report.getPositives());
            System.out.println("Total :\t" + report.getTotal());

            LinkedTreeMap<String, VirusScanInfo> scans = (LinkedTreeMap<String, VirusScanInfo>) report.getScans();
            for (String key : scans.keySet()) {
                VirusScanInfo virusInfo = scans.get(key);
                System.out.println("Scanner : " + key);
                System.out.println("\t\t Resut : " + virusInfo.getResult());
                System.out.println("\t\t Update : " + virusInfo.getUpdate());
                System.out.println("\t\t Version :" + virusInfo.getVersion());
            }

        } catch (APIKeyNotFoundException ex) {
            System.err.println("API Key not found! " + ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Unsupported Encoding Format!" + ex.getMessage());
        } catch (UnauthorizedAccessException ex) {
            System.err.println("Invalid API Key " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Something Bad Happened! " + ex.getMessage());
        }
    }
}
