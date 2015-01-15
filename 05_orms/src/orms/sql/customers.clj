(ns orms.sql.customers
  (:require [korma.db :refer :all]
            [korma.core :refer :all]))

(defdb development-db (postgres {:db "demo"
                                 :user "david"
                                 :password ""
                                 :host "localhost"}))

(defentity customers
  (pk :id)
  (table :customers)
  (database development-db)
  (entity-fields :name))

(defn all []
  (select customers))
