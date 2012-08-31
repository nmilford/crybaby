(ns crybaby.db
  (:use [crybaby.config])
  (:use [crybaby.util])
  (:use [clj-logging-config.log4j])
  (:use [clojure.tools.logging])
  (:use [clj-hector.core]))

(defn init-cassandra-connection []
  "Sets up all we need to talk to a Cassandra instance"
  (def my-cluster (cluster cass-cluster cass-server))
  (def my-ks (keyspace my-cluster cass-ks)))
