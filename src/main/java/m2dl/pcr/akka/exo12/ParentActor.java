package m2dl.pcr.akka.exo12;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Procedure;

/**
 * Created by MarS on 19/05/2017.
 */
public class ParentActor extends UntypedActor {

    private ActorRef goodByeActor;
    private ActorRef helloActor;

    public ParentActor(){
        //Create actors
        goodByeActor = getContext().actorOf(Props.create(GoodByeMsgActor.class), "good-bye-actor");
        helloActor = getContext().actorOf(Props.create(HelloMsgActor.class), "hello-actor");
    }

    Procedure<Object> hello = new Procedure<Object>() {

        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                helloActor.tell(msg,null);
                getContext().become(goodbye,false);
            } else {
                unhandled(msg);
            }
        }
    };
    /*
    Que permet de faire l'exécution de "getContext().unbecome()" ?
    Elle permet de revenir au comportement de base
     */
    Procedure<Object> goodbye = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
               goodByeActor.tell(msg,null);
                getContext().become(hello,false);
            } else {
                unhandled(msg);
            }
        }
    };

    public void onReceive(Object msg) throws Exception {
        hello.apply(msg);
    }
}
