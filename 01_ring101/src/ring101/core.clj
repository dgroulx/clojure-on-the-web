(ns ring101.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "the request is" request)})

#_(def app
  (-> handler
      (wrap-resource "public")
      (wrap-file-info)))

#_(def app
  (wrap-file-info (wrap-resource
                   handler "public")))

(defn -main []
  (jetty/run-jetty app {:port 3000
                        :join? false}))


