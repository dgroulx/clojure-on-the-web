(ns orms.core
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :refer :all]
            [cheshire.core :as cheshire]
            [orms.sql.customers :as sql-customers]
            [orms.neo4j.customers :as neo4j-customers]
            [orms.mongo.customers :as mongo-customers]))

(defroutes handler
  (GET "/sql/names.json" [] (cheshire/generate-string (sql-customers/all)))
  (GET "/neo4j/names.json" [] (cheshire/generate-string (neo4j-customers/all)))
  (GET "/mongo/names.json" [] (cheshire/generate-string (mongo-customers/all))))

(def app handler)

(defn -main []
  (jetty/run-jetty app {:port 3000
                        :join? false}))


