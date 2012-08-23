(defproject crybaby "0.0.1-SNAPSHOT"
  :description "crybaby events dashboard"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [noir "1.2.2"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.7.2"]]
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
