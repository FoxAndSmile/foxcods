package sometime.AggregateFileData;

import lombok.Data;

@Data
public class StatResult {
    private String datetime;
    private String dspKey;
    private double bidRequests;
    private double bidResponses;
    private double winNotices;
    private double clicks;
    private double resultPrice;
}
