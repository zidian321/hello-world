package com.crazyjava.blockingqueque;


import java.util.concurrent.*;
/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
class Producer extends Thread
{
	private BlockingQueue<String> bq;
	public Producer(BlockingQueue<String> bq)
	{
		this.bq = bq;
	}
	public void run()
	{
		String[] strArr = new String[]
		{
			"Java",
			"Struts",
			"Spring"
		};
		for (int i = 0 ; i < 10 ; i++ )
		{
			System.out.println(getName() + "生产者准备生产集合元素！");
			try
			{
				Thread.sleep(200);
				// ���Է���Ԫ�أ���������������̱߳�����
				bq.put(strArr[i % 3]);
			}
			catch (Exception ex){ex.printStackTrace();}
			System.out.println(getName() + "生产完成" + bq);
		}
	}
}
class Consumer extends Thread
{
	private BlockingQueue<String> bq;
	public Consumer(BlockingQueue<String> bq)
	{
		this.bq = bq;
	}
	public void run()
	{
		while(true)
		{
			System.out.println(getName() + "消费者准备消费集合元素！");
			try
			{
				Thread.sleep(200);
				// ����ȡ��Ԫ�أ���������ѿգ��̱߳�����
				bq.take();
			}
			catch (Exception ex){ex.printStackTrace();}
			System.out.println(getName() + "消费完成" + bq);
		}
	}
}
public class BlockingQueueTest2
{
	public static void main(String[] args)
	{
		// ����һ������Ϊ1��BlockingQueue
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);
		// ����3���������߳�
		new Producer(bq).start();
		new Producer(bq).start();
		new Producer(bq).start();
		// ����һ���������߳�
		new Consumer(bq).start();
	}
}