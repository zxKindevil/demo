package com.kindevil.demo;

public class HelloWord
{
    public static void main(String[] args)
    {
        ClientThread r = new ClientThread();
        Thread t = new Thread(r);
        t.setName("client thread");
        t.start();

        while(true)
        {
            try
            {
                Thread.sleep(3000);
            } catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            r.sendMsg();
        }

    }
}
