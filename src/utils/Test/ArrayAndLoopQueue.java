package utils.Test;

import java.util.Random;

import utils.Queue.ArrayQueue;
import utils.Queue.LinkedListQueue;
import utils.Queue.LoopQueue;
import utils.Queue.Queue;

/**
 * ±È½ÏArrayQueueºÍLoopQueue
 */
public class ArrayAndLoopQueue {
	
	private static double testQueue(Queue<Integer> q, int opCount){
		long startTime = System.nanoTime();
		
		Random random = new Random();
		for(int i = 0; i < opCount; i ++)
			q.enqueue(random.nextInt(Integer.MAX_VALUE));
		for(int i = 0; i < opCount; i ++)
			q.dequeue();
		long endTime = System.nanoTime();
		
		return (endTime - startTime) / 1000000000.0; 
	}
	
	public static void main(String[] args) {
		int opCount = 100000;
		ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
		double time1 = testQueue(arrayQueue, opCount);
		System.out.println(time1);
		
		LoopQueue<Integer> loopQueue = new LoopQueue<>();
		double time2 = testQueue(loopQueue, opCount);
		System.out.println(time2);
		
		LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
		double time3 = testQueue(linkedListQueue, opCount);
		System.out.println(time3);
	}
}
