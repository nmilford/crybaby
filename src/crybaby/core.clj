(ns crybaby.core
  (:use [crybaby.util])
  (:use [crybaby.db])
  (:use [clj-logging-config.log4j])
  (:use [clojure.tools.logging])
  (:use [clj-hector.core])
  (:use [clj-hector.ddl])
  (:use noir.core)
  (:require [noir.server :as server]))

(set-logger! :level :debug
   :out (org.apache.log4j.FileAppender.
        (org.apache.log4j.EnhancedPatternLayout. org.apache.log4j.EnhancedPatternLayout/TTCC_CONVERSION_PATTERN) my-log-file true))

(defn write-event [desc]
  "Takes an event description, generates an ID andtimestamp and writes it to
   Cassandra"
  (def row-key (gen-event-id))
  (def ts (gen-unix-timestamp))
  (put my-ks my-cf row-key {"ts" ts, "desc" desc})
  (info "EVENT:" ts desc))
  
(defpage "/" [] "curl -X POST 'http://" my-hostname ":" crybaby-port "/event?desc=your+event+description+here'")

(defpage [:post "/event"] {:keys [desc]}
  (write-event desc)
  (str desc))

(defn -main [& args]
  (get-config my-config-file)
  (init-cassandra-connection)
  (server/start crybaby-port))
