(ns liberation.models.customers
  (:require [korma.db :refer :all]
            [korma.core :refer :all]))

(defdb development-db (postgres {:db "liberator_demo"
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

(defn create [name]
  (insert customers (values {:name name})))

(defn find-by-name [name]
  (select customers (where {:name name})))
