package com.pahlsoft.examples.queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueProcessor {

	public static void main(String[] args) {
		// Initiate the Queue
		Queue<WorkUnit> queue = new LinkedList<WorkUnit>();
		
		// Stuff the Queue with Info
		for (int ctr=0; ctr<100; ctr++) {
			WorkUnit _workUnit = new WorkUnit();
			_workUnit.setSerialNumber(ctr);
			_workUnit.setDescription("Description: " + ctr);
			queue.add(_workUnit);
		}
		
		// Unravel the Queue
		for (WorkUnit unit: queue) {
			System.out.println(unit.getSerialNumber() + " " + unit.getDescription());
		}
		
		// What's the size
		System.out.println("Queue Size: "+queue.size());
		
		System.out.println("Deleting Item... " + queue.poll().getSerialNumber());
		
		// What's the size
	   System.out.println("Queue Size: "+queue.size());
				
		
		
	}

}
