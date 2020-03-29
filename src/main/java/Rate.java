import java.util.ArrayList;

public class Rate {
    private String no;
    private String effectiveDate;
    private float mid;

    public Rate(){};

    public Rate(String no, String effectiveDate, float mid){
        this.no = no;
        this.effectiveDate = effectiveDate;
        this.mid = mid;
    };

    public float getMid() {
        return this.mid;
    };

    public static float countAverage(ArrayList<Rate> rates){
        float avg = 0;
        for ( Rate rate : rates)
            avg+= rate.getMid();
        avg /= rates.size();

        return avg;
    };


    public static float countStandardDeviation(ArrayList<Rate> rates){
        float avg = countAverage(rates);
        float variance = 0;
        for ( Rate rate : rates)
            variance+= (rate.getMid() - avg)*(rate.getMid() - avg);

        return (float) Math.sqrt( variance / rates.size() );
    };

    public static void printDetails( String currencyCode, String startDate, String endDate, ArrayList<Rate> rates){
        System.out.println(currencyCode);
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(countAverage(rates));
        System.out.println(countStandardDeviation(rates));
    };

}
