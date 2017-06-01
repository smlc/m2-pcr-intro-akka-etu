package m2dl.pcr.akka.cribleEratosthene;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

import static m2dl.pcr.akka.helloworld2.System.log;

/**
 * Created by MarS on 01/06/2017.
 */
public class ActorLast extends UntypedActor {
    private int premier;
    private  ActorRef actorSuivant;
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    public ActorLast(int premier){
        this.premier = premier;
    }
    private int p;
    Procedure<Object> intermediarie = new Procedure<Object>() {

        public void apply(Object msg) throws Exception {
            p =(Integer) msg;
           if(p % premier != 0){
               actorSuivant.tell(p,null);
           }
        }
    };

    public void onReceive(Object o) throws Exception {
        if(o instanceof Integer){
           p =(Integer) o;
           if(p % premier !=0){
               log.info("["+premier+"]: "+p);
               actorSuivant = getContext().actorOf(Props.create(ActorLast.class,p));
               getContext().become(intermediarie);
           }

        }
    }

}
