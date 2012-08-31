(ns crybaby.core
  (:use [crybaby.config])
  (:use [crybaby.util])
  (:use [crybaby.db])
  (:use [crybaby.pages])
  (:use [clj-logging-config.log4j])
  (:use [clojure.tools.logging])
  (:require [noir.server :as server]))

(defn -main [& args]
  (get-config my-config-file)
  (init-cassandra-connection)
  (server/start crybaby-port))
