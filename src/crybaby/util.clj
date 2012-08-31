(ns crybaby.util
  (:use [crybaby.config])
  (:use [clj-logging-config.log4j])
  (:use [clojure.tools.logging])
  (:use [clj-hector.core])
  (:use [clj-hector.ddl]))

(defn gen-event-id []
  "Generates a unix timestamp with milliseconds. Currently this is our event ID.
   I want to keep the event ID and timestamp seperate in case they diverge."
  (System/currentTimeMillis))

(defn gen-unix-timestamp []
  "Generates a unix timestamp without milliseconds."
  (int (/ (System/currentTimeMillis) 1000)))

(defn write-event [desc]
  "Takes an event description, generates an ID and timestamp and writes it to
   Cassandra."
  (def row-key (gen-event-id))
  (def ts (gen-unix-timestamp)))
  ;(info "New Event" row-key desc))
  ;(put my-ks my-cf row-key {"ts" ts, "desc" desc})
