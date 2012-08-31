(ns crybaby.config
  (:use [clj-yaml.core]))

; Explicitly defining the config & log file here. Will make it optional later.
(def my-config-file "./conf/crybaby.yaml")

(defn get-config [conf-file]
  "Extracts config settings from a specified YAML file."
  (def my-config (parse-string (slurp conf-file)))
  (def crybaby-port (val (find my-config :crybaby.port)))
  (def cass-server (val (find my-config :cassandra.server)))
  (def cass-cluster (val (find my-config :cassandra.cluster)))
  (def cass-ks (val (find my-config :cassandra.keyspace)))
  (def my-cf (val (find my-config :cassandra.columnfamily))))
