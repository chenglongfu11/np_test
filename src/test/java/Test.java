import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

public class Test {

    public static void main(String[] args) {


        try {
            // open websocket
            int numberOfClients = 1000;
            WebSocketClient clientEndPoint = null;
            for(int i = 0 ; i < numberOfClients ; ++i){



            clientEndPoint = new WebSocketClient(
                    new URI("ws://ec2-13-48-124-43.eu-north-1.compute.amazonaws.com:8080/echo"));


            }

            // add listener


            clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
                public void handleMessage(String message) {
                    final Date date = new Date();
                    System.out.println(message + " timestamp " + date.getTime());
                }
            });

            // send message to websocket
            final Date date = new Date();
            System.out.println("sending message" + date.getTime());
            clientEndPoint.sendMessage(
                    "{" +
                            "'username':'test'," +
                            "'msg':'test msg'," +
                            "'date':'Sat, 11 Jan 2020 20:00:00'," +
                            "location:{'latitude':59.329323,'longitude':18.068581}" +
                            "}");


            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}