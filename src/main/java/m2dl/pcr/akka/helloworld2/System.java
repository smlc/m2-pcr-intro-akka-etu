package m2dl.pcr.akka.helloworld2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Main runtime class.
 */
/*
 *Comment expliquer le comportement du système observé ? Les opération de logging sont asynchrone
 * ce qui explique les message ne s'affiche pas dans le bon ordre
 */
public class System {

    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef actorRef = actorSystem.actorOf(Props.create(HelloActor.class), "hello-actor");

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
