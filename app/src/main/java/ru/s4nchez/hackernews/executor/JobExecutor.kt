package ru.s4nchez.hackernews.executor

import java.util.concurrent.*

class JobExecutor : Executor {

    private var threadPoolExecutor: ThreadPoolExecutor =
            ThreadPoolExecutor(3, 5, 10,
                    TimeUnit.SECONDS, LinkedBlockingQueue(), JobThreadFactory())

    override fun execute(command: Runnable) {
        threadPoolExecutor.execute(command)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}