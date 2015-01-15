(ns orms.neo4j.customers
  (:require [clojurewerkz.neocons.rest :as nr]
            [clojurewerkz.neocons.rest.cypher :as cy]))

(def connection (nr/connect "http://localhost:7474/db/data"))

(defn all []
  (map #(get-in % ["n" :data]) (cy/tquery connection "MATCH (n:Customer) RETURN n")))
