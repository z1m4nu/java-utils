/**
 * 
 */
package org.crossroad.util.thread;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @author e.soden
 *
 */
public class ScalableThreadPoolExecutor extends ThreadPoolExecutor {
	private ScalableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			TransferQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		

	}
	
	public static ThreadPoolExecutor get() {
		int cpu = Runtime.getRuntime().availableProcessors();
		int corePool = (cpu <= 4) ? cpu : cpu - 4;
		int maxPool = (cpu <= 2) ? cpu : cpu - 2;
		long timeout = 1000;
		
		return get(corePool, maxPool, timeout, TimeUnit.MILLISECONDS);
	}

	public static ThreadPoolExecutor get(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			TransferQueue<Runnable> workQueue) {
		DynamicBlockingQueue<Runnable> queue = new DynamicBlockingQueue<Runnable>(workQueue);
		ThreadPoolExecutor executor = new ScalableThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				queue);
		queue.setExecutor(executor);
		return executor;
	}

	public static ThreadPoolExecutor get(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit) {
		DynamicBlockingQueue<Runnable> queue = new DynamicBlockingQueue<Runnable>(new LinkedTransferQueue<Runnable>());
		ThreadPoolExecutor executor = new ScalableThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
				queue);
		queue.setExecutor(executor);
		return executor;
	}

	private static class DynamicBlockingQueue<E> implements TransferQueue<E> {

		private final ReentrantLock mainLock = new ReentrantLock();

		private final TransferQueue<E> delegate;
		private ThreadPoolExecutor executor;

		public DynamicBlockingQueue(final TransferQueue<E> delegate) {
			this.delegate = delegate;
		}

		public boolean offer(E e) {
			// If there are waiting threads, transfer to the tread directly
			boolean inserted = tryTransfer(e);

			if (!inserted) {
				final ReentrantLock mainLock = this.mainLock;
				mainLock.lock();
				try {
					// Add to queue only, If pool size is reached max pool size allowed
					if (executor.getPoolSize() == executor.getMaximumPoolSize()) {
						inserted = this.delegate.offer(e);
					} else {
						inserted = false;
					}
				} finally {
					mainLock.unlock();
				}
			}
			return inserted;
		}

		void setExecutor(ThreadPoolExecutor executor) {
			this.executor = executor;
		}

		public E remove() {
			return this.delegate.remove();
		}

		public E poll() {
			return this.delegate.poll();
		}

		public E element() {
			return this.delegate.element();
		}

		public E peek() {
			return this.delegate.peek();
		}


		public int size() {
			return this.delegate.size();
		}

		
		public boolean isEmpty() {
			return this.delegate.isEmpty();
		}

		
		public Iterator<E> iterator() {
			return this.delegate.iterator();
		}

		
		public Object[] toArray() {
			return this.delegate.toArray();
		}

		
		public <T> T[] toArray(T[] a) {
			return this.delegate.toArray(a);
		}

		
		public boolean containsAll(Collection<?> c) {
			return this.delegate.containsAll(c);
		}

		
		public boolean addAll(Collection<? extends E> c) {
			return this.delegate.addAll(c);
		}

		
		public boolean removeAll(Collection<?> c) {
			return this.delegate.removeAll(c);
		}

		
		public boolean retainAll(Collection<?> c) {
			return this.delegate.retainAll(c);
		}

		
		public void clear() {
			this.delegate.clear();
		}

		
		public boolean add(E e) {
			return this.delegate.add(e);
		}

		
		public void put(E e) throws InterruptedException {
			this.delegate.put(e);
		}

		
		public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
			return this.delegate.offer(e, timeout, unit);
		}

		
		public E take() throws InterruptedException {
			return this.delegate.take();
		}

		
		public E poll(long timeout, TimeUnit unit) throws InterruptedException {
			return this.delegate.poll(timeout, unit);
		}

		
		public int remainingCapacity() {
			return this.delegate.remainingCapacity();
		}

		
		public boolean remove(Object o) {
			return this.delegate.remove(o);
		}

		
		public boolean contains(Object o) {
			return this.delegate.contains(o);
		}

		
		public int drainTo(Collection<? super E> c) {
			return this.delegate.drainTo(c);
		}

		
		public int drainTo(Collection<? super E> c, int maxElements) {
			return this.delegate.drainTo(c, maxElements);
		}

		
		public boolean tryTransfer(E e) {
			return this.delegate.tryTransfer(e);
		}

		
		public void transfer(E e) throws InterruptedException {
			this.delegate.transfer(e);
		}

		
		public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
			return this.delegate.tryTransfer(e, timeout, unit);
		}

		
		public boolean hasWaitingConsumer() {
			return this.delegate.hasWaitingConsumer();
		}

		
		public int getWaitingConsumerCount() {
			return this.delegate.getWaitingConsumerCount();
		}
	}
}
