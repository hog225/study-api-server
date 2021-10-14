package se.yg.test.apiserver;

import org.junit.jupiter.api.Test;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ReactorTest {

    public class newThread implements Runnable {

        @Override
        public void run(){
            System.out.println("this is new Thread");
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }


    @Test
    void MonoTest1(){
        BlockHound.install();
        Mono<String> mono = Mono.just("Hello");
        String k = mono.flatMap(a -> {
          return Mono.just(a + " jini");
        }).block();


        System.out.println(k);

    }


    @Test
    void MonoTest2(){
        BlockHound.install();
        Mono<String> mono = Mono.just("Hello");
        String k = mono
                .flatMap(a -> {
                    return Mono.just(a + " jini");
                })
                .doFirst(new newThread())
                .doFinally(t->{
                    System.out.println("do Final");
                })
                .log()
                .block();


        System.out.println(k);


    }

    @Test
    void MonoDelay() throws InterruptedException {
        //BlockHound.install();
        Mono.delay(Duration.ofSeconds(4))
                .log()
                .filter(t->{
                    return false;
                })
                .map(t->{
                    System.out.println(t);
                    return t;
                }).subscribe();
        Thread.sleep(5000);
    }

    @Test
    void monoExpand(){

    }
}
