package sometime.MinuteWriteFile1;

public class MinuteWriteFile1 {
/*    public static final String writeFilePath = "D:\\hycho\\tools\\nginx-1.13.6\\html";


    public static void main(String [] args) throws FileNotFoundException {
        Traffic x = new Traffic();




        *//*PrintWriter pw = new PrintWriter(writeFilePath);
        for(int i=1; i<11; i++) {
            String data = i+" 번째 줄입니다.";
            pw.println(data);
        }
        pw.close();*//*
    }


    public static List<String> getMinusSecondBySize(int s) {
        List<String> r = new ArrayList<>();
        Calendar cal = new GregorianCalendar(Locale.KOREA);
        cal.setTime(new Date()) ;
        cal.add(Calendar.MINUTE, -10);

        for(int i=0; i<=s; i++) {
            cal.add(Calendar.SECOND, 3);
            SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss");
            r.add(fm.format(cal.getTime()));
        }

        return r;
    }*/


}

