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


