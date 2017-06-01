package m2dl.pcr.akka.exo12;

import akka.actor.UntypedActor;

import static m2dl.pcr.akka.helloworld2.System.log;

/**
 * Created by MarS on 19/05/2017.
 */
public class HelloMsgActor extends UntypedActor {
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            log.info("[HelloActor] : "+ msg);
        }
    }
}
