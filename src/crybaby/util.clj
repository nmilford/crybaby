(ns crybaby.util
  (:use [clj-yaml.core])
  (:use [clj-logging-config.log4j])
  (:use [clojure.tools.logging])
  (:use [clj-hector.core]))

; Explicitly defining the config & log file here. Will make it optional later.
(def my-config-file "./conf/crybaby.yaml")
(def my-log-file "./logs/crybaby.log")

(set-logger! :level :debug
   :out (org.apache.log4j.FileAppender.
        (org.apache.log4j.EnhancedPatternLayout. org.apache.log4j.EnhancedPatternLayout/TTCC_CONVERSION_PATTERN) my-log-file true))

(defn get-config [conf-file]
  "Extracts config settings from a specified YAML file."
  (def my-config (parse-string (slurp conf-file)))
  (def crybaby-port (val (find my-config :crybaby.port)))
  (def cass-server (val (find my-config :cassandra.server)))
  (def cass-cluster (val (find my-config :cassandra.cluster)))
  (def cass-ks (val (find my-config :cassandra.keyspace)))
  (def my-cf (val (find my-config :cassandra.columnfamily)))
  (info "Config loaded from:" conf-file))

(defn start-logging []
  "Sets up logging"
  (set-logger!
   :level :debug
   :out (org.apache.log4j.FileAppender.
        (org.apache.log4j.EnhancedPatternLayout. org.apache.log4j.EnhancedPatternLayout/TTCC_CONVERSION_PATTERN)
        my-log-file
        true)))

(defn gen-event-id []
  "Generates a unix timestamp with milliseconds. Currently this is our event ID.
   I want to keep the event ID and timestamp seperate in case they diverge."
  (System/currentTimeMillis))

(defn gen-unix-timestamp []
  "Generates a unix timestamp without milliseconds."
  (int (/ (System/currentTimeMillis) 1000)))

(def my-hostname (.getHostName (java.net.InetAddress/getLocalHost)))


