(ns organization.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :refer [wrap-resource]]
            [compojure.core :refer :all]
            [organization.views.selmer :as selmer]
            [organization.views.hiccup :as hiccup]
            [organization.views.enlive :as enlive]
            [cheshire.core :as cheshire]))


(def names ["Alice" "Bob" "Chris"])

(defroutes handler
  (GET "/selmer/:param" [param] (selmer/view {:param param
                                              :my-array [3 5 2 1]}))
  (GET "/hiccup/:param" [param] (hiccup/list-names names))
  (GET "/enlive/:param" [param] (enlive/list-names names))
  (GET "/cheshire/alice.json" [] (cheshire/generate-string {:name 'Alice' :job 'Programmer'})))

(def app (wrap-resource handler "public"))

(defn -main []
  (jetty/run-jetty app {:port 3000
                        :join? false}))
