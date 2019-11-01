import static java.lang.Thread.sleep;

public class volatileTest extends Thread{

    public   static volatile int  n  =   0 ;
    public static synchronized void inc(){//使用synchronized定义自增操作后，自增操作为原子操作，输出值等于1000
        n++;
    }
    public    void  run()
    {
        for  ( int  i  =   0 ; i  <   10 ; i ++ )
            try
            {
                //inc();
                n++;//由于此操作与n变量以前的值相关，volatile无法确保对n的此操作为原子操作，因此输出值小于1000。
                sleep( 3 );  
            }
            catch  (Exception e)
            {
            }
    }

    public   static   void  main(String[] args)  throws  Exception
    {

        Thread threads[]  =   new  Thread[ 100 ];
        for  ( int  i  =   0 ; i  <  threads.length; i ++ )
            //  建立100个线程
            threads[i]  =   new  volatileTest();
        for  ( int  i  =   0 ; i  <  threads.length; i ++ )
            //  运行刚才建立的100个线程
            threads[i].start();
        for  ( int  i  =   0 ; i  <  threads.length; i ++ )
            //  100个线程都执行完后继续
            threads[i].join();
        System.out.println( " n= "   +  volatileTest.n);
        //如果对n的操作是原子的，输出的结果应该为1000。
    }
}
