(ns basic-routing.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.resource :refer [wrap-resource]]
            [compojure.core :refer :all]))

(defroutes handler
  (GET "/blogs" [] "<h1>Blog Index</h1>")
  (GET "/blogs/:name" [name] (str "<h1>Blog Name: " name "</h1>")))

#_(defroutes handler
  (GET "/blogs" [] "<h1>Blog Index</h1>")
  (context "/blogs/:name" [name]
    (GET "/" [name] (str "<h1>Blog Name: " name "</h1>"))
    (GET "/posts/:post-id" [name post-id] (str "<p>Blog " name "</p><p>Post ID:" post-id "</p>"))))

(def app
  (-> handler
      (wrap-resource "public")))

(defn -main []
  (jetty/run-jetty app {:port 3000
                        :join? false}))



