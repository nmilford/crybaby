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

(defn write-event [desc]
  "Takes an event description, generates an ID and timestamp and writes it to
   Cassandra."
  (def row-key (gen-event-id))
  (def ts (gen-unix-timestamp))
  (try
    (put my-ks my-cf row-key {"ts" ts, "desc" desc})
    (info "New Event" row-key desc)
    (catch Exception e (error e))))
