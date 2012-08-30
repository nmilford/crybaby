(ns crybaby.db
  (:use [crybaby.util])
  (:use [clj-logging-config.log4j])
  (:use [clojure.tools.logging])
  (:use [clj-hector.core]))

(set-logger! :level :debug
   :out (org.apache.log4j.FileAppender.
        (org.apache.log4j.EnhancedPatternLayout. org.apache.log4j.EnhancedPatternLayout/TTCC_CONVERSION_PATTERN) my-log-file true))

(defn init-cassandra-connection []
  "Sets up all we need to talk to a Cassandra instance"
  (def my-cluster (cluster cass-cluster cass-server))
  (def my-ks (keyspace my-cluster cass-ks))
  (info "Connection made to cluster" cass-cluster "on server" cass-server))
