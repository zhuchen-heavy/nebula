/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nebula.netty.transport;

import java.io.Closeable;
import java.util.Map;
import java.util.concurrent.*;

/**
 * <p>
 * 兜底ResponseFuture
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public class InFlightRequests implements Closeable {

    /**
     * 超时时间
     */
    private final static long TIMEOUT_SEC = 10L;

    /**
     * 限流：用于保证生产者和消费者的速率，来保证生产者不至于生成太快导致内存溢出
     */
    private final Semaphore semaphore = new Semaphore(10);

    private final Map<Integer, ResponseFuture> futureMap = new ConcurrentHashMap<>();

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private final ScheduledFuture scheduledFuture;

    public InFlightRequests() {
        this.scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(this::removeTimeoutFutures, TIMEOUT_SEC, TIMEOUT_SEC, TimeUnit.SECONDS);
        ;
    }

    private void removeTimeoutFutures() {
        futureMap.entrySet().removeIf(entry -> {
            if (System.nanoTime() - entry.getValue().getTimeStamp() > TIMEOUT_SEC * 1000000000L) {
                semaphore.release();
                return true;
            } else {
                return false;
            }
        });
    }

    public void put(ResponseFuture responseFuture) throws InterruptedException, TimeoutException {
        if (semaphore.tryAcquire(TIMEOUT_SEC, TimeUnit.SECONDS)) {
            futureMap.put(responseFuture.getRequestId(), responseFuture);
        } else {
            throw new TimeoutException();
        }
    }

    public ResponseFuture remove(int requestId) {
        ResponseFuture future = futureMap.remove(requestId);
        if (future != null) {
            semaphore.release();
        }
        return future;
    }

    @Override
    public void close() {
        scheduledFuture.cancel(true);
        scheduledExecutorService.shutdown();
    }

}
