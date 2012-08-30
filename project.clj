(defproject crybaby "0.0.1-SNAPSHOT"
  :description "crybaby events dashboard"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [log4j/log4j "1.2.16" :exclusions [javax.mail/mail javax.jms/jms com.sun.jdmk/jmxtools com.sun.jmx/jmxri]]
                 [org.slf4j/slf4j-log4j12 "1.6.4"]
                 [org.clojure/tools.logging "0.2.3"]                
                 [clj-logging-config "1.9.7"]
                 [clj-yaml "0.3.1"]
                 [noir "1.2.2"]]
  :dev-dependencies [[org.clojars.paul/clj-hector "0.2.2"]]
  :url "https://github.com/nmilford/crybaby"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"
            :distribution :repo}
  :jar-name "crybaby.jar"
  :uberjar-name "crybaby-standalone.jar"
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :omit-source true
  :jar-exclusions [#"(?:^|/).git/"]
  :main crybaby.core)
