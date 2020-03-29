
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainClass {

    public static final String[] currencyCodes = new String[]{ "CHF", "USD", "EUR", "GBP"};
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static String currency;
    public static String startDate;
    public static String endDate;
    private static HttpURLConnection connection;

    public static void main(String[] args) {

        setup(args);
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/a/" + currency + "/" + startDate + "/" + endDate + "/");

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            int status = connection.getResponseCode();

            if(status > 299)
                reader = new BufferedReader( new InputStreamReader(connection.getErrorStream()));
            else
                reader = new BufferedReader( new InputStreamReader(connection.getInputStream()));

            while( (line = reader.readLine()) != null)
                responseContent.append(line);
            reader.close();

            JSONObject object = new JSONObject(responseContent.toString());
            JSONArray rates = object.getJSONArray("rates");
            ArrayList<Rate> currencyRates = new ArrayList();
            for( int i = 0; i < rates.length(); i++){
                String no = rates.getJSONObject(i).getString("no");
                String effectiveDate = rates.getJSONObject(i).getString("effectiveDate");
                Float mid = (float) rates.getJSONObject(i).getDouble("mid");
                Rate rate = new Rate(no, effectiveDate, mid);
                currencyRates.add( rate );
            }

            Rate.printDetails(currency, startDate, endDate, currencyRates);
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }  catch (JSONException e){
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    public static void setup(String[] args){
        if(args.length != 3){
            System.out.println("Invalid number of parametres");
            System.exit(1);
        }

        if( !Arrays.asList(currencyCodes).contains(args[0]) ){
            System.out.println("Wrong currency code");
            System.exit(1);
        }
        else
            currency = args[0];

        if( !( isValidDate(args[1]) && isValidDate(args[2]) )){
            System.out.println("Invalid date format");
            System.exit(1);
        }
        else{
            startDate = args[1];
            endDate = args[2];
        }

    }

    public static boolean isValidDate(String date) {
        try {
            format.parse(date);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }

    public static void setConnectionParams(){

    }
}


