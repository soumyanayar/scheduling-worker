/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.schedulingtasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedDelay = 1000)
	public void reportCurrentTime() throws InterruptedException {
		log.info("The Worker Ran at {}", dateFormat.format(new Date()));

		ExecutorService executor = Executors.newFixedThreadPool(10);

		List<Callable<Void>> callableTasks = new ArrayList<>();

		for (int i = 0; i < 15; i++) {
			Callable<Void> callableTask = () -> {
				LongTimeConsumingTask.performTask(log, dateFormat);
				return null;
			};

			callableTasks.add(callableTask);
		}

		executor.invokeAll(callableTasks);
	}
}
