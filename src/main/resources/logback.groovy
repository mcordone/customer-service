/*
 * Copyright (c) 2016
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>http://www.apache.org/licenses/LICENSE-2.0<p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import java.nio.charset.Charset

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO
import static ch.qos.logback.classic.Level.TRACE
import static ch.qos.logback.classic.Level.ERROR
import ch.qos.logback.classic.jul.LevelChangePropagator

scan()
def lcp = new LevelChangePropagator()
lcp.context = context
lcp.resetJUL = true
context.addListener(lcp)

appender("consoleAppender", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    charset = Charset.forName("UTF-8")
    pattern = "%d %-4relative [%thread] %-5level %logger{35} - %msg%n"
  }
}
appender("FILE", RollingFileAppender) {
  file = "logs/accountService.log"
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "logs/accountService_%d{yyyy-MM-dd}.%i.log"
    timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
      maxFileSize = "10MB"
    }
    maxHistory = 30
  }
  encoder(PatternLayoutEncoder) {
    charset = Charset.forName("UTF-8")
    pattern = "%d %-4relative [%thread] %-5level %logger{35} - %msg%n"
  }
}
root(INFO, ["consoleAppender", "FILE"])