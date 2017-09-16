package com.company;


public class Main {
    private static boolean quitFlag = false;
    private static String testStr = "zhk";

    public static void waitTime (int time) {
        int a = 1;
        for(long i = 1; i < 50000000*time;) {
            a = 2;
            if(i % 5 == 1)
                a = 3;
            i++;
            if (i % 50000000 == 5000000)
                System.out.println(a);
        }
    }

    public static void syncTestA (String str, int time) {
        synchronized (testStr) {
            testStr = str;
            waitTime(time);
//			try {
//				Thread.sleep(time);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            System.out.println(testStr);
        }
    }

    public synchronized static void syncTestB (String str, int time) {
        testStr = str;
        waitTime(time);
//		try {
//			Thread.sleep(time);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        System.out.println(testStr);
    }

    private static class helloThread extends Thread {
        @Override
        public void run() {
            while(!quitFlag) {
                System.out.println("hello thread run!");
                //Thread.yield();
                syncTestA("111111", 5000);
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
            }
            System.out.println("hello thread quit!");
        }
    }
    private static class helloThread2 extends Thread {
        @Override
        public void run() {
            while(!quitFlag) {
                //Thread.yield();
                try {
                    System.out.println("hello thread2 run!");
                    Thread.sleep(1000);
                    quitFlag = true;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                syncTestA("22222", 1000);
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("hello thread 2 quit!");
        }
    }


    public static void main(String[] args) throws Exception {
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;

        System.out.println(f1 == f2);
        System.out.println(f3 == f4);
        System.out.println(f3.compareTo(f4));
        System.out.println(f1.compareTo(f4));
        System.out.println(f3.equals(f4));
        System.out.println(f1.equals(f4));

        new helloThread().start();
        new helloThread2().start();
//		System.out.println("kill hello thread!");
//		quitFlag = true;
    }
}
