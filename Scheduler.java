import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


public class Scheduler {
    public static void main(String args[]) throws IOException {
        jobScheduler(args);
    }

    /**
     * calls External Services from Dkron at sccheduled interval
     *
     * sample arguments
     * args[0] = "http://host.docker.internal:8050/v1/metadata/objects/scheduler/callback"
     * args[1] = "token"
     * args[2] =
     */
    public static void jobScheduler(String args[]) throws IOException
    {
        URL urlForGetRequest = new URL(args[0]); //in docker
        //URL urlForGetRequest = new URL("http://localhost:8050/v1/metadata/objects/scheduler/callback"); //outside docker
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestProperty("Authorization","Bearer "+ args[1]);
        conection.setRequestProperty("Content-Type","application/json");
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            System.out.println("JSON String Result " + response.toString());
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }
    }
}
