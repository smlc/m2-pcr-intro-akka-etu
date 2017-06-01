package m2dl.pcr.akka.cribleEratosthene;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Main runtime class.
 */
public class System {
    public static final Integer N = 20;
    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef actorRef = actorSystem.actorOf(Props.create(ActorLast.class,2), "parent-actor");


        for (int i = 3 ; i<50 ;i++){
            actorRef.tell(i,null);
        }

         Thread.sleep(1000);

        log.debug("ActorLast System Shutdown Starting...");

        actorSystem.terminate();
    }
}
