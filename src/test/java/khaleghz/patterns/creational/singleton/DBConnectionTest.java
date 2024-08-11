package khaleghz.patterns.creational.singleton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    public void lazyLoadingPerformanceTest(){
        long time = System.currentTimeMillis();
        System.out.print("First time loading class (load time ms): ");
        var clazz = DBConnection.class;
        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        System.out.print("First time getting instance (load time ms): ");
        DBConnection.getInstance().getConnection();
        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        System.out.print("Second time getting instance (load time ms): ");
        DBConnection.getInstance().getConnection();
        System.out.println(System.currentTimeMillis() - time);
    }
}