(defproject crybaby "0.0.1-SNAPSHOT"
  :description "crybaby events dashboard"
  :dependencies [[org.clojure/clojure "1.3.0"]]
  :url "https://github.com/nmilford/crybaby"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"
            :distribution :repo}
  :jar-name "crybaby.jar"
  :uberjar-name "crybaby-standalone.jar"
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :omit-source true
  :jar-exclusions [#"(?:^|/).git/"])
