(ns liberation.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.format :refer [wrap-restful-format]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [compojure.core :refer [defroutes ANY]]
            [liberator.core :refer [defresource]]
            [liberation.models.customers :as customers]))


(defresource customers-resource
  :available-media-types ["application/json"]
  :allowed-methods [:get :post]
  :handle-ok (fn [_] (customers/all))
  :post! (fn [ctx] (customers/create (get-in ctx [:request :params "name"]))))

(defresource customer-resource [name]
  :available-media-types ["application/json"]
  :allowed-methods [:get]
  :exists? (fn [_] (do
                       (println (str "get name: " name))
                       (seq (customers/find-by-name name))))
  :handle-ok (fn [_] (customers/find-by-name name)))

(defroutes handler
  (ANY "/customers" [] customers-resource)
  (ANY "/customers/:name" [name] (customer-resource name)))

(def app
  (-> handler
      (wrap-restful-format)
      (wrap-keyword-params)))

(defn -main []
  (jetty/run-jetty app {:port 3000
                        :join? false}))

