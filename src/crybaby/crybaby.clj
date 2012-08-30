(ns crybaby.core
  (:use [clj-hector.core])
  (:use [clj-hector.ddl])
  (:use noir.core)
  (:require [noir.server :as server])
  (:require [noir.response :as response]))

; Cassandra settings.
(def my-cluster (cluster "sputnik" "localhost"))
(def my-ks (keyspace my-cluster "crybaby"))
(def my-cf "events")

(defn gen-event-id []
  "Generates a unix timestamp with milliseconds. Currently this is our event ID.
   I want to keep the event ID and timestamp seperate in case they diverge."
  (System/currentTimeMillis))

(defn gen-unix-timestamp []
  "Generates a unix timestamp without milliseconds."
  (int (/ (System/currentTimeMillis) 1000)))

(defn write-event [desc]
  "Takes an event description, generates an ID andtimestamp and writes it to
   Cassandra"
  (def row-key (gen-event-id))
  (def ts (gen-unix-timestamp))
  (put my-ks my-cf row-key {"ts" ts, "desc" desc}))
  
(defpage "/" [] "curl -X POST 'http://localhost:8080/event?desc=your+event+description+here'")

(defpage [:post "/event"] {:keys [desc]}
  (write-event desc)
  (str "shit got real: " desc))

(defn -main [& args]
   (server/start 808
