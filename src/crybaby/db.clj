(ns crybaby.db
  (:use [crybaby.config])
  (:use [crybaby.util])
  (:use [clj-logging-config.log4j])
  (:use [clojure.tools.logging])
  (:use [clj-hector.ddl])
  (:use [clj-hector.core]))

(defn init-cassandra-connection []
  "Sets up all we need to talk to a Cassandra instance"
  (try
    (def my-cluster (cluster cass-cluster cass-server))
    (info "Connected to Cassandra cluster" cass-cluster "via server" cass-server)
    (catch Exception e (error e)))
  (try
    (def my-ks (keyspace my-cluster cass-ks))
    (info "Connected to Keyspace" cass-ks "on cluster" cass-cluster)
    (catch Exception e (error e))))

(defn create-crybaby-schema []
  (try
    (add-keyspace my-cluster
      {:name cass-ks
       :replication 1
       :column-families [{
         :name my-cf
         :comparator :utf-8}]})
    (info "Initialized Crybaby's Cassandra Schema with Keyspace" cass-ks "and ColumnFamily" my-cf)
    (catch Exception e (error e))))

(defn write-event [desc]
  "Takes an event description, generates an ID and timestamp and writes it to
   Cassandra."
  (def row-key (gen-event-id))
  (def ts (gen-unix-timestamp))
  (try
    (put my-ks my-cf row-key {"ts" ts, "desc" desc})
    (info "New Event ID" row-key "Desctription:" desc "at:" ts)
    (catch Exception e (error e))))

(defn get-event []
  (get-rows-cql-query my-ks "select * from events" :n-serializer :string :v-serializer :integer))
