package Scheduling;

import java.util.ArrayList;
import java.util.HashSet;

public class ReadWriteLock {
	private int readSlot = 3;
	private int readers = 0;
	private int writer = 0;
	private int writeRequest = 0;
	private Thread writingThread = null;
	private HashSet<Thread> readingThreads = null;

	
	private ReadWriteLock(int readSlot){
		this.readSlot = readSlot;
		this.readingThreads = new HashSet<Thread>();
	}

	public synchronized void read() throws InterruptedException{
		if(!implicitRead(Thread.currentThread())){
			if(writer > 0 || readers == readSlot){ wait();}
			else{
				readers++; 
				readingThreads.add(Thread.currentThread());
			}
		}
	}

	public synchronized void write() throws InterruptedException{
		if(writingThread==null || !writingThread.equals(Thread.currentThread())){
			if(writer>0){ wait();}
			else{
				if(readers == 0 || (readers == 1 && readingThreads.contains(Thread.currentThread()))){
					writer++;
					writingThread = Thread.currentThread();
				}
				else{ wait();}
			}
			
		}

	}
	public synchronized void releaseRead(){
		if(readers >0) {
			readers--;
			readingThreads.remove(Thread.currentThread());
		}
		notifyAll();
	}
	public synchronized void releaseWrite(){
		writer--;
		writingThread = null;
		notifyAll();
	}

	public boolean implicitRead(Thread thread){
		if(readingThreads.contains(thread)) return true;
		if(writingThread !=null && writingThread.equals(thread)) return true;
		return false;
	}
	
	public synchronized int getWritingRequestNumber(){
		return writeRequest;
	}
	public static void main(String[] args){

		class testThread extends Thread{
			ReadWriteLock lock = null;
			int[] sharedNumber;
			public testThread(ReadWriteLock l, int[] sharedNumber){
				lock = l;
				this.sharedNumber = sharedNumber;
			}
			@Override
			public void run(){
				int cnt = 0;
				while(cnt<10){
					try {
						lock.read();
						System.out.println(Thread.currentThread().getName()+" reading: "+sharedNumber[0]);
						//lock.write();
						//sharedNumber[0]++;
						//System.out.println(Thread.currentThread().getName()+" writing in reading: "+sharedNumber[0]);
						//lock.releaseWrite();

						lock.releaseRead();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						lock.write();
						sharedNumber[0]++;
						System.out.println(Thread.currentThread().getName()+" writing: "+sharedNumber[0]);
						lock.read();
						System.out.println(Thread.currentThread().getName()+" reading in writing: "+sharedNumber[0]);
						lock.releaseRead();

						lock.releaseWrite();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cnt++;
				}

			}
		}
		int[] sharedNumber = {0};
		ReadWriteLock lock = new ReadWriteLock(5);
		ArrayList<Thread> poll = new ArrayList<Thread>();
		for(int i = 0;i<5;i++){
			poll.add(new testThread(lock,sharedNumber));
		}
		for(int i = 0;i<5;i++){
			poll.get(i).start();
		}

	}

}
