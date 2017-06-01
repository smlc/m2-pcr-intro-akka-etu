package m2dl.pcr.akka.exo12;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import m2dl.pcr.akka.helloworld3.HelloGoodbyeActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Main runtime class.
 */
public class System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef actorRef = actorSystem.actorOf(Props.create(ParentActor.class), "parent-actor");

        actorRef.tell("John",null);
        actorRef.tell("Pauline",null);
        actorRef.tell("Eva",null);
        actorRef.tell("Bill",null);
        actorRef.tell("Marc",null);

        Thread.sleep(1000);

        log.debug("ActorLast System Shutdown Starting...");

        actorSystem.terminate();
    }
}
