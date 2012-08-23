(ns crybaby.core
  (:require [clojure.contrib.sql :as sql])
  (:use noir.core)
  (:require [noir.server :as server]))

(def testdata
  {:date "1345687531",
   :body "tomcat6 restarted by nathan"})

(def db-specs
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/crybaby.db"})

(defn init-db []
  (sql/with-connection db-specs
  (sql/create-table :events
              [:date :text]
              [:body :text])))

(init-db)

(sql/with-connection db-specs
  (sql/insert-records :events testdata))

(def output
  (sql/with-connection db-specs
    (sql/with-query-results rs ["select * from events"] (doall rs))))

(keys (first output))

;(defpage [:get "/"] [] (keys (first output))

;(defpage [:post "/api/v1/post"] {:keys [event timestamp]}
;  (str event " occured at " timestamp))

;(server/start 8080)
